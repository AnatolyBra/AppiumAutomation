import core.BaseTest;
import org.junit.Test;
import page.IntroPage;
import page.SearchPage;

public class FirstTest extends BaseTest {

    @Override
    public void setUp() throws Exception {
        super.setUp();
    }

    @Test
    public void testSecond() {
        IntroPage introPage = new IntroPage(driver);
        SearchPage searchPage = new SearchPage(driver);

        introPage.clickSkipButton();

        searchPage.clickSearchInput();
        searchPage.clickBackButton();
        searchPage.backButtonNotVisible();
    }

    //    @Test
    public void testCompareArticleTitle() {
        IntroPage introPage = new IntroPage(driver);
        SearchPage searchPage = new SearchPage(driver);

        String searchText = "Java";
        String substring = "Object-oriented programming language";
        introPage.clickSkipButton();

        searchPage.clickSearchInput();
        searchPage.setTextSearchInput(searchText);
        searchPage.clickSearchBySubstring(substring);
    }
}
