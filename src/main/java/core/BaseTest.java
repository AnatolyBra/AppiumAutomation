package core;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import junit.framework.TestCase;
import org.openqa.selenium.remote.DesiredCapabilities;

import java.net.URL;

abstract public class BaseTest extends TestCase {
    protected AppiumDriver driver;
    private String appiumURL = "http://127.0.0.1:4723/wd/hub";
    private String appiumDirectory = "/Users/gudvin/IdeaProjects/AppiumAutomantion/apk/wikipedia-2-7-50463-r-2023-12-04.apk";

    @Override
    public void setUp() throws Exception {
        super.setUp();
        DesiredCapabilities capabilities = new DesiredCapabilities();

        capabilities.setCapability("platformName", "Android");
        capabilities.setCapability("deviceName", "AndroidTestName");
        capabilities.setCapability("platformVersion", "8");
        capabilities.setCapability("automationName", "Appium");
        capabilities.setCapability("appPackage", "org.wikipedia");
        capabilities.setCapability("appActivity", ".main.MainActivity");
        capabilities.setCapability("app", appiumDirectory);

        driver = new AndroidDriver(new URL(appiumURL), capabilities);
    }

    @Override
    public void tearDown() throws Exception {
        driver.quit();
        super.tearDown();
    }


}
