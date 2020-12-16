package com.udacity.jwdnd.course1.cloudstorage.pages;

import com.udacity.jwdnd.course1.cloudstorage.WebDriverUtils;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class PageObject {
    protected WebDriver driver;
    protected WebDriverUtils webDriverUtil;


    public PageObject(WebDriver driver){
        this.driver = driver;
        webDriverUtil = new WebDriverUtils(this.driver);
        PageFactory.initElements(driver, this);
    }
}
