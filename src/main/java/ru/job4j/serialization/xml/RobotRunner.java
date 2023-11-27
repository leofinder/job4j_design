package ru.job4j.serialization.xml;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.IOException;
import java.io.StringReader;
import java.io.StringWriter;

public class RobotRunner {
    public static void main(String[] args) throws JAXBException, IOException {
        Robot robot = new Robot(true, 75.5, "XJ-9",
                new RobotFeatures("Titanium", 10), "Self-Repair", "Navigation", "Communication");
        JAXBContext context = JAXBContext.newInstance(Robot.class);
        Marshaller marshaller = context.createMarshaller();
        marshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
        String xml;
        try (StringWriter writer = new StringWriter()) {
            marshaller.marshal(robot, writer);
            xml = writer.getBuffer().toString();
            System.out.println(xml);
        }
        Unmarshaller unmarshaller = context.createUnmarshaller();
        try (StringReader reader = new StringReader(xml)) {
            Robot result = (Robot) unmarshaller.unmarshal(reader);
            System.out.println(result);
        }
    }
}
