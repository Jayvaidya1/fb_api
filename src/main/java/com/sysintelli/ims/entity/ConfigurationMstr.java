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
@Table(name = "configuration_mstr", schema = "fb_api")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ConfigurationMstr.findAll", query = "SELECT c FROM ConfigurationMstr c")
    , @NamedQuery(name = "ConfigurationMstr.findById", query = "SELECT c FROM ConfigurationMstr c WHERE c.id = :id")
    , @NamedQuery(name = "ConfigurationMstr.findByAccessToken", query = "SELECT c FROM ConfigurationMstr c WHERE c.accessToken = :accessToken")
    , @NamedQuery(name = "ConfigurationMstr.findByIsActive", query = "SELECT c FROM ConfigurationMstr c WHERE c.isActive = :isActive")
    , @NamedQuery(name = "ConfigurationMstr.findByDetails", query = "SELECT c FROM ConfigurationMstr c WHERE c.details = :details")})
public class ConfigurationMstr implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(nullable = false)
    private Long id;
    @Size(max = 2147483647)
    @Column(name = "access_token", length = 2147483647)
    private String accessToken;
    @Column(name = "is_active")
    private Boolean isActive;
    @Size(max = 2147483647)
    @Column(length = 2147483647)
    private String details;

    public ConfigurationMstr() {
    }

    public ConfigurationMstr(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public Boolean getIsActive() {
        return isActive;
    }

    public void setIsActive(Boolean isActive) {
        this.isActive = isActive;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
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
        if (!(object instanceof ConfigurationMstr)) {
            return false;
        }
        ConfigurationMstr other = (ConfigurationMstr) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "com.sysintelli.ims.entity.ConfigurationMstr[ id=" + id + " ]";
    }

}
