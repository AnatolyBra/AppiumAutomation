package page;

import core.BasePage;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;

public class ArticlePage extends BasePage {
    public ArticlePage(AppiumDriver driver) {
        super(driver);
    }

    private static final String FOOTER = "//*[contains(@content-desc,'View article in browser')]",
            TITLE = "//android.view.View[@content-desc='{SUBSTRING}']";

    public void swipeToFooter() {
        this.swipeUpToFindElement(
                By.linkText(FOOTER),
                "Не проскролил до футтера",
                20
        );
    }

    public void titleVisible(String title) {
        this.assertElementPresent(
                By.xpath(titleBySubstringTmp(title)),title);
    }

    private String titleBySubstringTmp(String substring) {
        return TITLE.replace("{SUBSTRING}", substring);
    }
}
