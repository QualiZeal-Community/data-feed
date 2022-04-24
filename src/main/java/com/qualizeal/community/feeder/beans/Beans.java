package com.qualizeal.community.feeder.beans;

import com.github.javafaker.Faker;

import java.util.Objects;

public class Beans {
    private static Faker faker;

    private Beans() {
        //utility class
    }

    public static Faker getFakerInstance() {
        if (Objects.isNull(faker)) {
            faker = new Faker();
        }
        return faker;
    }
}
