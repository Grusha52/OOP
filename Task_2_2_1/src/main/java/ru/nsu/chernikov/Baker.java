package ru.nsu.chernikov;

import java.util.Random;
import java.util.concurrent.TimeUnit;

public class Baker extends Thread {
    Pizzeria pizzeria;
    int cooktime;

    int maxTime = 50;
    int minTime = 30;

    public Baker(Pizzeria pizzeria) {
        Random random = new Random();
        this.cooktime = random.nextInt(maxTime - minTime + 1) + minTime;
        this.pizzeria = pizzeria;
    }

    private void cooking(Order order) throws InterruptedException {
        TimeUnit.MILLISECONDS.sleep(cooktime * 10L * order.getCountofPizzas());
        System.out.println(Thread.currentThread().getName() + " Кароче пиццка сделана");
    }

    @Override
    public void run() {
        try {
            while (!Thread.currentThread().isInterrupted()) {
                Order order = pizzeria.orders.fromOrders();
                order.cooking();
                this.cooking(order);
                pizzeria.storage.toStorage(order);
                order.done();
            }
        } catch (InterruptedException e) {
            System.out.println(Thread.currentThread().getName() + " я увольняюсь");
            Thread.currentThread().interrupt();
        }
    }
}