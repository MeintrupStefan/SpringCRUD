package com.meintrup.springcrud.util;

import com.google.gson.Gson;

public class GsonJsonFactory implements JsonFactory {
    /** The JSON to String implementation*/
    private final Gson gson;

    public GsonJsonFactory() {
        gson = new Gson();
    }

    @Override
    public String toJson(Object obj) {
        return gson.toJson(obj);
    }
}
