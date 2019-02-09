package com.faust8888.cambridge.cqrs.command.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;

@Configuration
@RefreshScope
public class ConnectionConfig {

    @Value("${cambridge.sqrs.command.database.driverClass:com.mysql.jdbc.Driver}")
    private String driverClass;

    @Value("${cambridge.sqrs.command.database.url:jdbc:mysql://localhost:3306/cambridge?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC}")
    private String url;

    @Value("${cambridge.sqrs.command.database.username:root}")
    private String username;

    @Value("${cambridge.sqrs.command.database.password:ilya130288}")
    private String password;

    @Value("${cambridge.sqrs.query.elasticsearch.cluster.name:elasticsearch}")
    private String elasticSearchClusterName;

    @Value("${cambridge.sqrs.query.elasticsearch.cluster.ip:127.0.0.1}")
    private String elasticSearchClusterIpAddress;

    @Value("${cambridge.sqrs.query.elasticsearch.cluster.port:9300}")
    private String elasticSearchClusterPort;

    public String getDriverClass() {
        return driverClass;
    }

    public void setDriverClass(String driverClass) {
        this.driverClass = driverClass;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getElasticSearchClusterName() {
        return elasticSearchClusterName;
    }

    public void setElasticSearchClusterName(String elasticSearchClusterName) {
        this.elasticSearchClusterName = elasticSearchClusterName;
    }

    public Integer getElasticSearchClusterPort() {
        return Integer.valueOf(elasticSearchClusterPort);
    }

    public void setElasticSearchClusterPort(String elasticSearchClusterPort) {
        this.elasticSearchClusterPort = elasticSearchClusterPort;
    }

    public String getElasticSearchClusterIpAddress() {
        return elasticSearchClusterIpAddress;
    }

    public void setElasticSearchClusterIpAddress(String elasticSearchClusterIpAddress) {
        this.elasticSearchClusterIpAddress = elasticSearchClusterIpAddress;
    }
}
