module org.example.homework {
    requires javafx.controls;
    requires javafx.fxml;
    requires java.desktop;
    requires javafx.graphics;
requires javafx.base;
requires  javafx.swing;
    opens org.example.homework to javafx.fxml;
    exports org.example.homework;
}