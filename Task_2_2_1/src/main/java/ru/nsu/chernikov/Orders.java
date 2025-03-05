package ru.nsu.chernikov;

import java.util.ArrayList;

public class Orders {
    private final ArrayList<Integer> queue;

    public Orders () {
        this.queue = new ArrayList<>();
    }

    public synchronized void toOrders(int count) throws InterruptedException {
        queue.addFirst(count);
        this.notify();
    }

    public synchronized Integer fromOrders() throws InterruptedException {
        while(queue.isEmpty()) {
            this.wait();
        }
        return this.queue.getLast();
    }
}
