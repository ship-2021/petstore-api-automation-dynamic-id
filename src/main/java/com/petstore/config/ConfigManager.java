package com.petstore.config;

public class ConfigManager {
    private static final String BASE_URL = "https://petstore.swagger.io/v2";
    private static final String API_KEY = "special-key";

    public static String getBaseUrl() {
        return BASE_URL;
    }

    public static String getApiKey() {
        return API_KEY;
    }
}
