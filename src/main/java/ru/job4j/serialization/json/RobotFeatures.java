package ru.job4j.serialization.json;

public class RobotFeatures {
    private String material;
    private int strengthLevel;

    public RobotFeatures(String material, int strengthLevel) {
        this.material = material;
        this.strengthLevel = strengthLevel;
    }

    public String getMaterial() {
        return material;
    }

    public int getStrengthLevel() {
        return strengthLevel;
    }

    @Override
    public String toString() {
        return "RobotFeatures{"
                + "material='" + material + '\''
                + ", strengthLevel=" + strengthLevel
                + '}';
    }
}
