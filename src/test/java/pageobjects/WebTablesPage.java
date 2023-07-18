package pageobjects;

import models.Employee;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;

public class WebTablesPage extends AbstractPageObject {
    private final By uploadAndDownload = By.xpath("(//*[@id=\"item-6\"])[1]");
    private final List<Employee> employeeList = new ArrayList<>();
    private final String tableGroupRow = "//*[@class='rt-tr-group']";
    private final String groupRowClassName = "//*[@class = 'rt-td']";

    private final By EMPTY_ROW = By.xpath("//*[contains(@class, 'rt-tr -padRow')]");
    private final By ADD_BUTTON = By.xpath("//*[@id=\"addNewRecordButton\"]");
    private final By FIST_NAME_FIELD = By.xpath("//*[@id=\"firstName\"]");
    private final By LAST_NAME_FIELD = By.xpath("//*[@id=\"lastName\"]");
    private final By EMAIL_FIELD = By.xpath("//*[@id=\"userEmail\"]");
    private final By AGE_FIELD = By.xpath("//*[@id=\"age\"]");
    private final By SALARY_FIELD = By.xpath("//*[@id=\"salary\"]");
    private final By DEPARTMENT_FIELD = By.xpath("//*[@id=\"department\"]");
    private final By SUBMIT_BUTTON = By.xpath("//*[@id=\"submit\"]");

    private final By NEXT_BUTTON = By.xpath("//button[text()=\"Next\"]");
    private final By TOTAL_PAGES = By.xpath("//*[@class='-totalPages']");
    ////*[@id='edit-record-1']
    private String editButton = "//*[@id='edit-record-";
    ////*[@id='delete-record-1']
    private String deleteButton = "//*[@id='delete-record-";


    public WebTablesPage(WebDriver driver) {
        super(driver);
        driver.get("https://demoqa.com/webtables");
    }

    public WebTablesPage clickAddButton() {
        adsScrollHandler(uploadAndDownload);
        getElement(ADD_BUTTON,6).click();
        return this;
    }

    public void fillRegistrationForm(String firstName, String lastName, String email, String age,
                                              String salary, String department) {

        getElement(FIST_NAME_FIELD).sendKeys(firstName);
        getElement(LAST_NAME_FIELD).sendKeys(lastName);
        getElement(EMAIL_FIELD).sendKeys(email);
        getElement(AGE_FIELD).sendKeys(age);
        getElement(SALARY_FIELD).sendKeys(salary);
        getElement(DEPARTMENT_FIELD).sendKeys(department);

        getElement(SUBMIT_BUTTON).click();

        getRecordsList();
    }

    ////*[@class='rt-tr-group'][1]//*[@class='rt-td'][1]
    private void getRecordsList() {

        int countOfPages = Integer.parseInt(getElement(TOTAL_PAGES).getText());

        for (int k = 1; k <= countOfPages; k++) {

            int countOfEmptyRows = getElements(EMPTY_ROW).size();

            for (int i = 1; i <= 10 - countOfEmptyRows; i++) {
                int numberOfGroup = i;

                Employee employee = new Employee() {
                    {
                        this.setFirstName(getElement(By.xpath(String.format("%s[%d]%s[%d]", tableGroupRow, numberOfGroup, groupRowClassName, 1))).getText());
                        this.setLastName(getElement(By.xpath(String.format("%s[%d]%s[%d]", tableGroupRow, numberOfGroup, groupRowClassName, 2))).getText());
                        this.setAge(getElement(By.xpath(String.format("%s[%d]%s[%d]", tableGroupRow, numberOfGroup, groupRowClassName, 3))).getText());
                        this.setEmail(getElement(By.xpath(String.format("%s[%d]%s[%d]", tableGroupRow, numberOfGroup, groupRowClassName, 4))).getText());
                        this.setSalary(getElement(By.xpath(String.format("%s[%d]%s[%d]", tableGroupRow, numberOfGroup, groupRowClassName, 5))).getText());
                        this.setDepartment(getElement(By.xpath(String.format("%s[%d]%s[%d]", tableGroupRow, numberOfGroup, groupRowClassName, 6))).getText());
                    }
                };

                employeeList.add(employee);
            }
            if (getElement(NEXT_BUTTON).isEnabled() && countOfEmptyRows == 0)
                getElement(NEXT_BUTTON).click();
        }
       // return employeeList;
    }

    public int findRecord(String email) {
        return IntStream.range(0, employeeList.size())
                .filter(i -> employeeList.get(i).getEmail().equals(email))
                .findFirst()
                .orElse(-1);
    }

    ////*[@id='delete-record-1']
    public void deleteEmployee(String email) {

        int numberOfRecord = findRecord(email);

        if(numberOfRecord != -1) {
            int countOfPages = Integer.parseInt(getElement(TOTAL_PAGES).getText());

                for (int k = 1; k <= countOfPages; k++) {
                    WebElement rowItem = getElement(By.xpath(String.format("%s%d']", deleteButton, numberOfRecord + 1)));
                    if (rowItem != null) {
                        rowItem.click();

                        getRecordsList();
                        break;
                    } else if (getElement(NEXT_BUTTON).isEnabled())
                        getElement(NEXT_BUTTON).click();
                }

            } else
            throw new RuntimeException("Such a record does not exist.");


    }

    ////*[@id='edit-record-,4']
    public void editEmployee(String oldEmail, String newEmail) {

        int numberOfRecord = findRecord(oldEmail);
        int countOfPages = Integer.parseInt(getElement(TOTAL_PAGES).getText());

        if (numberOfRecord != -1) {
            employeeList.get(numberOfRecord).setEmail(newEmail);
            for (int k = 1; k <= countOfPages; k++) {
                WebElement rowItem = getElement(By.xpath(String.format("%s%d']", editButton, numberOfRecord+1)));
                if (rowItem != null) {
                    rowItem.click();
                    getElement(EMAIL_FIELD).clear();
                    getElement(EMAIL_FIELD).sendKeys(newEmail);
                    getElement(SUBMIT_BUTTON).click();

                    getRecordsList();
                    break;
                } else if (getElement(NEXT_BUTTON).isEnabled())
                    getElement(NEXT_BUTTON).click();
            }

        } else
            throw new RuntimeException("Such a record does not exist.");
    }
}
