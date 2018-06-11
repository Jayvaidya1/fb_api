/*
 * @CopyRight : Jay
 */
package com.sysintelli.ims.dao;

import com.sysintelli.ims.entity.PostMstr;
import java.io.Serializable;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.stereotype.Component;

/**
 *
 * @author jay
 */
@Component
public class PostDao implements GenericDao<PostMstr, Serializable> {

    protected Class<PostMstr> add;

    @PersistenceContext
    protected EntityManager em;


    @Override
    public PostMstr create(PostMstr item) {
        this.em.persist(item);
        return item;
    }

    @Override
    public PostMstr read(Serializable id) {
        return this.em.find(PostMstr.class, id);
    }

    @Override
    public PostMstr update(PostMstr item) {
        return this.em.merge(item);
    }

    @Override
    public void delete(PostMstr t) {
        t = this.em.merge(t);
        this.em.remove(t);
    }

}
