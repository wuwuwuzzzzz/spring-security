package com.example.springsecurity.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;

/**
 * @author wxz
 * @date 17:07 2023/2/16
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User implements Serializable {

    /**
     * 主键
     */
    private Long id;

    /**
     * 用户名
     */
    private String userName;

    /**
     * 昵称
     */
    private String nickName;

    /**
     * 密码
     */
    private String password;

    /**
     * 账号状态(0 正常 1 停用)
     */
    private String status;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 手机号
     */
    private String pbonenumber;

    /**
     * 性别
     */
    private String sex;

    /**
     * 头像
     */
    private String avatar;

    /**
     * 用户类型(0 管理员 1 普通用户)
     */
    private String userType;

    /**
     * 创建人的用户ID
     */
    private Long createBy;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新人的用户ID
     */
    private Long updateBy;

    /**
     * 更新时间
     */
    private Date updateTime;
}
