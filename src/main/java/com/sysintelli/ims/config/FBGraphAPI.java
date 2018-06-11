/*
 * @CopyRight : Jay
 */
package com.sysintelli.ims.config;

import com.restfb.Connection;
import com.restfb.FacebookClient;
import com.restfb.Parameter;
import com.restfb.types.Comment;
import com.restfb.types.Post;
import com.restfb.types.User;
import com.sysintelli.ims.dao.CommentDao;
import com.sysintelli.ims.dao.FbOperationDao;
import com.sysintelli.ims.dao.PostDao;
import com.sysintelli.ims.entity.CommentMstr;
import com.sysintelli.ims.entity.FbOperationMstr;
import com.sysintelli.ims.entity.PostMstr;
import com.sysintelli.ims.proxy.CommentProxy;
import com.sysintelli.ims.proxy.FbOperationProxy;
import com.sysintelli.ims.proxy.PostProxy;
import com.sysintelli.ims.utility.ListUtility;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author jay
 */
@Component
public class FBGraphAPI {

    public static final Logger logger = Logger.getLogger(FBGraphAPI.class.getName());

    @Autowired
    LogInterceptor logInterceptor;

    @PersistenceContext
    private EntityManager em;

    @Autowired
    private FbOperationDao fbOperationDao;

    @Autowired
    private PostDao postDao;

    @Autowired
    private CommentDao commentDao;

    public FbOperationProxy getDetailFromPage(FbOperationProxy proxy, FacebookClient fbClient) {
//        FacebookClient fbClient = new DefaultFacebookClient(accessToken);
//        String fbId = isChannelAvailable(proxy.getYtChannel(), fbClient);
//        if (!StringUtility.isValidString(fbId)) {
//            logger.info("There are no channel available in facebook.");
//            return null;
//        }
//        proxy.setFbRefId(fbId);
        Connection<Post> result = fbClient.fetchConnection(proxy.getYtChannel() + "/feed", Post.class, Parameter.with("fields", "comments,link,shares,type,created_time,message"));
        //fbClient.fetch
        List<PostProxy> postList = new ArrayList<>();
        for (List<Post> page : result) {
            for (Post postObj : page) {
                PostProxy postProxy = new PostProxy();
                System.out.println("aPost.getMessage() ==>> " + postObj.getMessage());
                System.out.println("aPost.getId() ==>> " + postObj.getId());
                System.out.println("aPost-CreatedTime ==> " + postObj.getCreatedTime());
                System.out.println("link::::::::" + postObj.getLink());
                System.out.println("shareCount:::" + postObj.getSharesCount());
                System.out.println("LikeCount::" + postObj.getLikesCount());
                System.out.println("PostType::" + postObj.getType());//photo//video//status

                postProxy.setMessage(postObj.getMessage());
                postProxy.setPostRefId(postObj.getId());
                postProxy.setCreatedDate(postObj.getCreatedTime() != null ? postObj.getCreatedTime().getTime() : null);
                postProxy.setLink(postObj.getLink());
                postProxy.setShareCount((int) (long) postObj.getSharesCount());//after make into long
                postProxy.setLikeCount((int) (long) postObj.getLikesCount());
                postProxy.setPostType(postObj.getType());

                List<CommentProxy> commentList = new ArrayList<>();
                if (postObj.getComments() != null) {
                    System.out.println(postObj.getComments().getOrder());
                    if (postObj.getComments().getData() != null && postObj.getComments().getData().size() > 0) {
                        for (Comment commentObj : postObj.getComments().getData()) {
                            CommentProxy commentProxy = new CommentProxy();
                            System.out.println("comment-message::" + commentObj.getMessage());
                            System.out.println("comment-creaedTime::" + commentObj.getCreatedTime());
                            System.out.println("comment-getId::" + commentObj.getId());
                            commentProxy.setCreatedDate(commentObj.getCreatedTime() != null ? commentObj.getCreatedTime().getTime() : null);
                            commentProxy.setMessages(commentObj.getMessage());
                            commentProxy.setCommentRefId(commentObj.getId());
                            commentList.add(commentProxy);
                        }

                    }
                }
                postProxy.setComments(commentList);
                postList.add(postProxy);

            }
        }
        proxy.setPosts(postList);

        return proxy;
    }

    public static String isChannelAvailable(String channelName, FacebookClient fbClient) {
        User userObj = null;
        try {
            userObj = fbClient.fetchObject(channelName, User.class, Parameter.with("limit", 1));
            return userObj.getId();
        } catch (Exception e) {
            return null;
        }

    }

