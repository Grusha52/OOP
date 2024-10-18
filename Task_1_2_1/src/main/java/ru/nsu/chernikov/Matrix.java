package ru.nsu.chernikov;

import java.util.ArrayList;
import java.util.HashMap;

public class Matrix<T,F extends Number> implements Graph<T,F> {

    private ArrayList<ArrayList<Edge<F>>> matrix;
    private ArrayList<Vertex<T>> vertices;

    public Matrix()

    {
        this.matrix = new ArrayList<>();
        this.vertices = new ArrayList<>();
    }

    @Override
    public void addVertice(Vertex<T> vertice) {

        if (vertices.contains(vertice)) {
            throw new IllegalArgumentException("Vertex already exists");
        }

        vertices.add(vertice);
        int size = matrix.size();

        for (ArrayList<Edge<F>> rowAdd : matrix) {
            rowAdd.add(null);
        }

        ArrayList<Edge<F>> row = new ArrayList<>();
        for (int i = 0; i < size + 1; i++) {
            row.add(null);
        }

        matrix.add(row);

    }

    @Override
    public void delVertice(Vertex<T> vertice) throws IllegalArgumentException {
        int index = vertices.indexOf(vertice);

        if (index == -1) {
            throw new IllegalArgumentException();
        }

        if (index < matrix.size()) {
            matrix.remove(index);
        } else {
            throw new IllegalArgumentException();
        }
        for (ArrayList<Edge<F>> row : matrix) {
            row.remove(index);
        }

    }

    @Override
    public void addEdge(Vertex<T> start, Vertex<T> end, Edge<F> edge) throws IllegalArgumentException {
        int startIndx = vertices.indexOf(start);
        int endIndx = vertices.indexOf(end);
        if (startIndx < matrix.size() && endIndx < matrix.size()) {
            matrix.get(startIndx).set(endIndx, edge);
            matrix.get(endIndx).set(startIndx, edge);
        } else {
            System.out.println("One of vertices does not exist");
            throw new IllegalArgumentException();
        }
    }

    @Override
    public void delEdge(Vertex<T> start, Vertex<T> end, Edge<F> edge) throws IllegalArgumentException {
        int startIndx = vertices.indexOf(start);
        int endIndx = vertices.indexOf(end);
        if (startIndx < matrix.size() && endIndx < matrix.size()) {
            matrix.get(startIndx).set(endIndx, null);
            matrix.get(endIndx).set(startIndx, null);
        } else {
            System.out.println("One of vertices does not exist");
            throw new IllegalArgumentException();
        }
    }

    @Override
    public HashMap<Vertex<T>, Edge<F>> getAdj(Vertex<T> vertice) {
        int index = vertices.indexOf(vertice);

        if (index == -1) {
            throw new IllegalArgumentException("Vertex not found");
        }

        HashMap<Vertex<T>, Edge<F>> adj = new HashMap<>();
        for (int i = 0; i < matrix.size(); i++) {
            if (matrix.get(index).get(i) != null) {
                adj.put(vertices.get(i), matrix.get(index).get(i));
            }
        }
        return adj;
    }

    @Override
    public void read() {

    }

    @Override
    public String toString() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("   ");
        for (Vertex<T> vertex : vertices){
            stringBuilder.append(vertex.toString()).append(" ");
        }
        stringBuilder.append("\n");
        for (int i = 0; i < matrix.size(); i++){
            stringBuilder.append(vertices.get(i).toString()).append(": ");
            for (int j = 0; j < matrix.size(); j++) {
                stringBuilder.append(matrix.get(i).get(j)).append(" ");
            }
            stringBuilder.append("\n");
        }
        return stringBuilder.toString();
    }
}
