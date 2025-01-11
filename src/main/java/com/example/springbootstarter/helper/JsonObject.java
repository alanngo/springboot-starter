package com.example.springbootstarter.helper;

import java.util.HashMap;

public class JsonObject extends HashMap<String, Object> {

    private JsonObject() {
        super();
    }

    /**
     * builds json object from arguments
     * @param args kvp arguments to insert
     * @return constructed json object
     */
    public static JsonObject create(Pair... args) {
        JsonObject ret = new JsonObject();
        for (Pair entry : args)
            ret.put(entry.getKey(), entry.getValue());
        return ret;
    }

    public static JsonObject create(String key, Object value) {
        JsonObject ret = new JsonObject();
        ret.put(key, value);
        return ret;
    }


}
