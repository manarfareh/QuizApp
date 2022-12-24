package com.quizappdev.quizapp;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.image.Image;
import javafx.stage.Stage;

public class QuizApp extends Application {
    private Stage stage;

    public void start(Stage primaryStage) throws Exception {
        stage = primaryStage;
        stage.getIcons().add(new Image("file:U:\\Practice\\QuizApp\\src\\main\\java\\com\\quizappdev\\quizapp\\icon.png"));
        loginPage();
    }

    public void loginPage() throws Exception {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("loginPage.fxml"));

        Parent root = loader.load();
        LoginPageController loginPageController = loader.getController();
        loginPageController.setQuizApp(this);

        stage.setTitle("Welcome To Quiz APP");
        stage.setScene(new Scene(root));
        stage.setResizable(false);
        stage.show();
    }

    public void adminDash() throws Exception {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("adminPanel.fxml"));

        Parent root = loader.load();
        AdminPanel adminPanel = loader.getController();
        adminPanel.setQuizApp(this);

        stage.setTitle("Admin Dashboard");
        stage.setScene(new Scene(root));
        stage.setResizable(false);
        stage.show();
    }

    public void stdPanel() throws Exception {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("studentPanel.fxml"));

        Parent root = loader.load();
        StudentPanel studentPanel = loader.getController();
        studentPanel.setQuizApp(this);
        studentPanel.getFromDB();

        stage.setTitle("Welcome To Quiz Dashboard");
        stage.setScene(new Scene(root));
        stage.setResizable(false);
        stage.show();
    }

    public void showAns() throws Exception {
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("showAns.fxml"));

        Parent root = loader.load();
        ShowAns showAns = loader.getController();
        showAns.setQuizApp(this);

        stage.setTitle("Your Result Dashboard");
        stage.setScene(new Scene(root));
        stage.setResizable(false);
        stage.show();
    }

    // alert section
    public void showAlert(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setContentText("Username or pass is wrong\nPlease try again");
        alert.show();
    }
    public void conformationAlert(){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Successful");
        alert.setContentText("Successfully Added to Database");
        alert.show();
    }
    public void alreadyExists(){
        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Error");
        alert.setContentText("UserID already found at out Database");
        alert.show();
    }

    public void foundAlert(){
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Found on database");
        alert.setContentText("Successfully Found on database");
        alert.show();
    }

    public void notFoundAlert(){
        Alert alert = new Alert(Alert.AlertType.ERROR);
        alert.setTitle("Error");
        alert.setContentText("Not found on database Student ID is wrong\nPlease try again");
        alert.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}
