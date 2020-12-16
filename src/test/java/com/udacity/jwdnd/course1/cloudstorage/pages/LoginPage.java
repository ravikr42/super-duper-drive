package com.udacity.jwdnd.course1.cloudstorage.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends PageObject {

    @FindBy(id = "inputUsername")
    private WebElement usernameTxtBox;

    @FindBy(id = "inputPassword")
    private WebElement passwordTxtBox;

    @FindBy(id = "submit-btn")
    private WebElement submitBtn;

    @FindBy(id = "signup-link")
    private WebElement signUpLink;

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    public void openLoginPage(final String url) {
        driver.get(url);
    }

    public void login(final String userName, final String password) {
        usernameTxtBox.sendKeys(userName);
        passwordTxtBox.sendKeys(password);
        submitBtn.submit();
    }



}
