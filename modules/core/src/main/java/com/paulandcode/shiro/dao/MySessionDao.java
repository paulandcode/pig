package com.paulandcode.shiro.dao;

import com.alibaba.fastjson.JSON;
import com.paulandcode.shiro.entity.ShiroSession;
import com.paulandcode.utils.IDUtils;
import org.apache.shiro.session.Session;
import org.apache.shiro.session.mgt.ValidatingSession;
import org.apache.shiro.session.mgt.eis.EnterpriseCacheSessionDAO;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.Serializable;

/**
 * 自定义sessionDao, 这其实是一个Service, 真正的DAO其实是RealSessionDAO, 由于sessionManager.setSessionDAO(), 所以起名DAO
 * 对于分布式系统, 一般都牵扯到Session共享问题, 而想实现Session共享, 就要实现Session的持久化操作,
 * 即将内存中的Session持久化至缓存数据库.
 *
 * @author paulandcode paulandcode@gmail.com
 * @since 2019/3/21 20:41
 */
@Component
public class MySessionDao extends EnterpriseCacheSessionDAO {
    @Resource
    private RealSessionDAO mybatisSessionDAO;

    public MySessionDao() {
        super();
        // 设置Session的缓存名, 默认是shiro-activeSessionCache
        this.setActiveSessionsCacheName("activeSessionCache");
        // 用于生成会话ID, 默认是JavaUuidSessionIdGenerator, 即使用java.util.UUID生成, 这里去掉"-", 使生成的ID为32位
        this.setSessionIdGenerator(session -> IDUtils.getId());
    }

    @Override
    protected Serializable doCreate(Session session) {
        Serializable sessionId = generateSessionId(session);
        assignSessionId(session, sessionId);
        mybatisSessionDAO.insert(new ShiroSession(sessionId.toString(), JSON.toJSONString(session)));
        return sessionId;
    }

    @Override
    protected Session doReadSession(Serializable sessionId) {
        return JSON.parseObject(mybatisSessionDAO.selectById(sessionId.toString()).getSession(), Session.class);
    }

    @Override
    protected void doUpdate(Session session) {
        if (session instanceof ValidatingSession && !((ValidatingSession) session).isValid()) {
            return;
        }
        mybatisSessionDAO.updateById(new ShiroSession(session.getId().toString(), JSON.toJSONString(session)));
    }

    @Override
    protected void doDelete(Session session) {
        mybatisSessionDAO.deleteById(session.getId().toString());
    }
}
