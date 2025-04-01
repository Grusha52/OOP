package ru.nsu.chernikov.task_2_3_1;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SnakeControllerTest {
    private SnakeController controller;

    @BeforeEach
    void setUp() {
//        controller = new SnakeController();
        // Инициализируем только контроллер, без запуска GUI
        controller.model = new SnakeModel(winScore); // Убедитесь, что модель инициализирована
        controller.view = new SnakeView();   // Инициализация представления
    }

    @Test
    void testRestartGame() {
        // Убедитесь, что перед началом игры направление установлено в RIGHT
        assertEquals(SnakeModel.Direction.RIGHT, controller.model.getDirection());

        // Перезапускаем игру
        controller.restartGame();

        // Проверяем, что игра перезапустилась правильно
        assertNotNull(controller.model);  // Убедитесь, что модель не равна null
        assertEquals(SnakeModel.Direction.RIGHT, controller.model.getDirection());
    }

    @Test
    void testUpdateGame() {
        // Начальный статус игры
        assertTrue(controller.model.update()); // Предполагаем, что игра должна обновляться без ошибок
        controller.view.draw(null, controller.model); // Просто вызываем метод рисования, проверяя, что он не вызывает ошибок
    }

    @Test
    void testKeyInput() {
        // Проверим, что контроллер реагирует на изменение направления
        controller.model.setNextDirection(SnakeModel.Direction.UP); // Устанавливаем направление вручную
        assertEquals(SnakeModel.Direction.UP, controller.model.getDirection()); // Проверяем, что направление было изменено
    }

    @Test
    void testDirectionChange() {
        // Проверяем изменение направления с помощью метода контроллера
        controller.model.setNextDirection(SnakeModel.Direction.LEFT);
        assertEquals(SnakeModel.Direction.LEFT, controller.model.getDirection());
    }
}