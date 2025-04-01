module ru.nsu.chernikov.task_2_3_1 {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;


    opens ru.nsu.chernikov.task_2_3_1 to javafx.fxml;
    exports ru.nsu.chernikov.task_2_3_1;
}