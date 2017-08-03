package com.lb.core.info.config;

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
 * @author LB
 * @date 2017/8/2 15:03
 */
@Configuration
@EnableTransactionManagement
@MapperScan(basePackages = "com.lb.core.info.mapper", sqlSessionFactoryRef = "infoSqlSessionFactory")
@EnableConfigurationProperties({InfoDbProperty.class})
public class InfoDbConfig {
    @Autowired
    private InfoDbProperty property;

    @Bean(name = "infoDataSource")
    public DataSource dataSource() {
        System.out.println("加载info数据库!!!");
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

    @Bean(name = "infoSqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory(@Qualifier("infoDataSource") DataSource dataSource) throws Exception {
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
