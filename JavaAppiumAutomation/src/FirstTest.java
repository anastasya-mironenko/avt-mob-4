import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.net.URL;

public class FirstTest {

    private AppiumDriver driver;

    @Before
    public void setUp() throws Exception
    {
        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability("platformName","Android");
        capabilities.setCapability("deviceName","AndroidTestDevice");
        capabilities.setCapability("platformVersion","6.0");
        capabilities.setCapability("automationName","Appium");
        capabilities.setCapability("appPackage","org.wikipedia");
        capabilities.setCapability("appActivity",".main.MainActivity");
        capabilities.setCapability("app","C:\\Users\\a.voronina\\Documents\\GitHub\\avt-mob-4\\JavaAppiumAutomation\\apks\\org.wikipedia.apk");

        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
    }

    @After
    public void tearDown()
    {
        driver.quit();
    }

    @Test
    public void zad1(){
        waitForElementAndClick(By.xpath("//*[contains(@text, 'Поиск по Википедии')]"), "Cannot find search wiki input", 5);
        waitForElementPresent(By.xpath("//*[contains(@text, 'Поиск')]"),"Cannot find search input", 5);
    }

    @Test
    public void fistTest()
    {
        waitForElementAndClick(By.xpath("//*[contains(@text, 'Поиск по Википедии')]"), "Cannot find search input", 5);
        waitForElementAndSendKeys(By.xpath("//*[contains(@text, 'Поиск')]"), "Java", "Cannot find 'Язык программирования' topic searching by 'Java'", 5);
        waitForElementPresent(
                By.xpath("//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='Язык программирования']"),
                "Cannot find 'Язык программирования' topic searching by 'Java'",
                15
        );

        // System.out.println("first test run");
    }

    @Test
    public void testCancelSearch(){
        waitForElementAndClick(By.id("org.wikipedia:id/search_container"), "Cannot find search input", 5);
        waitForElementAndClick(By.id("org.wikipedia:id/search_close_btn"), "Cannot find X to cancel search", 5);
        waitForElementNotPresent(By.id("org.wikipedia:id/search_close_btn"), "X is still present on the page", 5);
    }

    private WebElement waitForElementPresent(By by, String error_message, long timeoutInSeconds){
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");
        return wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }

    private WebElement waitForElementPresent(By by, String error_message){
        return waitForElementPresent(by, error_message, 5);
    }

    private WebElement waitForElementAndClick(By by, String error_message, long timeoutInSeconds){
        WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
        element.click();
        return element;
    }

    private WebElement waitForElementAndSendKeys(By by, String value, String error_message, long timeoutInSeconds){
        WebElement element = waitForElementPresent(by, error_message, timeoutInSeconds);
        element.sendKeys(value);
        return element;
    }


    private boolean waitForElementNotPresent (By by, String error_message, long timeoutInSeconds){
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + '\n');
        return wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
    }


}
