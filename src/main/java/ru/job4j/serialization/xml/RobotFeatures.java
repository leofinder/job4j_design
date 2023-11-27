package ru.job4j.serialization.xml;

import javax.xml.bind.annotation.XmlAttribute;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "features")
public class RobotFeatures {

    @XmlAttribute
    private String material;

    @XmlAttribute
    private int strengthLevel;

    public RobotFeatures() { }

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
