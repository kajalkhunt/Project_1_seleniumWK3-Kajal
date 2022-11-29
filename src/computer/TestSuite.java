package computer;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import utilities.Utility;

import java.util.Random;

public class TestSuite extends Utility {

    String baseUrl = "https://demo.nopcommerce.com/";


    @Before
    public void setUp() {
        openBrowser(baseUrl);

    }

    @Test
    public void verifyProductArrangeInAlphaBaticalOrder() {
        Actions actions = new Actions(driver);
        //1.1 Click on Computer Menu.
        //1.2 Click on Desktop
        WebElement computer = driver.findElement(By.xpath("//body/div[6]/div[2]/ul[1]/li[1]/a[1]"));
        WebElement desktop = driver.findElement(By.xpath("//body/div[6]/div[2]/ul[1]/li[1]/ul[1]/li[1]/a[1]"));
        //1.3 Select Sort By position "Name: Z to A"
        actions.moveToElement(computer).moveToElement(desktop).click().build().perform();
        // 1.4 Verify the Product will arrange in Descending order.
        selectByValue(By.id("products-orderby"), "6");

    }

    @Test
    public void verifyProductAddedToShoppingCartSuccessFully() throws InterruptedException {
        Actions actions = new Actions(driver);
        //2.1 Click on Computer Menu
        WebElement computer = driver.findElement(By.xpath("//body/div[6]/div[2]/ul[1]/li[1]/a[1]"));
        //2.2 Click on Desktop
        WebElement desktop = driver.findElement(By.xpath("//body/div[6]/div[2]/ul[1]/li[1]/ul[1]/li[1]/a[1]"));

        actions.moveToElement(computer).moveToElement(desktop).click().build().perform();
        // 2.3 Select Sort By position "Name: A to Z"
        selectByValue(By.id("products-orderby"), "5");
        Thread.sleep(5000);
        //2.4 Click on "Add To Cart"
        clickOnElement(By.xpath("(//button[contains(text(),'to cart')])[1]"));
        // 2.5 Verify the Text "Build your own computer"
        String expectedTextBuildComputer = "Build your own computer";
        String actualTextBuildComputer = getTextFromElement(By.xpath("//h1[contains(text(),'Build your own computer')]"));
        Assert.assertEquals("Not verifying the Text", expectedTextBuildComputer, actualTextBuildComputer);

        //2.6 Select "2.2 GHz Intel Pentium Dual-Core E2200" using Select class
        selectByVisibleTextFromDropDown(By.name("product_attribute_1"), "2.2 GHz Intel Pentium Dual-Core E2200");

        //2.7 Select "8GB [+$60.00]" using Select class.
        selectByVisibleTextFromDropDown(By.name("product_attribute_2"), "8GB [+$60.00]");

        //2.8 Select HDD radio "400 GB [+$100.00]"
        clickOnElement(By.id("product_attribute_3_7"));

        //Select OS radio "Vista Premium [+$60.00]"
        clickOnElement(By.id("product_attribute_4_9"));

        //2.10 Check Two Check boxes "Microsoft Office [+$50.00]" and "Total Commander
        clickOnElement(By.id("product_attribute_5_12"));
Thread.sleep(5000);
        //2.11 Verify the price "$1,475.00"
        String expectedTextPrice = "$1,475.00";
        String actualTextPrice = getTextFromElement(By.xpath("//span[@id='price-value-1']"));//body/div[@id='bar-notification']/div[1]/p[1]
        Assert.assertEquals("Products are not added to cart", expectedTextPrice, actualTextPrice);

        //2.12 Click on "ADD TO CARD" Button
        clickOnElement(By.xpath("//button[@id='add-to-cart-button-1']"));
        //2.13 Verify the Message "The product has been added to your shopping cart" on Top green Bar
        //After that close the bar clicking on the cross button
        String expectedTextAddToCartMessage = "The product has been added to your shopping cart";
        String actualTextAddToCartMessage = getTextFromElement(By.xpath("//body/div[@id='bar-notification']/div[1]/p[1]"));
        Assert.assertEquals("Products are not added to cart", expectedTextAddToCartMessage, actualTextAddToCartMessage);
        System.out.println("********************************" + actualTextAddToCartMessage);
        clickOnElement(By.xpath("//span[@class='close']"));
        Thread.sleep(5000);
        //2.14  Then MouseHover on "Shopping cart" and Click on "GO TO CART" button
        mouseHoverToElement(By.xpath("//span[contains(text(),'Shopping cart')]"));
        clickOnElement(By.xpath("//button[contains(text(),'Go to cart')]"));

        //2.15 Verify the message "Shopping cart"
        String expectedTextShoppingCartMessage = "Shopping cart";
        String actualTextShoppingCartMessage = getTextFromElement(By.xpath("//h1[contains(text(),'Shopping cart')]"));
        Assert.assertEquals("Shopping cart message not displayed", expectedTextShoppingCartMessage, actualTextShoppingCartMessage);

        //2.16 Change the Qty to "2" and Click on "Update shopping cart"
        driver.findElement(By.xpath("//input[@class='qty-input']")).clear();
        sendTextToElement(By.xpath("//input[@class='qty-input']"), "2");
        clickOnElement(By.id("updatecart"));

        //2.17 Verify the Total"$2,950.00"
        String expectedTotalPrice = "$2,950.00";
        String actualTotalPrice = getTextFromElement(By.xpath("(//strong[text()='$2,950.00'])[2]"));
        Assert.assertEquals("Not matching total", expectedTotalPrice, actualTotalPrice);

        //2.18 click on checkbox “I agree with the terms of service”
        clickOnElement(By.xpath("//input[@id='termsofservice']"));
        //2.19 Click on “CHECKOUT”
        clickOnElement(By.xpath("//button[@id='checkout']"));
        //2.20 Verify the Text “Welcome, Please Sign In!”
        String expectedText = "Welcome, Please Sign In!";
        String actualText = getTextFromElement(By.xpath("//h1[contains(text(),'Welcome, Please Sign In!')]"));
        Assert.assertEquals("Message not displayed", expectedText, actualText);

        //2.21Click on “CHECKOUT AS GUEST” Tab
        clickOnElement(By.xpath("//button[contains(text(),'Checkout as Guest')]"));

        //2.22 Fill the all mandatory field
        sendTextToElement(By.id("BillingNewAddress_FirstName"), "Prime");
        sendTextToElement(By.id("BillingNewAddress_LastName"), "Test");
        Random randomGenerator = new Random();
        int emailNum = randomGenerator.nextInt(9999);
        sendTextToElement(By.id("BillingNewAddress_Email"), "john.smith" + emailNum + "@gmail.com");
        selectByValue(By.xpath("//select[@id='BillingNewAddress_CountryId']"), "233");
        sendTextToElement(By.xpath("//input[@id='BillingNewAddress_City']"), "London");
        sendTextToElement(By.xpath("//input[@id='BillingNewAddress_Address1']"), "25 Granville Road");
        sendTextToElement(By.xpath("//input[@id='BillingNewAddress_ZipPostalCode']"), "N12 0AJ");
        sendTextToElement(By.xpath("//input[@id='BillingNewAddress_PhoneNumber']"), "012345678");

        //2.23 Click on “CONTINUE”
        clickOnElement(By.xpath("//body/div[6]/div[3]/div[1]/div[1]/div[1]/div[2]/ol[1]/li[1]/div[2]/div[1]/button[4]"));
        //2.24 Click on Radio Button “Next Day Air($0.00)”
        clickOnElement(By.xpath("//input[@id='shippingoption_1']"));
        //2.25 Click on “CONTINUE”
        clickOnElement(By.xpath("//body/div[6]/div[3]/div[1]/div[1]/div[1]/div[2]/ol[1]/li[3]/div[2]/form[1]/div[2]/button[1]"));
        //2.26 Select Radio Button “Credit Card”
        clickOnElement(By.xpath("//input[@id='paymentmethod_1']"));

        //2.27 Select “Master card” From Select credit card dropdown
        clickOnElement(By.xpath("//label[contains(text(),'Credit Card')]"));
       clickOnElement(By.xpath("//body/div[6]/div[3]/div[1]/div[1]/div[1]/div[2]/ol[1]/li[4]/div[2]/div[1]/button[1]"));
        //2.28 Fill all the details
         selectByVisibleTextFromDropDown(By.id("CreditCardType"), "Master card");
        sendTextToElement(By.xpath("//input[@id='CardholderName']"),"Mr J Smith");
        sendTextToElement(By.xpath("//input[@id='CardNumber']"), "5252158607699923");
        sendTextToElement(By.xpath("//select[@id='ExpireMonth']"), "2");
        sendTextToElement(By.xpath("//select[@id='ExpireYear']"), "2027");
        sendTextToElement(By.xpath("//input[@id='CardCode']"), "982");
        //2.29 Click on “CONTINUE”
        clickOnElement(By.xpath("//body/div[6]/div[3]/div[1]/div[1]/div[1]/div[2]/ol[1]/li[5]/div[2]/div[1]/button[1]"));
        //2.30 Verify “Payment Method” is “Credit Card”
        String expectedPaymentMethod1 = "Payment Method:";
        String actualTextPaymentMethod1 = getTextFromElement(By.xpath("//span[contains(text(), 'Payment Method:')]"));
        Assert.assertEquals("This is not payment method", expectedPaymentMethod1, actualTextPaymentMethod1);

        String expPaymentMethod = "Credit Card";
        String actPaymentMethod = getTextFromElement(By.xpath("//span[contains(text(), 'Credit Card')]"));
        Assert.assertEquals("Not correct payment method",expPaymentMethod,actPaymentMethod);

        //2.32 Verify “Shipping Method” is “Next Day Air”
        String expectedShippingMethod1 = "Shipping Method:";
        String actualShippingMethod1 = getTextFromElement(By.xpath("//span[contains(text(), 'Shipping Method:')]"));
        Assert.assertEquals("This is not shipping method", expectedShippingMethod1, actualShippingMethod1);

        String expShippingMethod = "Next Day Air";
        String actShippingMethod = getTextFromElement(By.xpath("//span[contains(text(),'Next Day Air')]"));
        Assert.assertEquals("Not correct payment method",expShippingMethod,actShippingMethod);

        //2.33 Verify Total is “$2,950.00”
        String expectedTotal = "$2,950.00";
        String actualTotal = getTextFromElement(By.xpath("//body[1]/div[6]/div[3]/div[1]/div[1]/div[1]/div[2]/ol[1]/li[6]/div[2]/div[1]/div[1]/div[1]/div[1]/form[1]/div[3]/div[1]/div[1]/table[1]/tbody[1]/tr[4]/td[2]/span[1]/strong[1]"));
        Assert.assertEquals("Not matching total", expectedTotal, actualTotal);

        //2.34 Click on “CONFIRM”
        clickOnElement(By.xpath("//button[contains(text(),'Confirm')]"));
        //2.35 Verify the Text “Thank You”
        String expectedTextThankYou  = "Thank you";
        String actTextThankYou = getTextFromElement(By.xpath("//h1[contains(text(),'Thank you')]"));
        Assert.assertEquals("Message not displayed",expectedTextThankYou, actTextThankYou);

        //2.36 Verify the message “Your order has been successfully processed!”
        String expectedOrderSuccessful = "Your order has been successfully processed!";
        String actOrderSuccessful = getTextFromElement(By.xpath("//strong[contains(text(),'Your order has been successfully processed!')]"));
        Assert.assertEquals("Message not displayed",expectedOrderSuccessful,actOrderSuccessful);

        //2.37 Click on “CONTINUE”
        clickOnElement(By.xpath("//button[contains(text(),'Continue')]"));
        //2.37 Verify the text “Welcome to our store”
        String expText = "Welcome to our store";
        String actText = getTextFromElement(By.xpath("//h2[contains(text(),'Welcome to our store')]"));
        Assert.assertEquals("Message not displayed",expText,actText);

    }
    @After
    public void closeBrowser(){
        driver.quit();

    }
}