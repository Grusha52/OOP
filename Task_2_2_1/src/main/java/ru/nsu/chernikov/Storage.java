package ru.nsu.chernikov;

import java.util.LinkedList;

/**
 * Storage for finished pizza orders.
 */
public class Storage implements IQueue {
    private final LinkedList<Order> queue;
    private volatile boolean isClosed = false;
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
    @Override
    public synchronized void to(Order order) throws InterruptedException {
        queue.addFirst(order);
        this.notify();
    }

    /**
     * Gets an order from storage.
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