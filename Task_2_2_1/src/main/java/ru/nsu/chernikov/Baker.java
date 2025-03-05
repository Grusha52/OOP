package ru.nsu.chernikov;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Baker extends Thread {
    Pizzeria pizzeria;
    int cooktime;
    int countOfOrders;

    int maxTime = 50;
    int minTime = 30;

    public Baker(Pizzeria pizzeria) {
        Random random = new Random();
        this.cooktime = random.nextInt(maxTime - minTime + 1) + minTime;
        this.pizzeria = pizzeria;
    }

    private void cooking() throws InterruptedException {
        TimeUnit.MILLISECONDS.sleep(cooktime * 10L * countOfOrders);
        System.out.println(Thread.currentThread().getName() + " Кароче пиццка сделана");
    }

    @Override
    public void run() {
        try {
            while (!Thread.currentThread().isInterrupted()) {
                countOfOrders = pizzeria.orders.fromOrders();
                this.cooking();
                pizzeria.storage.toStorage(countOfOrders);
            }
        } catch (InterruptedException e) {
            System.out.println(Thread.currentThread().getName() + " я увольняюсь");
            Thread.currentThread().interrupt();
        }
    }
}