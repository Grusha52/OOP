package ru.nsu.chernikov;

public interface IQueue {
    void to(Order order) throws InterruptedException;

    Order from() throws InterruptedException;

    void close() throws InterruptedException;

    boolean isClosed();
}
