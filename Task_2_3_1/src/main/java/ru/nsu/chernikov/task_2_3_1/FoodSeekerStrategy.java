package ru.nsu.chernikov.task_2_3_1;

import java.util.List;

public class FoodSeekerStrategy implements BotStrategy {
    @Override
    public SnakeModel.Direction chooseDirection(SnakeModel snake, List<SnakeModel> allSnakes) {
        int[] head = snake.getSnake().getFirst();
        int[] food = snake.getFood();
        SnakeModel.Direction current = snake.getDirection();

        // Проверяем, можем ли двигаться к еде по X или Y
        if (Math.abs(head[0] - food[0]) > Math.abs(head[1] - food[1])) {
            // Движение по X предпочтительнее
            if (head[0] < food[0] && current != SnakeModel.Direction.LEFT && isSafe(snake, allSnakes, head[0] + 1, head[1])) {
                return SnakeModel.Direction.RIGHT;
            } else if (head[0] > food[0] && current != SnakeModel.Direction.RIGHT && isSafe(snake, allSnakes, head[0] - 1, head[1])) {
                return SnakeModel.Direction.LEFT;
            }
        } else {
            // Движение по Y предпочтительнее
            if (head[1] < food[1] && current != SnakeModel.Direction.UP && isSafe(snake, allSnakes, head[0], head[1] + 1)) {
                return SnakeModel.Direction.DOWN;
            } else if (head[1] > food[1] && current != SnakeModel.Direction.DOWN && isSafe(snake, allSnakes, head[0], head[1] - 1)) {
                return SnakeModel.Direction.UP;
            }
        }

        // Если движение к еде небезопасно, продолжаем в текущем направлении или выбираем безопасное
        return findSafeDirection(snake, allSnakes, current);
    }

    private boolean isSafe(SnakeModel snake, List<SnakeModel> allSnakes, int x, int y) {
        if (x < 0 || x >= SnakeModel.WIDTH || y < 0 || y >= SnakeModel.HEIGHT) {
            return false;
        }
        for (SnakeModel other : allSnakes) {
            for (int[] part : other.getSnake()) {
                if (x == part[0] && y == part[1]) {
                    return false;
                }
            }
        }
        return true;
    }

    private SnakeModel.Direction findSafeDirection(SnakeModel snake, List<SnakeModel> allSnakes, SnakeModel.Direction current) {
        int[] head = snake.getSnake().getFirst();
        if (current == SnakeModel.Direction.UP || current == SnakeModel.Direction.DOWN) {
            if (isSafe(snake, allSnakes, head[0] - 1, head[1]) && current != SnakeModel.Direction.RIGHT) {
                return SnakeModel.Direction.LEFT;
            } else if (isSafe(snake, allSnakes, head[0] + 1, head[1]) && current != SnakeModel.Direction.LEFT) {
                return SnakeModel.Direction.RIGHT;
            }
        } else {
            if (isSafe(snake, allSnakes, head[0], head[1] - 1) && current != SnakeModel.Direction.DOWN) {
                return SnakeModel.Direction.UP;
            } else if (isSafe(snake, allSnakes, head[0], head[1] + 1) && current != SnakeModel.Direction.UP) {
                return SnakeModel.Direction.DOWN;
            }
        }
        return current;
    }
}