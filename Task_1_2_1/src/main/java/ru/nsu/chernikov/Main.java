package ru.nsu.chernikov;
public class Main {
    public static void main(String[] args) {
        System.out.println("Hello");

        // Создаем граф
        MatrixInc<String,Integer> graph = new MatrixInc<>();

        // Создаем вершины и сохраняем ссылки на них
        Vertex<String> v1 = new Vertex<>("first");
        Vertex<String> v2 = new Vertex<>("second");
        Vertex<String> v3 = new Vertex<>("third");

        // Добавляем вершины в граф
        graph.addVertice(v1);
        graph.addVertice(v2);
        graph.addVertice(v3);

        // Добавляем ребро между существующими вершинами
        graph.addEdge(v1, v2, new Edge<Integer>(25));

        // Получаем и выводим соседей для первой вершины
        System.out.println("Neighbors of 'first': " + graph.getAdj(v1));
    }
}