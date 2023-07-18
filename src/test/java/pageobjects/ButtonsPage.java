package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ButtonsPage extends  AbstractPageObject{
    private final By clickMeButton = By.xpath("//button[text()=\"Click Me\"]");
    private final By dynamicClickMessage = By.xpath("//*[@id=\"dynamicClickMessage\"]");

    public ButtonsPage(WebDriver driver) {
        super(driver);
    }

    public String clickOnClickMeButton() {
        getElement(clickMeButton).click();
        return getElement(dynamicClickMessage).getText();
    }



}
