package ru.nsu.chernikov;

import java.util.Objects;

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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Vertex<?> vertex)) return false;
        return Objects.equals(name, vertex.name);
    }
}
