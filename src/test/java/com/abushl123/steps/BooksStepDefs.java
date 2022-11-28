package com.abushl123.steps;

import com.abushl123.pages.BookPage;
import com.abushl123.pages.DashBoardPage;
import com.abushl123.utility.BrowserUtil;
import com.abushl123.utility.DB_Util;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.support.ui.Select;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

public class BooksStepDefs {
    BookPage bookPage=new BookPage();
    List<String> actualCategoryList;


    @When("the user navigates to {string} page")
    public void the_user_navigates_to_page(String moduleName) {
        new DashBoardPage().navigateModule(moduleName);
    }


    @When("the user gets all book categories in webpage")
    public void the_user_gets_all_book_categories_in_webpage() {
        actualCategoryList=BrowserUtil.getAllSelectOptions(bookPage.mainCategoryElement);
        actualCategoryList.remove(0);
    }

    @Then("user should be able to see following categories")

    public void user_should_be_able_to_see_following_categories(List<String> expectedCategoryList) {
        assertEquals(expectedCategoryList, actualCategoryList);
    }


    @When("I open book {string}")
    public void i_open_book(String bookName) {
        BrowserUtil.waitForClickablility(bookPage.search, 5).sendKeys(bookName);
        BrowserUtil.waitFor(2);
        BrowserUtil.waitForClickablility(bookPage.editBook(bookName), 5).click();

    }

    @Then("verify book categories must match book categories table from db")
    public void verifyBookCategoriesMustMatchBookCategoriesTableFromDb() {
        DB_Util.runQuery("select name from book_categories");
        List<String> expectedCategoryList = DB_Util.getColumnDataAsList(1);
        assertEquals(expectedCategoryList, actualCategoryList);
    }

    @Then("book information must match the database for {string}")
    public void bookInformationMustMatchTheDatabaseFor(String providedBook) {
        BrowserUtil.waitFor(3);

        String actualBookName = bookPage.bookName.getAttribute("value");
        String actualYear = bookPage.year.getAttribute("value");
        String actualAuthor = bookPage.author.getAttribute("value");
        String actualDescription = bookPage.description.getAttribute("value");
        String actualIsbn = bookPage.isbn.getAttribute("value");

        Select categories = new Select(bookPage.book_category_id);
        String actualCategory = categories.getFirstSelectedOption().getText();

        List<String> actualBookInfo = Arrays.asList(
                actualBookName, actualYear, actualAuthor,
                actualDescription, actualIsbn, actualCategory
        );

        String query = "select b.name, b.year, b.author, b.description, b.isbn, c.name\n" +
                    "from books b join book_categories c\n" +
                    "on b.book_category_id = c.id\n" +
                    "where b.name='" + providedBook + "';";
        DB_Util.runQuery(query);

        List<String> expectedBookInfo = DB_Util.getRowDataAsList(1);

        for (int i = 0; i < actualBookInfo.size(); i++) {
            assertEquals(expectedBookInfo.get(i), actualBookInfo.get(i));
        }
    }
}
