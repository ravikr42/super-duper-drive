package com.udacity.jwdnd.course1.cloudstorage.pages;

import org.h2.mvstore.Page;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SignUpPage extends PageObject {


    @FindBy(id = "inputFirstName")
    private WebElement firstNameTxtBox;

    @FindBy(id = "inputLastName")
    private WebElement lastNameTxtBox;

    @FindBy(id = "inputUsername")
    private WebElement usernameTxtBox;

    @FindBy(id = "inputPassword")
    private WebElement passwordTxtBox;

    @FindBy(id = "signUpBtn")
    private WebElement signUpBtn;

    @FindBy(id = "login-link")
    private WebElement loginLink;

    public SignUpPage(WebDriver driver) {
        super(driver);
    }

    public void openSignUpPage(final String url) {
        driver.get(url);
    }

    public void signUpUser(final String firstName, final String lastName, final String userName, final String password) {
        firstNameTxtBox.sendKeys(firstName);
        lastNameTxtBox.sendKeys(lastName);
        usernameTxtBox.sendKeys(userName);
        passwordTxtBox.sendKeys(password);
        signUpBtn.submit();
    }

    public void navigateToLoginPageAfterSignUp() {
        loginLink.click();
    }

    public boolean isSignUpPage() {
        return (driver.getTitle().equalsIgnoreCase("Sign Up"));
    }

}
