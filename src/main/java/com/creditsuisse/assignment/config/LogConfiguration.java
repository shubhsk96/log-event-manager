package com.creditsuisse.assignment.config;

import com.creditsuisse.assignment.log.model.Log;
import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Configuration class for Log
 * @author Shubham K
 */
@Configuration
public class LogConfiguration {
    /**
     * Create a module to add deserializer for Log
     * @return log module
     */
    @Bean
    public Module logModule() {
        SimpleModule module = new SimpleModule("LogModule");
        module.addDeserializer(Log.class, new LogDeserializer(Log.class));
        return module;
    }
}
