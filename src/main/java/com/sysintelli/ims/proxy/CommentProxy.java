/*
 * @CopyRight : Jay
 */
package com.sysintelli.ims.proxy;

import java.io.Serializable;
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
public class CommentProxy implements Serializable {

    private Long id;
    private String messages;
    private Long createdDate;
    private PostProxy postId;
    private String commentRefId;

    /**
     * @Additiona Fields
     */
    /**
     * @Constructor CommentProxy_01
     */
    public CommentProxy(Long id) {
        this.id = id;
    }

    /**
     * @Constructor CommentProxy_02
     */
    public CommentProxy(Long id, String messages, Long createdDate, Long postId, String commentRefId) {
        this.id = id;
        this.messages = messages;
        this.createdDate = createdDate;
        this.postId = new PostProxy(postId);
        this.commentRefId = commentRefId;
    }

}
