package com.paulandcode.system.controller;

import com.paulandcode.common.BaseController;
import com.paulandcode.system.entity.CoreSysUserEntity;
import com.paulandcode.system.service.UserService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 用户
 *
 * @author paulandcode paulandcode@gmail.com
 * @since 2019/3/28 14:54
 */
@RestController
@RequestMapping("sys/user")
public class UserController extends BaseController<CoreSysUserEntity, UserService> {
}
