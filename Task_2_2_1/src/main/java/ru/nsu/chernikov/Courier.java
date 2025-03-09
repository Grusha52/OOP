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

    private void delivery(Order order) throws InterruptedException {
        TimeUnit.MILLISECONDS.sleep(deliveryTime * 10L);
        System.out.println(Thread.currentThread().getName() + " Кароче пиццка доставлена");
    }

    @Override
    public void run() {
        try {
            while (!Thread.currentThread().isInterrupted()) {
                Order order = pizzeria.storage.fromStorage();
                order.delivering();
                this.delivery(order);
                order.delivered();
            }
        } catch (InterruptedException e) {
            System.out.println(Thread.currentThread().getName() + " я увольняюсь");
            Thread.currentThread().interrupt();
        }
    }
}