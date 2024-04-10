module org.example.compulsory {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.dlsc.formsfx;

    opens org.example.compulsory to javafx.fxml;
    exports org.example.compulsory;
}