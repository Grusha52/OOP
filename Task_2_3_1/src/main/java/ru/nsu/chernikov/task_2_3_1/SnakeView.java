package ru.nsu.chernikov.task_2_3_1;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import java.util.List;

/**
 * VIEW CLASS.
 */
public class SnakeView {
    /**
     * drawing.
     * @param gc graphic context.
     * @param snakes list of snakes.
     */
    public void draw(GraphicsContext gc, List<SnakeModel> snakes) {
        drawBoard(gc);

        // Отрисовка каждой змеи с уникальным цветом
        int index = 0;
        for (SnakeModel model : snakes) {
            Color snakeColor = switch (index % 3) {
                case 0 -> Color.ORANGE; // Игрок
                case 1 -> Color.GREEN;  // Бот 1
                case 2 -> Color.PURPLE; // Бот 2
                default -> Color.BLUE;
            };
            gc.setFill(snakeColor);

            for (int[] part : model.getSnake()) {
                gc.fillRect(part[0] * SnakeModel.TILE_SIZE,
                        part[1] * SnakeModel.TILE_SIZE,
                        SnakeModel.TILE_SIZE, SnakeModel.TILE_SIZE);
            }
            index++;
        }

        // Отрисовка еды (берем еду от первой змеи, предполагая общую еду)
        gc.setFill(Color.GOLD);
        int[] food = snakes.get(0).getFood();
        gc.fillOval(food[0] * SnakeModel.TILE_SIZE,
                food[1] * SnakeModel.TILE_SIZE,
                SnakeModel.TILE_SIZE, SnakeModel.TILE_SIZE);
    }

    /**
     * draw field.
     * @param gc graphic context.
     */
    private void drawBoard(GraphicsContext gc) {
        Color color1 = Color.web("#1E3A5F");
        Color color2 = Color.web("#305F8F");

        for (int y = 0; y < SnakeModel.HEIGHT; y++) {
            for (int x = 0; x < SnakeModel.WIDTH; x++) {
                gc.setFill((x + y) % 2 == 0 ? color1 : color2);
                gc.fillRect(x * SnakeModel.TILE_SIZE,
                        y * SnakeModel.TILE_SIZE,
                        SnakeModel.TILE_SIZE,
                        SnakeModel.TILE_SIZE);
            }
        }
    }

    /**
     * Drawing game over.
     *
     * @param gc game context.
     */
    public void drawGameOver(GraphicsContext gc) {
        gc.setFill(Color.BLACK);
        gc.fillRect(
                0,
                0,
                SnakeModel.WIDTH * SnakeModel.TILE_SIZE,
                SnakeModel.HEIGHT * SnakeModel.TILE_SIZE
        );

        gc.setFill(Color.RED);
        gc.setFont(new Font("Arial", 50));
        String gameOverText = "GAME OVER";
        double textX = (double) (SnakeModel.WIDTH * SnakeModel.TILE_SIZE) / 2 - 120;
        double textY = (double) (SnakeModel.HEIGHT * SnakeModel.TILE_SIZE) / 2 - 20;
        gc.fillText(gameOverText, textX, textY);

        gc.setFont(new Font("Arial", 24));
        gc.setFill(Color.WHITE);
        String restartText = "Press 'R' to Restart";
        double restartTextX = (double) (SnakeModel.WIDTH * SnakeModel.TILE_SIZE) / 2 - 100;
        double restartTextY = textY + 50;
        gc.fillText(restartText, restartTextX, restartTextY);
    }
}