package ru.nsu.chernikov.task_2_3_1;

import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.stage.Stage;

/**
 * Start screen.
 */
public class StartScreenController {
    @FXML
    private TextField scoreInput;

    private Stage primaryStage;

    public void setPrimaryStage(Stage primaryStage) {
        this.primaryStage = primaryStage;
    }

    /**
     * startGameMethod.
     */
    @FXML
    private void startGame() {
        try {
            int winScore = Integer.parseInt(scoreInput.getText());

            if (winScore < 3 || winScore > 225) {
                scoreInput.setText("Введите число от 3 до 225");
                return;
            }

            Stage gameStage = new Stage();
            SnakeController game = new SnakeController(winScore);
            game.start(gameStage);
            primaryStage.close();

        } catch (NumberFormatException e) {
            scoreInput.setText("Введите число от 3 до 225");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}