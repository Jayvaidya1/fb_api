/*
 * @CopyRight : Jay
 */
package com.sysintelli.ims.dao;

import com.sysintelli.ims.entity.ConfigurationMstr;
import java.io.Serializable;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Component;

/**
 *
 * @author jay
 */
@Component
public class ConfigurationDao implements GenericDao<ConfigurationMstr, Serializable> {

    protected Class<ConfigurationMstr> add;

    @PersistenceContext
    protected EntityManager em;


    @Override
    public ConfigurationMstr create(ConfigurationMstr item) {
        this.em.persist(item);
        return item;
    }

    @Override
    public ConfigurationMstr read(Serializable id) {
        return this.em.find(ConfigurationMstr.class, id);
    }

    @Override
    public ConfigurationMstr update(ConfigurationMstr item) {
        return this.em.merge(item);
    }

    @Override
    public void delete(ConfigurationMstr t) {
        t = this.em.merge(t);
        this.em.remove(t);
    }

}
