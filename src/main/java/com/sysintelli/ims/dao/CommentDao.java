/*
 * @CopyRight : Jay
 */
package com.sysintelli.ims.dao;

import com.sysintelli.ims.entity.CommentMstr;
import java.io.Serializable;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Component;

/**
 *
 * @author jay
 */
@Component
public class CommentDao implements GenericDao<CommentMstr, Serializable> {

    protected Class<CommentMstr> add;

    @PersistenceContext
    protected EntityManager em;


    @Override
    public CommentMstr create(CommentMstr item) {
        this.em.persist(item);
        return item;
    }

    @Override
    public CommentMstr read(Serializable id) {
        return this.em.find(CommentMstr.class, id);
    }

    @Override
    public CommentMstr update(CommentMstr item) {
        return this.em.merge(item);
    }

    @Override
    public void delete(CommentMstr t) {
        t = this.em.merge(t);
        this.em.remove(t);
    }

}
