package ru.nsu.chernikov;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;


public class MainTest {
    @Test
    void mainTest() {
        Main.main(null);
    }

    @Test
    void adjListTest() {
        // Создаем граф
        AdjList<String, Integer> graph = new AdjList<>();

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

        assertEquals("[A, B, C, D]", graph.getVertices().toString());
        assertEquals("[5, 10, 7]", graph.getEdges().toString());

        List<Vertex<String>> result = Toposort.topologicalSortWithColoring(graph);
        assertEquals("[A, B, C, D]", result.toString());

        Vertex<String> vertex = new Vertex<>("C");
        graph.delVertice(vertex);

        assertEquals("[A, B, D]", graph.getVertices().toString());
        assertEquals("[5]", graph.getEdges().toString());

        graph.delEdge(new Vertex<>("A"), new Vertex<>("B"), new Edge<>(5));

        assertEquals("[]", graph.getEdges().toString());
    }

    @Test
    void matrix() {
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

        assertEquals("[A, B, C, D]", graph.getVertices().toString());
        assertEquals("[5, 10, 7]", graph.getEdges().toString());

        List<Vertex<String>> result = Toposort.topologicalSortWithColoring(graph);
        assertEquals("[A, B, C, D]", result.toString());

        Vertex<String> vertex = new Vertex<>("C");
        graph.delVertice(vertex);

        assertEquals("[A, B, D]", graph.getVertices().toString());
        assertEquals("[5]", graph.getEdges().toString());

        graph.delEdge(new Vertex<>("A"), new Vertex<>("B"), new Edge<>(5));

        assertEquals("[]", graph.getEdges().toString());
    }

    @Test
    void matrixInc() {
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

        assertEquals("[A, B, C, D]", graph.getVertices().toString());
        assertEquals("[5, 10, 7]", graph.getEdges().toString());

        List<Vertex<String>> result = Toposort.topologicalSortWithColoring(graph);
        assertEquals("[A, B, C, D]", result.toString());

        Vertex<String> vertex = new Vertex<>("C");
        graph.delVertice(vertex);

        assertEquals("[A, B, D]", graph.getVertices().toString());
        assertEquals("[5]", graph.getEdges().toString());

        graph.delEdge(new Vertex<>("A"), new Vertex<>("B"), new Edge<>(5));

        assertEquals("[]", graph.getEdges().toString());
    }
}