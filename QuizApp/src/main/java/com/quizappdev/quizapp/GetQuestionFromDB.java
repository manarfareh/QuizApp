package com.quizappdev.quizapp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


public class GetQuestionFromDB {
    public QuizApp quizApp;
    /**
     * getQuestion, getOp1, getOp2, getOp3, getOp4, getAns from DB
     * check them and add +1 to QuizPoint
     * ans checking will be as string==string
     */

    static Connection connection = DatabaseQuiz.create();

    // IN use
    public static ArrayList<String> nextQ(Integer id) {
//        ArrayList<String> list = new ArrayList<>();
        String send;
        try {
            Connection connection = DatabaseQuiz.create();
            String query = "select question,id,op1,op2,op3,op4,ans from quizs where id=?";

            ArrayList<String> list = new ArrayList<>();
            // Step 2:Create a statement using connection object
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            {
                preparedStatement.setInt(1, id);
                System.out.println(preparedStatement);

                // Step 3: Execute the query or update query
                ResultSet rs = preparedStatement.executeQuery();

                // Step 4: Process the ResultSet object.
                while (rs.next()) {
                    //String stdId = String.valueOf(rs.getInt("stdid"));
                    //String question = rs.getString("question");
                    Integer qNo = rs.getInt("id");
                    String question = rs.getString("question");
                    String op1 = rs.getString("op1");
                    String op2 = rs.getString("op2");
                    String op3 = rs.getString("op3");
                    String op4 = rs.getString("op4");
                    String q = String.valueOf(qNo);
                    String ans = rs.getString("ans");

                    list.add(0, op1);
                    list.add(1, op2);
                    list.add(2, op3);
                    list.add(3, op4);
                    list.add(4, q);
                    list.add(5, question);
                    list.add(6, ans);
                }

            }
            return list;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static Integer getLastQno(){
        int id = 0;
        try {
            String query = "SELECT * FROM quizs ORDER BY id DESC LIMIT 1";

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            {
                System.out.println(preparedStatement);
//                preparedStatement.setString(1, "");
                ResultSet rs = preparedStatement.executeQuery();

                while (rs.next()) {
                    id = rs.getInt("id");
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }

    public void setQuizApp(QuizApp quizApp) {
        this.quizApp = quizApp;
    }

}
