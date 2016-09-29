package com.example.configuration;

import java.sql.SQLException;

import javax.sql.DataSource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.bind.RelaxedPropertyResolver;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import com.alibaba.druid.pool.DruidDataSource;

@Configuration
@MapperScan(basePackages = { "com.example.mapper.slave" }, sqlSessionFactoryRef = "slaveSqlSessionFactory")
public class SlaveDataBaseConfig implements EnvironmentAware {

	private Log logger = LogFactory.getLog(getClass());

	private RelaxedPropertyResolver propertyResolver;

	@Override
	public void setEnvironment(Environment arg0) {
		this.propertyResolver = new RelaxedPropertyResolver(arg0, "spring.");
	}

	@Bean(name = "slaveDataSource")
	public DataSource dataSource() throws SQLException {
		logger.info("注入 druid 数据源2");

		DruidDataSource dataSource = new DruidDataSource();

		dataSource.setUrl(propertyResolver.getProperty("other.datasource.url"));
		dataSource.setUsername(propertyResolver.getProperty("other.datasource.username"));
		dataSource.setPassword(propertyResolver.getProperty("other.datasource.password"));
		dataSource.setDriverClassName(propertyResolver.getProperty("datasource.driver-class-name"));
		dataSource.setInitialSize(Integer.parseInt(propertyResolver.getProperty("datasource.initialSize")));
		dataSource.setMaxActive(Integer.parseInt(propertyResolver.getProperty("datasource.maxActive")));
		dataSource.setMinIdle(Integer.parseInt(propertyResolver.getProperty("datasource.minIdle")));
		dataSource.setMaxWait(Integer.parseInt(propertyResolver.getProperty("datasource.maxWait")));
		dataSource.setTimeBetweenEvictionRunsMillis(
				Integer.parseInt(propertyResolver.getProperty("datasource.timeBetweenEvictionRunsMillis")));
		dataSource.setMinEvictableIdleTimeMillis(
				Integer.parseInt(propertyResolver.getProperty("datasource.minEvictableIdleTimeMillis")));
		dataSource.setValidationQuery(propertyResolver.getProperty("datasource.validationQuery"));
		dataSource.setTestOnBorrow(Boolean.getBoolean(propertyResolver.getProperty("datasource.testOnBorrow")));
		dataSource.setTestWhileIdle(Boolean.getBoolean(propertyResolver.getProperty("datasource.testWhileIdle")));
		dataSource.setTestOnReturn(Boolean.getBoolean(propertyResolver.getProperty("datasource.testOnReturn")));
		dataSource.setPoolPreparedStatements(
				Boolean.getBoolean(propertyResolver.getProperty("datasource.poolPreparedStatements")));
		dataSource.setMaxPoolPreparedStatementPerConnectionSize(
				Integer.parseInt(propertyResolver.getProperty("datasource.maxOpenPreparedStatements")));
		// 配置监控统计拦截的filters，去掉后监控界面sql无法统计，'wall'用于防火墙
		dataSource.setFilters(propertyResolver.getProperty("datasource.filters"));

		return dataSource;
	}

	@Bean(name = "slaveTransactionManager")
	public DataSourceTransactionManager transactionManager(@Qualifier("slaveDataSource") DataSource dataSource) {
		return new DataSourceTransactionManager(dataSource);
	}

	@Bean(name = "slaveSqlSessionFactory")
	public SqlSessionFactory basicSqlSessionFactory(@Qualifier("slaveDataSource") DataSource basicDataSource)
			throws Exception {
		SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
		factoryBean.setDataSource(basicDataSource);
		factoryBean.setTypeAliasesPackage("demo.model");
		
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
		
        factoryBean.setMapperLocations(resolver.getResources("classpath:/mapper/*.xml"));


		return factoryBean.getObject();
	}

}
