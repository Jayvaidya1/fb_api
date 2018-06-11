/*
 * @CopyRight : Jay
 */
package com.sysintelli.ims.dao;

import com.sysintelli.ims.entity.FbOperationMstr;
import java.io.Serializable;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Component;

/**
 *
 * @author jay
 */
@Component
public class FbOperationDao implements GenericDao<FbOperationMstr, Serializable> {

    protected Class<FbOperationMstr> add;

    @PersistenceContext
    protected EntityManager em;


    @Override
    public FbOperationMstr create(FbOperationMstr item) {
        this.em.persist(item);
        return item;
    }

    @Override
    public FbOperationMstr read(Serializable id) {
        return this.em.find(FbOperationMstr.class, id);
    }

    @Override
    public FbOperationMstr update(FbOperationMstr item) {
        return this.em.merge(item);
    }

    @Override
    public void delete(FbOperationMstr t) {
        t = this.em.merge(t);
        this.em.remove(t);
    }

}
