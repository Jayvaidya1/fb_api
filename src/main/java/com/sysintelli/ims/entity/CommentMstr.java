/*
 * @CopyRight : Jay
 */
package com.sysintelli.ims.entity;

import java.io.Serializable;
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
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author jay
 */
@Entity
@Table(name = "comment_mstr", schema = "fb_api")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "CommentMstr.findAll", query = "SELECT c FROM CommentMstr c")
    , @NamedQuery(name = "CommentMstr.findById", query = "SELECT c FROM CommentMstr c WHERE c.id = :id")
    , @NamedQuery(name = "CommentMstr.findByMessages", query = "SELECT c FROM CommentMstr c WHERE c.messages = :messages")
    , @NamedQuery(name = "CommentMstr.findByCreatedDate", query = "SELECT c FROM CommentMstr c WHERE c.createdDate = :createdDate")})
public class CommentMstr implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false)
    private Long id;
    @Size(max = 2147483647)
    @Column(length = 2147483647)
    private String messages;
    @Column(name = "created_date")
    private Long createdDate;
    @Column(name = "comment_ref_id")
    private String commentRefId;
    @JoinColumn(name = "post_id", referencedColumnName = "id")
    @ManyToOne
    private PostMstr postId;

    public CommentMstr() {
    }

    public CommentMstr(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getMessages() {
        return messages;
    }

    public void setMessages(String messages) {
        this.messages = messages;
    }

    public Long getCreatedDate() {
        return createdDate;
    }

    public void setCreatedDate(Long createdDate) {
        this.createdDate = createdDate;
    }

    public PostMstr getPostId() {
        return postId;
    }

    public void setPostId(PostMstr postId) {
        this.postId = postId;
    }

    public String getCommentRefId() {
        return commentRefId;
    }

    public void setCommentRefId(String commentRefId) {
        this.commentRefId = commentRefId;
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
        if (!(object instanceof CommentMstr)) {
            return false;
        }
        CommentMstr other = (CommentMstr) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sysintelli.ims.entity.CommentMstr[ id=" + id + " ]";
    }

}
