package com.ch.cloud.demo.dao;

/**
 * @author yjph83
 * @date 2018/3/7
 */
public interface CommonDAO<T, K> {

    /**
     * 根据Id获取对象
     *
     * @param id
     * @return
     */
    T getById(K id);

    /**
     * 保存对象
     *
     * @param t
     * @return
     */
    int save(T t);

    /**
     * 删除对象
     *
     * @param id
     * @return
     */
    int remove(K id);

    /**
     * 更新对象
     *
     * @param t
     * @return
     */
    int update(T t);
}
