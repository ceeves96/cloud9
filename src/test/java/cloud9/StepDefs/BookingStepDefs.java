package cloud9.StepDefs;

import cucumber.api.PendingException;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;

public class BookingStepDefs {

    WebDriver driver;


    public By originLocator = By.id("Origin");
    public By destLocator = By.id("Destination");
    public By seatLocator = By.id("seat");
    public By classLocator = By.id("Flightclass");
    public By submitLocator = By.xpath("/html/body/div/div/div[2]/form/button");

    @Given("^A valid login with email  \"([^\"]*)\" and password \"([^\"]*)\"$")
    public void aValidLoginWithEmailAndPassword(String arg0, String arg1) throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        //set it up
        System.setProperty("webdriver.chrome.driver","C:/Users/Student01/Downloads/chromeDriver/chromedriver.exe");
        driver = new ChromeDriver();

        String baseURL = "http://10.9.10.139:81/sqlite/Main/login.html";
        driver.get(baseURL);

        //do the login
        driver.findElement(By.id("inputEmail")).sendKeys(arg0);
        driver.findElement(By.id("inputPassword")).sendKeys(arg1);

        driver.findElement(By.xpath("/html/body/div/form/button")).click();


    }


    //SELECT BY VISIBLE TEXT
    //oSelect.selectByVisibleText(booking.getOrigin());

    @When("^I input Origin as \"([^\"]*)\"  and Destination as \"([^\"]*)\" and Seat as \"([^\"]*)\" and Class as \"([^\"]*)\"$")
    public void iInputOriginAsAndDestinationAsAndSeatAsAndClassAs(String arg0, String arg1, String arg2, String arg3) throws Throwable {
        // Write code here that turns the phrase above into concrete actions

        //get to the booking page
        driver.findElement(By.xpath("/html/body/center[3]/a")).click();
        driver.findElement(By.xpath("/html/body/div/div/div[1]/ul/li[1]/a")).click();

        //make the actual booking
        Select oselect = new Select(driver.findElement(originLocator));
        oselect.selectByVisibleText(arg0);

        Select dselect = new Select(driver.findElement(destLocator));
        dselect.selectByVisibleText(arg1);

        driver.findElement(seatLocator).sendKeys(arg2);

        Select fselect = new Select(driver.findElement(classLocator));
        fselect.selectByVisibleText(arg3);

        driver.findElement(submitLocator).click();



    }


    @Then("^the booking should be successful$")
    public void theBookingShouldBeSuccessful() throws Throwable {
        // Write code here that turns the phrase above into concrete actions
        By bodyTextLocator = By.tagName("body");
        String successHeader = "Flight booked successfully";
        String bodyText = driver.findElement(bodyTextLocator).getText();
        Assert.assertTrue("Text not found!", bodyText.contains(successHeader));
    }
}
