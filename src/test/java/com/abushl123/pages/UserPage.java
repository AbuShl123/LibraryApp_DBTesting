package com.abushl123.pages;

import com.abushl123.utility.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class UserPage {
    public UserPage() {
        PageFactory.initElements(Driver.getDriver(), this);
    }

    @FindBy(xpath = "(//tbody//a[@role='button'])[1]")
    public WebElement editUser;

    @FindBy (name = "full_name")
    public WebElement full_name;

    @FindBy (name = "password")
    public WebElement password;

    @FindBy (name = "email")
    public WebElement email;

    @FindBy(xpath = " //button[@type=\"submit\"]")
    public WebElement saveChanges;

    @FindBy(id = "user_status")
    public WebElement userStatusDropdown;

    @FindBy(css = ".dataTables_info")
    private WebElement userCount;

    @FindBy (css = "div.toast-message")
    public WebElement toastMessage;

    public WebElement editUser(String email) {
        String xpath = "//td[.='"+ email+ "']/..//a";
        return Driver.getDriver().findElement(By.xpath(xpath));
    }
}
