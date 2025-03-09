package ru.nsu.chernikov;

import java.util.ArrayList;

public class Storage {

    private final ArrayList<Order> queue;

    public Storage () {
        this.queue = new ArrayList<>();
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
