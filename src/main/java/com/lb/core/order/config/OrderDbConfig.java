package com.lb.core.order.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.github.miemiedev.mybatis.paginator.OffsetLimitInterceptor;
import com.github.pagehelper.PageHelper;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

/**
 * Created by LB on 2017/6/28.
 */
@Configuration
@EnableTransactionManagement
@MapperScan(basePackages = "com.lb.core.order.mapper", sqlSessionFactoryRef = "orderSqlSessionFactory")
@EnableConfigurationProperties({OrderDbProperty.class})
public class OrderDbConfig {
    @Autowired
    private OrderDbProperty property;

    @Bean(name = "orderDataSource")
    @Primary //多个bean冲突，优先使用此bean对象
    public DataSource dataSource() {
        System.out.println("加载order数据库!!!");
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUrl(property.getUrl());
        dataSource.setUsername(property.getUsername());
        dataSource.setPassword(property.getPassword());
        return dataSource;
    }

    @Bean
    @Primary
    public DataSourceTransactionManager transactionManager(DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean(name = "orderSqlSessionFactory")
    @Primary
    public SqlSessionFactory sqlSessionFactory(@Qualifier("orderDataSource") DataSource dataSource) throws Exception {
        final SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(dataSource);
        sessionFactory.setPlugins(plugins());
        return sessionFactory.getObject();
    }

    public Interceptor[] plugins() {
        OffsetLimitInterceptor plugin = new OffsetLimitInterceptor();
        plugin.setDialectClass("com.github.miemiedev.mybatis.paginator.dialect.MySQLDialect");

        //分页插件设置
        PageHelper pageHelper = new PageHelper();
        Properties properties = new Properties();
        properties.setProperty("reasonable", "true");
        properties.setProperty("supportMethodsArguments", "true");
        properties.setProperty("returnPageInfo", "check");
        properties.setProperty("params", "count=countSql");
        pageHelper.setProperties(properties);

        return new Interceptor[] {plugin,pageHelper};
    }
}
