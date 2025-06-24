package ru.nsu.chernikov.task_2_3_1;

import java.util.LinkedList;
import java.util.List;

public class GameState {
    private final int[] food = new int[2];
    private final List<SnakeModel> snakes = new LinkedList<>();

    public int[] getFood() {
        return food;
    }

    public void setFood(int x, int y) {
        food[0] = x;
        food[1] = y;
    }

    public List<SnakeModel> getSnakes() {
        return snakes;
    }

    public void addSnake(SnakeModel snake) {
        snakes.add(snake);
    }

    public void clearSnakes() {
        snakes.clear();
    }
}