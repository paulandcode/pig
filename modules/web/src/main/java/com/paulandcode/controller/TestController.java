package com.paulandcode.controller;

import com.paulandcode.shiro.credential.PasswordHelper;
import com.paulandcode.system.entity.SysUser;
import com.paulandcode.system.service.SysUserService;
import com.paulandcode.utils.IDUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 测试
 *
 * @author paulandcode paulandcode@gmail.com
 * @since 2019/3/24 13:18
 */
@Controller
public class TestController {
    @Autowired
    SysUserService sysUserService;

    @RequestMapping("login")
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
        SysUser sysUser = new SysUser(IDUtils.getId(), "admin",
                "admin", "sa", false, false);
        PasswordHelper.encryptPassword(sysUser);
        sysUserService.insert(sysUser);
        return "login";
    }
}
