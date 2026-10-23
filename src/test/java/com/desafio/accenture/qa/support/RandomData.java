package com.desafio.accenture.qa.support;

import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

public final class RandomData {

    private static final String[] FIRST_NAMES = {"Ana", "Bruno", "Carla", "Diego", "Elena", "Fabio"};
    private static final String[] LAST_NAMES = {"Silva", "Souza", "Oliveira", "Santos", "Pereira", "Costa"};
    private static final String[] DEPARTMENTS = {"QA", "Dev", "RH", "Financeiro", "Suporte"};
    private static final String[] STATES = {"NCR", "Uttar Pradesh", "Haryana", "Rajasthan"};
    private static final String[][] CITIES = {
            {"Delhi", "Gurgaon", "Noida"},
            {"Agra", "Lucknow", "Merrut"},
            {"Karnal", "Panipat"},
            {"Jaipur", "Jaiselmer"}
    };

    private RandomData() {
    }

    public static String firstName() {
        return pick(FIRST_NAMES);
    }

    public static String lastName() {
        return pick(LAST_NAMES);
    }

    public static String email(String firstName) {
        return firstName.toLowerCase() + suffix() + ThreadLocalRandom.current().nextInt(10, 99) + "@mail.com";
    }

    public static String phone() {
        return String.format("%010d", ThreadLocalRandom.current().nextLong(1_000_000_000L, 10_000_000_000L));
    }

    public static String address() {
        return "Rua " + pick(LAST_NAMES) + ", " + ThreadLocalRandom.current().nextInt(10, 999);
    }

    public static int age() {
        return ThreadLocalRandom.current().nextInt(20, 55);
    }

    public static int salary() {
        return ThreadLocalRandom.current().nextInt(3000, 15000);
    }

    public static String department() {
        return pick(DEPARTMENTS);
    }

    public static int stateIndex() {
        return ThreadLocalRandom.current().nextInt(STATES.length);
    }

    public static String state(int index) {
        return STATES[index];
    }

    public static String city(int stateIndex) {
        String[] options = CITIES[stateIndex];
        return options[ThreadLocalRandom.current().nextInt(options.length)];
    }

    public static String uniqueUserName() {
        return "qa.accenture." + UUID.randomUUID().toString().replace("-", "").substring(0, 12);
    }

    public static String strongPassword() {
        return "Aa@" + UUID.randomUUID().toString().replace("-", "").substring(0, 8) + "1";
    }

    private static String pick(String[] values) {
        return values[ThreadLocalRandom.current().nextInt(values.length)];
    }

    public static String suffixSafe() {
        return suffix();
    }

    private static String suffix() {
        return String.valueOf(ThreadLocalRandom.current().nextInt(1000, 9999));
    }
}
