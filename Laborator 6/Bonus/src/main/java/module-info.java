module org.example.bonus {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires javafx.base;
    requires javafx.swing;
    requires com.dlsc.formsfx;
    requires org.jgrapht.core;

    opens org.example.bonus to javafx.fxml;


    exports org.example.bonus;
}