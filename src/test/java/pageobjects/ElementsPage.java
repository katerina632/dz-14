package pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ElementsPage extends  AbstractPageObject{
    private final By ADS_WIDGET  = By.xpath("//*[@id=\"app\"]/div/div/div[2]/div[1]/div/div/div[4]/span/div");
    private final By BUTTON_TAB = By.xpath("//*[@id='item-4']");

    public ElementsPage(WebDriver driver) {
        super(driver);
        driver.get("https://demoqa.com/elements");
    }

    public ButtonsPage clickButtonsTab(){
        adsScrollHandler(ADS_WIDGET);
        getElement(BUTTON_TAB,10).click();
        return new ButtonsPage(driver);
    }


}
