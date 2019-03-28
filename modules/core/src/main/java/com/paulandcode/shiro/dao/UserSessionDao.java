package com.paulandcode.shiro.dao;

import com.alibaba.fastjson.JSON;
import com.paulandcode.shiro.entity.Session;
import com.paulandcode.utils.IDUtils;
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
public class UserSessionDao extends EnterpriseCacheSessionDAO {
    @Resource
    private RealSessionDAO realSessionDAO;

    public UserSessionDao() {
        super();
        // 设置Session的缓存名, 默认是shiro-activeSessionCache
        this.setActiveSessionsCacheName("activeSessionCache");
        // 用于生成会话ID, 默认是JavaUuidSessionIdGenerator, 即: 使用java.util.UUID生成, 这里去掉"-", 使生成的ID为32位
        this.setSessionIdGenerator(session -> IDUtils.getId());
    }

    @Override
    protected Serializable doCreate(org.apache.shiro.session.Session session) {
        Serializable sessionId = generateSessionId(session);
        assignSessionId(session, sessionId);
        realSessionDAO.insert(new Session(sessionId, JSON.toJSONString(session)));
        return sessionId;
    }

    @Override
    protected org.apache.shiro.session.Session doReadSession(Serializable sessionId) {
        return JSON.parseObject(realSessionDAO.selectById(sessionId).getSession(),
                org.apache.shiro.session.Session.class);
    }

    @Override
    protected void doUpdate(org.apache.shiro.session.Session session) {
        if (session instanceof ValidatingSession && !((ValidatingSession) session).isValid()) {
            return;
        }
        realSessionDAO.updateById(new Session(session.getId(), JSON.toJSONString(session)));
    }

    @Override
    protected void doDelete(org.apache.shiro.session.Session session) {
        realSessionDAO.deleteById(session.getId());
    }
}
