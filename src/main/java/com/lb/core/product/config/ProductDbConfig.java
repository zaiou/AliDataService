package com.lb.core.product.config;

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
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;
import java.util.Properties;

/**
 *
 * Created by LB on 2017/6/29.
 */

@Configuration
@EnableTransactionManagement
@MapperScan(basePackages = "com.lb.demo.product.mapper", sqlSessionFactoryRef = "productSqlSessionFactory")
@EnableConfigurationProperties({ProductDbProperty.class })
public class ProductDbConfig {
    @Autowired
    private ProductDbProperty property;

    @Bean(name = "productDataSource")
    public DataSource dataSource() {
        System.out.println("加载product数据库!!!");
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setUrl(property.getUrl());
        dataSource.setUsername(property.getUsername());
        dataSource.setPassword(property.getPassword());
        return dataSource;
    }

    @Bean
    public DataSourceTransactionManager transactionManager(DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    @Bean(name = "productSqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory(@Qualifier("productDataSource") DataSource dataSource) throws Exception {
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
