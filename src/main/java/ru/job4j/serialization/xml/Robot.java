package ru.job4j.serialization.xml;

import javax.xml.bind.annotation.*;
import java.util.Arrays;

@XmlRootElement(name = "robot")
@XmlAccessorType(XmlAccessType.FIELD)
public class Robot {

    @XmlAttribute
    private boolean isActive;
    @XmlAttribute
    private double batteryLevel;
    private String model;
    private RobotFeatures features;

    @XmlElementWrapper(name = "installedPrograms")
    @XmlElement(name = "program")
    private String[] installedPrograms;

    public Robot() { }

    public Robot(boolean isActive, double batteryLevel, String model, RobotFeatures features, String... installedPrograms) {
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
