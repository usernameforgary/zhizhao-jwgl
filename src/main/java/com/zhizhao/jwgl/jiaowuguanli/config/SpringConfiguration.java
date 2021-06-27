package com.zhizhao.jwgl.jiaowuguanli.config;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.module.SimpleModule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

@Configuration
public class SpringConfiguration {
    @Autowired
    public ObjectMapper objectMapper(ObjectMapper objectMapper) {
        SimpleModule simpleModule = new SimpleModule();
        // 防止(js的Number不能完全覆盖java中Long的范围, 会精度丢失, 如理雪花id)
        simpleModule.addSerializer(Long.class, new JsonSerializer<Long>() {
            @Override
            public void serialize(Long longValue, JsonGenerator jsonGenerator, SerializerProvider serializerProvider) throws IOException {
                jsonGenerator.writeString(String.valueOf(longValue));
            }
        });

        objectMapper.registerModule(simpleModule);
        return objectMapper;
    }
}
