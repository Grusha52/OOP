module ru.nsu.chernikov.task_2_3_1 {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.media;
    requires java.desktop;


    opens ru.nsu.chernikov.task_2_3_1 to javafx.fxml;
    exports ru.nsu.chernikov.task_2_3_1;
}