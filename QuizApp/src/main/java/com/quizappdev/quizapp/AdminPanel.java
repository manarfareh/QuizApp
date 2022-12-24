package com.quizappdev.quizapp;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AdminPanel {
    public QuizApp quizApp;

    // Quiz add
    @FXML
    public TextArea quizTitle;
    @FXML
    public TextArea quizQuestion;
    @FXML
    public TextField option1;
    @FXML
    public TextField option2;
    @FXML
    public TextField option3;
    @FXML
    public TextField option4;
    @FXML
    public RadioButton radioButton_1;
    @FXML
    public RadioButton radioButton_2;
    @FXML
    public RadioButton radioButton_3;
    @FXML
    public RadioButton radioButton_4;
    @FXML
    public TextField quizID;

    @FXML
    private ToggleGroup toggleGroup;


    public TextArea displayDB;


    // Student add & show
    @FXML
    private TextField stdID;
    @FXML
    private TextField stdName;
    @FXML
    private PasswordField studentPassword;
    @FXML
    private TextField userName;
    @FXML
    private TextArea detailsLabel;
    @FXML
    private TextField studentIDforDetails;

    // Show DB section
    @FXML
    public TableView<Student> table;
    @FXML
    public TableColumn<Student, String> col_stdID;
    @FXML
    public TableColumn<Student, String> col_name;
    @FXML
    public TableColumn<Student, String> col_uname;
    @FXML
    public TableColumn<Student, String> col_points;


    @FXML
    public void cancelButton(ActionEvent actionEvent) {
        stdID.setText("");
        stdName.setText("");
        userName.setText("");
        studentPassword.setText("");
    }


    @FXML
    public void adminStdSubmit(ActionEvent actionEvent) {
        //submit to database
        try {
            String id = stdID.getText();
            String name = stdName.getText();
            String uName = userName.getText();
            String pass = studentPassword.getText();
            Integer point =0;

            Student student = new Student(id, name, uName, pass, point);
            boolean check = MysqlDB.InsertToDB(student);
            if (check) {
                //show alert
                quizApp.conformationAlert();
            } else {
                quizApp.showAlert();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        clearField();
    }

    

    @FXML
    public void showDetailButton(ActionEvent actionEvent) {
        String id = studentIDforDetails.getText();

        detailsLabel.setText(MysqlDB.getInfo(id));

        if(MysqlDB.getInfo(id) == null){
            quizApp.notFoundAlert();
        }
        else {
            quizApp.foundAlert();
        }
    }


    public void clearField() {
        stdID.setText("");
        stdName.setText("");
        userName.setText("");
        studentPassword.setText("");
    }

    // ******** Quiz section *********

    public void radioButtonSetup(){
        toggleGroup = new ToggleGroup();
        radioButton_1.setToggleGroup(toggleGroup);
        radioButton_2.setToggleGroup(toggleGroup);
        radioButton_3.setToggleGroup(toggleGroup);
        radioButton_4.setToggleGroup(toggleGroup);
    }

    @FXML
    public void quizSubmitButton(ActionEvent actionEvent) {
        Integer id = Integer.parseInt(quizID.getText());
        String title = quizTitle.getText();
        String question = quizQuestion.getText();
        String option_1 = option1.getText();
        String option_2 = option2.getText();
        String option_3 = option3.getText();
        String option_4 = option4.getText();

        String answer = "";

        radioButtonSetup();

        if (radioButton_1.isSelected()) {
            answer = option_1;
        } else if (radioButton_2.isSelected()) {
            answer = option_2;
        } else if (radioButton_3.isSelected()) {
            answer = option_3;
        } else if (radioButton_4.isSelected()) {
            answer = option_4;
        }

        Quiz quiz = new Quiz(id, title, question, option_1, option_2, option_3, option_4, answer);

        try {
            if(MysqlDB.InsertToQuizDB(quiz)){
                quizApp.conformationAlert();
            }
            else {
                quizApp.showAlert();
            }
        } catch (SQLException e) {
        }
        clearField();
    }

    public void clearQuizField() {
        quizID.setText("");
        quizTitle.setText("");
        quizQuestion.setText("");
        option1.setText("");
        option2.setText("");
        option3.setText("");
        option4.setText("");

    }
    
    @FXML
    public void quizCancelButton(ActionEvent actionEvent) {
        clearQuizField();
    }

    // ****** Show data *****

    ObservableList<Student> list = FXCollections.observableArrayList();

    @FXML
    public void showAllData(ActionEvent actionEvent) {

        Connection connection = DataBaseStudent.create();
        try {
            // getting from database
            ResultSet rs = connection.createStatement().executeQuery("select * from students");
            // this is data is adding to table from database
            while (rs.next()){
                list.add(new Student (rs.getString("stdid")
                        ,rs.getString("sname"),
                        rs.getString("uname"),
                        rs.getString( "pass"),
                        rs.getInt("points")));

                table.setItems(list);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }

        // this information is getting from Student Class
        col_stdID.setCellValueFactory(new PropertyValueFactory<>("studentID"));
        col_name.setCellValueFactory(new PropertyValueFactory<>("studentName"));
        col_uname.setCellValueFactory(new PropertyValueFactory<>("studentUserName"));
        col_points.setCellValueFactory(new PropertyValueFactory<>("quizPoint"));
    }


    // ****** Delete Student & quiz *****


    public void setQuizApp(QuizApp quizApp) {
        this.quizApp = quizApp;
    }
}
