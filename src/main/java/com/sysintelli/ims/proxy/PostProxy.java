/*
 * @CopyRight : Jay
 */
package com.sysintelli.ims.proxy;

import java.io.Serializable;
import java.util.List;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.map.annotate.JsonSerialize;

/**
 *
 * @author Jay
 */
@JsonSerialize(include = JsonSerialize.Inclusion.NON_NULL)
@JsonIgnoreProperties(ignoreUnknown = true)
@Getter
@Setter
@NoArgsConstructor
public class PostProxy implements Serializable {

    private Long id;
    private String message;
    private Integer shareCount;
    private Integer likeCount;
    private String postType;
    private Long createdDate;
    private FbOperationProxy fbId;
    private String postRefId;
    private String link;

    /**
     * @Additiona Fields
     */
    private List<CommentProxy> comments;

    /**
     * @Constructor PostProxy_01
     */
    public PostProxy(Long id) {
        this.id = id;
    }

    /**
     * @Constructor PostProxy_02
     */
    public PostProxy(Long id, String message, Integer shareCount, Integer likeCount, String postType, Long createdDate, Long fbId,
            String postRefId, String link) {
        this.id = id;
        this.message = message;
        this.shareCount = shareCount;
        this.likeCount = likeCount;
        this.postType = postType;
        this.createdDate = createdDate;
        this.fbId = new FbOperationProxy(fbId);
        this.postRefId = postRefId;
        this.link = link;
    }

}
