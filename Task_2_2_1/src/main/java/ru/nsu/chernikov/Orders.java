package ru.nsu.chernikov;

import java.util.LinkedList;

/**
 * Manages the queue of pizza orders.
 */
public class Orders implements IQueue {
    private final LinkedList<Order> queue;
    private volatile boolean isClosed = false;

    /**
     * Creates an empty order queue.
     */
    public Orders() {
        this.queue = new LinkedList<>();
    }

    /**
     * Adds a new order to the queue.
     *
     * @throws InterruptedException if the thread is interrupted
     */

    @Override
    public synchronized void to(Order order) throws InterruptedException {
        ;
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

    @Override
    public synchronized Order from() throws InterruptedException {
        while (queue.isEmpty()) {
            this.wait();
            if (isClosed) {
                return null;
            }
        }
        return this.queue.removeLast();
    }

    @Override
    public synchronized void close() throws InterruptedException {
        isClosed = true;
        this.notifyAll();
    }

    @Override
    public synchronized boolean isClosed() {
        return isClosed;
    }
}