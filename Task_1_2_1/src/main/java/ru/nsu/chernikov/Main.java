package ru.nsu.chernikov;
import java.io.FileNotFoundException;

public class Main {
    public static void main(String[] args) {
        // Создаем граф
        MatrixInc<String, Integer> graph = new MatrixInc<>();

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
        for (Vertex<String> vertex : graph.vertices) {
            System.out.println(vertex);
        }

        System.out.println("\nРёбра графа:");
        for (Edge<Integer> edge : graph.edges) {
            System.out.println(edge);
        }
    }
    }
