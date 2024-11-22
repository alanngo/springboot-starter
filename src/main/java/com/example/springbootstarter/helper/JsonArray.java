package com.example.springbootstarter.helper;

import java.util.ArrayList;
import java.util.Arrays;

public class JsonArray extends ArrayList<Object> {

    /**
     * default constructor
     */
    public JsonArray() {
        super();
    }

    /**
     * Varargs constructor
     *
     * @param args varargs to insert
     */
    public JsonArray(Object... args) {
        this();
        addAll(Arrays.asList(args));
    }
}
