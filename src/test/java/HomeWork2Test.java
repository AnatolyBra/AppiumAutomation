import core.BaseTest;
import org.junit.Test;
import page.IntroPage;
import page.SearchPage;

public class HomeWork2Test extends BaseTest {
    @Override
    public void setUp() throws Exception {
        super.setUp();
    }
    @Test
    public void testFirst() {
        IntroPage introPage = new IntroPage(driver);
        SearchPage searchPage = new SearchPage(driver);

        String searchText = "Java";
        String substring = "Object-oriented programming language";

        introPage.clickSkipButton();
        searchPage.clickSearchInput();
        searchPage.assertInputHasText("Search Wikipedia");
        searchPage.setTextSearchInput(searchText);

        searchPage.assertListHasText(substring);

        searchPage.clickSearchBySubstring(substring);
    }

    //Ex3
    @Test
    public void testCancelSearch() {
        IntroPage introPage = new IntroPage(driver);
        SearchPage searchPage = new SearchPage(driver);

        String searchText = "Kiss";

        introPage.clickSkipButton();
        searchPage.clickSearchInput();

        searchPage.assertInputHasText("Search Wikipedia");
        searchPage.setTextSearchInput(searchText);

        searchPage.assertTitleHasText(searchText);
        searchPage.clickBackButton();
        searchPage.backButtonNotVisible();
    }

    //Ex4
    @Test
    public void testCheckWordInSearch() {
        IntroPage introPage = new IntroPage(driver);
        SearchPage searchPage = new SearchPage(driver);

        String searchText = "Kiss";

        introPage.clickSkipButton();

        searchPage.clickSearchInput();
        searchPage.assertInputHasText("Search Wikipedia");
        searchPage.setTextSearchInput(searchText);

        searchPage.assertListHasTitleText(searchText);
    }
}
