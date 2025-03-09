package ru.nsu.chernikov;

import com.fasterxml.jackson.databind.ObjectMapper;
import java.io.File;
import java.io.IOException;

/**
 * A record representing the configuration of a pizzeria.
 */
public record PizzeriaConfig(int countOfBakers, int countOfCouriers, int workTime, int maxOrders) {

    /**
     * Loads the pizzeria configuration from a JSON.
     *
     * @param filename the name of the configuration file
     * @return the PizzeriaConfig object
     * @throws RuntimeException if there is an error loading the configuration
     */
    public static PizzeriaConfig loadConfig(String filename) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(new File(filename), PizzeriaConfig.class);
        } catch (IOException e) {
            throw new RuntimeException("Ошибка загрузки конфигурации", e);
        }
    }
}