package com.paulandcode.shiro.entity;

import com.paulandcode.common.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * shiro会话
 *
 * @author paulandcode paulandcode@gmail.com
 * @since 2019/3/21 22:57
 */
@Data
public class SessionEntity extends BaseEntity {
    private static final long serialVersionUID = -1058318866957363412L;
    private String session;

    public SessionEntity(Object id, String session) {
        this.id = id;
        this.session = session;
    }
}
