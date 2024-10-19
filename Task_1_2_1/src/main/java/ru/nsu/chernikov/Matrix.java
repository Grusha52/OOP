package ru.nsu.chernikov;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class Matrix<T,F extends Number> implements Graph<T,F> {

    private ArrayList<ArrayList<Edge<F>>> matrix;
    private ArrayList<Vertex<T>> vertices;
    private Transformer<T> vertexTransformer;
    private Transformer<F> weightTransformer;

    public Matrix() {
        this.matrix = new ArrayList<>();
        this.vertices = new ArrayList<>();
    }

    public Matrix(Transformer<T> vertexTransformer, Transformer<F> weightTransformer) {
        this.matrix = new ArrayList<>();
        this.vertices = new ArrayList<>();
        this.vertexTransformer = vertexTransformer;
        this.weightTransformer = weightTransformer;
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
    public void read(Transformer<T> vertexTransformer, Transformer<F> edgeTransformer) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("file.txt"));

        String curr = scanner.nextLine().trim();
        curr = curr.replace("\n", "");
        String[] cur = curr.split(" ");

        for (String i : cur) {
            Vertex<T> vertex = new Vertex<>(vertexTransformer.transform(i));
            addVertice(vertex);
        }

        while (scanner.hasNext()) {
            String line = scanner.nextLine().trim();
            if (line.isEmpty()) continue;

            String[] edgeData = line.split(" ");
            if (edgeData.length != 3) {
                throw new IllegalArgumentException("Неверный формат строки: " + line);
            }

            Vertex<T> start = new Vertex<>(vertexTransformer.transform(edgeData[0]));
            Vertex<T> end = new Vertex<>(vertexTransformer.transform(edgeData[1]));
            Edge<F> edge = new Edge<>(edgeTransformer.transform(edgeData[2]));

            addEdge(start, end, edge);
        }
    }
}
