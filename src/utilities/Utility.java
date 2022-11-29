package utilities;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class Utility extends BaseTest {

    public void clickOnElement(By by) {
        WebElement loginLink = driver.findElement(by);
        loginLink.click();
    }

    public void sendTextToElement(By by, String text) {
        driver.findElement(by).sendKeys(text);

    }

      public String getTextFromElement(By by) {
          WebElement actualTextMessageElement = driver.findElement(by);
          return actualTextMessageElement.getText();
      }

    public void switchToAlert() {
        driver.switchTo().alert();
    }

        public void acceptAlert() {
        driver.switchTo().alert().accept();
    }

    public void dismissAlert() {
        driver.switchTo().alert().dismiss();
    }

    public String getTextFromAlert(String text) {
        driver.switchTo().alert().getText();
        return text;
    }

    public void sendTextToAlert(String text) {
        driver.switchTo().alert().sendKeys(text);

    }

    public void selectByVisibleTextFromDropDown(By by, String text) {
        WebElement dropDown = driver.findElement(by);
        Select select = new Select(dropDown);
        //Select by Value
        select.selectByVisibleText(text);

    }

    public void selectByValue(By by, String text) {
        WebElement value = driver.findElement(by);
        Select select = new Select(value);
        select.selectByValue(text);

    }


    public void selectByIndex(By by, int index) {
        WebElement dropdown = driver.findElement(by);
        Select select = new Select(dropdown);
        select.selectByIndex(index);
    }


    public void selectOptionByContainsText(By by) {
        WebElement dropdown = driver.findElement(by);
        Select select = new Select(dropdown);
        List<WebElement> list = select.getOptions();

    }
    //************************************************** Window Handle **********************************************************
    //************************************************** Action Class ***********************************************************
    //  mouseHoverToElement(By by), mouseHoverToElementAndClick(By by)

    public void mouseHoverToElement(By by) {
        Actions actions = new Actions(driver);
        actions.moveToElement(driver.findElement(by)).build().perform();
    }

    public void mouseHoverToElementAndClick(By by) {
        Actions actions = new Actions(driver);
        actions.moveToElement(driver.findElement(by)).click().build().perform();
    }
}

