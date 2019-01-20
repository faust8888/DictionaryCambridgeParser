package com.faust8888.cambridge.cqrs.command.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;

@Configuration
@RefreshScope
public class DataBaseConnectionConfig {

    @Value("${cambridge.sqrs.command.database.driverClass:com.mysql.jdbc.Driver}")
    private String driverClass;

    @Value("${cambridge.sqrs.command.database.url:jdbc:mysql://localhost:3306/cambridge?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC}")
    private String url;

    @Value("${cambridge.sqrs.command.database.username:root}")
    private String username;

    @Value("${cambridge.sqrs.command.database.password:ilya130288}")
    private String password;

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
}
