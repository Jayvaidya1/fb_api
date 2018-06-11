/*
 * @CopyRight : Jay
 */
package com.sysintelli.ims.dao;

import java.io.Serializable;

/**
 * @This is generic Dao interface
 * @author jay
 * @param <T>
 * @param <PK>
 */
public interface GenericDao<T, PK extends Serializable> {

    T create(T t);

    T read(PK id);

    T update(T t);

    void delete(T t);
}