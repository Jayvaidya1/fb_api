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
@Table(name = "fb_operation_mstr", schema = "fb_api")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "FbOperationMstr.findAll", query = "SELECT f FROM FbOperationMstr f")
    , @NamedQuery(name = "FbOperationMstr.findById", query = "SELECT f FROM FbOperationMstr f WHERE f.id = :id")
    , @NamedQuery(name = "FbOperationMstr.findByYtChannel", query = "SELECT f FROM FbOperationMstr f WHERE f.ytChannel = :ytChannel")
    , @NamedQuery(name = "FbOperationMstr.findByFbRefId", query = "SELECT f FROM FbOperationMstr f WHERE f.fbRefId = :fbRefId")
    , @NamedQuery(name = "FbOperationMstr.findByLastAccess", query = "SELECT f FROM FbOperationMstr f WHERE f.lastAccess = :lastAccess")})
public class FbOperationMstr implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false)
    private Long id;
    @Size(max = 2147483647)
    @Column(name = "yt_channel", length = 2147483647)
    private String ytChannel;
    @Size(max = 2147483647)
    @Column(name = "fb_ref_id", length = 2147483647)
    private String fbRefId;
    @Column(name = "last_access")
    private Long lastAccess;
    @OneToMany(mappedBy = "fbId")
    private Collection<PostMstr> postMstrCollection;

    public FbOperationMstr() {
    }

    public FbOperationMstr(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getYtChannel() {
        return ytChannel;
    }

    public void setYtChannel(String ytChannel) {
        this.ytChannel = ytChannel;
    }

    public String getFbRefId() {
        return fbRefId;
    }

    public void setFbRefId(String fbRefId) {
        this.fbRefId = fbRefId;
    }

    public Long getLastAccess() {
        return lastAccess;
    }

    public void setLastAccess(Long lastAccess) {
        this.lastAccess = lastAccess;
    }

    @XmlTransient
    public Collection<PostMstr> getPostMstrCollection() {
        return postMstrCollection;
    }

    public void setPostMstrCollection(Collection<PostMstr> postMstrCollection) {
        this.postMstrCollection = postMstrCollection;
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
        if (!(object instanceof FbOperationMstr)) {
            return false;
        }
        FbOperationMstr other = (FbOperationMstr) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sysintelli.ims.entity.FbOperationMstr[ id=" + id + " ]";
    }
    
}
