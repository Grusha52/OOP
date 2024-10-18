package ru.nsu.chernikov;

public class Vertex<T> {
    private T name;

    public Vertex(T name) {
        this.name = name;
    }

    public T getName(){
        return name;
    }

    @Override
    public String toString() {
        return name.toString();
    }
}
