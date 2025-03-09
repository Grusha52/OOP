package ru.nsu.chernikov;

import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * Baker that prepares pizzas and moves orders to storage.
 */
public class Baker extends Thread {
    private final Pizzeria pizzeria;
    private final int cooktime;

    /**
     * Creates a baker with a random cooking time.
     *
     * @param pizzeria the pizzeria where the baker works
     */
    public Baker(Pizzeria pizzeria) {
        Random random = new Random();
        int maxTime = 50;
        int minTime = 30;
        this.cooktime = random.nextInt(maxTime - minTime + 1) + minTime;
        this.pizzeria = pizzeria;
    }

    /**
     * Simulates pizza cooking time.
     *
     * @param order the order being prepared
     * @throws InterruptedException if the thread is interrupted
     */
    private void cooking(Order order) throws InterruptedException {
        TimeUnit.MILLISECONDS.sleep(cooktime * 10L * order.getCountofPizzas());
        System.out.println(Thread.currentThread().getName() + " Pizza is ready");
    }

    /**
     * Runs the baker's process.
     */
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
            System.out.println(Thread.currentThread().getName() + " I'm quitting");
            Thread.currentThread().interrupt();
        }
    }
}