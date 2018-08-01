package cloud9;

import org.junit.*;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.util.List;

public class bookingObj {


    WebDriver driver;

    public By gotoHomeLocator = By.xpath("/html/body/center[3]/a");

    public By secondBtnLocator = By.xpath("/html/body/div/div/div[1]/ul/li[1]/a");
    public By originLocator = By.id("Origin");
    public By destLocator = By.id("Destination");
    public By seatLocator = By.id("seat");
    public By classLocator = By.id("Flightclass");
    public By submitLocator = By.xpath("/html/body/div/div/div[2]/form/button");

    public bookingObj(WebDriver driver) {

        this.driver = driver;
    }




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
        oselect.selectByIndex(1);

        Select dselect = new Select(driver.findElement(destLocator));
        dselect.selectByIndex(4);

        driver.findElement(seatLocator).sendKeys("25C");

        Select fselect = new Select(driver.findElement(classLocator));
        fselect.selectByIndex(0);

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

    public By originLocator = By.id("Origin");
    public By destLocator = By.id("Destination");
    public By seatLocator = By.id("seat");
    public By classLocator = By.id("Flightclass");
    public By submitLocator = By.xpath("/html/body/div/div/div[2]/form/button");
    public By initLocator = By.xpath("/html/body/div/div/div[2]/center/a");


    public updateBooking(WebDriver driver) {
        this.driver = driver;
    }

    public void BookAndUpdate (String FlightID){
        //Utilities.login(driver,"wsi@netactive.co.za", "xxx");
        //do a login in the set up first
        driver.findElement(By.linkText("Homepage")).click();

        List<WebElement> rows = driver.findElements(By.xpath("//table[@class='table table-striped']/tbody/tr"));
        //*   /html/body/div/div/div[2]/div/table


        int rowNum =  rows.size();
        int rowID = -1;

        for(int i =1; i<rows.size(); i++){

            String flightIDNum = rows.get(i).findElements(By.tagName("td")).get(3).getText();
            if(flightIDNum.equals(FlightID)){
                rowID = i;
                break;
            }

        }

        // Get booking id for requested booking
        List<WebElement> colVals2 = rows.get(rowID).findElements(By.tagName("td"));
        colVals2.get(0).findElement(By.linkText("Update")).click();

        //the booking part
        Select oselect = new Select(driver.findElement(originLocator));
        oselect.selectByIndex(0);

        Select dselect = new Select(driver.findElement(destLocator));
        dselect.selectByIndex(4);

        driver.findElement(seatLocator).sendKeys(Keys.chord(Keys.CONTROL, "a"), "26A");

        Select fselect = new Select(driver.findElement(classLocator));
        fselect.selectByIndex(1);
        driver.findElement(submitLocator).click();
        driver.findElement(initLocator).click();


        List<WebElement> rows2 = driver.findElements(By.xpath("//table[@class='table table-striped']/tbody/tr"));
        List<WebElement> colVals3 = rows2.get(rowNum-1).findElements(By.tagName("td"));

        Assert.assertEquals("199", colVals3.get(3).getText());
        Assert.assertNotEquals("1A", colVals3.get(4).getText());

        }
    }
class deleteBooking {

    WebDriver driver;

    public deleteBooking(WebDriver driver) {
        this.driver = driver;
    }

    public void deleteBook(String flightID) {
        //do a login in the set up first
        driver.findElement(By.linkText("Homepage")).click();

        List<WebElement> rows = driver.findElements(By.xpath("//table[@class='table table-striped']/tbody/tr"));
        //*   /html/body/div/div/div[2]/div/table


        int rowNum = rows.size();
        int rowID = -1;

        for (int i = 1; i < rows.size(); i++) {

            String flightIDNum = rows.get(i).findElements(By.tagName("td")).get(3).getText();
            if (flightIDNum.equals(flightID)) {
                rowID = i;
                break;
            }

        }

        // Get booking id for requested booking
        List<WebElement> colVals2 = rows.get(rowID).findElements(By.tagName("td"));
        colVals2.get(0).findElement(By.linkText("Delete")).click();


        //check if the row was deleted
        driver.findElement(By.linkText("itinerary")).click();

        List<WebElement> rowsNew = driver.findElements(By.xpath("//table[@class='table table-striped']/tbody/tr"));
        int rowNumNew = rowsNew.size();

        Assert.assertNotEquals(rowNumNew, rowNum);
    }
}






