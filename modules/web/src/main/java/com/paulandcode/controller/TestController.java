package com.paulandcode.controller;

import com.paulandcode.common.BaseController;
import com.paulandcode.shiro.credential.PasswordHelper;
import com.paulandcode.system.entity.User;
import com.paulandcode.system.service.UserService;
import com.paulandcode.utils.IDUtils;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 测试
 *
 * @author paulandcode paulandcode@gmail.com
 * @since 2019/3/24 13:18
 */
@Controller
@RequestMapping("test")
public class TestController extends BaseController<User, UserService> {
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
        User user = new User();
        user.setId(IDUtils.getId());
        user.setUsername("admin");
        user.setPassword("admin");
        PasswordHelper.encryptPassword(user);
        service.insert(user);
        return "ok";
    }
}
