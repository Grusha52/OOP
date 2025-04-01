package ru.nsu.chernikov.task_2_3_1;

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

import java.io.IOException;
import java.util.Objects;
import java.util.Random;

public class SnakeController extends Application {
    AudioClip gameOverSound = new AudioClip(Objects.requireNonNull(getClass().getResource("wav/mario.wav")).toString());
    AudioClip gameWinSound = new AudioClip(Objects.requireNonNull(getClass().getResource("wav/invincible_theme.wav")).toString());
    private final int winScore;
    private SnakeModel model;
    private SnakeView view;
    private Timeline timeline;
    private Stage primaryStage;
    private Scene gameScene;
    private Scene gameOverScene;
    private Scene gameWinScreen;
    private GraphicsContext gc;

    public SnakeController(int winScore) {
        this.winScore = winScore;
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        this.primaryStage = primaryStage;
        model = new SnakeModel(winScore);
        view = new SnakeView();

        Pane gameRoot = new Pane();
        Canvas canvas = new Canvas(SnakeModel.WIDTH * SnakeModel.TILE_SIZE, SnakeModel.HEIGHT * SnakeModel.TILE_SIZE);
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

    private void startGameLoop() {
        timeline = new Timeline(new KeyFrame(Duration.millis(100), e -> update()));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    private void update() {
        if (model.update() == GameStatus.Playing) {
            view.draw(gc, model);
        } else if (model.update() == GameStatus.GameOver) {
            timeline.stop();
            showGameOver();
        } else {
            timeline.stop();
            showWinScreen();
        }
    }

    void restartGame() {
        model.restart();
        gameOverSound.stop();
        gameWinSound.stop();
        timeline.stop();

        Random rand = new Random();
        int newSpeed = 50 + rand.nextInt(150);

        timeline = new Timeline(new KeyFrame(Duration.millis(newSpeed), e -> update()));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();

        view.draw(gc, model);
        primaryStage.setScene(gameScene);
    }

    private void showGameOver() {
        gameOverSound.play();
        primaryStage.setScene(gameOverScene);
    }

    private void showWinScreen() {
        gameWinSound.play();
        primaryStage.setScene(gameWinScreen);
    }

    private void setupGameKeyHandlers() {
        gameScene.setOnKeyPressed(event -> {
            KeyCode code = event.getCode();
            if (code == KeyCode.UP) model.setNextDirection(SnakeModel.Direction.UP);
            else if (code == KeyCode.DOWN) model.setNextDirection(SnakeModel.Direction.DOWN);
            else if (code == KeyCode.LEFT) model.setNextDirection(SnakeModel.Direction.LEFT);
            else if (code == KeyCode.RIGHT) model.setNextDirection(SnakeModel.Direction.RIGHT);
        });
    }

    private void loadGameOverScene() {
        try {
            Parent gameOverRoot = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("fxml/game_over.fxml")));
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

    private void loadWinScreen() {
        try {
            Parent gameWinRoot = FXMLLoader.load(Objects.requireNonNull(getClass().getResource("fxml/win_screen.fxml")));
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