package com.creditsuisse.assignment.config;

import com.creditsuisse.assignment.log.model.Log;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.Module;
import com.fasterxml.jackson.databind.module.SimpleDeserializers;
import com.fasterxml.jackson.databind.module.SimpleModule;
import com.fasterxml.jackson.databind.type.TypeFactory;
import org.junit.Test;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class LogConfigurationTest {
    @Test
    public void logEntryHierarchyModule() throws JsonMappingException {
        LogConfiguration configuration = new LogConfiguration();
        Module module = configuration.logModule();

        assertEquals("LogModule", module.getModuleName());
        assertTrue(module instanceof SimpleModule);

        SimpleModule simpleModule = (SimpleModule) module;

        Field deserializers = ReflectionUtils.findField(SimpleModule.class, "_deserializers");
        ReflectionUtils.makeAccessible(deserializers);
        SimpleDeserializers field = (SimpleDeserializers) ReflectionUtils.getField(deserializers, simpleModule);

        JavaType javaType = TypeFactory.defaultInstance().constructSimpleType(Log.class, null);
        JsonDeserializer<?> beanDeserializer = field.findBeanDeserializer(javaType, null, null);
        assertTrue(beanDeserializer instanceof LogDeserializer);
    }
}
