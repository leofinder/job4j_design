package ru.job4j.io;

import org.slf4j.LoggerFactory;
import org.slf4j.Logger;

public class UsageLog4j {
    private static final Logger LOG = LoggerFactory.getLogger(UsageLog4j.class.getName());

    public static void main(String[] args) {
        String name = "Leo Hyde";
        boolean isHuman = true;
        byte age = 37;
        float temperature = 36.6F;
        double accountBalance = 673409.67;

        short year = 2027;
        int populationOfCity = 498000;
        long distanceToMoon = 384400000L;
        char dangerLevel = 'B';

        LOG.debug("User info name: {}, human: {}, age: {}, temperature: {}, balance: {}",
                name, isHuman, age, temperature, accountBalance);
        LOG.debug("Current year: {}, population of city: {}, danger level: {}, distance to moon: {}",
                year, populationOfCity, dangerLevel, distanceToMoon);
    }
}
