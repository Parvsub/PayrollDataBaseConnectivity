package com.JDBCPayroll;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;


public class JavaDataBase {
    public static void ConnectingtoJDBC() throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        String url = "jdbc:mysql://localhost:3306/RFP122";
        String username = "root";
        String pwd = "Parvathi135@";
        Connection con = DriverManager.getConnection(url, username, pwd);
        Statement statement = con.createStatement();
        String query = "insert into rfp(name,age) values('Praveen',234)";
        int Rowefftected = statement.executeUpdate(query);
        System.out.println(Rowefftected);
        con.close();
    }
    public static void main(String[] args) throws SQLException, ClassNotFoundException {
        ConnectingtoJDBC();
    }
}
