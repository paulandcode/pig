package com.paulandcode.common;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * 基础的实体类, 可序列化, 主键字段默认为id
 *
 * @author paulandcode paulandcode@gmail.com
 * @since 2019/3/27 22:27
 */
@Data
public abstract class BaseEntity implements Serializable {
    private static final long serialVersionUID = -5532439383771563598L;
    /**
     * 主键, 使用UUID, 这里用Object类型, 这样可以支持Integer
     */
    protected Object id;
    /**
     * 备注
     */
    protected String remark;
    /**
     * 创建人ID, 若为系统创建, 则为system
     */
    protected String createBy;
    /**
     * 创建时间, 默认当前时间
     */
    protected Date createDate;
    /**
     * 更新人ID, 若为系统更新, 则为system
     */
    protected String updateBy;
    /**
     * 更新时间, 根据当前时间自动更新
     */
    protected Date updateDate;
    /**
     * 备用字段1
     */
    protected String column1;
    /**
     * 备用字段2
     */
    protected String column2;
    /**
     * 备用字段3
     */
    protected String column3;
    /**
     * 删除标志
     */
    protected Boolean delFlag;
}
