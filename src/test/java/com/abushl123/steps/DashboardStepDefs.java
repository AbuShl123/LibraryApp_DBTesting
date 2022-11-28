package com.abushl123.steps;

import com.abushl123.pages.DashBoardPage;
import com.abushl123.pages.LoginPage;
import com.abushl123.utility.BrowserUtil;
import com.abushl123.utility.DB_Util;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;

public class DashboardStepDefs
{
    String actualUserNumbers;
    String actualBookNumbers;
    String actualBorrowedBookNumbers;
    LoginPage loginPage=new LoginPage();
    DashBoardPage dashBoardPage=new DashBoardPage();


    @Given("the user logged in as {string}")
    public void the_user_logged_in_as(String user) {
        loginPage.login(user);
         BrowserUtil.waitFor(4);
    }
    @When("user gets all information from modules")
    public void user_gets_all_information_from_modules() {

        actualUserNumbers = dashBoardPage.usersNumber.getText();
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

        String expectedUsers = DB_Util.getFirstRowFirstColumn();

        Assert.assertEquals(expectedUsers, actualUserNumbers);

        DB_Util.destroy();
    }
}
