package com.quizappdev.quizapp;

import javafx.fxml.FXML;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

public class LoginPageController {
    // Object of main class
    public QuizApp quizApp;

    @FXML
    private PasswordField studentPass;
    @FXML
    private TextField adminUsername;
    @FXML
    private PasswordField adminPass;
    @FXML
    private TextField studentUsername;


    @FXML
    public void adminLoginButton() {
        String adminU = adminUsername.getText();
        String adminP = adminPass.getText();

        try{
            if(adminU.equals("") && adminP.equals("")){
                quizApp.showAlert();
            }
            else if(adminU.equals("Admin") && adminP.equals("1234")){
                //System.out.println("Login to admin panel");
                quizApp.adminDash();
            }
            else {
                // show error massage
                quizApp.showAlert();
                adminUsername.setText("");
                adminPass.setText("");
            }
        }catch (Exception e){
            e.printStackTrace();
        }

    }

    @FXML
    public void studentLoginButton(){
        String stdU = studentUsername.getText();
        String stdP = studentPass.getText();

        try {
            if(MysqlDB.checkDB(stdU, stdP)){
                StudentPanel.setUs(MysqlDB.showU(stdU));
                StudentPanel.setuName(MysqlDB.onlyShowU(stdU));
                quizApp.stdPanel();
            }
            else {
                // show error massage
                quizApp.showAlert();
                studentUsername.setText("");
                studentPass.setText("");
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public void setQuizApp(QuizApp quizApp) {
        this.quizApp = quizApp;
    }

}
