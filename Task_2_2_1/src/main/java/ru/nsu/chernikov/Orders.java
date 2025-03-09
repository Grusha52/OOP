package ru.nsu.chernikov;

import java.util.ArrayList;

public class Orders {
    private final ArrayList<Order> queue;
    private Integer idCount = 0;

    public Orders () {
        this.queue = new ArrayList<>();
        idCount = 0;
    }

    public synchronized void toOrders(int count) throws InterruptedException {
        idCount++;
        Order order = new Order(idCount, count);
        queue.addFirst(order);
        order.waiting();
        this.notify();
    }

    public synchronized Order fromOrders() throws InterruptedException {
        while(queue.isEmpty()) {
            this.wait();
        }
        return this.queue.removeLast();
    }
}
