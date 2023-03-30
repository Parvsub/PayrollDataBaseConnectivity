package com.JDBCPayroll;

import java.sql.*;

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
    public void FindMultipleValues() throws Exception{
        System.out.println("------------------------------------------------------");
        preparedStatements = connection.prepareStatement("SELECT SUM(net_pay) FROM employee_payroll WHERE gender = 'M' GROUP BY gender");
        resultSet = preparedStatements.executeQuery();
        while (resultSet.next()){
            System.out.println("sum of salaries of male: "+ resultSet.getInt(1));
        }
        preparedStatements = connection.prepareStatement("SELECT SUM(net_pay) FROM employee_payroll WHERE gender = 'F' GROUP BY gender");
        resultSet = preparedStatements.executeQuery();
        while (resultSet.next()){
            System.out.println("sum of salaries of females: "+ resultSet.getInt(1));
        }
        preparedStatements = connection.prepareStatement("SELECT AVG(net_pay) FROM employee_payroll WHERE gender = 'M' GROUP BY gender;");
        resultSet = preparedStatements.executeQuery();
        while (resultSet.next()){
            System.out.println("Average of salaries of males: " + resultSet.getInt(1));
        }
        preparedStatements = connection.prepareStatement("SELECT AVG(net_pay) FROM employee_payroll WHERE gender = 'F' GROUP BY gender;");
        resultSet = preparedStatements.executeQuery();
        while (resultSet.next()){
            System.out.println("Average of salaries of females: " + resultSet.getInt(1));
        }
        preparedStatements = connection.prepareStatement("SELECT MAX(net_pay) FROM employee_payroll WHERE gender = 'M' GROUP BY gender;");
        resultSet = preparedStatements.executeQuery();
        while (resultSet.next()){
            System.out.println("Maximum of salaries of males: " + resultSet.getInt(1));
        }
        preparedStatements = connection.prepareStatement("SELECT MAX(net_pay) FROM employee_payroll WHERE gender = 'F' GROUP BY gender;");
        resultSet = preparedStatements.executeQuery();
        while (resultSet.next()){
            System.out.println("Maximum of salaries of females: " + resultSet.getInt(1));
        }
        preparedStatements = connection.prepareStatement("SELECT MIN(net_pay) FROM employee_payroll WHERE gender = 'M' GROUP BY gender;");
        resultSet = preparedStatements.executeQuery();
        while (resultSet.next()){
            System.out.println("Minimum of salaries of males: " + resultSet.getInt(1));
        }
        preparedStatements = connection.prepareStatement("SELECT MIN(net_pay) FROM employee_payroll WHERE gender = 'F' GROUP BY gender;");
        resultSet = preparedStatements.executeQuery();
        while (resultSet.next()){
            System.out.println("Minimum of salaries of females: " + resultSet.getInt(1));
        }
        preparedStatements = connection.prepareStatement("SELECT COUNT(net_pay) FROM employee_payroll WHERE gender = 'M' GROUP BY gender;");
        resultSet = preparedStatements.executeQuery();
        while (resultSet.next()){
            System.out.println("Number of male employee: " + resultSet.getInt(1));
        }
        preparedStatements = connection.prepareStatement("SELECT COUNT(net_pay) FROM employee_payroll WHERE gender = 'F' GROUP BY gender;");
        resultSet = preparedStatements.executeQuery();
        while (resultSet.next()){
            System.out.println("Number of female employee: " + resultSet.getInt(1));
        }
        System.out.println("------------------------------------------------------");
    }

    public void addEmployee(String name, String gender, double basic, String date, String phone, String address) throws Exception {
        double deduction = (basic * 0.2);
        double taxablePay = basic - deduction;
        double tax = (taxablePay * 0.1);
        double netPay = (basic - tax);
        Date date1 = new Date(0000-00-00);
        preparedStatements = connection.prepareStatement("INSERT INTO `payroll_service`.`employee_payroll` (`id`, `name`, `gender`, `basic_pay`, `deductions`, `taxable_pay`, `tax`, `net_pay`, `start`, `employee_phone`, `address`) VALUES ('5', 'tanuja', 'F', '400000', '30000', '20000', '1500', '450000', '2020-01-15', '93287299', 'India');");
        preparedStatements.setInt(1,5);
        preparedStatements.setString(2,name);
        preparedStatements.setString(3,gender);
        preparedStatements.setDouble(4,basic);
        preparedStatements.setDouble(5,deduction);
        preparedStatements.setDouble(6,taxablePay);
        preparedStatements.setDouble(7,tax);
        preparedStatements.setDouble(8,netPay);
        preparedStatements.setDate(9,date1.valueOf(date));
        preparedStatements.setString(10,phone);
        preparedStatements.setString(11,address);
        preparedStatements.executeUpdate();
        System.out.println("Record updated........");
    }
}



