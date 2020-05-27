module JavaFx.Threads {
    requires javafx.controls;
    requires javafx.fxml;
    requires org.controlsfx.controls;

    opens com.murilo to javafx.fxml;

    exports com.murilo;
}