package com.logger.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

@Component
@Configuration
@PropertySource(value = {"classpath:jdbc.properties"})
@Data
public class JdbcConfig {
    @Value("${jdbc.driver}")
    private String driver;
    @Value("${jdbc.url}")
    private String url;
    @Value("${jdbc.username}")
    private String username;
    @Value("${jdbc.password}")
    private String password;
    @Value("${jdbc.pool.init}")
    private int poolInit;
    @Value("${jdbc.pool.minIdle}")
    private int poolMinIdle;
    @Value("${jdbc.pool.maxActive}")
    private int poolMaxActive;
    @Value("${jdbc.testSql}")
    private String testSql;

    /**
     * <!-- 数据库配置文件位置 -->
     * <context:property-placeholder location="classpath:jdbc.properties" />
     * 更多信息请参考：https://blog.csdn.net/wrs120/article/details/84554366
     * */
//    @Bean
//    public static PropertySourcesPlaceholderConfigurer propertySourcesPlaceholderConfigurer() {
//        return new PropertySourcesPlaceholderConfigurer();
//    }
}