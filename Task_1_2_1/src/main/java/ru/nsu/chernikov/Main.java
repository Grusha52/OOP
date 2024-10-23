package ru.nsu.chernikov;

import java.io.FileNotFoundException;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        // Создаем граф
        Matrix<String, Integer> graph = new Matrix<>();

        try {
            // Чтение графа из файла
            graph.read(new Transformer<String>() {
                @Override
                public String transform(String str) {
                    return str;
                }
            }, new Transformer<Integer>() {
                @Override
                public Integer transform(String str) {
                    return Integer.parseInt(str);
                }
            });
        } catch (FileNotFoundException e) {
            System.err.println("Файл не найден: " + e.getMessage());
        }

        // Дополнительные операции с графом
        System.out.println("Вершины графа:");
        for (Vertex<String> vertex : graph.getVertices()) {
            System.out.println(vertex);
        }

        System.out.println("\nРёбра графа:");
        for (Edge<Integer> edge : graph.getEdges()) {
            System.out.println(edge);
        }

        List<Vertex<String>> result = Toposort.topologicalSortWithColoring(graph);
        System.out.println(result);
    }
}
