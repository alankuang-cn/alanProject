package com.example.configuration;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.boot.bind.RelaxedPropertyResolver;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import com.alibaba.druid.pool.DruidDataSource;


//启注解事务管理  
@EnableTransactionManagement  
@Configuration
public class DataSourceConfiguration implements EnvironmentAware{
	
	private Log logger =LogFactory.getLog(getClass());
	
	private RelaxedPropertyResolver propertyResolver;
	
	@Override
	public void setEnvironment(Environment arg0) {
		this.propertyResolver = new RelaxedPropertyResolver(arg0, "spring.datasource.");
	}
	
	
	@Bean
	public Object testBean(PlatformTransactionManager platformTransactionManager) {
		//System.out.println(">>>>>>>>>>" + platformTransactionManager.getClass().getName());
		return new Object();
	}
	
	@Bean(destroyMethod="close" , initMethod="init")
	public DataSource getDataSource() throws SQLException{
		
		logger.info("注入 druid 数据源");
		
		DruidDataSource dataSource = new DruidDataSource();
		
		dataSource.setUrl(propertyResolver.getProperty("url"));
		dataSource.setUsername(propertyResolver.getProperty("username"));
		dataSource.setPassword(propertyResolver.getProperty("password"));
		dataSource.setDriverClassName(propertyResolver.getProperty("driver-class-name"));
        dataSource.setInitialSize(Integer.parseInt(propertyResolver.getProperty("initialSize")));  
        dataSource.setMaxActive(Integer.parseInt(propertyResolver.getProperty("maxActive")));  
        dataSource.setMinIdle(Integer.parseInt(propertyResolver.getProperty("minIdle")));  
        dataSource.setMaxWait(Integer.parseInt(propertyResolver.getProperty("maxWait")));  
        dataSource.setTimeBetweenEvictionRunsMillis(Integer.parseInt(propertyResolver.getProperty("timeBetweenEvictionRunsMillis")));  
        dataSource.setMinEvictableIdleTimeMillis(Integer.parseInt(propertyResolver.getProperty("minEvictableIdleTimeMillis")));  
        dataSource.setValidationQuery(propertyResolver.getProperty("validationQuery"));  
        dataSource.setTestOnBorrow(Boolean.getBoolean(propertyResolver.getProperty("testOnBorrow")));  
        dataSource.setTestWhileIdle(Boolean.getBoolean(propertyResolver.getProperty("testWhileIdle")));  
        dataSource.setTestOnReturn(Boolean.getBoolean(propertyResolver.getProperty("testOnReturn")));  
        dataSource.setPoolPreparedStatements(Boolean.getBoolean(propertyResolver.getProperty("poolPreparedStatements")));  
        dataSource.setMaxPoolPreparedStatementPerConnectionSize(Integer.parseInt(propertyResolver.getProperty("maxOpenPreparedStatements")));  
        //配置监控统计拦截的filters，去掉后监控界面sql无法统计，'wall'用于防火墙  
        dataSource.setFilters(propertyResolver.getProperty("filters")); 
		
		return dataSource;
		
	}
	
	@Bean
	public SqlSessionFactory sqlSessionFactoryBean() throws Exception{
		
		SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
		sqlSessionFactoryBean.setDataSource(getDataSource());
		
		PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
		
		sqlSessionFactoryBean.setMapperLocations(resolver.getResources("classpath:/mapper/*.xml"));
		
		return sqlSessionFactoryBean.getObject();
	}

}
