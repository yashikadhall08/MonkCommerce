package com.example.demo.utils;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;

@Slf4j
public class MapperUtil {
    private static ObjectMapper mapper = createMapper();

   private static ObjectMapper createMapper() {
        mapper = new ObjectMapper();
        return mapper;
   }

   public static String toJson(Object obj) {
       try {
           return mapper.writeValueAsString(obj);
       } catch (IOException e) {
           log.error(e.getMessage(),e);
           return null;
       }
   }

   public static <T> T fromJson(String json, Class<T> clazz) {
       try {
           return mapper.readValue(json,clazz);
       } catch (IOException e) {
           log.error(e.getMessage(), e);
           return null;
       }
   }
 }
