package ru.nsu.chernikov;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Courier extends Thread {
    Pizzeria pizzeria;
    int countOfPizza;
    int deliveryTime;

    int maxTime = 50;
    int minTime = 30;

    public Courier(Pizzeria pizzeria) {
        Random random = new Random();
        this.deliveryTime = random.nextInt(maxTime - minTime + 1) + minTime;
        this.pizzeria = pizzeria;
    }

    private void delivery() throws InterruptedException {
        TimeUnit.MILLISECONDS.sleep(deliveryTime * 10L * countOfPizza);
        System.out.println(Thread.currentThread().getName() + " Кароче пиццка доставлена");
    }

    @Override
    public void run() {
        try {
            while (!Thread.currentThread().isInterrupted()) {
                countOfPizza = pizzeria.storage.fromStorage();
                this.delivery();
            }
        } catch (InterruptedException e) {
            System.out.println(Thread.currentThread().getName() + " я увольняюсь");
            Thread.currentThread().interrupt(); // Восстанавливаем флаг прерывания
        }
    }
}