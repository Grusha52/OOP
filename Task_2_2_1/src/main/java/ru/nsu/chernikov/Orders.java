package ru.nsu.chernikov;

import java.util.LinkedList;

public class Orders {
    private final LinkedList<Order> queue;
    private Integer idCount = 0;

    public Orders () {
        this.queue = new LinkedList<>();
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
