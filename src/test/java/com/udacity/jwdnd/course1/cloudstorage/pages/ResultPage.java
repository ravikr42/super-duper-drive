package com.udacity.jwdnd.course1.cloudstorage.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class ResultPage extends PageObject {

    public ResultPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(id = "return-btn-here")
    private WebElement returnBack;


    public void returnBack() {
        WebDriverWait wait = new WebDriverWait(driver, 15);
        wait.until(ExpectedConditions.visibilityOf(returnBack)).click();
    }
}
