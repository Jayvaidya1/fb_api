/*
 * @CopyRight : Jay
 */
package com.sysintelli.ims.entity;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author jay
 */
@Entity
@Table(name = "post_mstr", schema = "fb_api")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "PostMstr.findAll", query = "SELECT p FROM PostMstr p")
    , @NamedQuery(name = "PostMstr.findById", query = "SELECT p FROM PostMstr p WHERE p.id = :id")
    , @NamedQuery(name = "PostMstr.findByMessage", query = "SELECT p FROM PostMstr p WHERE p.message = :message")
    , @NamedQuery(name = "PostMstr.findByShareCount", query = "SELECT p FROM PostMstr p WHERE p.shareCount = :shareCount")
    , @NamedQuery(name = "PostMstr.findByLikeCount", query = "SELECT p FROM PostMstr p WHERE p.likeCount = :likeCount")
    , @NamedQuery(name = "PostMstr.findByPostType", query = "SELECT p FROM PostMstr p WHERE p.postType = :postType")
    , @NamedQuery(name = "PostMstr.findByCreatedDate", query = "SELECT p FROM PostMstr p WHERE p.createdDate = :createdDate")})
public class PostMstr implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false)
    private Long id;
    @Size(max = 2147483647)
    @Column(length = 2147483647)
    private String message;
    @Column(name = "share_count")
    private Integer shareCount;
    @Column(name = "like_count")
    private Integer likeCount;
    @Size(max = 2147483647)
    @Column(name = "post_type", length = 2147483647)
    private String postType;
    @Column(name = "created_date")
    private Long createdDate;
    @Column(name = "post_ref_id")
    private String postRefId;
    @Column(name = "link")
    private String link;
    @JoinColumn(name = "fb_id", referencedColumnName = "id")
    @ManyToOne
    private FbOperationMstr fbId;
    @OneToMany(mappedBy = "postId")
    private Collection<CommentMstr> commentMstrCollection;

    public PostMstr() {
    }

    public PostMstr(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getShareCount() {
        return shareCount;
    }

    public void setShareCount(Integer shareCount) {
        this.shareCount = shareCount;
    }

    public Integer getLikeCount() {
        return likeCount;
    }

    public void setLikeCount(Integer likeCount) {
        this.likeCount = likeCount;
    }

    public String getPostType() {
        return postType;
    }

    public void setPostType(String postType) {
        this.postType = postType;
    }

    public Long getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Long createdDate) {
        this.createdDate = createdDate;
    }

    public FbOperationMstr getFbId() {
        return fbId;
    }

    public void setFbId(FbOperationMstr fbId) {
        this.fbId = fbId;
    }

    public String getPostRefId() {
        return postRefId;
    }

    public void setPostRefId(String postRefId) {
        this.postRefId = postRefId;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    @XmlTransient
    public Collection<CommentMstr> getCommentMstrCollection() {
        return commentMstrCollection;
    }

    public void setCommentMstrCollection(Collection<CommentMstr> commentMstrCollection) {
        this.commentMstrCollection = commentMstrCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof PostMstr)) {
            return false;
        }
        PostMstr other = (PostMstr) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sysintelli.ims.entity.PostMstr[ id=" + id + " ]";
    }

}
