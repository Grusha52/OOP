package ru.nsu.chernikov;

public class Edge<T extends Number> {
    private T value;

    public Edge(T name){
        this.value = name;
    }

    public T getValue() {
        return value;
    }

    @Override
    public String toString() {
        return value.toString();
    }
}
