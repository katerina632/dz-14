package tests;

import io.qameta.allure.Description;
import org.testng.Assert;
import org.testng.annotations.Test;
import pageobjects.ElementsPage;

public class ButtonsTest extends BaseTest{
    private final String EXPECTED_MESSAGE = "You have done a dynamic click";

    @Test
    @Description
    public void testClickMeButton(){
       String currentMessage = new ElementsPage(driver)
                .clickButtonsTab()
                .clickOnClickMeButton();

        System.out.println(currentMessage);

        Assert.assertEquals(currentMessage, EXPECTED_MESSAGE, "Wrong message." );
    }
}
