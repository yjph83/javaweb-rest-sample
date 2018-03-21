package com.ch.cloud.demo.service;

import com.ch.cloud.demo.dto.UserDTO;
import com.github.pagehelper.PageInfo;

/**
 * 描述:
 * 时间:2018-03-06 13:00
 *
 * @author:yjph83
 */

public interface UserService {

    /**
     * 根据id获得用户
     *
     * @param id
     * @return
     */
    UserDTO getUser(Long id);

    /**
     * 获取用户列表
     *
     * @param pageNum
     * @param pageSize
     * @param status
     * @return 用户分页数据
     */
    PageInfo<UserDTO> listUser(int pageNum, int pageSize, Integer status);

    /**
     * 添加用户
     *
     * @param userDTO
     */
    void saveUser(UserDTO userDTO);

    /**
     * 删除用户
     *
     * @param id
     */
    void removeUser(Long id);

    /**
     * 修改用户信息
     *
     * @param userDTO
     */
    void updateUser(UserDTO userDTO);
}
