//package com.same.microservice.engine.platform.config;
//
//import com.alibaba.druid.pool.DruidDataSource;
//import org.apache.commons.lang3.ArrayUtils;
//import org.apache.ibatis.io.VFS;
//import org.apache.ibatis.session.SqlSessionFactory;
//import org.mybatis.spring.SqlSessionFactoryBean;
//import org.mybatis.spring.SqlSessionTemplate;
//import org.mybatis.spring.annotation.MapperScan;
//import org.mybatis.spring.boot.autoconfigure.SpringBootVFS;
//import org.springframework.beans.factory.annotation.Qualifier;
//import org.springframework.boot.context.properties.ConfigurationProperties;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//import org.springframework.context.annotation.Primary;
//import org.springframework.core.io.DefaultResourceLoader;
//import org.springframework.core.io.Resource;
//import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
//
//import javax.sql.DataSource;
//
//@Configuration
//@MapperScan(basePackages = {"com.red.rec.offline.engine.mapper"}, sqlSessionFactoryRef = "offlineEngineWebSqlSessionFactory")
//public class MyBatisConfig {
//
//    @Bean(name = "offlineEngineDs")
//    @Primary
//    @ConfigurationProperties(prefix = "spring.datasource.offline-engine")
//    public DataSource getArticleServiceDataSource() {
//        return new DruidDataSource();
//    }
//
//    @Bean(name = "offlineEngineWebSqlSessionFactory")
//    @Primary
//    public SqlSessionFactory articleServiceSqlSessionFactory(@Qualifier("offlineEngineDs") DataSource dataSource) throws Exception {
//        VFS.addImplClass(SpringBootVFS.class);
//        SqlSessionFactoryBean bean = new SqlSessionFactoryBean();
//        bean.setDataSource(dataSource);
//        bean.setConfigLocation((new DefaultResourceLoader().getResource("mybatis.xml")));
//        Resource[] offlineEngineResource = new PathMatchingResourcePatternResolver().getResources("classpath*:mapper/offlineenginewebdao/*.xml");
//        Resource[] resources = ArrayUtils.addAll(offlineEngineResource);
//        bean.setMapperLocations(resources);
//        return bean.getObject();
//    }
//
//    @Bean(name = "offlineEngineSqlSessionTemplate")
//    @Primary
//    public SqlSessionTemplate articleServiceSqlSessionTemplate(@Qualifier("offlineEngineWebSqlSessionFactory") SqlSessionFactory sqlSessionFactory) {
//        return new SqlSessionTemplate(sqlSessionFactory);
//    }
//}