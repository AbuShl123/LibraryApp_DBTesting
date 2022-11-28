package com.abushl123.steps;

import com.abushl123.pages.DashBoardPage;
import com.abushl123.pages.LoginPage;
import com.abushl123.utility.BrowserUtil;
import com.abushl123.utility.DB_Util;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import static org.junit.Assert.*;

public class DashboardStepDefs {
    // re-usable fields
    String actualUserNumbers;
    String actualBookNumbers;
    String actualBorrowedBookNumbers;

    // pages
    LoginPage loginPage = new LoginPage();
    DashBoardPage dashBoardPage = new DashBoardPage();


    // steps
    @Given("the user logged in as {string}")
    public void the_user_logged_in_as(String user) {
        loginPage.login(user);
         BrowserUtil.waitFor(7);
    }

    @When("user gets all information from modules")
    public void user_gets_all_information_from_modules() {
        actualUserNumbers = dashBoardPage.getModuleCount("Users");
        System.out.println("actualUserNumbers = " + actualUserNumbers);

        actualBookNumbers = dashBoardPage.booksNumber.getText();
        System.out.println("actualBookNumbers = " + actualBookNumbers);

        actualBorrowedBookNumbers = dashBoardPage.borrowedBooksNumber.getText();
        System.out.println("actualBorrowedBookNumbers = " + actualBorrowedBookNumbers);
    }

    @Then("the information should be same with database")
    public void theInformationShouldBeSameWithDatabase() {
        DB_Util.createConnection();

        DB_Util.runQuery("select count(*) from users");
        String expectedUserNumbers = DB_Util.getFirstRowFirstColumn();
        assertEquals(expectedUserNumbers, actualUserNumbers);

        DB_Util.runQuery("select count(*) from books");
        String expectedBooksNumber = DB_Util.getFirstRowFirstColumn();
        assertEquals(expectedBooksNumber, actualBookNumbers);

        DB_Util.runQuery("select count(*) from book_borrow\n" +
                "where is_returned=0");
        String expectedBorrowedBooksNumber = DB_Util.getFirstRowFirstColumn();
        assertEquals(expectedBorrowedBooksNumber, actualBorrowedBookNumbers);


        DB_Util.destroy();
    }
}
