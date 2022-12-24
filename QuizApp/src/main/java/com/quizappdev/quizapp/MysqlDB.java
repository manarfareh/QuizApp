package com.quizappdev.quizapp;

import java.sql.*;
import java.util.ArrayList;

public class MysqlDB {
    static Connection con;
    boolean flag = false;

    public static void create() throws ClassNotFoundException {
        //load the driver
        Class.forName("com.mysql.cj.jdbc.Driver");

        String user = "root";
        String password = "";
        String url = "jdbc:mysql://localhost:3306/students_data";
        // create the connection
        try {
            con = DriverManager.getConnection(url, user, password);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public static boolean InsertToDB(Student std) throws SQLException {
        //jdbc code here
        boolean flag = false;
        try {
            Connection connection = DataBaseStudent.create();
            String query = "INSERT INTO students(stdid,sname,uname,pass,points) VALUES(?,?,?,?,?)";
            // prepared connection
            PreparedStatement statement = connection.prepareStatement(query);
            // set the values of parameter
            statement.setString(1, std.getStudentID());
            statement.setString(2, std.getStudentName());
            statement.setString(3, std.getStudentUserName());
            statement.setString(4, std.getPassword());
            statement.setInt(5, std.getQuizPoint());
            //execute
            if(getInfo(std.getStudentID()) == null){
                statement.executeUpdate();
                flag = true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flag;
    }


    public static String getInfo(String id) {
        String send = null;
        try {
            Connection connection = DataBaseStudent.create();
            String query = "select stdid,sname,uname,points from students where stdid =?";

            // Step 2:Create a statement using connection object
            PreparedStatement preparedStatement = connection.prepareStatement(query);
            {
                preparedStatement.setString(1, id);
                System.out.println(preparedStatement);
                // Step 3: Execute the query or update query
                ResultSet rs = preparedStatement.executeQuery();

                // Step 4: Process the ResultSet object.
                while (rs.next()) {
                    String stdId = String.valueOf(rs.getInt("stdid"));
                    String sname = rs.getString("sname");
                    String uname = rs.getString("uname");
                    String points = rs.getString("points");

                    //send = stdId + " " + sname + " " + uname + " " + points;
                    send = "Student{" + "\n" +
                            "studentID= " + stdId + ",\n" +
                            "studentName= " + sname + ",\n" +
                            "studentUserName= " + uname + ",\n" +
                            "quizPoint= " + points +
                            '}';
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return send;
    }


    // ***********************************************************************************************************
    public static boolean checkDB(String id, String pass) throws ClassNotFoundException{
        boolean flag = false;
        try {
            Connection connection = DataBaseStudent.create();
            String query = "select * from students where stdid=? and pass=?";

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            preparedStatement.setString(1, id);
            preparedStatement.setString(2, pass);

            System.out.println(preparedStatement);

            ResultSet resultSet = preparedStatement.executeQuery();
            if (resultSet.next()) {
                flag = true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flag;
    }

    public static String showU(String id) throws ClassNotFoundException{
//        boolean flag = false;
        String send = null;
        try {
            Connection connection = DataBaseStudent.create();
            String query = "select * from students where stdid=?";

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            {
                preparedStatement.setString(1, id);
                System.out.println(preparedStatement);
                // Step 3: Execute the query or update query
                ResultSet rs = preparedStatement.executeQuery();

                // Step 4: Process the ResultSet object.
                while (rs.next()) {
                    //String stdId = String.valueOf(rs.getInt("stdid"));
                    String uname = rs.getString("uname");
                    String points = rs.getString("points");

                    send = "User: " + uname + " & Point: " + points;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return send;
    }

    public static String onlyShowU(String id) throws ClassNotFoundException{
        String send = null;
        try {
            Connection connection = DataBaseStudent.create();
            String query = "select * from students where stdid=?";

            PreparedStatement preparedStatement = connection.prepareStatement(query);
            {
                preparedStatement.setString(1, id);
                System.out.println(preparedStatement);
                // Step 3: Execute the query or update query
                ResultSet rs = preparedStatement.executeQuery();

                // Step 4: Process the ResultSet object.
                while (rs.next()) {
                    //String stdId = String.valueOf(rs.getInt("stdid"));
                    String uname = rs.getString("uname");

                    send = uname;
                }
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
        return send;
    }

    public static boolean InsertToQuizDB(Quiz quiz) throws SQLException {
        //jdbc code here
        boolean flag = false;
        try {
            Connection connection = DatabaseQuiz.create();
            String query = "INSERT INTO quizs(id,title,question,op1,op2,op3,op4,ans) VALUES(?,?,?,?,?,?,?,?)";
            // prepared connection
            PreparedStatement statement = connection.prepareStatement(query);
            // set the values of parameter
            statement.setInt(1, quiz.getId());
            statement.setString(2, quiz.getQuizTitle());
            statement.setString(3, quiz.getQuizQuestion());
            statement.setString(4, quiz.getOptions_1());
            statement.setString(5, quiz.getOptions_2());
            statement.setString(6, quiz.getOptions_3());
            statement.setString(7, quiz.getOptions_4());
            statement.setString(8, quiz.getAnswer());

            //execute
            statement.executeUpdate();
            flag = true;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return flag;
    }

}



      