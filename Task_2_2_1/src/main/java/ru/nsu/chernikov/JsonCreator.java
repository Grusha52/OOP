package ru.nsu.chernikov;

import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;


public class JsonCreator {
    public static void main(String[] args) {
        PizzeriaConfig config = new PizzeriaConfig(100, 100, 15, 1000);
        ObjectMapper objectMapper = new ObjectMapper();
        try {
            objectMapper.writeValue(new File("config.json"), config);
            System.out.println("JSON ready");
        } catch (IOException e) {
            System.out.println("ай яй ошибка" + e.getMessage());
        }

    }
}
