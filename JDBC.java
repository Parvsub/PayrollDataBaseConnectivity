package com.JDBCPayroll;


import javax.xml.transform.Result;
import java.sql.*;

public class JDBC {
    public static void ConnectingtoJDBC() throws ClassNotFoundException, SQLException , EnterProperDataTypeException {
        Class.forName("com.mysql.cj.jdbc.Driver");
        String url="jdbc:mysql://localhost:3306/payrollservices2";
        String username = "root";
        String password = "Parvathi135@";
        Connection con = DriverManager.getConnection(url,username,password);
        Statement statement = con.createStatement();
        ResultSet rs = statement.executeQuery("Select * from employee_payroll");
        while (rs.next()){
            System.out.println("id="+rs.getInt(1));
            System.out.println("name="+rs.getString(2));
            System.out.println("phonenumber="+rs.getString(3));
            System.out.println("address="+rs.getString(4));
            System.out.println("department="+rs.getString(5));
            System.out.println("gender="+rs.getString(6));
            System.out.println("basic_pay="+rs.getDouble(7));
            System.out.println("deductions="+rs.getDouble(8));
            System.out.println("taxable_pay="+rs.getDouble(9));
            System.out.println("tax="+rs.getDouble(10));
            System.out.println("net_pay="+rs.getDouble(11));
            try {
                System.out.println("start="+rs.getString(12));
                throw new EnterProperDataTypeException(" "+"Please do datetype conversion");
            }catch (EnterProperDataTypeException e){
                e.printStackTrace();
                System.out.println("Inside catch");
            }
        }
        statement.close();
        con.close();
    }

    public static void main(String[] args) throws SQLException, ClassNotFoundException, EnterProperDataTypeException {
        ConnectingtoJDBC();
    }
}
