package ru.nsu.chernikov.task_2_3_1;

import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class RandomWandererStrategy implements BotStrategy {
    private final Random random = new Random();

    @Override
    public SnakeModel.Direction chooseDirection(SnakeModel snake, List<SnakeModel> allSnakes) {
        int[] head = snake.getSnake().getFirst();
        SnakeModel.Direction current = snake.getDirection();

        List<SnakeModel.Direction> directions = Arrays.asList(
                SnakeModel.Direction.UP,
                SnakeModel.Direction.DOWN,
                SnakeModel.Direction.LEFT,
                SnakeModel.Direction.RIGHT
        );

        // Фильтруем направления, исключая противоположное текущему
        List<SnakeModel.Direction> validDirections = directions.stream()
                .filter(dir -> !isOpposite(current, dir))
                .filter(dir -> isSafe(snake, allSnakes, head, dir))
                .toList();

        // Если есть безопасные направления, выбираем случайное
        if (!validDirections.isEmpty()) {
            return validDirections.get(random.nextInt(validDirections.size()));
        }

        // Если безопасных направлений нет, продолжаем в текущем
        return current;
    }

    private boolean isOpposite(SnakeModel.Direction current, SnakeModel.Direction next) {
        return (current == SnakeModel.Direction.UP && next == SnakeModel.Direction.DOWN) ||
                (current == SnakeModel.Direction.DOWN && next == SnakeModel.Direction.UP) ||
                (current == SnakeModel.Direction.LEFT && next == SnakeModel.Direction.RIGHT) ||
                (current == SnakeModel.Direction.RIGHT && next == SnakeModel.Direction.LEFT);
    }

    private boolean isSafe(SnakeModel snake, List<SnakeModel> allSnakes, int[] head, SnakeModel.Direction dir) {
        int newX = head[0];
        int newY = head[1];
        switch (dir) {
            case UP -> newY--;
            case DOWN -> newY++;
            case LEFT -> newX--;
            case RIGHT -> newX++;
        }
        if (newX < 0 || newX >= SnakeModel.WIDTH || newY < 0 || newY >= SnakeModel.HEIGHT) {
            return false;
        }
        for (SnakeModel other : allSnakes) {
            for (int[] part : other.getSnake()) {
                if (newX == part[0] && newY == part[1]) {
                    return false;
                }
            }
        }
        return true;
    }
}