package org.configuration;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.SingleConnectionDataSource;

@Configuration
public class DatasourceConfiguration {

    @Value("${jdbc.driverClassName}")
    private String driverClassName;

    @Value("${jdbc.url}")
    private String url;

    @Value("${jdbc.username}")
    private String username;

    @Value("${jdbc.password}")
    private String password;

    @Bean(name = "dataSource")
    public SingleConnectionDataSource singleConnectionDataSource() {

        final SingleConnectionDataSource singleConnectionDataSource = new SingleConnectionDataSource();
        singleConnectionDataSource.setDriverClassName(driverClassName);
        singleConnectionDataSource.setUrl(url);
        singleConnectionDataSource.setUsername(username);
        singleConnectionDataSource.setPassword(password);
        singleConnectionDataSource.setSuppressClose(true);
        return singleConnectionDataSource;
    }
}
