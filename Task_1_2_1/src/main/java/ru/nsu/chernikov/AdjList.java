package ru.nsu.chernikov;

import java.util.ArrayList;
import java.util.HashMap;

public class AdjList<T,F extends Number> implements Graph<T,F>{

    private HashMap<Vertex<T>, HashMap<Vertex<T>,Edge<F>>> adjList;

    public AdjList() {
        adjList = new HashMap<>();
    }

    @Override
    public void addVertice(Vertex<T> vertice) {
        adjList.putIfAbsent(vertice,new HashMap<>());

    }

    @Override
    public void addEdge(Vertex<T> start, Vertex<T> end, Edge<F> edge) {
        if (!adjList.containsKey(start) || !adjList.get(start).containsKey(end)){
            throw new IllegalArgumentException("One of vertices does not exist");
        }
        adjList.putIfAbsent(start, new HashMap<>());
        adjList.putIfAbsent(end, new HashMap<>());
        adjList.get(start).put(end, edge);
    }

    @Override
    public void delEdge(Vertex<T> start, Vertex<T> end, Edge<F> edge) throws IllegalArgumentException {

        if (adjList.get(start).isEmpty()){
            throw new IllegalArgumentException("Vertice hasnt edges");
        }
        if (!adjList.containsKey(start) || !adjList.get(start).containsKey(end)){
            throw new IllegalArgumentException("One of vertices does not exist");
        }

        adjList.get(start).remove(end);

    }

    @Override
    public HashMap<Vertex<T>, Edge<F>> getAdj(Vertex<T> vertice) {
        if (!adjList.containsKey(vertice)){
            throw new IllegalArgumentException("Vertice does not exist");
        }
        return new HashMap<>(adjList.get(vertice));
    }

    @Override
    public void delVertice(Vertex<T> vertice) throws IllegalArgumentException {


    }

    @Override
    public void read() {

    }
}
