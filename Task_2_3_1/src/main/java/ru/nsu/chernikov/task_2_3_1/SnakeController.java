package ru.nsu.chernikov.task_2_3_1;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Random;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.layout.Pane;
import javafx.scene.media.AudioClip;
import javafx.stage.Stage;
import javafx.util.Duration;

/**
 * Snake controller.
 */
public class SnakeController extends Application {
    private final int winScore;
    AudioClip gameOverSound = new AudioClip(Objects.requireNonNull(
            getClass().getResource("wav/mario.wav")).toString());
    AudioClip gameWinSound = new AudioClip(Objects.requireNonNull(
            getClass().getResource("wav/invincible_theme.wav")).toString());
    private List<SnakeModel> snakes; // List to hold player and bot snakes
    private SnakeModel playerSnake; // Reference to player's snake
    private SnakeView view;
    private Timeline timeline;
    private Stage primaryStage;
    private Scene gameScene;
    private Scene gameOverScene;
    private Scene gameWinScreen;
    private GraphicsContext gc;

    /**
     * constructor.
     *
     * @param winScore - score to win
     */
    public SnakeController(int winScore) {
        this.winScore = winScore;
    }

    /**
     * main.
     *
     * @param args args.
     */
    public static void main(String[] args) {
        launch(args);
    }

    /**
     * starting game stage.
     *
     * @param primaryStage game stage.
     */
    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        snakes = new ArrayList<>();

        // Initialize player snake
        playerSnake = new SnakeModel(winScore);
        snakes.add(playerSnake);

        // Initialize bot snakes
        initializeBots();

        view = new SnakeView();

        Pane gameRoot = new Pane();
        Canvas canvas = new Canvas(SnakeModel.WIDTH * SnakeModel.TILE_SIZE,
                SnakeModel.HEIGHT * SnakeModel.TILE_SIZE);
        gameRoot.getChildren().add(canvas);
        gameScene = new Scene(gameRoot);
        gc = canvas.getGraphicsContext2D();

        setupGameKeyHandlers();
        loadGameOverScene();
        loadWinScreen();
        startGameLoop();

        primaryStage.setTitle("Snake Game");
        primaryStage.setScene(gameScene);
        primaryStage.setResizable(false);
        primaryStage.show();
    }

    /**
     * Initialize bot snakes with different strategies.
     */
    private void initializeBots() {
        SnakeModel bot1 = new SnakeModel(winScore, new FoodSeekerStrategy());
        SnakeModel bot2 = new SnakeModel(winScore, new RandomWandererStrategy());
        snakes.add(bot1);
        snakes.add(bot2);
    }

    /**
     * game loop.
     */
    private void startGameLoop() {
        timeline = new Timeline(new KeyFrame(Duration.millis(100), e -> update()));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    /**
     * updating game.
     */
    private void update() {
        // Update bots' directions
        for (SnakeModel snake : snakes) {
            if (snake != playerSnake && snake.getRunning() == GameStatus.Playing) {
                snake.updateBot(snakes);
            }
        }

        // Update all snakes and check collisions
        for (SnakeModel snake : snakes) {
            if (snake.getRunning() == GameStatus.Playing) {
                if (snake.update() == GameStatus.Playing) {
                    // Check collisions with other snakes
                    int[] head = snake.getSnake().getFirst();
                    for (SnakeModel otherSnake : snakes) {
                        if (otherSnake != snake && otherSnake.getRunning() == GameStatus.Playing) {
                            for (int[] part : otherSnake.getSnake()) {
                                if (head[0] == part[0] && head[1] == part[1]) {
                                    snake.setRunning(GameStatus.GameOver);
                                    if (snake == playerSnake) {
                                        // Если игрок столкнулся, завершаем игру
                                        timeline.stop();
                                        showGameOver();
                                        return;
                                    }
                                    break;
                                }
                            }
                        }
                    }
                } else if (snake == playerSnake && snake.getRunning() == GameStatus.GameOver) {
                    // Игрок врезался в стену или сам себя
                    timeline.stop();
                    showGameOver();
                    return;
                } else if (snake == playerSnake && snake.getRunning() == GameStatus.WIN) {
                    // Игрок победил
                    timeline.stop();
                    showWinScreen();
                    return;
                } else if (snake != playerSnake && snake.getRunning() != GameStatus.Playing) {
                    // Бот проиграл или выиграл, просто помечаем его
                    snake.setRunning(GameStatus.GameOver);
                }
            }
        }

        // Отрисовка всех змей
        view.draw(gc, snakes);
    }

    /**
     * restart of game.
     */
    void restartGame() {
        snakes.clear();
        playerSnake = new SnakeModel(winScore);
        snakes.add(playerSnake);
        initializeBots();

        gameOverSound.stop();
        gameWinSound.stop();
        timeline.stop();

        Random rand = new Random();
        int newSpeed = 50 + rand.nextInt(150);

        timeline = new Timeline(new KeyFrame(Duration.millis(newSpeed), e -> update()));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();

        view.draw(gc, snakes);
        primaryStage.setScene(gameScene);
    }

    /**
     * game over scene show.
     */
    private void showGameOver() {
        gameOverSound.play();
        primaryStage.setScene(gameOverScene);
    }

    /**
     * win scene show.
     */
    private void showWinScreen() {
        gameWinSound.play();
        primaryStage.setScene(gameWinScreen);
    }

    private void setupGameKeyHandlers() {
        gameScene.setOnKeyPressed(event -> {
            KeyCode code = event.getCode();
            if (code == KeyCode.UP) {
                playerSnake.setNextDirection(SnakeModel.Direction.UP);
            } else if (code == KeyCode.DOWN) {
                playerSnake.setNextDirection(SnakeModel.Direction.DOWN);
            } else if (code == KeyCode.LEFT) {
                playerSnake.setNextDirection(SnakeModel.Direction.LEFT);
            } else if (code == KeyCode.RIGHT) {
                playerSnake.setNextDirection(SnakeModel.Direction.RIGHT);
            }
        });
    }

    /**
     * game over scene loader.
     */
    private void loadGameOverScene() {
        try {
            Parent gameOverRoot = FXMLLoader.load(
                    Objects.requireNonNull(getClass().getResource("fxml/game_over.fxml")));
            gameOverScene = new Scene(gameOverRoot);
            gameOverSound.setVolume(100);
            gameOverScene.setOnKeyPressed(event -> {
                if (event.getCode() == KeyCode.R) {
                    restartGame();
                }
            });

        } catch (IOException e) {
            System.err.println("Ошибка при загрузке game_over.fxml: " + e.getMessage());
            e.printStackTrace();
        }
    }

    /**
     * win scene loader.
     */
    private void loadWinScreen() {
        try {
            Parent gameWinRoot = FXMLLoader.load(
                    Objects.requireNonNull(getClass().getResource("fxml/win_screen.fxml")));
            gameWinScreen = new Scene(gameWinRoot);

            gameWinScreen.setOnKeyPressed(event -> {
                if (event.getCode() == KeyCode.R) {
                    restartGame();
                }
            });

        } catch (IOException e) {
            System.err.println("Ошибка при загрузке win_screen.fxml: " + e.getMessage());
            e.printStackTrace();
        }
    }
}