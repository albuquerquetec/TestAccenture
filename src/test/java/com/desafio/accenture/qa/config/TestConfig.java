package com.desafio.accenture.qa.config;

public final class TestConfig {

    public static final String UI_BASE_URL = "https://demoqa.com/";
    public static final String API_BASE_URI = "https://demoqa.com";
    public static final int DEFAULT_TIMEOUT_SECONDS = 15;

    private TestConfig() {
    }

    public static boolean isHeadless() {
        return Boolean.parseBoolean(System.getProperty("headless", "false"));
    }
}
