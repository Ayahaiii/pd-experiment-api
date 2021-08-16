package com.monetware.pdexperiment.system.base;

/**
 *系统常量配置
 */
public class SystemConfig {

    /**
     * 用户类型（游客
     */
    public static String USER_TYPE_VISITOR = "visitor";

    /**
     * web端token传输标识
     */
    public static String WEB_TOKEN = "tokenWeb";

    /**
     * 登陆方式 1：WEB登陆
     */
    public static Integer LOGIN_WEB = 1;

    /**
     * web Token 需修改时间(毫秒单位
     */
    public static long MODIFY_TOKEN_TIME = 7 * 20 * 60 * 60 * 1000;

    /**
     * web Token 过期时间(毫秒单位
     */
    public static long OVERDUE_TOKEN_TIME = 7 * 24 * 60 * 60 * 1000;

    /**
     * web Token redis中TOKEN删除时间(毫秒单位
     */
    public static long REDIS_DELETE_TOKEN_TIME = 25 * 60 * 60 * 1000;
}
