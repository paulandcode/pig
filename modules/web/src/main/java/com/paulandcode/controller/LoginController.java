package com.paulandcode.controller;

import com.paulandcode.common.BaseController;
import com.paulandcode.shiro.credential.PasswordHelper;
import com.paulandcode.system.entity.CoreSysUserEntity;
import com.paulandcode.system.service.UserService;
import com.paulandcode.utils.IDUtils;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 登录
 *
 * @author paulandcode paulandcode@gmail.com
 * @since 2019/3/24 13:18
 */
@Controller
@RequestMapping("login")
public class LoginController extends BaseController<CoreSysUserEntity, UserService> {
    @RequestMapping("/a")
    public String login() {
        return "login";
    }

    @RequestMapping("index")
    public String index() {
        return "index";
    }

    @RequestMapping("do")
    @ResponseBody
    public String doIt() {
        CoreSysUserEntity coreSysUserEntity = new CoreSysUserEntity();
        coreSysUserEntity.setId(IDUtils.getId());
        coreSysUserEntity.setUsername("admin");
        coreSysUserEntity.setPassword("admin");
        PasswordHelper.encryptPassword(coreSysUserEntity);
        service.insert(coreSysUserEntity);
        return "ok";
    }
}
