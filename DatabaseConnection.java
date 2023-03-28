package com.JDBCPayroll;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class DatabaseConnection {
    Connection connection;
    PreparedStatement preparedStatements;
    ResultSet resultSet;

    void DBConnection() throws Exception{
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/payrollservices2", "root", "password");
            System.out.println("connection established........");
        }catch (Exception e){}
    }

    public void display() throws Exception {
        preparedStatements = connection.prepareStatement("select * from employee_payroll");
        resultSet = preparedStatements.executeQuery();
        while (resultSet.next()){
            System.out.println(resultSet.getInt(1)+" "+resultSet.getString(2)+" "+resultSet.getString(3)
                    +" "+resultSet.getDouble(4)+"  "+resultSet.getDouble(5)
                    +"  "+resultSet.getDouble(6)+"  "+resultSet.getDouble(7)
                    +"  "+resultSet.getDouble(8)+"  "+resultSet.getDate(9)
                    +"  "+resultSet.getString(10)+"  "+resultSet.getString(11)
                    +"  "+resultSet.getString(12));
        }
    }
    public void update(String name,Double pay) throws Exception {
        preparedStatements = connection.prepareStatement("UPDATE EMPLOYEE_PAYROLL SET BASIC_PAY = ? WHERE NAME = ?");
        preparedStatements.setDouble(1,pay);
        preparedStatements.setString(2,name);
        preparedStatements.executeUpdate();
        System.out.println("Record updated........");
    }
    public double getSalaryFromDB(String name) throws Exception {
        preparedStatements = connection.prepareStatement("SELECT BASIC_PAY FROM EMPLOYEE_PAYROLL WHERE NAME = ?");
        preparedStatements.setString(1, name);
        resultSet = preparedStatements.executeQuery();
        double sal = 0;
        while (resultSet.next()) {
            sal = resultSet.getDouble(1);
        }
        return sal;
    }
    public void close() throws Exception{
        connection.close();
        System.out.println("Connection closed.....");
    }
    public void updateDate(String date) throws Exception {
        preparedStatements = connection.prepareStatement("SELECT * FROM employee_payroll WHERE CAST(? AS DATE) AND DATE(NOW())");
        preparedStatements.setDate(1, java.sql.Date.valueOf(date));
        resultSet = preparedStatements.executeQuery();
        while (resultSet.next()) {
            System.out.println(resultSet.getInt(1)+" "+resultSet.getString(2)+" "+resultSet.getString(3)
                    +" "+resultSet.getDouble(4)+"  "+resultSet.getDouble(5)
                    +"  "+resultSet.getDouble(6)+"  "+resultSet.getDouble(7)
                    +"  "+resultSet.getDouble(8)+"  "+resultSet.getDate(9)
                    +"  "+resultSet.getString(10)+"  "+resultSet.getString(11));
        }
    }
}


