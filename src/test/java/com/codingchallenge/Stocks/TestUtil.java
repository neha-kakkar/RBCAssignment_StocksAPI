package com.codingchallenge.Stocks;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.http.MediaType;

import java.io.IOException;
import java.nio.charset.Charset;

public class TestUtil {
    public static final MediaType APPLICATION_JSON_UTF8 = new MediaType(MediaType.APPLICATION_JSON.getType(), MediaType.APPLICATION_JSON.getSubtype(), Charset.forName("utf8"));
    public static String objectToJson(StockRecord obj) {
        // Creating Object of ObjectMapper define in Jakson Api
        ObjectMapper Obj = new ObjectMapper();
        String jsonStr = null;
        try {
            // get StockRecord object as a json string
            jsonStr = Obj.writeValueAsString(obj);
            // Displaying JSON String
            System.out.println(jsonStr);

        } catch (IOException e) {
            e.printStackTrace();
        }
        return jsonStr;
    }
}