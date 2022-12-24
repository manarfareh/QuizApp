package com.quizappdev.quizapp;

import java.sql.Connection;
import java.sql.DriverManager;

public class DatabaseQuiz {
    public QuizApp quizApp;

    public static Connection con;

    public static Connection create(){
        try {
            String url = "jdbc:mysql://localhost:3306/QuizDB";
            String user = "root";
            String password = "";
            //load the driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            // create the connection
            con = DriverManager.getConnection(url, user, password);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return con;
    }

    public void setQuizApp(QuizApp quizApp) {
        this.quizApp = quizApp;
    }
}
