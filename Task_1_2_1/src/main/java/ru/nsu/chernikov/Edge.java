package ru.nsu.chernikov;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Edge<?> edge)) return false;
        return Objects.equals(value, edge.value);
    }
}
