package cloud9;

import org.junit.*;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class bookingObj {


    WebDriver driver;

    public bookingObj(WebDriver driver) {

        this.driver = driver;
    }

    private By gotoHomeLocator = By.xpath("/html/body/center[3]/a");

    private By secondBtnLocator = By.xpath("/html/body/div/div/div[1]/ul/li[1]/a");
    private By originLocator = By.id("Origin");
    private By destLocator = By.id("Destination");
    private By seatLocator = By.id("seat");
    private By classLocator = By.id("Flightclass");
    private By submitLocator = By.xpath("/html/body/div/div/div[2]/form/button");


    private By bodyTextLocator = By.tagName("body");
    private String bookingHeader = "Book Flight";
    private String SuccessHeader = "Flight booked successfully";

    public void assertBookingHeader(){

        driver.findElement(gotoHomeLocator).click();
        driver.findElement(secondBtnLocator).click();

        String bodyText = driver.findElement(bodyTextLocator).getText();
        Assert.assertTrue("Text not found!", bodyText.contains(bookingHeader));
    }


    public void book() {


        Select oselect = new Select(driver.findElement(originLocator));
        oselect.selectByIndex(2);

        Select dselect = new Select(driver.findElement(destLocator));
        dselect.selectByIndex(4);

        driver.findElement(seatLocator).sendKeys("25C");

        Select fselect = new Select(driver.findElement(classLocator));
        fselect.selectByIndex(2);

        driver.findElement(submitLocator).click();


    }

    public void assertSuccess(){
        String successHeader = "Flight booked successfully";
        String bodyText = driver.findElement(bodyTextLocator).getText();
        Assert.assertTrue("Text not found!", bodyText.contains(successHeader));
    }



}

class updateBooking{

    WebDriver driver;

    public updateBooking(WebDriver driver) {
        this.driver = driver;
    }

    public void BookAndUpdate (String bookingID){
        //Utilities.login(driver,"wsi@netactive.co.za", "xxx");
        //do a login in the set up first


        driver.findElement(By.linkText("Homepage")).click();

        List<WebElement> rows = driver.findElements(By.xpath("//table[@class='table table-striped']/tbody/tr"));
        //*   /html/body/div/div/div[2]/div/table
        System.out.println("Number of rows is " + rows.size());

        int rowNum =  rows.size();

        // Get booking id for latest booking
        List<WebElement> colVals2 = rows.get(rowNum-1).findElements(By.tagName("td"));
        System.out.println(colVals2.get(3).getText());

        for(int i=1; i<rowNum; i++){
            List<WebElement> colVals1 = rows.get(i).findElements(By.tagName("td"));
            for(int j=0; j<6; j++){
                System.out.println(colVals1.get(j).getText());
            }
        }
    }
}





