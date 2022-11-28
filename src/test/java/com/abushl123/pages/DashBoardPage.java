package com.abushl123.pages;

import com.abushl123.utility.Driver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DashBoardPage extends BasePage
{
    public DashBoardPage() {
        PageFactory.initElements(Driver.getDriver(), this);
    }


    @FindBy(id = "borrowed_books")
    public WebElement borrowedBooksNumber;

    @FindBy(xpath = "//h2[@id='user_count']")
    public WebElement usersNumber;

    @FindBy(id = "book_count")
    public WebElement booksNumber;




    public String getModuleCount(String module){
        //h6[normalize-space(.)='Users']//..//h2

        String locator="//h6[normalize-space(.)='"+module+"']//..//h2";

        WebElement elementOfModule = Driver.getDriver().findElement(By.xpath(locator));

        return elementOfModule.getText();
    }

}
