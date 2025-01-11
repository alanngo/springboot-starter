package com.example.springbootstarter.helper;

import java.util.ArrayList;
import java.util.Arrays;

public class JsonArray extends ArrayList<Object> {

    private JsonArray() {
        super();
    }

    /**
     * creates json array with prepopulated values
     * @param args varargs to insert
     * @return populated Json Array
     */
    public static JsonArray create(Object... args) {
        JsonArray ret = new JsonArray();
        ret.addAll(Arrays.asList(args));
        return ret;
    }

}
