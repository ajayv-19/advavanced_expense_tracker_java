module advancedexpensetracker {
    requires java.sql;
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
    requires javafx.base;
    requires org.jfree.jfreechart;

    opens client to javafx.fxml;
}