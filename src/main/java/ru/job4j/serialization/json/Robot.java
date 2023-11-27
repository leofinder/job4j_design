package ru.job4j.serialization.json;

import java.util.Arrays;

public class Robot {
    private boolean isActive;
    private double batteryLevel;
    private String model;
    private RobotFeatures features;
    private String[] installedPrograms;

    public boolean isActive() {
        return isActive;
    }

    public double getBatteryLevel() {
        return batteryLevel;
    }

    public String getModel() {
        return model;
    }

    public RobotFeatures getFeatures() {
        return features;
    }

    public String[] getInstalledPrograms() {
        return installedPrograms;
    }

    public Robot(boolean isActive, double batteryLevel, String model, RobotFeatures features, String[] installedPrograms) {
        this.isActive = isActive;
        this.batteryLevel = batteryLevel;
        this.model = model;
        this.features = features;
        this.installedPrograms = installedPrograms;
    }

    @Override
    public String toString() {
        return "Robot{"
                + "isActive=" + isActive
                + ", batteryLevel=" + batteryLevel
                + ", model='" + model + '\''
                + ", features=" + features
                + ", installedPrograms=" + Arrays.toString(installedPrograms)
                + '}';
    }
}
