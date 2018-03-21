package com.ch.cloud.demo.service.impl;

import com.ch.cloud.demo.dao.UserDAO;
import com.ch.cloud.demo.dto.UserDTO;
import com.ch.cloud.demo.entity.UserDO;
import com.ch.cloud.demo.exception.ServiceError;
import com.ch.cloud.demo.exception.ServiceException;
import com.ch.cloud.demo.service.UserService;
import com.ch.cloud.demo.utils.BeanMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * 示例服务层代码，实际编码过程中请做好业务逻辑控制和参数校验等工作
 *
 * @author yjph83
 * @date 2018/3/7
 */

@Service("userService")
public class UserServiceImpl implements UserService {

    @Autowired
    UserDAO userDAO;

    @Override
    public UserDTO getUser(Long id) {
        UserDO userDO = userDAO.getById(id);
        if (null != userDO) {
            UserDTO userDTO = BeanMapper.map(userDO, UserDTO.class);
            return userDTO;
        }
        return null;
    }

    @Override
    public PageInfo<UserDTO> listUser(int pageNum, int pageSize, Integer status) {
        PageHelper.startPage(pageNum, pageSize);
        List<UserDO> userDOList = userDAO.listUser(status);
        PageInfo<UserDTO> pageInfo = BeanMapper.mapPageInfo(userDOList, UserDTO.class);
        return pageInfo;
    }

    @Override
    public void saveUser(UserDTO userDTO) {
        UserDO userDO = BeanMapper.map(userDTO, UserDO.class);
        userDO.setCreateTime(new Date());
        userDO.setUpdateTime(new Date());
        int result;
        try {
            result = userDAO.save(userDO);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServiceException(ServiceError.DB_ACCESS_ERROR, null);
        }
        if (result == 1) {
            userDTO.setId(userDO.getId());
        }
    }

    @Override
    public void removeUser(Long id) {
        int result;
        try {
            result = userDAO.remove(id);
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServiceException(ServiceError.DB_ACCESS_ERROR, null);
        }
        if (result != 1) {
            throw new ServiceException(ServiceError.DB_OPERATE_FAIL, null);
        }
    }

    @Override
    public void updateUser(UserDTO userDTO) {
        Long id = userDTO.getId();
        if (null == id) {
            throw new ServiceException(ServiceError.PARAMETER_ERROR, "id");
        } else {
            UserDO existingUser = userDAO.getById(id);
            if (null == existingUser) {
                throw new ServiceException(ServiceError.TARGET_NOT_FOUND, null);
            } else {
                UserDO userDO = BeanMapper.map(userDTO, UserDO.class);
                userDO.setUpdateTime(new Date());
                int result;
                try {
                    result = userDAO.update(userDO);
                } catch (DataIntegrityViolationException e) {
                    e.printStackTrace();
                    throw new ServiceException(ServiceError.PARAMETER_ERROR, null);
                } catch (Exception e) {
                    throw new ServiceException(ServiceError.DB_ACCESS_ERROR, null);
                }
                if (result != 1) {
                    throw new ServiceException(ServiceError.DB_OPERATE_LOCKED, null);
                }
            }
        }
    }
}
