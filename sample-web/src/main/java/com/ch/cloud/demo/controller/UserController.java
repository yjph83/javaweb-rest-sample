package com.ch.cloud.demo.controller;

import com.ch.cloud.demo.dto.UserDTO;
import com.ch.cloud.demo.exception.RestException;
import com.ch.cloud.demo.exception.ServiceException;
import com.ch.cloud.demo.service.UserService;
import com.ch.cloud.demo.utils.Constant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * 描述:
 * 时间:2018-03-06 12:58
 *
 * @author:yjph83
 */
@RestController
@RequestMapping(value = "/v1", produces = {MediaType.APPLICATION_JSON_UTF8_VALUE})
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/users/{id}", method = RequestMethod.GET)
    public ResponseEntity getUser(@PathVariable("id") Long id) {
        UserDTO userDTO = userService.getUser(id);
        if (userDTO != null) {
            return ResponseEntity.ok(userDTO);
        }
        throw new RestException(HttpStatus.NOT_FOUND.value(), Constant.ERROR_TARGET_NOT_FOUND);
    }

    @RequestMapping(value = "users", method = RequestMethod.GET)
    public ResponseEntity list(@RequestParam(value = "pageNum", defaultValue = "1") int pageNum,
                               @RequestParam(value = "pageSize", defaultValue = "5") int pageSize,
                               @RequestParam(value = "status", required = false) Integer status) {
        if (pageNum < 1 || pageSize < 1) {
            throw new RestException(HttpStatus.BAD_REQUEST.value(),
                    Constant.ERROR_INCORRECT_PARAMETER);

        }
        return ResponseEntity.ok(userService.listUser(pageNum, pageSize, status));
    }

    @RequestMapping(value = "users", method = RequestMethod.POST,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity saveUser(@RequestBody @Valid UserDTO userDTO) {
        try {
            userService.saveUser(userDTO);
        } catch (ServiceException e) {
            e.printStackTrace();
            throw new RestException(e.serviceError.httpStatus, e.serviceError.message);
        }
        return ResponseEntity.ok(userDTO.getId());
    }

    @RequestMapping(value = "users/{id}", method = RequestMethod.PUT,
            consumes = MediaType.APPLICATION_JSON_UTF8_VALUE)
    public ResponseEntity updateUser(@PathVariable(value = "id") Long id,
                                     @RequestBody @Valid UserDTO userDTO) {
        UserDTO existingUser = userService.getUser(id);
        if (null != existingUser) {
            try {
                userDTO.setId(id);
                userService.updateUser(userDTO);
                return ResponseEntity.ok().build();
            } catch (ServiceException e) {
                e.printStackTrace();
                throw new RestException(e.serviceError.httpStatus, e.serviceError.message);
            }

        } else {
            throw new RestException(HttpStatus.NOT_FOUND.value(), Constant.ERROR_TARGET_NOT_FOUND);
        }
    }

    @RequestMapping(value = "users/{id}", method = RequestMethod.DELETE)
    public ResponseEntity removeUser(
            @PathVariable(value = "id") Long id) {
        UserDTO existingUser = userService.getUser(id);
        if (null != existingUser) {
            try {
                userService.removeUser(id);
                return ResponseEntity.ok().build();
            } catch (ServiceException e) {
                e.printStackTrace();
                throw new RestException(e.serviceError.httpStatus, e.serviceError.message);
            }
        } else {
            throw new RestException(HttpStatus.NOT_FOUND.value(), Constant.ERROR_TARGET_NOT_FOUND);
        }
    }

}
