package com.coding.assignment.bankservice.configurations;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;


@Getter
@Setter
@RefreshScope
@Configuration
@ConfigurationProperties(prefix = "log")
public class LoggingConfiguration {
        //if this property is true, the logging filter will show the request body
        private boolean request;
        //if this property is true, the logging filter will show the response body
        private boolean response;
}
