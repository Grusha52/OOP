package ru.nsu.chernikov;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;

/**
 * Class responsible for creating and writing the PizzeriaConfig to a JSON file.
 */
public class JsonCreator {

    /**
     * Main method to create a PizzeriaConfig object and write it to a JSON file.
     *
     * @param args command-line arguments (not used)
     */
    public static void main(String[] args) {
        PizzeriaConfig config = new PizzeriaConfig(100, 100, 5, 1000);
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            objectMapper.writeValue(new File("config.json"), config);
            System.out.println("JSON ready");
        } catch (IOException e) {
            System.out.println("ай яй ошибка" + e.getMessage());
        }
    }
}