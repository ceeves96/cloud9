package cloud9;


import org.junit.*;
import org.junit.runners.MethodSorters;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;


public class Cloud9App
{

     WebDriver driver;

    String expectedTitle = "Cloud9 Airlines";
    String cloud9SigninHeader = "Cloud9 - Sign in";
    private String registrationSuccessful = "Registration Successful";
    String bookingHeader = "Book Flight";
    String bookingSuccessful = "Flight booked successfully";


    loginObj loginPg;


     @Before
    public void setUp() throws Exception {
        System.setProperty("webdriver.chrome.driver", "C:/Users/Student01/Downloads/chromeDriver/chromedriver.exe");
        driver = new ChromeDriver();
        String baseUrl = "http://10.9.10.139:81/sqlite/Main/login.html";
        driver.get(baseUrl);
        loginPg = new loginObj(driver);
     }
/*
     @Test
     public void RunA(){
         String actualTitle = driver.getTitle();
         Assert.assertEquals(actualTitle, expectedTitle);
     }

     @Test
     public void RunB(){
         String bodyText1 = driver.findElement(By.tagName("body")).getText();
         Assert.assertTrue("Text not found!", bodyText1.contains(cloud9SigninHeader));
     }

     @Test
     public void register() {
         regPgObj registrationPage;
         registrationPage = new regPgObj(driver);
         registrationPage.clickLoginRegister();
         registrationPage.assertRegistrationHeader();
         registrationPage.populateRegistration("ceeves", "Robi", "cr52@sqs.com", "xxx");
         registrationPage.clickRegister();
     }
*/

    @Test
    public void runLogin(){
    loginPg.assertLoginHeader();
    loginPg.login();
    loginPg.assertSuccess();
    }

    @Test
    public void runBook(){
        loginPg.login();
        bookingObj bookPg;
        bookPg = new bookingObj(driver);
        bookPg.assertBookingHeader();
        bookPg.book();
        bookPg.assertSuccess();
    }

    /*
    @Test
    public void runA(){
        String expectedTitle = "Cloud9 Airlines";
        String actualTitle = driver.getTitle();
        Assert.assertEquals(actualTitle, expectedTitle);
        driver.findElement(By.linkText("Register")).click();
        driver.findElement(By.name("firstname")).sendKeys("Caoimhe");
        driver.findElement(By.name("lastname")).sendKeys("Robinson");
        driver.findElement(By.id("inputEmail")).sendKeys("crobinson53@qub.ac.uk");
        driver.findElement(By.id("inputPassword")).sendKeys("password");
        driver.findElement(By.xpath("/html/body/div/form/button")).click();

    }
*/
    /*
    @Test
    public void runLoginBookFlight(){

        driver.findElement(By.id("inputEmail")).sendKeys("crobinson53@qub.ac.uk");
        driver.findElement(By.id("inputPassword")).sendKeys("password");
        driver.findElement(By.xpath("/html/body/div/form/button")).click();

        driver.findElement(By.xpath("/html/body/center[3]/a")).click();
        driver.findElement(By.xpath("/html/body/div/div/div[1]/ul/li[1]/a")).click();

       Select oselect = new Select(driver.findElement(By.id("Origin")));
       oselect.selectByIndex(2);

        Select dselect = new Select(driver.findElement(By.id("Destination")));
        dselect.selectByIndex(4);

        driver.findElement(By.id("seat")).sendKeys("25C");

        Select fselect = new Select(driver.findElement(By.id("Flightclass")));
        fselect.selectByIndex(2);

        driver.findElement(By.xpath("/html/body/div/div/div[2]/form/button")).click();

    }
*/

    @After
    public void tearDown() throws Exception {
        //TODO dont forget to close driver
        //driver.Close();
    }
}
