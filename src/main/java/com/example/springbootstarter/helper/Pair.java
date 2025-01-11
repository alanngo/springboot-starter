package com.example.springbootstarter.helper;

import lombok.Data;
import  java.util.Map.Entry;

@Data
public class Pair implements Entry<String, Object> {
    private final String key;
    private Object value;


    private Pair(String k, Object v) {
        key = k;
        setValue(v);
    }

    public static Pair create(String k, Object v) {
        if (k.trim().isEmpty())
            throw new IllegalArgumentException("Cannot have blank keys");
        return new Pair(k, v);
    }
}