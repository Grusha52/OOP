package ru.nsu.chernikov;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;

public record PizzeriaConfig(int countOfBakers, int countOfCouriers, int workTime, int maxOrders) {

    public static PizzeriaConfig loadConfig(String filename) {
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            return objectMapper.readValue(new File(filename), PizzeriaConfig.class);
        } catch (IOException e) {
            throw new RuntimeException("Ошибка загрузки конфигурации", e);
        }
    }
}
