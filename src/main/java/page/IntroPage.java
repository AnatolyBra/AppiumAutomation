package page;

import core.BasePage;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class IntroPage extends BasePage {
    /**
     *
     * /intro
     *
     */
    public IntroPage(AppiumDriver driver) {
        super(driver);
    }

    private static final String SKIP_BUTTON = "//*[contains(@text,'Skip')]";

    public void clickSkipButton(){
        this.waitForElementPresentBy(
                By.xpath(SKIP_BUTTON),
                "Cannot find button 'Skip'",
                5
        );
       this.waitForElementByAndClick(
                By.xpath(SKIP_BUTTON),
                "Cannot find button 'Skip'",
                5
        );
    }
}
