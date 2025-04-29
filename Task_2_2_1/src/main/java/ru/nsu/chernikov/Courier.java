package ru.nsu.chernikov;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * Courier that delivers pizzas from storage to customers.
 */
public class Courier extends Thread {
    private final Storage storage;
    private final int deliveryTime;

    /**
     * Creates a courier with a random delivery time.
     *
     * @param storage the storage where orders go
     */
    public Courier(Storage storage) {
        Random random = new Random();
        int maxTime = 50;
        int minTime = 30;
        this.deliveryTime = random.nextInt(maxTime - minTime + 1) + minTime;
        this.storage = storage;
    }

    /**
     * Simulates order delivery time.
     *
     * @param order being delivered
     * @throws InterruptedException if the thread is interrupted
     */
    private void delivery(Order order) throws InterruptedException {
        TimeUnit.MILLISECONDS.sleep(deliveryTime * 10L);
        System.out.println(Thread.currentThread().getName() + " Pizza delivered");
    }

    /**
     * Runs the courier's process.
     */
    @Override
    public void run() {
        try {
            while (!Thread.currentThread().isInterrupted() && !storage.isClosed()) {
                Order order = storage.from();
                if (order == null) {
                    return;
                }
                System.out.println(Thread.currentThread().getName() + " Pizza delivered");
                order.delivering();
                this.delivery(order);
                order.delivered();
            }
        } catch (InterruptedException e) {
            System.out.println(Thread.currentThread().getName() + " I'm quitting");
            Thread.currentThread().interrupt();
        }
        System.out.println(Thread.currentThread().getName() + " I'm done");
    }
}