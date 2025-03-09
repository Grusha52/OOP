package ru.nsu.chernikov;

import java.util.LinkedList;

public class Storage {

    private final LinkedList<Order> queue;

    public Storage () {
        this.queue = new LinkedList<>();
    }

    synchronized void toStorage(Order order) throws InterruptedException {
        queue.addFirst(order);
        this.notify();
    }

    synchronized Order fromStorage() throws InterruptedException {
        while(queue.isEmpty()) {
            this.wait();
        }
        return this.queue.removeLast();
    }
}
