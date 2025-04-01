//package ru.nsu.chernikov.task_2_3_1;
//
//import javafx.animation.KeyFrame;
//import javafx.animation.Timeline;
//import javafx.application.Application;
//import javafx.scene.Scene;
//import javafx.scene.canvas.Canvas;
//import javafx.scene.canvas.GraphicsContext;
//import javafx.scene.input.KeyCode;
//import javafx.scene.layout.Pane;
//import javafx.scene.paint.Color;
//import javafx.scene.text.Font;
//import javafx.stage.Stage;
//import javafx.util.Duration;
//import java.util.LinkedList;
//import java.util.Random;
//
//public class SnakeGame extends Application {
//    private static final int TILE_SIZE = 25;
//    private static final int WIDTH = 30;
//    private static final int HEIGHT = 30;
//
//    private LinkedList<int[]> snake = new LinkedList<>();
//    private int[] food = new int[2];
//    private Direction direction = Direction.RIGHT;
//    private Direction nextDirection = Direction.RIGHT;
//    private boolean running = true;
//    private Timeline timeline;
//
//    private enum Direction { UP, DOWN, LEFT, RIGHT }
//
//    @Override
//    public void start(Stage primaryStage) {
//        Pane root = new Pane();
//        Canvas canvas = new Canvas(WIDTH * TILE_SIZE, HEIGHT * TILE_SIZE);
//        root.getChildren().add(canvas);
//        Scene scene = new Scene(root);
//
//        GraphicsContext gc = canvas.getGraphicsContext2D();
//
//        initializeGame();
//        spawnFood();
//
//        scene.setOnKeyPressed(event -> {
//            if (event.getCode() == KeyCode.UP && direction != Direction.DOWN) nextDirection = Direction.UP;
//            if (event.getCode() == KeyCode.DOWN && direction != Direction.UP) nextDirection = Direction.DOWN;
//            if (event.getCode() == KeyCode.LEFT && direction != Direction.RIGHT) nextDirection = Direction.LEFT;
//            if (event.getCode() == KeyCode.RIGHT && direction != Direction.LEFT) nextDirection = Direction.RIGHT;
//            if (event.getCode() == KeyCode.R) restartGame(gc);
//        });
//
//        timeline = new Timeline(new KeyFrame(Duration.millis(50), e -> update(gc)));
//        timeline.setCycleCount(Timeline.INDEFINITE);
//        timeline.play();
//
//        primaryStage.setTitle("Snake Game");
//        primaryStage.setScene(scene);
//        primaryStage.setResizable(false);
//        primaryStage.show();
//    }
//
//    private void initializeGame() {
//        snake.clear();
//        int startX = WIDTH / 2;
//        int startY = HEIGHT / 2;
//
//        snake.add(new int[]{startX, startY});
//        snake.add(new int[]{startX - 1, startY});
//
//        direction = Direction.RIGHT;
//        nextDirection = Direction.RIGHT;
//        running = true;
//    }
//
//    private void spawnFood() {
//        Random rand = new Random();
//        int attempts = 100;
//
//        do {
//            food[0] = rand.nextInt(WIDTH);
//            food[1] = rand.nextInt(HEIGHT);
//            attempts--;
//        } while (snakeContains(food[0], food[1]) && attempts > 0);
//
//        if (attempts == 0) {
//            running = false;
//            timeline.stop();
//        }
//    }
//
//    private boolean snakeContains(int x, int y) {
//        return snake.stream().anyMatch(part -> part[0] == x && part[1] == y);
//    }
//
//    private void update(GraphicsContext gc) {
//        if (!running) {
//            drawGameOver(gc);
//            return;
//        }
//
//        direction = nextDirection;
//        int[] head = snake.getFirst().clone();
//        switch (direction) {
//            case UP -> head[1]--;
//            case DOWN -> head[1]++;
//            case LEFT -> head[0]--;
//            case RIGHT -> head[0]++;
//        }
//
//        if (head[0] < 0 || head[0] >= WIDTH || head[1] < 0 || head[1] >= HEIGHT || snakeContains(head[0], head[1])) {
//            running = false;
//            timeline.stop();
//            drawGameOver(gc);
//            return;
//        }
//
//        snake.addFirst(head);
//        if (head[0] == food[0] && head[1] == food[1]) {
//            spawnFood();
//        } else {
//            snake.removeLast();
//        }
//
//        draw(gc);
//    }
//
//    private void draw(GraphicsContext gc) {
//        drawBoard(gc);
//
//        gc.setFill(Color.ORANGE); // Мягкий оранжевый цвет для змейки
//        for (int[] part : snake) {
//            gc.fillRect(part[0] * TILE_SIZE, part[1] * TILE_SIZE, TILE_SIZE, TILE_SIZE);
//        }
//
//        gc.setFill(Color.GOLD); // Ярко-желтая еда
//        gc.fillOval(food[0] * TILE_SIZE, food[1] * TILE_SIZE, TILE_SIZE, TILE_SIZE);
//    }
//
//    private void drawBoard(GraphicsContext gc) {
//        Color color1 = Color.web("#1E3A5F"); // Темно-синий
//        Color color2 = Color.web("#305F8F"); // Сине-голубой
//
//        for (int y = 0; y < HEIGHT; y++) {
//            for (int x = 0; x < WIDTH; x++) {
//                gc.setFill((x + y) % 2 == 0 ? color1 : color2);
//                gc.fillRect(x * TILE_SIZE, y * TILE_SIZE, TILE_SIZE, TILE_SIZE);
//            }
//        }
//    }
//
//    private void drawGameOver(GraphicsContext gc) {
//        gc.setFill(Color.BLACK);
//        gc.fillRect(0, 0, WIDTH * TILE_SIZE, HEIGHT * TILE_SIZE);
//
//        gc.setFill(Color.RED);
//        gc.setFont(new Font("Arial", 40));
//        gc.fillText("GAME OVER", WIDTH * TILE_SIZE / 4, HEIGHT * TILE_SIZE / 2);
//        gc.setFont(new Font("Arial", 20));
//        gc.fillText("Press 'R' to Restart", WIDTH * TILE_SIZE / 4, HEIGHT * TILE_SIZE / 2 + 40);
//    }
//
//    private void restartGame(GraphicsContext gc) {
//        initializeGame();
//        spawnFood();
//
//        if (timeline != null) {
//            timeline.stop();
//        }
//
//        Random rand = new Random();
//        int newSpeed = 50 + rand.nextInt(150);
//
//        timeline = new Timeline(new KeyFrame(Duration.millis(newSpeed), e -> update(gc)));
//        timeline.setCycleCount(Timeline.INDEFINITE);
//        timeline.play();
//
//        draw(gc);
//    }
//
//    public static void main(String[] args) {
//        launch(args);
//    }
//}