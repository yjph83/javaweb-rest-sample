package com.ch.cloud.demo.dao;

import com.ch.cloud.demo.entity.UserDO;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;


/**
 * 描述:
 * 时间:2018-03-06 9:27
 *
 * @author:yjph83
 */
@Mapper
public interface UserDAO extends CommonDAO<UserDO, Long> {


    /**
     * 获取用户列表
     *
     * @param status
     * @return
     */
    List<UserDO> listUser(@Param("status") Integer status);
}
