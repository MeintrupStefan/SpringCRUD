package com.meintrup.springcrud.util;

import java.util.Map;

/**
 * An interface exposing json conversion functions.
 */
public interface JsonFactory {
    /**
     * Converts an Object to JSON String representation.
     *
     * @param obj The Object that will be parsed to JSON String
     * @return The JSON String of obj
     */
    String toJson(Object obj);

}
