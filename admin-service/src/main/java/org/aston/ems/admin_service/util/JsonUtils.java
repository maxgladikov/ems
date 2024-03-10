package org.aston.ems.admin_service.util;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.json.JsonMapper;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import java.util.List;

public class JsonUtils {

    public static String asJsonString(final Object obj) throws JsonProcessingException {
        return new ObjectMapper().writeValueAsString(obj);
    }

    public static <T> T jsonToObject(final String json, final Class<T> cl) throws JsonProcessingException {
        ObjectMapper mapper = JsonMapper.builder()
            .addModule(new JavaTimeModule())
            .build();
        return mapper.readValue(json, cl);
    }
    public static <T> List<T> jsonToList(String jsonString, Class<T> valueType) throws JsonProcessingException {
        ObjectMapper mapper = JsonMapper.builder()
            .addModule(new JavaTimeModule())
            .build();
        return mapper.readValue(jsonString, mapper.getTypeFactory().constructCollectionType(List.class, valueType));
    }
}
