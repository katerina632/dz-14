package tests;

import models.Employee;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageobjects.WebTablesPage;

public class WebTablesTests extends BaseTest{

    private final Employee employee = new Employee("Bob", "Black", "bblack@email.com", "25", "10000", "Development");

    @Test
    public void testCrudOneRecordPositive(){
        WebTablesPage webTablesPage = new WebTablesPage(driver);
        Boolean isExistRecordExpected = true;
        Boolean isRecordDeletedExpected = true;
        String newEmail = "bblackUppd@email.com";

        //Check adding record
        webTablesPage.clickAddButton()
                .fillRegistrationForm(employee.getFirstName(),employee.getLastName(),
                employee.getEmail(),employee.getAge(),
                employee.getSalary(), employee.getDepartment());

        Boolean isExistRecordActual = webTablesPage.findRecord(employee.getEmail()) != -1;


        Assert.assertEquals(isExistRecordActual,isExistRecordExpected, "Record has not been added.");

        //Check editing record
        webTablesPage.editEmployee(employee.getEmail(),newEmail);
        isExistRecordActual = webTablesPage.findRecord(newEmail) != -1;

        Assert.assertEquals(isExistRecordActual,isExistRecordExpected, "Record has not been edited.");

        webTablesPage.deleteEmployee(newEmail);
        Boolean isRecordDeletedActual = webTablesPage.findRecord(employee.getEmail()) == -1;

        Assert.assertEquals(isRecordDeletedActual,isRecordDeletedExpected,"Record has not been deleted.");

    }
}
