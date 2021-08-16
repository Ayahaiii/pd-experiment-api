package com.monetware.pdexperiment.system.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.ImportResource;

/**
 * @Author: Cookie
 * @Date: 2020-10-14 10:57
 * @Description: 统一事务管理配置
 */
@Configuration
@ImportResource(locations = "classpath:/spring/spring-trans.xml")
public class TransactionConfig {

}
