package com.logger.config;

import com.alibaba.druid.pool.DruidDataSource;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.mybatis.spring.annotation.MapperScans;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.multipart.MultipartResolver;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

import javax.sql.DataSource;
import java.io.IOException;

@Configuration
@ComponentScan(basePackages = {"com.logger.service", "com.logger.config","com.logger.dao"})
@MapperScans(value = {@MapperScan("com.logger.*.dao")})
@EnableTransactionManagement
public class BeanFactoryConfig {
    @Autowired
    private JdbcConfig jdbcConfig;

    @Bean
    public DataSource dataSource() {
        DruidDataSource druidDataSource = new DruidDataSource();
        druidDataSource.setUrl(jdbcConfig.getUrl());
        druidDataSource.setUsername(jdbcConfig.getUsername());
        druidDataSource.setPassword(jdbcConfig.getPassword());
        druidDataSource.setMaxActive(jdbcConfig.getPoolMaxActive());
        druidDataSource.setInitialSize(jdbcConfig.getPoolInit());
        druidDataSource.setMinIdle(jdbcConfig.getPoolMinIdle());

        return druidDataSource;
    }

    /**
     * mybatis plugin 打印执行sql
     */
    @Bean
    public SqlStatementInterceptor sqlStatementInterceptor() {
        return new SqlStatementInterceptor();
    }

    @Bean
    public SqlSessionFactoryBean sqlSessionFactoryBean(DataSource dataSource) throws IOException {
        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource);
        sqlSessionFactoryBean.setTypeAliasesPackage("com.logger.*.model");
        // 添加mybatis plugin实现打印执行sql
        sqlSessionFactoryBean.setPlugins(sqlStatementInterceptor());

        // 动态获取SqlMapper
        PathMatchingResourcePatternResolver pathResource = new PathMatchingResourcePatternResolver();
        sqlSessionFactoryBean.setMapperLocations(pathResource.getResources("classpath*:/mappers/*.xml"));
        return sqlSessionFactoryBean;
    }

    /**
     * 事务
     *
     * @param dataSource
     * @return
     */
    @Bean
    public PlatformTransactionManager transactionManager(DataSource dataSource) {
        DataSourceTransactionManager transactionManager = new DataSourceTransactionManager();
        transactionManager.setDataSource(dataSource);
        return transactionManager;
    }

    /**
     * 配置MultipartResolver 解析器
     *
     * @return
     */
    @Bean
    public MultipartResolver multipartResolver() {
        CommonsMultipartResolver multipartRe = new CommonsMultipartResolver();
        multipartRe.setMaxUploadSize(1024000000);
        multipartRe.setResolveLazily(true);
        multipartRe.setMaxInMemorySize(4096);
        multipartRe.setDefaultEncoding("UTF-8");// 设置默认字符集
//      try {
//            multipartRe.setUploadTempDir(new FileSystemResource("/tmp/spittr/uploads"));
//        } catch (IOException e) {
//            e.printStackTrace();
//        }
        return multipartRe;
    }
}