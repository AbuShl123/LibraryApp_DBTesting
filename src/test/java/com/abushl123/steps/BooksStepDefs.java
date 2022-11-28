package com.abushl123.steps;

import com.abushl123.pages.BookPage;
import com.abushl123.pages.DashBoardPage;
import com.abushl123.utility.BrowserUtil;
import com.abushl123.utility.DB_Util;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import static org.junit.Assert.*;
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
        System.out.println("actualCategoryList = " + actualCategoryList);

    }

    @Then("user should be able to see following categories")

    public void user_should_be_able_to_see_following_categories(List<String> expectedCategoryList) {
        assertEquals(expectedCategoryList, actualCategoryList);

    }


    @When("I open book {string}")
    public void i_open_book(String bookName) {

        System.out.println("bookName = " + bookName);
        BrowserUtil.waitForClickablility(bookPage.search, 5).sendKeys(bookName);
        BrowserUtil.waitForClickablility(bookPage.editBook(bookName), 5).click();

    }

    @Then("verify book categories must match book categories table from db")
    public void verifyBookCategoriesMustMatchBookCategoriesTableFromDb() {
        DB_Util.runQuery("select name from book_categories");

        List<String> expectedCategoryList = DB_Util.getColumnDataAsList(1);


        assertEquals(expectedCategoryList, actualCategoryList);
    }
}
