package com.abushl123.steps;

import com.abushl123.pages.UserPage;
import com.abushl123.utility.BrowserUtil;
import com.abushl123.utility.DB_Util;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.openqa.selenium.support.ui.Select;

import static org.junit.Assert.*;

public class UsersStepDefs {
    UserPage userPage = new UserPage();

    String userEmail;
    String newStatus;

    @When("the user clicks Edit User button")
    public void the_user_clicks_edit_user_button() {
        BrowserUtil.waitFor(4);
        userPage.editUser.click();
        BrowserUtil.waitFor(1);
        userEmail = userPage.email.getAttribute("value");
        System.out.println("userEmail = " + userEmail);
    }

    @When("the user changes user status ACTIVE to {string}")
    public void the_user_changes_user_status_to(String newStatus) {
        BrowserUtil.waitFor(2);
        Select statusDropdown = new Select(userPage.userStatusDropdown);
        statusDropdown.selectByValue(newStatus);
        this.newStatus = statusDropdown.getFirstSelectedOption().getText();
        System.out.println("this.newStatus = " + this.newStatus);
    }

    @When("the user clicks save changes button")
    public void the_user_clicks_save_changes_button() {
        BrowserUtil.waitFor(2);
        userPage.saveChanges.click();
    }

    @Then("{string} message should appear")
    public void message_should_appear(String messageText) {
        assertTrue(userPage.toastMessage.isDisplayed());
        assertEquals(messageText, userPage.toastMessage.getText());
    }

    @Then("the users should see same status for related user in database")
    public void the_users_should_see_same_status_for_related_user_in_database() {
        BrowserUtil.waitFor(4);
        String query = "select status from users where email='" + userEmail + "';";
        DB_Util.runQuery(query);
        String expectedStatus = DB_Util.getFirstRowFirstColumn();

        System.out.println("query = " + query);
        System.out.println("expectedStatus = " + expectedStatus);
        assertEquals(expectedStatus, newStatus);
    }

    @Then("the user changes current user status INACTIVE to {string}")
    public void the_user_changes_current_user_status_to(String newStatus) {
        BrowserUtil.waitFor(4);
        userPage.editUser(userEmail).click();
        BrowserUtil.selectByValue(userPage.userStatusDropdown, newStatus);
        System.out.println("he-he");
    }
}
