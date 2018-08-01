package cloud9;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class regPgObj {

    WebDriver driver;

    private By firstNameLocator = By.name("firstname");
    private By lastNameLocator = By.name("lastname");
    private By emailLocator = By.name("email");
    private By passwordLocator = By.name("password");
    private By loginRegisterButtonLocator = By.xpath("/html/body/div/form/center/a");
    private By registerButtonLocator = By.xpath("/html/body/div/form/button");
    private By bodyTextLocator = By.tagName("body");

    private String cloud9RegisterHeader = "Cloud9 - Register";

    public regPgObj(WebDriver driver) {

        this.driver = driver;
    }

    public void clickLoginRegister(){

        driver.findElement(loginRegisterButtonLocator).click();
    }

    public void assertRegistrationHeader(){
        String bodyText = driver.findElement(bodyTextLocator).getText();
        Assert.assertTrue("Text not found!", bodyText.contains(cloud9RegisterHeader));
    }

    public void populateRegistration(String firstName, String lastName, String emailAddress, String password){

        driver.findElement(firstNameLocator).sendKeys(firstName);
        driver.findElement(lastNameLocator).sendKeys(lastName);
        driver.findElement(emailLocator).sendKeys(emailAddress);
        driver.findElement(passwordLocator).sendKeys(password);
    }


    public void clickRegister(){
        driver.findElement(registerButtonLocator).click();
    }
}

    class loginObj{

    WebDriver driver;

        public loginObj(WebDriver driver) {

            this.driver = driver;
        }

        private By loginEmailLocator = By.id("inputEmail");
        private By loginPasswordLocator = By.id("inputPassword");
        private By loginBtnLocator = By.xpath("/html/body/div/form/button");
        private By bodyTextLocator = By.tagName("body");

        private String loginHeader = "Cloud9 - Sign in";

        public void assertLoginHeader(){
            String bodyText = driver.findElement(bodyTextLocator).getText();
            Assert.assertTrue("Text not found!", bodyText.contains(loginHeader));
        }

        public void login() {

            driver.findElement(loginEmailLocator).sendKeys("crobinson53@qub.ac.uk");
            driver.findElement(loginPasswordLocator).sendKeys("password");
            driver.findElement(loginBtnLocator).click();

        }

        public void assertSuccess(){
            String successHeader = "Welcome";
            String bodyText = driver.findElement(bodyTextLocator).getText();
            Assert.assertTrue("Text not found!", bodyText.contains(successHeader));
        }



}







