package com.monetware.pdexperiment.system.util.threadlocal;

import lombok.Data;

/**
 *保存至redis中用户信息
 */
@Data
public class TokenContext {

    /**
     * 用户id
     */
	private Integer userId;

    /**
     * 1.PC端用户
     * 2.App用户
     */
	private Integer type;

    /**
     * Token创建时间
     */
    private Long createTime;

    /**
     * token
     */
    private String token;

    /**
     * 用户权限
     */
    private Integer role;

}
