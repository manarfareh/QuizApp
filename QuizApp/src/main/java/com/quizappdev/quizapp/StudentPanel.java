package com.quizappdev.quizapp;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;

import java.net.URL;
import java.util.ArrayList;

import java.util.ResourceBundle;

public class StudentPanel implements Initializable {
    public QuizApp quizApp;

    @FXML
    public Label uShow;

    public static String us;

    @FXML
    public Label quizLabel;
    @FXML
    public ToggleGroup ansToggle;
    @FXML
    public RadioButton op_1;
    @FXML
    public RadioButton op_2;
    @FXML
    public RadioButton op_3;
    @FXML
    public RadioButton op_4;
    @FXML
    public Label op_label_1;
    @FXML
    public Label op_label_2;
    @FXML
    public Label op_label_3;
    @FXML
    public Label op_label_4;
    @FXML
    public Label qNo;

    @FXML
    public Button nextButtonID;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        uShow.setText(getUs());
        nextButtonID.fire();
        radioButtonSetup();
    }

    public static String getUs() {
        return us;
    }

    public static void setUs(String us) {
        StudentPanel.us = us;
    }

    //collect uname
    static String uName;
    public static void setuName(String uName){
        StudentPanel.uName = uName;
    }
    public static String getuName(){
        return uName;
    }

    //**************************************
    String answer = "";
    public void radioButtonSetup(){
        ansToggle = new ToggleGroup();
        op_1.setToggleGroup(ansToggle);
        op_2.setToggleGroup(ansToggle);
        op_3.setToggleGroup(ansToggle);
        op_4.setToggleGroup(ansToggle);
    }


    //***************

    // Buttons
    int count = 0;
    int ansCount = 0;

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    String qAns;
    public void getFromDB() throws IndexOutOfBoundsException{
        try {
            setCount(getCount() + 1);
            //System.out.println(getCount());
            if(getCount() == GetQuestionFromDB.getLastQno() + 1) {
                //System.out.println(ansCount);
                ShowAns.setUs(getuName());
                UpdateStdDB.updatePoints(getuName(), String.valueOf(ansCount));
                quizApp.showAns();
                System.out.println("Question ended");
            } else {
                ArrayList<String> list = GetQuestionFromDB.nextQ(getCount());
                if (list != null) {
                    quizLabel.setText(list.get(5));
                    op_label_1.setText(list.get(0));
                    op_label_2.setText(list.get(1));
                    op_label_3.setText(list.get(2));
                    op_label_4.setText(list.get(3));
                    qNo.setText(list.get(4));
                    qAns = list.get(6);

                    // here was radioselect section

                    op_1.setSelected(false);
                    op_2.setSelected(false);
                    op_3.setSelected(false);
                    op_4.setSelected(false);
                }
            }
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    public boolean getAnsNo(){
        boolean flag = false;
        if (op_1.isSelected()) {
            answer = op_label_1.getText();
            flag = true;
        } else if (op_2.isSelected()) {
            answer = op_label_2.getText();
            flag = true;
        } else if (op_3.isSelected()) {
            answer = op_label_3.getText();
            flag = true;
        } else if (op_4.isSelected()) {
            answer = op_label_4.getText();
            flag = true;
        }

        System.out.println(qAns + " and " + answer);

        if (answer.equals(qAns)) {
            ansCount++;
        }

        System.out.println(ansCount);
        return flag;
    }

    @FXML
    public void nextButton(ActionEvent actionEvent){
        if(getAnsNo()){
            getFromDB();
        }
        else {
            System.out.println("Button is not selected");
        }
    }

    @FXML
    public void getResultButton(ActionEvent actionEvent) {
        // show result in a javafx scene
        UpdateStdDB.updatePoints(getuName(), String.valueOf(ansCount));
        System.out.println("ans point is = "+ ansCount);
    }

    public void setQuizApp(QuizApp quizApp) {
        this.quizApp = quizApp;
    }

}
