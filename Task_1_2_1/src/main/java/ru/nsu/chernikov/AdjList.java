package ru.nsu.chernikov;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

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
