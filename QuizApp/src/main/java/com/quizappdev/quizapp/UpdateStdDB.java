package com.quizappdev.quizapp;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UpdateStdDB {
    public QuizApp quizApp;
    public String updatePoint;

    public void setUpdatePoint(String point){
        this.updatePoint = point;
    }

    public String getUpdatePoint() {
        return updatePoint;
    }

    public static void updatePoints(String uName, String points){
        Connection connection = DataBaseStudent.create();
        try {
            String query = "update students set points=? where uname=?"; // update query
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, points);
            ps.setString(2, uName);
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static String getPoints(String uName){
        String send = null;
        Connection connection = DataBaseStudent.create();
        try {
            String query = "select points from students where uname=?"; // update query
            PreparedStatement ps = connection.prepareStatement(query);
            ps.setString(1, uName);

            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                String s = rs.getString("points");
                send = s;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return send;
    }

    public void setQuizApp(QuizApp quizApp) {
        this.quizApp = quizApp;
    }
}
