import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
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
    public void fistTest()
    {
        WebElement element_to_init_search = driver.findElementByXPath("//*[contains(@text, 'Поиск по Википедии')]");
        element_to_init_search.click();

        WebElement element_to_enter_search_line = waitForElementPresentByXpath("//*[contains(@text, 'Поиск')]","Cannot find search input");
                //driver.findElementByXPath("//*[contains(@text, 'Поиск')]");
        element_to_enter_search_line.sendKeys("Java");
        waitForElementPresentByXpath(
                "//*[@resource-id='org.wikipedia:id/page_list_item_container']//*[@text='Язык программирования']",
                "Cannot find 'Язык программирования' topic searching by 'Java'",
                15
        );

        // System.out.println("first test run");
    }

    private WebElement waitForElementPresentByXpath(String xpath, String error_message, long timeoutInSeconds){
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(error_message + "\n");
        By by = By.xpath(xpath);
        return wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }

    private WebElement waitForElementPresentByXpath(String xpath, String error_message){
        return waitForElementPresentByXpath(xpath, error_message, 5);
    }
}
