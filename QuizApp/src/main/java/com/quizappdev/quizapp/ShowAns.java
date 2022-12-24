package com.quizappdev.quizapp;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.ResourceBundle;

public class ShowAns implements Initializable {
    public QuizApp quizApp;

    @FXML
    public Label showUN;
    @FXML
    public Label showPoint;
    @FXML
    public ImageView imageView;

    static String us;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        showUN.setText(getUs());
        imageView.setImage(new Image("C:\\Users\\khouloud said\\Desktop\\QuizApp-master - Copie\\src\\main\\resources\\com\\quizappdev\\quizapp\\image\\thank u.png"));
        showPoint.setText(UpdateStdDB.getPoints(getUs())); //Please add here
    }

    public static String getUs() {
        return us;
    }

    public static void setUs(String us) {
        ShowAns.us = us;
    }

    @FXML
    public void logOutButton(ActionEvent actionEvent) throws Exception {
        quizApp.loginPage();
    }

    public void setQuizApp(QuizApp quizApp) {
        this.quizApp = quizApp;
    }
}
