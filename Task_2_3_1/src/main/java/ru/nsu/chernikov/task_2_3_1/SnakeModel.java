package ru.nsu.chernikov.task_2_3_1;

import java.util.LinkedList;
import java.util.Objects;
import java.util.Random;
import javafx.scene.media.AudioClip;



/**
 * MODEL.
 */
public class SnakeModel {
    public static final int TILE_SIZE = 50;
    public static final int WIDTH = 15;
    public static final int HEIGHT = 15;
    AudioClip snakeEatSound = new AudioClip(
            Objects.requireNonNull(getClass().getResource("wav/collect.wav")).toString()
    );
    private final LinkedList<int[]> snake = new LinkedList<>();
    private final int[] food = new int[2];
    private Direction direction = Direction.RIGHT;
    private Direction nextDirection = Direction.RIGHT;
    private GameStatus running = GameStatus.Playing;
    private int score;
    private final int winScore;

    public SnakeModel(int winScore) {
        this.winScore = winScore;
        initializeGame();
        spawnFood();
    }

    /**
     * Direction getter.
     *
     * @return DIRECTION.
     */
    public Direction getDirection() {
        return direction;
    }

    private void initializeGame() {
        snake.clear();
        score = 2;

        int startX = WIDTH / 2;
        int startY = HEIGHT / 2;

        snake.add(new int[]{startX, startY});
        snake.add(new int[]{startX - 1, startY});

        direction = Direction.RIGHT;
        nextDirection = Direction.RIGHT;
        running = GameStatus.Playing;
    }

    /**
     * food spawner.
     */
    public void spawnFood() {
        Random rand = new Random();
        int attempts = 1000;

        do {
            food[0] = rand.nextInt(WIDTH);
            food[1] = rand.nextInt(HEIGHT);
            attempts--;
        } while (snakeCollision(food[0], food[1]) && attempts > 0);

        if (attempts == 0)  {
            running = GameStatus.GameOver;
        }
    }

    private boolean snakeCollision(int x, int y) {
        return snake.stream().anyMatch(part -> part[0] == x && part[1] == y);
    }

    public GameStatus update() {

        direction = nextDirection;
        int[] head = snake.getFirst().clone();
        switch (direction) {
            case UP -> head[1]--;
            case DOWN -> head[1]++;
            case LEFT -> head[0]--;
            case RIGHT -> head[0]++;
        }

        if (head[0] < 0 || head[0] >= WIDTH || head[1] < 0 || head[1] >= HEIGHT
                || snakeCollision(head[0], head[1])) {
            running = GameStatus.GameOver;
            return GameStatus.GameOver;
        }

        if (score == winScore) {
            running = GameStatus.WIN;
            return GameStatus.WIN;
        }

        snake.addFirst(head);
        if (head[0] == food[0] && head[1] == food[1]) {
            score++;
            if (score != winScore) {
                snakeEatSound.play();
            }
            spawnFood();
        } else {
            snake.removeLast();
        }

        return GameStatus.Playing;
    }

    /**
     * snake getter.
     *
     * @return snake list.
     */
    public LinkedList<int[]> getSnake() {
        return snake;
    }

    /**
     * food getter.
     * @return food
     */
    public int[] getFood() {
        return food;
    }

    /** nextDirection setter.
     *
     * @param nextDirection set
     */
    public void setNextDirection(Direction nextDirection) {
        if ((this.direction == Direction.UP && nextDirection != Direction.DOWN)
                || (this.direction == Direction.DOWN && nextDirection != Direction.UP)
                || (this.direction == Direction.LEFT && nextDirection != Direction.RIGHT)
                || (this.direction == Direction.RIGHT && nextDirection != Direction.LEFT)) {
            this.nextDirection = nextDirection;
        }
    }

    /**
     * restarting game.
     */
    public void restart() {
        initializeGame();
        spawnFood();
    }

    /**
     * enumchik.
     */
    public enum Direction {
        UP,
        DOWN,
        LEFT,
        RIGHT
    }
}