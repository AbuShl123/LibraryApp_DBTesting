package com.abushl123.steps;

import com.abushl123.pages.DashBoardPage;
import com.abushl123.pages.LoginPage;
import com.abushl123.utility.BrowserUtil;
import com.abushl123.utility.DB_Util;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import static org.junit.Assert.*;

public class LoginStepDefs {
    LoginPage loginPage = new LoginPage();
    DashBoardPage dashBoardPage = new DashBoardPage();

    String actualUsername;
    String email;

    @Given("the user logged in  {string} and {string}")
    public void the_user_logged_in_and(String email, String password) {
        loginPage.login(email, password);
        BrowserUtil.waitFor(10);
        this.email = email;
    }

    @When("user gets username from user fields")
    public void user_gets_username_from_user_fields() {
        this.actualUsername = dashBoardPage.accountHolderName.getText();
    }

    @Then("the username should be same with database")
    public void the_username_should_be_same_with_database() {
        String query = "select full_name from users where email='" + email + "';";
        DB_Util.runQuery(query);

        String expectedUsername = DB_Util.getFirstRowFirstColumn();

        assertEquals(expectedUsername, actualUsername);
    }

}
