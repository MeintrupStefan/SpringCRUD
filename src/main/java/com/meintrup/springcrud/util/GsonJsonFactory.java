package com.meintrup.springcrud.util;

import com.google.gson.Gson;

import java.util.Map;

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


    public static void main(String[] args) {
        JsonFactory jsonFactory = new GsonJsonFactory();
        System.out.println(
                jsonFactory.toJson(
                        Map.of(
                                "jwt",  "test"
                        )

                )
        );
    }
}
