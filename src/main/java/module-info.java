module com.example.lab2workjatin {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.almasb.fxgl.all;

    opens com.example.lab2workjatin to javafx.fxml;
    exports com.example.lab2workjatin;
}