package ru.nsu.chernikov;

import java.util.LinkedList;

/**
 * Storage for finished pizza orders.
 */
public class Storage {
    private final LinkedList<Order> queue;

    /**
     * Creates an empty storage.
     */
    public Storage() {
        this.queue = new LinkedList<>();
    }

    /**
     * Adds an order to storage.
     *
     * @param order the completed order
     * @throws InterruptedException if the thread is interrupted
     */
    synchronized void toStorage(Order order) throws InterruptedException {
        queue.addFirst(order);
        this.notify();
    }

    /**
     * Gets an order from storage.
     *
     * @return the next order
     * @throws InterruptedException if the thread is interrupted
     */
    synchronized Order fromStorage() throws InterruptedException {
        while (queue.isEmpty()) {
            this.wait();
        }
        return this.queue.removeLast();
    }
}