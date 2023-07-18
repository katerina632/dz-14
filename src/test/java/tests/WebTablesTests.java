package tests;

import models.Employee;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.asserts.SoftAssert;
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



        /*webTablesPage.clickAddButton().fillRegistrationForm("Alice", "White", "awhite@email.com", "28", "12000", "Marketing");
        webTablesPage.clickAddButton().fillRegistrationForm("John", "Brown", "jbrown@email.com", "30", "9000", "Sales");
        webTablesPage.clickAddButton().fillRegistrationForm("Emma", "Green", "egreen@email.com", "35", "8000", "HR");
        webTablesPage.clickAddButton().fillRegistrationForm("Michael", "Grey", "mgrey@email.com", "27", "11000", "Finance");
        webTablesPage.clickAddButton().fillRegistrationForm("Sophia", "Smith", "ssmith@email.com", "24", "10500", "Operations");
        webTablesPage.clickAddButton().fillRegistrationForm("David", "Johnson", "djohnson@email.com", "32", "9500", "Development");
        webTablesPage.clickAddButton().fillRegistrationForm("Olivia", "Martinez", "omartinez@email.com", "29", "11500", "Marketing");
        webTablesPage.clickAddButton().fillRegistrationForm("James", "Miller", "jmiller@email.com", "26", "9700", "Sales");
        webTablesPage.clickAddButton().fillRegistrationForm("Daniel", "Rodriguez", "drodriguez@email.com", "31", "8500", "HR");*/
        //webTablesPage.clickAddButton().fillRegistrationForm("Bob", "Black", "bblack@email.com", "25", "10000", "Development");

       /* for (Employee employee: webTablesPage.getRecordsList()) {
            System.out.println(employee.toString());
        }

        webTablesPage.editEmployee("bblack@email.com","bblack@emailUpd.com");
        for (Employee employee: webTablesPage.getRecordsList()) {
            System.out.println(employee.toString());
        }
        webTablesPage.deleteEmployee("bblack@emailUpd.com");

        for (Employee employee: webTablesPage.getRecordsList()) {
            System.out.println(employee.toString());
        }*/







    }
}
