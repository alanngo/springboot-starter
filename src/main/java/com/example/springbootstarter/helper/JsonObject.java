package com.example.springbootstarter.helper;

import java.util.HashMap;

public class JsonObject extends HashMap<String, Object> {
    /**
     * default constructor
     */
    public JsonObject() {
        super();
    }

    /**
     * builds dto from different pairs
     *
     * @param args kvp arguments to insert
     */
    public JsonObject(Pair... args) {
        this();
        for (Pair entry : args)
            super.put(entry.getKey(), entry.getValue());
    }
}