    public Boolean addChannelData(FbOperationProxy proxy, FacebookClient fbClient) {

        proxy = getDetailFromPage(proxy, fbClient);
        FbOperationProxy dbproxy = getFbOperationByFbRefId(proxy.getFbRefId());
        FbOperationMstr fbMstr = null;
        if (dbproxy.getId() != null) {
            fbMstr = em.find(FbOperationMstr.class, dbproxy.getId());
            fbMstr.setLastAccess(System.currentTimeMillis());
            fbOperationDao.update(fbMstr);
        } else {
            //add FbOperation
            fbMstr = new FbOperationMstr();
            fbMstr.setFbRefId(proxy.getFbRefId());
            fbMstr.setLastAccess(System.currentTimeMillis());
            fbMstr.setYtChannel(proxy.getYtChannel());
            fbOperationDao.create(fbMstr);
        }
        //add post
        if (!ListUtility.isNullOrEmpty(proxy.getPosts())) {
            for (PostProxy postProxy : proxy.getPosts()) {
                PostProxy dbPostProxy = getPostByFbRefIdAndRefId(fbMstr.getFbRefId(), postProxy.getPostRefId());
                PostMstr postMstr = null;
                if (dbPostProxy != null && dbPostProxy.getId() != null) {
                    postMstr = em.find(PostMstr.class, dbPostProxy.getId());
                    postMstr.setCreatedDate(postProxy.getCreatedDate());
                    postMstr.setLikeCount(postProxy.getLikeCount());
                    postMstr.setLink(postProxy.getLink());
                    postMstr.setMessage(postProxy.getMessage());
                    postMstr.setPostType(postProxy.getPostType());
                    postMstr.setShareCount(postProxy.getShareCount());
                    postDao.update(postMstr);
                } else {
                    postMstr = new PostMstr();
                    postMstr.setCreatedDate(postProxy.getCreatedDate());
                    postMstr.setFbId(new FbOperationMstr(fbMstr.getId()));
                    postMstr.setLikeCount(postProxy.getLikeCount());
                    postMstr.setLink(postProxy.getLink());
                    postMstr.setMessage(postProxy.getMessage());
                    postMstr.setPostRefId(postProxy.getPostRefId());
                    postMstr.setPostType(postProxy.getPostType());
                    postMstr.setShareCount(postProxy.getShareCount());
                    postDao.create(postMstr);
                }
                //addComment
                if (!ListUtility.isNullOrEmpty(postProxy.getComments())) {
                    for (CommentProxy commentProxy : postProxy.getComments()) {
                        CommentProxy dbCommentProxy = getPostByPostRefIdAndCommentRefId(postProxy.getPostRefId(), commentProxy.getCommentRefId());
                        CommentMstr commentMstr = null;
                        if (dbCommentProxy != null && dbCommentProxy.getId() != null) {
                            commentMstr = em.find(CommentMstr.class, dbCommentProxy.getId());
                            commentMstr.setCreatedDate(commentProxy.getCreatedDate());
                            commentMstr.setMessages(commentProxy.getMessages());
                            commentDao.update(commentMstr);
                        } else {
                            commentMstr = new CommentMstr();
                            commentMstr.setCreatedDate(commentProxy.getCreatedDate());
                            commentMstr.setPostId(new PostMstr(postMstr.getId()));
                            commentMstr.setMessages(commentProxy.getMessages());
                            commentMstr.setCommentRefId(commentProxy.getCommentRefId());
                            commentDao.create(commentMstr);
                        }
                    }
                }
            }
        }

        return Boolean.TRUE;
    }

    public Boolean isChannelAvailableInDb(String channelId) {
        return (Long) em.createQuery("select count(f.id) from FbOperationMstr f where f.ytChannel=:channelId or f.fbRefId=:channelId")
                .setParameter("channelId", channelId)
                .getSingleResult() > 0;
    }

    public FbOperationProxy getFbOperationByFbRefId(String fbRefId) {
        /**
         * @Constructor FbOperationProxy_02
         */
        List<FbOperationProxy> resList = em.createQuery("select new com.sysintelli.ims.proxy.FbOperationProxy(f.id, f.ytChannel, f.fbRefId, f.lastAccess) from FbOperationMstr f where f.fbRefId=:fbRefId")
                .setParameter("fbRefId", fbRefId)
                .getResultList();
        return !ListUtility.isNullOrEmpty(resList) ? resList.get(0) : null;
    }

    public List<FbOperationProxy> getFbOperationList(Integer limit) {
        /**
         * @Constructor FbOperationProxy_02
         */
        return em.createQuery("select new com.sysintelli.ims.proxy.FbOperationProxy(f.id, f.ytChannel, f.fbRefId, f.lastAccess) from FbOperationMstr f ")
                .setMaxResults(limit)
                .getResultList();
    }

    public PostProxy getPostByFbRefIdAndRefId(String fbRefId, String postRefId) {
        /**
         * @Constructor PostProxy_03
         */
        List<PostProxy> resList = em.createQuery("select new com.sysintelli.ims.proxy.PostProxy(p.id, p.message, p.shareCount, p.likeCount, p.postType, p.createdDate, p.fbId.id,p.postRefId,p.link) from PostMstr p where p.fbId.fbRefId=:fbRefId and p.postRefId=:postRefId")
                .setParameter("fbRefId", fbRefId)
                .setParameter("postRefId", postRefId)
                .getResultList();
        return !ListUtility.isNullOrEmpty(resList) ? resList.get(0) : null;
    }

    public CommentProxy getPostByPostRefIdAndCommentRefId(String postRefId, String commentRefId) {
        /**
         * @Constructor CommentProxy_02
         */
        List<CommentProxy> resList = em.createQuery("select new com.sysintelli.ims.proxy.CommentProxy(c.id, c.messages, c.createdDate, c.postId.id ,c.commentRefId) from CommentMstr c where c.postId.postRefId=:postRefId and c.commentRefId=:commentRefId")
                .setParameter("postRefId", postRefId)
                .setParameter("commentRefId", commentRefId)
                .getResultList();
        return !ListUtility.isNullOrEmpty(resList) ? resList.get(0) : null;
    }

}

//SamsungMobile?fields=about
