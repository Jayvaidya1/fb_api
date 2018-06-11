/*
 * @CopyRight : Jay
 */
package com.sysintelli.ims.config;

import com.sysintelli.ims.proxy.ConfigurationProxy;
import com.sysintelli.ims.utility.ListUtility;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * @author jay
 */
@Component
public class ConfigBean {

    @Autowired
    LogInterceptor logInterceptor;

    @PersistenceContext
    private EntityManager em;

    public List<ConfigurationProxy> getConfigurationList(Boolean isAll) {
        if (isAll) {
            /**
             * @Constructor ConfigurationProxy_02
             */
            return em.createQuery("select new com.sysintelli.ims.proxy.ConfigurationProxy( c.id, c.accessToken, c.isActive, c.details) from ConfigurationMstr c  ")
                    .getResultList();
        }
        /**
         * @Constructor ConfigurationProxy_02
         */
        return em.createQuery("select new com.sysintelli.ims.proxy.ConfigurationProxy( c.id, c.accessToken, c.isActive, c.details) from ConfigurationMstr c where c.isActive=:isActive ")
                .setParameter("isActive", Boolean.TRUE)
                .getResultList();
    }

    public ConfigurationProxy getActiveConfiguration() {

        /**
         * @Constructor ConfigurationProxy_02
         */
        List<ConfigurationProxy> resList = em.createQuery("select new com.sysintelli.ims.proxy.ConfigurationProxy( c.id, c.accessToken, c.isActive, c.details) from ConfigurationMstr c where c.isActive=:isActive ")
                .setParameter("isActive", Boolean.TRUE)
                .getResultList();
        return !ListUtility.isNullOrEmpty(resList) ? resList.get(0) : null;
    }

}
