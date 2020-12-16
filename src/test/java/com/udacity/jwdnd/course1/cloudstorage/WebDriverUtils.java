package com.udacity.jwdnd.course1.cloudstorage;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class WebDriverUtils {
    private WebDriver driver;

    public WebDriverUtils(WebDriver driver) {
        this.driver = driver;
    }

    public void waitByVisibility(WebElement locator, int secondsToWait) {
        WebDriverWait wait = new WebDriverWait(driver, secondsToWait);
        wait.until(ExpectedConditions.visibilityOf(locator));
    }

}
