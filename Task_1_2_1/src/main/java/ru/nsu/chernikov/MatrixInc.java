package ru.nsu.chernikov;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Objects;
import java.util.Scanner;

public class MatrixInc<T,F extends Number> implements Graph<T,F>{
    ArrayList<ArrayList<Integer>> matrix;
    ArrayList<Vertex<T>> vertices;
    ArrayList<Edge<F>> edges;

    public MatrixInc(){
        this.matrix = new ArrayList<>();
        this.vertices = new ArrayList<>();
        this.edges = new ArrayList<>();
    }


    @Override
    public void addVertice(Vertex<T> vertice) throws IllegalArgumentException {

        if (vertices.contains(vertice)) {
            throw new IllegalArgumentException("Vertex already exists");
        }
        vertices.add(vertice);

        ArrayList<Integer> vertex = new ArrayList<>();
        for (int i = 0; i < edges.size(); i++) {
            vertex.add(0);
        }
        matrix.add(vertex);
    }

    @Override
    public void addEdge(Vertex<T> start, Vertex<T> end, Edge<F> edge) {
        if (matrix.isEmpty()) {
            throw new IllegalArgumentException("Empty matrix");
        }
        if (!vertices.contains(start) || !vertices.contains(end)) {
            throw new IllegalArgumentException("One of vertices does not exist");
        }
        if (edges.contains(edge)) {
            throw new IllegalArgumentException("Edge already exists");
        }
        edges.add(edge);

        int edgeid = edges.indexOf(edge);
        int stid = vertices.indexOf(start);
        int endid = vertices.indexOf(end);

        for (ArrayList<Integer> vertex : matrix) {
            vertex.add(0);
        }

        if (Objects.equals(start,end)){
            matrix.get(stid).set(edgeid, 2);
            return;
        }

        matrix.get(stid).set(edgeid, 1);
        matrix.get(endid).add(edgeid, -1);

    }

    @Override
    public void delEdge(Vertex<T> start, Vertex<T> end, Edge<F> edge) throws IllegalArgumentException {
        if (matrix.isEmpty()){
            throw new IllegalArgumentException("Empty matrix");
        }
        if (!vertices.contains(start) || !vertices.contains(end)) {
            throw new IllegalArgumentException("One of vertices does not exist");
        }
        if (!edges.contains(edge)){
            throw new IllegalArgumentException("Edge does not exist");
        }

        int edgeid = edges.indexOf(edge);
        edges.remove(edge);

        for (ArrayList<Integer> vertex : matrix){
            vertex.remove(edgeid);
        }
    }

    @Override
    public HashMap<Vertex<T>, Edge<F>> getAdj(Vertex<T> vertice){
        HashMap<Vertex<T>, Edge<F>> adj = new HashMap<>();
        int vertex = vertices.indexOf(vertice);
        for (int j = 0; j < edges.size(); j++){
            if(matrix.get(vertex).get(j) == 1){
                for (ArrayList<Integer> row : matrix){
                    if (row.get(j) == -1){

                        adj.put(vertices.get(row.indexOf(j)), edges.get(j));

                    }
                }
            }
        }
        return adj;
    }

    @Override
    public void delVertice(Vertex<T> vertice) throws IllegalArgumentException {
        if (!vertices.contains(vertice)){
            throw new IllegalArgumentException("Vertex does not exists");
        }

        int vertex = vertices.indexOf(vertice);
        vertices.remove(vertice);

        for (int j = edges.size() - 1; j >= 0; j--){
            if (matrix.get(vertex).get(j) == 1 || matrix.get(vertex).get(j) == -1){
                edges.remove(j);
                for (ArrayList<Integer> row : matrix){
                    row.remove(j);
                }
            }
        }
        matrix.remove(vertex);

    }

    @Override
    public void read(Transformer<T> vertexTransformer, Transformer<F> edgeTransformer) throws FileNotFoundException {
        Scanner scanner = new Scanner(new File("file.txt"));

        String curr = scanner.nextLine().trim();
        curr = curr.replace("\n","");
        String[] cur = curr.split(" ");

        for (String i : cur){
            Vertex<T> vertex = new Vertex<>(vertexTransformer.transform(i));
            addVertice(vertex);
        }

        while(scanner.hasNext()) {
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
