package ru.nsu.chernikov;

import java.util.LinkedList;

/**
 * Manages the queue of pizza orders.
 */
public class Orders {
    private final LinkedList<Order> queue;
    private Integer idCount = 0;

    /**
     * Creates an empty order queue.
     */
    public Orders() {
        this.queue = new LinkedList<>();
        idCount = 0;
    }

    /**
     * Adds a new order to the queue.
     *
     * @param count the number of pizzas in the order
     * @throws InterruptedException if the thread is interrupted
     */
    public synchronized void toOrders(int count) throws InterruptedException {
        idCount++;
        Order order = new Order(idCount, count);
        queue.addFirst(order);
        order.waiting();
        this.notify();
    }

    /**
     * Gets an order from the queue.
     *
     * @return the next order
     * @throws InterruptedException if the thread is interrupted
     */
    public synchronized Order fromOrders() throws InterruptedException {
        while (queue.isEmpty()) {
            this.wait();
        }
        return this.queue.removeLast();
    }
}