package com.paulandcode.entity;

import com.paulandcode.common.BaseEntity;
import lombok.Data;

import java.util.Date;

/**
 * @author paulandcode paulandcode@gmail.com
 * @since 2019/3/28 19:59
 */
@Data
public class TestEntity extends BaseEntity {
    private static final long serialVersionUID = -568639849174256641L;
    private String name;
    private int age;
    private Date birthday;
    private Boolean man;
}
