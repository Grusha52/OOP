package ru.nsu.chernikov;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class AdjList<T,F extends Number> implements Graph<T,F>{

    private HashMap<Vertex<T>, HashMap<Vertex<T>,Edge<F>>> adjList;
    private ArrayList<Vertex<T>> vertices;
    private ArrayList<Edge<F>> edges;

    public AdjList() {
        this.adjList = new HashMap<>();
        this.vertices = new ArrayList<>();
        this.edges = new ArrayList<>();
    }

    @Override
    public void addVertice(Vertex<T> vertice) {
        vertices.add(vertice);
    }

    @Override
    public void addEdge(Vertex<T> start, Vertex<T> end, Edge<F> edge) {

        edges.add(edge);

        if (!vertices.contains(start) || !vertices.contains(end)) {
            throw new IllegalArgumentException("One of vertices does not exist");
        }
        HashMap<Vertex<T>, Edge<F>> neighbor = new HashMap<>();
        neighbor.put(end, edge);
        adjList.putIfAbsent(start, neighbor);
    }

    @Override
    public void delEdge(Vertex<T> start, Vertex<T> end, Edge<F> edge) throws IllegalArgumentException {

        if (adjList.get(start).isEmpty()){
            throw new IllegalArgumentException("Vertex haven't edges");
        }
        if (!vertices.contains(start) || !vertices.contains(end)) {
            throw new IllegalArgumentException("One of vertices does not exist");
        }

        adjList.get(start).remove(end);
        edges.remove(edge);

    }

    @Override
    public HashMap<Vertex<T>, Edge<F>> getAdj(Vertex<T> vertice) {
        if (!vertices.contains(vertice)) {
            throw new IllegalArgumentException("One of vertices does not exist");
        }
        if(adjList.get(vertice) == null){
            return new HashMap<>();
        } else {
            return new HashMap<>(adjList.get(vertice));
        }
    }

    @Override
    public void delVertice(Vertex<T> vertice) throws IllegalArgumentException {
        if (!adjList.containsKey(vertice)) {
            throw new IllegalArgumentException("Vertex does not exist");
        }

        // Удаление рёбер, которые связаны с удаленной вершиной
        for (Edge<F> edge : adjList.get(vertice).values()) {
            edges.remove(edge);
        }
        for (Vertex<T> vertex : adjList.keySet()){
            if (adjList.get(vertex).containsKey(vertice)) {
                F edge = adjList.get(vertex).get(vertice).getValue();
                edges.remove(new Edge<>(edge));
            }
            adjList.get(vertex).remove(vertice);
        }
        adjList.remove(vertice);
        vertices.remove(vertice);
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
    @Override
    public ArrayList<Vertex<T>> getVertices(){
        return vertices;
    }

    /**
     * @return
     */
    @Override
    public ArrayList<Edge<F>> getEdges() {
       return edges;
    }

}