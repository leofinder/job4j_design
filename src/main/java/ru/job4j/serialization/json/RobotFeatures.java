package ru.job4j.serialization.json;

public class RobotFeatures {
    private String material;
    private int strengthLevel;

    public RobotFeatures(String material, int strengthLevel) {
        this.material = material;
        this.strengthLevel = strengthLevel;
    }

    @Override
    public String toString() {
        return "RobotFeatures{"
                + "material='" + material + '\''
                + ", strengthLevel=" + strengthLevel
                + '}';
    }
}
