module com.quizappdev.quizapp {
    requires javafx.controls;
    requires javafx.fxml;
    requires mysql.connector.java;
    requires java.sql;

    opens com.quizappdev.quizapp to javafx.fxml;
    exports com.quizappdev.quizapp;
}