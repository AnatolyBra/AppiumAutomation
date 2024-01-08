import core.BaseTest;
import org.junit.Test;
import org.openqa.selenium.ScreenOrientation;
import page.ArticlePage;
import page.IntroPage;
import page.MenuPage;
import page.SearchPage;

import java.util.List;

public class HomeWork3Test extends BaseTest {

    @Test
    public void testSwitchArticle() {
        IntroPage introPage = new IntroPage(driver);
        SearchPage searchPage = new SearchPage(driver);
        ArticlePage articlePage = new ArticlePage(driver);

        String searchText = "Java";
        String descriptionText = "Island in Indonesia";

        introPage.clickSkipButton();

        searchPage.clickSearchInput();
        searchPage.assertInputHasText("Search Wikipedia");
        searchPage.setTextSearchInput(searchText);

        searchPage.waitForElementByTitleAndDescription(searchText, descriptionText);
//        searchPage.clickByTitleInSearch(searchText);

//        articlePage.swipeToFooter();
    }


    @Test
    public void testArticleSaveToMyList() {
        IntroPage introPage = new IntroPage(driver);
        SearchPage searchPage = new SearchPage(driver);
        ArticlePage articlePage = new ArticlePage(driver);
        MenuPage menuPage = new MenuPage(driver);

        driver.rotate(ScreenOrientation.PORTRAIT);

        List<String> titles = List.of("Java", "Appium");
        String titleName = "My favorite articles";

        introPage.clickSkipButton();

        boolean listExist = false;
        for (String title : titles) {
            searchPage.clickSearchInput();
            searchPage.assertInputHasText("Search Wikipedia");
            searchPage.setTextSearchInput(title);
            searchPage.clickByTitleInSearch(title);
            searchPage.clickSaveButton();

            if (!listExist) {

                menuPage.clickAddToList();
                menuPage.setNameOfThisList(titleName);
                menuPage.clickOkButton();

            } else {

                menuPage.clickAddToList();
                menuPage.clickCurrentList(titleName);
            }
//ушли из статьи
            searchPage.clickBackButton();
//ушли из поиска
            searchPage.clickBackButton();
            searchPage.backButtonNotVisible();
            listExist = true;
        }

        searchPage.clickSaveButton();

        menuPage.clickFolderInSaved(titleName);

        titles.forEach(menuPage::assertTitleByList);

        menuPage.deleteToSwipeArticle(titles.get(0));

        menuPage.titleNotVisible(titles.get(0));

        menuPage.assertTitleByList(titles.get(1));

        menuPage.clickTitleOfArticleByList(titles.get(1));

        articlePage.titleVisible(titles.get(1));

    }

    @Test
    public void testCheckTitle() {
        IntroPage introPage = new IntroPage(driver);
        SearchPage searchPage = new SearchPage(driver);
        ArticlePage articlePage = new ArticlePage(driver);

        String searchText = "Appium";
        introPage.clickSkipButton();

        searchPage.clickSearchInput();

        searchPage.assertInputHasText("Search Wikipedia");

        searchPage.setTextSearchInput(searchText);
        searchPage.clickByTitleInSearch(searchText);

        articlePage.titleVisible(searchText);

        driver.rotate(ScreenOrientation.LANDSCAPE);
    }

//    private void addTitleToList(List<String> titles, String titleName) {
//        SearchPage searchPage = new SearchPage(driver);
//        MenuPage menuPage = new MenuPage(driver);
//
//        boolean listExist = false;
//        for (String title : titles) {
//            searchPage.clickSearchInput();
//            searchPage.assertInputHasText("Search Wikipedia");
//            searchPage.setTextSearchInput(title);
//            searchPage.clickByTitleInSearch(title);
//            searchPage.clickSavedButton();
//
//            if (!listExist) {
//
//                menuPage.clickAddToList();
//                menuPage.setNameOfThisList(titleName);
//                menuPage.clickOkButton();
//
//            } else {
//                menuPage.clickAddToList();
//                menuPage.clickCurrentList(titleName);
//            }
////ушли из статьи
//            searchPage.clickBackButton();
////ушли из поиска
//            searchPage.clickBackButton();
//            searchPage.backButtonNotVisible();
//            listExist = true;
//        }
//    }
}
