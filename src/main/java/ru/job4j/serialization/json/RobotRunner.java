package ru.job4j.serialization.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class RobotRunner {
    public static void main(String[] args) {
        final Robot robot = new Robot(true, 75.5, "XJ-9",
                new RobotFeatures("Titanium", 10), new String[]{"Self-Repair", "Navigation", "Communication"});

        final Gson gson = new GsonBuilder().create();
        System.out.println(gson.toJson(robot));

        final String robotJson =
                "{"
                        + "\"isActive\":true,"
                        + "\"batteryLevel\":100.0,"
                        + "\"model\" : \"Destroyer X1\","
                        + "\"features\":"
                        + "{"
                        + "\"material\":\"Dark Steel\","
                        + "\"strengthLevel\":20"
                        + "},"
                        + "\"installedPrograms\":"
                        + "[\"Hacking\", \"Stealth Mode\", \"Advanced Combat\"]"
                        + "}";
        final Robot villainRobot = gson.fromJson(robotJson, Robot.class);
        System.out.println(villainRobot);
    }
}
