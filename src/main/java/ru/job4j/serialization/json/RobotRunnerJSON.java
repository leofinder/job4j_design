package ru.job4j.serialization.json;

import org.json.JSONArray;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class RobotRunnerJSON {
    public static void main(String[] args) {
        JSONObject jsonFeatures = new JSONObject("{\"material\":\"Dark Steel\",\"strengthLevel\":8}");

        List<String> list = new ArrayList<>();
        list.add("Hacking");
        list.add("Stealth Mode");
        list.add("Advanced Combat");
        JSONArray jsonPrograms = new JSONArray(list);

        final Robot robot = new Robot(true, 75.5, "XJ-9",
                new RobotFeatures("Titanium", 10), new String[]{"Self-Repair", "Navigation", "Communication"});

        JSONObject jsonObject = new JSONObject();
        jsonObject.put("active", robot.isActive());
        jsonObject.put("batteryLevel", robot.getBatteryLevel());
        jsonObject.put("model", robot.getModel());
        jsonObject.put("features", jsonFeatures);
        jsonObject.put("installedPrograms", jsonPrograms);

        System.out.println(jsonObject.toString());

        System.out.println(new JSONObject(robot).toString());
    }
}
