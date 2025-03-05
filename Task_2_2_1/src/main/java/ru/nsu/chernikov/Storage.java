package ru.nsu.chernikov;

import java.util.ArrayList;

public class Storage {

    private final ArrayList<Integer> queue;

    public Storage () {
        this.queue = new ArrayList<>();
    }

    synchronized void toStorage(int count) throws InterruptedException {
        queue.addFirst(count);
        this.notify();
    }

    synchronized Integer fromStorage() throws InterruptedException {
        while(queue.isEmpty()) {
            this.wait();
        }
        return this.queue.getLast();
    }
}
