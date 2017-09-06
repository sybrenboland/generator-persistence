package org.configuration;

import java.util.Properties;

import javax.persistence.EntityManagerFactory;
import javax.sql.DataSource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.orm.jpa.vendor.HibernateJpaVendorAdapter;

@Configuration
@ComponentScan(basePackages = { "org.db.hibernate.dao" })
public class PersistenceConfiguration {

    @Value("${hibernate.dialect}")
    private String hibernateDialect;

    @Value("${hibernate.show_sql}")
    private String hibernateShowSQL;

    @Value("${hibernate.generate_statistics}")
    private String hibernateGenerateStatistics;

    @Value("${hibernate.hbm2ddl.auto}")
    private String hibernateHbm2ddl;

    @Bean(name = "transactionManager")
    public JpaTransactionManager jpaTransactionManager(EntityManagerFactory entityManagerFactory) {
        return new JpaTransactionManager(entityManagerFactory);
    }

    @Bean
    public EntityManagerFactory entityManagerFactory(DataSource datasource) {
        LocalContainerEntityManagerFactoryBean entityManagerFactoryBean = new LocalContainerEntityManagerFactoryBean();
        entityManagerFactoryBean.setDataSource(datasource);
        entityManagerFactoryBean.setPackagesToScan("nl.kvk.or.persistence.notaris.db.hibernate.bean");
        entityManagerFactoryBean.setJpaVendorAdapter(new HibernateJpaVendorAdapter());
        Properties jpaProperties = new Properties();
        jpaProperties.setProperty("hibernate.dialect", hibernateDialect);
        jpaProperties.setProperty("hibernate.show_sql", hibernateShowSQL);
        jpaProperties.setProperty("hibernate.generate_statistics", hibernateGenerateStatistics);
        if (StringUtils.isNotBlank(hibernateHbm2ddl)) {
            jpaProperties.setProperty("hibernate.hbm2ddl.auto", hibernateHbm2ddl);
        }
        entityManagerFactoryBean.setJpaProperties(jpaProperties);
        entityManagerFactoryBean.afterPropertiesSet();
        return entityManagerFactoryBean.getObject();
    }

}
