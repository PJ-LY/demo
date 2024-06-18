package com.jasonpeng.demo.service;

public interface RedisService {
    void save(String key, String value);

    Object get(String key);

    void delete(String key);
}
