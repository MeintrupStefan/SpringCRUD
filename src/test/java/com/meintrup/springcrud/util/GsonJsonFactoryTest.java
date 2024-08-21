package com.meintrup.springcrud.util;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * Unit test to test if gson works as expected.
 */
class GsonJsonFactoryTest {
    ArrayList<JsonEncTestValue> testValues = new ArrayList<>();

    @Test
    void toJson() {
        JsonFactory jFact = new GsonJsonFactory();

        addArrayListTests();

        for (JsonEncTestValue jtv : testValues) {
            assertEquals(jtv.jsonEncoding, jFact.toJson(jtv.input));
        }
    }

    private void addArrayListTests() {
        testValues.add(
                getArrayListTest(
                        new ArrayList<>(
                                List.of(
                                        "TestValue 1",
                                        "TestValue 1",
                                        "TestValue 2"
                                )
                        )
                )
        );
        testValues.add(
                getArrayListTest(
                        new ArrayList<>(List.of())
                )
        );
        testValues.add(
                getArrayListTest(null)
        );

    }

    private JsonEncTestValue getArrayListTest(ArrayList<String> testValues) {
        if (testValues == null) {
            return new JsonEncTestValue("null", testValues);
        }

        StringBuilder resBuilder = new StringBuilder();

        resBuilder.append("[");

        for (int i = 0; i < testValues.size(); i++) {
            String delimiter = "";
            if (i < testValues.size() - 1) {
                delimiter = ",";
            }
            resBuilder.append(
                    String.format(
                            "\"%s\"%s", testValues.get(i), delimiter
                    )
            );
        }

        resBuilder.append("]");

        return new JsonEncTestValue(resBuilder.toString(), testValues);
    }
}

class JsonEncTestValue {
    Object input;
    String jsonEncoding;

    public JsonEncTestValue(String jsonEncoding, Object input) {
        this.jsonEncoding = jsonEncoding;
        this.input = input;

        System.out.printf(
                "Testing %s %s\n", jsonEncoding, input
        );
    }
}