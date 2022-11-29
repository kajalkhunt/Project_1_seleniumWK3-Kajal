package electronics;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import utilities.Utility;

import java.util.Random;

public class ElectronicsTest extends Utility {
    String baseUrl = "https://demo.nopcommerce.com/";

    // static  String menu ="Computers";

    @Before
    public void setUp() {
        openBrowser(baseUrl);
    }

    @Test
    public void verifyUserShouldNavigateToCellPhonesPageSuccessfully() {
        //Actions actions = new Actions(driver);
        //1.1 Mouse Hover on “Electronics” Tab
        mouseHoverToElement(By.xpath("//body/div[6]/div[2]/ul[1]/li[2]/a[1]"));
        //1.2 Mouse Hover on “Cell phones” and click
        mouseHoverToElementAndClick(By.xpath("//body/div[6]/div[2]/ul[1]/li[2]/ul[1]/li[2]/a[1]"));

        // 1.3 Verify the text “Cell phones”
        String expectedText = "Cell phones";
        String actualText = getTextFromElement(By.xpath("//h1[contains(text(),'Cell phones')]"));
        Assert.assertEquals("Not verifying the Text", expectedText, actualText);
    }

    @Test
    public void verifyThatTheProductAddedSuccessfullyAndPlaceOrderSuccessfully() throws InterruptedException {

        //  2.1 Mouse Hover on “Electronics”Tab
        mouseHoverToElement(By.xpath("//body/div[6]/div[2]/ul[1]/li[2]/a[1]"));
        //2.2 Mouse Hover on “Cell phones” and click
        mouseHoverToElementAndClick(By.xpath("//body/div[6]/div[2]/ul[1]/li[2]/ul[1]/li[2]/a[1]"));
        //2.3 Verify the text “Cell phones”
        String expectedText = "Cell phones";
        String actualText = getTextFromElement(By.xpath("//h1[contains(text(),'Cell phones')]"));
        Assert.assertEquals("Not verifying the Text", expectedText, actualText);
        //2.4 Click on List View Tab
        clickOnElement(By.xpath("//a[contains(text(),'List')]"));
        //2.5 Click on product name “Nokia Lumia 1020” link
        Thread.sleep(2000);
        clickOnElement(By.xpath("//a[contains(text(),'Nokia Lumia 1020')]"));
        //2.6 Verify the text “Nokia Lumia 1020”
        String expText = "Nokia Lumia 1020";
        String actText = getTextFromElement(By.xpath("//h1[contains(text(),'Nokia Lumia 1020')]"));
        Assert.assertEquals("Not verifying the Text", expText, actText);
        //2.7 Verify the price “$349.00”
        String expectedTextPrice = "$349.00";
        String actualTextPrice = getTextFromElement(By.xpath("//span[@class='price-value-20']"));
        Assert.assertEquals("Not correct price", expectedTextPrice, actualTextPrice);
        //2.8 Change quantity to 2
        driver.findElement(By.xpath("//input[@class='qty-input']")).clear();
        sendTextToElement(By.xpath("//input[@class='qty-input']"), "2");
        clickOnElement(By.id("add-to-cart-button-20"));
        //  2.9 Click on “ADD TO CART” tab
        clickOnElement(By.xpath("//button[@id='add-to-cart-button-20']"));

        //  2.10 Verify the Message "The product has been added to your shopping cart" on Top green Bar
        //After that close the bar clicking on the cross button.
        String expectedTextAddToCartMessage = "The product has been added to your shopping cart";
        String actualTextAddToCartMessage = getTextFromElement(By.xpath("//body/div[@id='bar-notification']/div[1]/p[1]"));
        Assert.assertEquals("Products are not added to cart", expectedTextAddToCartMessage, actualTextAddToCartMessage);
        System.out.println("********************************" + actualTextAddToCartMessage);
        clickOnElement(By.xpath("//span[@class='close']"));
        Thread.sleep(5000);

        // 2.11 Then MouseHover on "Shopping cart" and Click on "GO TO CART" button.
        mouseHoverToElement(By.xpath("//span[contains(text(),'Shopping cart')]"));
        clickOnElement(By.xpath("//button[contains(text(),'Go to cart')]"));
        //2.12 Verify the message "Shopping cart"
        String expectedTextShoppingCartMessage = "Shopping cart";
        String actualTextShoppingCartMessage = getTextFromElement(By.xpath("//span[contains(text(),'Shopping cart')]"));
        Assert.assertEquals("Shopping cart message not displayed", expectedTextShoppingCartMessage, actualTextShoppingCartMessage);
        //2.13 Verify the quantity is 2 //div[@class = 'quantity']
        String expectedQuantity = "2";
        String actualQuantity = getTextFromElement(By.xpath("//span[contains(text(),'(2)')]"));
        Assert.assertEquals("Shopping cart message not displayed", expectedQuantity, actualQuantity.substring(1, 2));
        //2.14 Verify the Total $698.00
        String expectedTotal = "$698.00";
        String actualTotal = getTextFromElement(By.xpath("(//strong[text()='$698.00'])[2]"));
        Assert.assertEquals("Not correct total", expectedTotal, actualTotal);


        //2.15 click on checkbox “I agree with the terms of service”
        clickOnElement(By.xpath("//input[@id='termsofservice']"));
        //2.16 Click on “CHECKOUT”
        clickOnElement(By.xpath("//button[@id='checkout']"));
        //2.17 Verify the Text “Welcome, Please Sign In!”
        String expTextSignIn = "Welcome, Please Sign In!";
        String actTextSignIn = getTextFromElement(By.xpath("//h1[contains(text(),'Welcome, Please Sign In!')]"));
        Assert.assertEquals("Message not displayed", expTextSignIn, actTextSignIn);
        //2.18 Click on “REGISTER” tab
        clickOnElement(By.xpath("//button[contains(text(),'Register')]"));
        //2.19 Verify the text “Register”
        String expTextReg = "Register";
        String actTextReg = getTextFromElement(By.xpath("//h1[contains(text(),'Register')]"));
        Assert.assertEquals("Message not displayed", expTextReg, actTextReg);
        //2.20 Fill the mandatory fields
        sendTextToElement(By.id("FirstName"), "Peter");
        sendTextToElement(By.id("LastName"), "Rabbit");
        Random randomGenerator = new Random();
        int emailNum = randomGenerator.nextInt(9999);
        sendTextToElement(By.id("Email"), "Peter.Rabbit" + emailNum + "@gmail.com");
        sendTextToElement(By.id("Password"), "123456");
        sendTextToElement(By.id("ConfirmPassword"), "123456");
        //2.21 Click on “REGISTER” Button
        clickOnElement(By.xpath("//button[@id='register-button']"));

        //2.22 Verify the message “Your registration completed”
        String expTextRegCom = "Your registration completed";
        String actTextRegCom = getTextFromElement(By.xpath("//div[contains(text(),'Your registration completed')]"));
        Assert.assertEquals("Message not displayed", expTextRegCom, actTextRegCom);
        //2.23 Click on “CONTINUE” tab
        clickOnElement(By.xpath("//a[contains(text(),'Continue')]"));
        //2.24 Verify the text “Shopping card”
        String expTextSC = "Shopping cart";
        String actTextSC = getTextFromElement(By.xpath("//h1[contains(text(),'Shopping cart')]"));
        Assert.assertEquals("Message not displayed", expTextSC, actTextSC);
        //2.25 click on checkbox “I agree with the terms of service”
        clickOnElement(By.xpath("//input[@id='termsofservice']"));
        //2.26 Click on “CHECKOUT”
        clickOnElement(By.xpath("//button[@id='checkout']"));

        //2.27 Fill the Mandatory fields
       // sendTextToElement(By.id("BillingNewAddress_FirstName"), " ");
       // sendTextToElement(By.id("BillingNewAddress_LastName"), " ");
     //   driver.findElement(By.id("Email")).clear();
     //   Random randomGenerator1 = new Random();
     //   int emailNum1 = randomGenerator.nextInt(9999);
     //   sendTextToElement(By.id("Email"), "Peter.Rabbit" + emailNum1 + "@gmail.com");

        selectByVisibleTextFromDropDown(By.xpath("//select[@id='BillingNewAddress_CountryId']"), "United Kingdom");
        sendTextToElement(By.xpath("//input[@id='BillingNewAddress_City']"), "London");
        sendTextToElement(By.xpath("//input[@id='BillingNewAddress_Address1']"), "154 Peters Road");
        sendTextToElement(By.xpath("//input[@id='BillingNewAddress_ZipPostalCode']"), "Ha2 5PY");
        sendTextToElement(By.xpath("//input[@id='BillingNewAddress_PhoneNumber']"), "876543210");

        //2.28 Click on “CONTINUE”
        clickOnElement(By.name("save"));

        //2.29 Click on Radio Button “2nd Day Air ($0.00)”
        clickOnElement(By.id("shippingoption_2"));

        //2.30 Click on “CONTINUE”
        clickOnElement(By.xpath("//button[@onclick='ShippingMethod.save()']"));
        //2.31 Select Radio Button “Credit Card”
        clickOnElement(By.xpath("//input[@id='paymentmethod_1']"));
        //2.32 Select “Visa” From Select credit card dropdown
        clickOnElement(By.xpath("//button[@class='button-1 payment-method-next-step-button']"));//input[@id='paymentmethod_1']
        //2.33 Fill all the details
        //selectByVisibleTextFromDropDown(By.id("CreditCardType"), "visa");
        sendTextToElement(By.xpath("//input[@id='CardholderName']"), "Mr P Rabbit");
        sendTextToElement(By.xpath("//input[@id='CardNumber']"), "4929523915462577");
        sendTextToElement(By.xpath("//select[@id='ExpireMonth']"), "1");
        sendTextToElement(By.xpath("//select[@id='ExpireYear']"), "2028");
        sendTextToElement(By.xpath("//input[@id='CardCode']"), "538");
        //2.34 Click on “CONTINUE”
        clickOnElement(By.xpath("//body/div[6]/div[3]/div[1]/div[1]/div[1]/div[2]/ol[1]/li[5]/div[2]/div[1]/button[1]"));


        //2.35 Verify “Payment Method” is “Credit Card”
        String expectedPaymentMethod1 = "Payment Method:";
        String actualTextPaymentMethod1 = getTextFromElement(By.xpath("//span[contains(text(), 'Payment Method:')]"));
        Assert.assertEquals("This is not payment method", expectedPaymentMethod1, actualTextPaymentMethod1);

        String expPaymentMethod = "Credit Card";
        String actPaymentMethod = getTextFromElement(By.xpath("//span[contains(text(), 'Credit Card')]"));
        Assert.assertEquals("Not correct payment method", expPaymentMethod, actPaymentMethod);
       // 2.36 Verify “Shipping Method” is “2nd Day Air”
        String expectedShippingMethod1 = "Shipping Method:";
        String actualShippingMethod1 = getTextFromElement(By.xpath("//span[contains(text(), 'Shipping Method:')]"));
        Assert.assertEquals("This is not shipping method", expectedShippingMethod1, actualShippingMethod1);

        String expShippingMethod = "2nd Day Air";
        String actShippingMethod = getTextFromElement(By.xpath("//span[contains(text(),'2nd Day Air')]"));
        Assert.assertEquals("Not correct payment method",expShippingMethod,actShippingMethod);
        //2.37 Verify Total is “$698.00”
        String expectedTotal1 = "$698.00";
        String actualTotal1 = getTextFromElement(By.xpath(" (//strong[text()='$698.00'])[2]"));
        Assert.assertEquals("Not matching total", expectedTotal1, actualTotal1);
        //2.38 Click on “CONFIRM”
        clickOnElement(By.xpath("//button[contains(text(),'Confirm')]"));
        //2.39 Verify the Text “Thank You”
        String expectedTextThankYou = "Thank you";
        String actTextThankYou = getTextFromElement(By.xpath("//h1[contains(text(),'Thank you')]"));
        Assert.assertEquals("Message not displayed", expectedTextThankYou, actTextThankYou);
        //2.40 Verify the message “Your order has been successfully processed!”
        String expectedOrderSuccessful = "Your order has been successfully processed!";
        String actOrderSuccessful = getTextFromElement(By.xpath("//strong[contains(text(),'Your order has been successfully processed!')]"));
        Assert.assertEquals("Message not displayed", expectedOrderSuccessful, actOrderSuccessful);
        //2.41 Click on “CONTINUE”
        clickOnElement(By.xpath("//button[contains(text(),'Continue')]"));
        //2.42 Verify the text “Welcome to our store”
        String expTextWelcome = "Welcome to our store";
        String actTextWelcome = getTextFromElement(By.xpath("//h2[contains(text(),'Welcome to our store')]"));
        Assert.assertEquals("Message not displayed", expTextWelcome, actTextWelcome);
        //2.43 Click on “Logout” link
        clickOnElement(By.xpath("//a[contains(text(),'Log out')]"));
        //2.44 Verify the URL is “https://demo.nopcommerce.com/
        String expTextUrl = "Welcome to our store";
        String actTextUrl = getTextFromElement(By.xpath("//h2[contains(text(),'Welcome to our store')]"));
        Assert.assertEquals("Message not displayed", expTextUrl, actTextUrl);

    }

}
