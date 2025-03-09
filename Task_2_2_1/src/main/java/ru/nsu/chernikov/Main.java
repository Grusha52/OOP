package ru.nsu.chernikov;

public class Main {
    public static void main(String[] args) {
        JsonCreator.main(new String[]{});
        Pizzeria pizzeria = new Pizzeria(PizzeriaConfig.loadConfig("config.json"));
        try {
            pizzeria.workingProcess();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
