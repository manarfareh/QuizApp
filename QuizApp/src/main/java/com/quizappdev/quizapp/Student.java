package com.quizappdev.quizapp;

public class Student {
    private String studentID;
    private String studentName;
    private String studentUserName;
    private String password;
    private Integer quizPoint = 0;

    public Student() {
    }

    public Student(String studentID, String password) {
        this.studentID = studentID;
        this.password = password;
    }

    public Student(String studentID, String studentName, String studentUserName, String password, Integer points ) {
        this.studentID = studentID;
        this.studentName = studentName;
        this.studentUserName = studentUserName;
        this.password = password;
        this.quizPoint = points;
        
    }

    public Student(String studentID, String studentName, String studentUserName, Integer quizPoint) {
        this.studentID = studentID;
        this.studentName = studentName;
        this.studentUserName = studentUserName;
        this.quizPoint = quizPoint;
    }

    public String getStudentID() {
        return studentID;
    }

    public void setStudentID(String studentID) {
        this.studentID = studentID;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getStudentUserName() {
        return studentUserName;
    }

    public void setStudentUserName(String studentUserName) {
        this.studentUserName = studentUserName;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getQuizPoint() {
        return quizPoint;
    }

    public void setQuizPoint(Integer quizPoint) {
        this.quizPoint = quizPoint;
    }

    @Override
    public String toString() {
        return "Student{" +
                "studentID='" + studentID + '\'' +
                ", studentName='" + studentName + '\'' +
                ", studentUserName='" + studentUserName + '\'' +
                ", password='" + password + '\'' +
                ", quizPoint=" + quizPoint +
                '}';
    }
}
