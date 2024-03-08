module com.example.lab2work {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.almasb.fxgl.all;

    opens com.example.lab2work to javafx.fxml;
    exports com.example.lab2work;
}