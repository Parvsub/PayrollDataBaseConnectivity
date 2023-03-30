package com.JDBCPayroll;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.sql.Date;

public class EmployeePayroll {
    public static void main(String[] args) throws Exception{
        DatabaseConnection con = new DatabaseConnection();
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int choice = -1;
        while (choice!=0){
            System.out.println("Enter your choice:\n1. DISPLAY EMPLOYEE PAYROLL\n2. UPDATE\n0. EXIT");
            choice = Integer.parseInt(br.readLine());
            switch (choice){
                case 1: con.display();
                    break;
                case 2:
                    System.out.println("Enter the name to update base pay");
                    String name = br.readLine();
                    System.out.println("Enter the basic pay to update");
                    Double pay = Double.parseDouble(br.readLine());
                    con.update(name,pay);
                    break;
                case 3:
                    System.out.println("Enter the date in format eg. '2020-01-10' ");
                    String Date = br.readLine();
                    con.updateDate(Date);
                    break;
                case 4:
                    con.FindMultipleValues();
                    break;
                case 5:
                    System.out.println("Enter the name");
                    name = br.readLine();
                    System.out.println("Enter the gender");
                    String gender = br.readLine();
                    System.out.println("Enter basic Pay");
                    double basic = Double.parseDouble(br.readLine());
                    System.out.println("Enter the start date eg '2010-11-02'");
                    Date = br.readLine();
                    System.out.println("Enter the employee phone");
                    String phone = br.readLine();
                    System.out.println("Enter the address");
                    String address = br.readLine();
                    con.addEmployee(name,gender,basic, Date,phone,address);
                case 0:
                    con.close();
                    break;
            }
        }
    }
}