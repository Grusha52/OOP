package ru.nsu.chernikov;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;

public interface Graph<T,F extends Number> {

    void addVertice(Vertex<T> vertice);

    void addEdge(Vertex<T> start, Vertex<T> end, Edge<F> edge);

    void delEdge(Vertex<T> start, Vertex<T> end, Edge<F> edge) throws IllegalArgumentException;

    HashMap<Vertex<T>, Edge<F>> getAdj(Vertex<T> vertice);

    void delVertice(Vertex<T> vertice) throws IllegalArgumentException;

    void read(Transformer<T> vertexTransformer, Transformer<F> edgeTransformer) throws FileNotFoundException;

    ArrayList<Vertex<T>> getVertices();

    ArrayList<Edge<F>> getEdges();
}