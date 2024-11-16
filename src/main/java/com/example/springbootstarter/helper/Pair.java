package com.example.springbootstarter.helper;

import java.util.Map;

public class Pair implements Map.Entry<String, Object>
{
    private final String key;
    private Object value;

    private Pair(String k) {
        key = k;
    }

    private Pair(String k, Object v) {
        this(k);
        setValue(v);
    }

    @Override
    public String getKey() {
        return key;
    }

    @Override
    public Object getValue() {
        return value;
    }

    @Override
    public Object setValue(Object o) {
        value = o;
        return value;
    }

    public static Pair create(String k, Object v)
    {
        if (k.isBlank())
            throw new IllegalArgumentException("Cannot have blank keys");
        return new Pair(k, v);
    }
}