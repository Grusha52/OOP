package ru.nsu.chernikov;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Topological sorting.
 */
public class Toposort {


    // Вспомогательный метод для выполнения DFS с покраской вершин
    private static <T, F extends Number> boolean dfs(
            Vertex<T> vertex, Map<Vertex<T>, String> colors,
            Deque<Vertex<T>> stack, Graph<T, F> graph) {

        colors.put(vertex, "GRAY"); // Вершина начинает обрабатываться (становится серой)

        // Обрабатываем всех соседей вершины
        for (Vertex<T> neighbor : graph.getAdj(vertex).keySet()) {
            String neighborColor = colors.get(neighbor);
            if (neighborColor.equals("WHITE")) {
                // Если сосед белый, продолжаем DFS для него
                if (!dfs(neighbor, colors, stack, graph)) {
                    return false; // Если найдём цикл, вернём false
                }
            } else if (neighborColor.equals("GRAY")) {
                // Если сосед серый — это цикл
                return false; // Цикл найден
            }
        }

        colors.put(vertex, "BLACK"); // Вершина полностью обработана (становится чёрной)
        stack.push(vertex); // Добавляем вершину в стек по завершению её обработки
        return true;
    }

    // Основной метод для топологической сортировки через DFS с покраской вершин
    public static <T, F extends Number> List<Vertex<T>> topologicalSortWithColoring(
            Graph<T, F> graph) throws IllegalArgumentException{
        // Карта для хранения цветов вершин (по умолчанию все белые)
        Map<Vertex<T>, String> colors = new HashMap<>();
        for (Vertex<T> vertex : graph.getVertices()) {
            colors.put(vertex, "WHITE");
        }

        Deque<Vertex<T>> stack = new ArrayDeque<>(); // Стек для хранения результата топосортировки

        // Запуск DFS для каждой непосещённой вершины (белой)
        for (Vertex<T> vertex : graph.getVertices()) {
            if (colors.get(vertex).equals("WHITE")) {
                if (!dfs(vertex, colors, stack, graph)) {
                    throw new IllegalArgumentException(
                            "Graph has a cycle, topological sort not possible"
                    );
                }
            }
        }

        // Список для хранения топологического порядка
        List<Vertex<T>> topologicalOrder = new ArrayList<>();

        // Извлекаем вершины из стека
        while (!stack.isEmpty()) {
            topologicalOrder.add(stack.pop());
        }

        return topologicalOrder;
    }
}
