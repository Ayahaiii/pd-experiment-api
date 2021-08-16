package com.monetware.pdexperiment.system.util.threadlocal;


import com.monetware.pdexperiment.system.base.ErrorCode;
import com.monetware.pdexperiment.system.exception.ServiceException;

/**
 * 存储token中用户相关数据
 */
public class ThreadLocalManager {

	private static ThreadLocal<TokenContext> tokenContext = new ThreadLocal<TokenContext>();

	public static TokenContext getTokenContext() {
		return tokenContext.get();
	}

	/**
	 * @Author: Cookie
	 * @Date: 2019/1/19 10:08
	 * @Description: 获取当前登陆用户id
	 */
	public static Integer getUserId(){
	    try {
            Integer userId = tokenContext.get().getUserId();
            if (userId == null){
                throw new ServiceException(ErrorCode.USER_NOT_LOGIN);
            }
            return userId;
        } catch (Exception e){
	        e.printStackTrace();
            throw new ServiceException(ErrorCode.USER_NOT_LOGIN);
        }
    }

	public static void setTokenContext(TokenContext token) {
		tokenContext.set(token);
	}
	public static void removeTokenContext() {
		tokenContext.remove();
	}
}
