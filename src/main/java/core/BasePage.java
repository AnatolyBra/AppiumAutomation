package core;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.TouchAction;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import page.IntroPage;
import page.SearchPage;

import static junit.framework.TestCase.assertEquals;

public class BasePage extends BaseTest{
    protected AppiumDriver driver;
    public BasePage(AppiumDriver driver){
        this.driver = driver;
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
        Assert.assertEquals("Text not found in this locator", expected, actual);
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
