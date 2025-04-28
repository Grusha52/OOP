package ru.nsu.chernikov.task_2_3_1;

import java.util.List;

public interface BotStrategy {
    SnakeModel.Direction chooseDirection(SnakeModel snake, List<SnakeModel> allSnakes);
}