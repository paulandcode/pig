package com.paulandcode.controller;

import com.paulandcode.common.BaseController;
import com.paulandcode.entity.TestEntity;
import com.paulandcode.service.TestService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author paulandcode paulandcode@gmail.com
 * @since 2019/3/28 19:59
 */
@RestController
@RequestMapping("test")
public class TestController extends BaseController<TestEntity, TestService> {
}
