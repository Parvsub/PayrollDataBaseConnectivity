package com.JDBCPayroll;

import org.junit.Assert;
import org.junit.Test;

public class EmployeePayrollTest {
EmployeeObject employeePayrollObj = new EmployeeObject();
    @Test
    public void  afterUpdatingSalaryShouldReturnTrue() throws Exception{
        DatabaseConnection con = new DatabaseConnection();
        Assert.assertEquals(300000.0, con.getSalaryFromDB("kundan"), 0.0);
    }
}

