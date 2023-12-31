package core;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import io.appium.java_client.android.AndroidDriver;
import org.junit.After;
import org.junit.Before;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import page.SearchPage;

import java.net.URL;
import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

abstract public class BaseTest {
    protected AppiumDriver driver;
    private final SearchPage searchPage = new SearchPage();

    @Before
    public void setUp() throws Exception {
        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("deviceName", "AndroidTestName");
        capabilities.setCapability("platformVersion", "8");
        capabilities.setCapability("automationName", "Appium");
        capabilities.setCapability("appPackage", "org.wikipedia");
        capabilities.setCapability("appActivity", ".main.MainActivity");
//        capabilities.setCapability("automationName", "uiautomator2");
        capabilities.setCapability("app", "/Users/gudvin/IdeaProjects/AppiumAutomantion/apk/wikipedia-2-7-50463-r-2023-12-04.apk");

        driver = new AndroidDriver(new URL("http://127.0.0.1:4723/wd/hub"), capabilities);
        BasePage.setDriver(driver);
    }

    @After
    public void turnDown() {
        driver.quit();
    }

    public WebElement waitForElementPresentBy(By by, String errorMessage, long timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(errorMessage + "\n");
        return wait.until(ExpectedConditions.presenceOfElementLocated(by));
    }

    public WebElement waitForElementPresentBy(By by, String errorMessage) {
        return waitForElementPresentBy(by, errorMessage, 5);
    }

    public WebElement waitForElementByAndClick(By by, String errorMessage, long timeoutInSeconds) {
        WebElement element = waitForElementPresentBy(by, errorMessage, timeoutInSeconds);
        element.click();
        return element;
    }

    public WebElement waitForElementByAndSendKeys(By by, String value, String errorMessage, long timeoutInSeconds) {
        WebElement element = waitForElementPresentBy(by, errorMessage, timeoutInSeconds);
        element.sendKeys(value);
        return element;
    }

    public boolean waitForElementNotPresent(By by, String errorMessage, long timeoutInSeconds) {
        WebDriverWait wait = new WebDriverWait(driver, timeoutInSeconds);
        wait.withMessage(errorMessage + "\n");
        return wait.until(ExpectedConditions.invisibilityOfElementLocated(by));
    }

    public void assertElementHasText(By by, String expected) {
        WebElement element = waitForElementPresentBy(
                by,
                "Text not found in this locator",
                15
        );
        String actual = element.getAttribute("text");
        assertEquals("Text not found in this locator", expected, actual);
    }

    public void assertElementPresent(By by, String expected) {
        WebElement element = waitForElementPresentBy(
                by,
                "Text not found in this locator for title",
                15
        );
        String actual = element.getAttribute("contentDescription");
        assertEquals("Text not found in this locator", expected, actual);
    }

    public void assertElementContainsText(By by, String expected) {
        WebElement element = waitForElementPresentBy(
                by,
                "Text not found in this locator",
                15
        );
        String actualText = element.getAttribute("text");
        boolean actual = actualText.contains(expected);
        assertTrue("Text not contains " + actualText, actual);
    }

    public void assertElementContainsTextSearchList(String expected) {
        List<WebElement> listOfSearch = driver.findElements(searchPage.getSearchResultsTitleId());

        for (WebElement element : listOfSearch) {
            String actualText = element.getAttribute("text");
            boolean actual = actualText.contains(expected);
            assertTrue("Text not contains " + actualText, actual);
        }
    }

    protected void swipeUp(int timeOfSwipe) {
        TouchAction action = new TouchAction(driver);
        Dimension size = driver.manage().window().getSize();
        int x = size.width / 2;
        int startY = (int) (size.height * 0.8);
        int endY = (int) (size.height * 0.2);

        action
                .press(x, startY)
                .waitAction(timeOfSwipe)
                .moveTo(x, endY)
                .release()
                .perform();
    }

    protected void swipeUpQuick() {
        swipeUp(200);
    }

    protected void swipeUpToFindElement(By by, String errorMessage, int maxSwipes) {
        int alreadySwiped = 0;
        while (driver.findElements(by).isEmpty()) {
            if (alreadySwiped > maxSwipes) {
                waitForElementPresentBy(by, "Cannot find element by swiping up. \n" + errorMessage, 0);
                return;
            }
            swipeUpQuick();
            ++alreadySwiped;
        }
    }

    protected void swipeElementToLeft(By by, String errorMessage) {
        WebElement element = waitForElementPresentBy(by, errorMessage, 10);

        int leftX = element.getLocation().getX();
        int rightX = leftX + element.getSize().getWidth();

        int upperY = element.getLocation().getY();
        int lowerY = upperY + element.getSize().getHeight();

        int middleY = (upperY + lowerY) / 2;

        TouchAction action = new TouchAction(driver);
        action
                .press(rightX, middleY)
                .waitAction(150)
                .moveTo(leftX, middleY)
                .release()
                .perform();
    }


}
