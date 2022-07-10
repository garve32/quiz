package com.ict.quiz.config.datasource;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.type.JdbcType;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;

import javax.sql.DataSource;

@Configuration
public class MysqlDs {

    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.hikari")
    public HikariConfig myConfig() {
        return new HikariConfig();
    }

    @Bean
    public DataSource myDs() {
        return new HikariDataSource(myConfig());
    }

    @Bean
    public SqlSessionFactory mySf(@Autowired @Qualifier("myDs") DataSource dataSource) throws Exception {
        SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
        factoryBean.setDataSource(myDs());
        PathMatchingResourcePatternResolver patternResolver = new PathMatchingResourcePatternResolver();
        factoryBean.setMapperLocations(patternResolver.getResources("/mapper/**/**.xml"));
        //factoryBean.setTypeAliasesPackage("com.ict.quiz.dto");
        factoryBean.setTypeAliasesPackage("com.ict.quiz.domain");

        SqlSessionFactory sqlSessionFactory = factoryBean.getObject();
        sqlSessionFactory.getConfiguration().setLazyLoadingEnabled(true);
        sqlSessionFactory.getConfiguration().setJdbcTypeForNull(JdbcType.NULL);

        return sqlSessionFactory;
    }
}
