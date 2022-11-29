package homepage;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import utilities.Utility;

public class TopMenuTest extends Utility {
    String baseUrl = "https://demo.nopcommerce.com/";

    static  String menu ="Computers";

    @Before
    public void setUp() {
        openBrowser(baseUrl);
    }

    public void selectMenu( String menu) {
        Actions actions = new Actions(driver);
        WebElement clickMenu = driver.findElement(By.linkText(menu));
        actions.moveToElement(clickMenu).click().build().perform();
    }

    @Test
    public void verifyPageNavigation(){
        selectMenu(menu);
        String expectedText = menu;
        String actualText = getTextFromElement(By.xpath("//h1[contains(text()," + "'" + menu +"')]"));
        Assert.assertEquals("Not Navigating",expectedText, actualText);


    }
    @After
    public void tearDown(){
        //closeBrowser();
    }
}
