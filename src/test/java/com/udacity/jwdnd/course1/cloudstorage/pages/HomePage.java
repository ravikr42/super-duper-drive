package com.udacity.jwdnd.course1.cloudstorage.pages;

import com.github.javafaker.Faker;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class HomePage extends PageObject {

    public HomePage(WebDriver driver) {
        super(driver);
    }

    @FindBy(id = "logout-button")
    private WebElement logoutBtn;

    @FindBy(id = "nav-credentials-tab")
    private WebElement credentialTab;

    @FindBy(id = "nav-notes-tab")
    private WebElement noteTab;


    @FindBy(id = "nav-files-tab")
    private WebElement fileTab;

    @FindBy(id = "add-credential-btn")
    private WebElement addCredBtn;

    @FindBy(id = "add-note-button")
    private WebElement addNoteBtn;

    @FindBy(id = "noteModalLabel")
    private WebElement noteModalLabel;

    @FindBy(id = "note-title")
    private WebElement noteTitleTxtBox;

    @FindBy(id = "note-description")
    private WebElement noteDescTextBox;

    @FindBy(id = "note-save-changes")
    private WebElement noteSaveBtn;

    @FindBy(id = "credential-url")
    private WebElement credUrlTxtBox;

    @FindBy(id = "credential-username")
    private WebElement credUserNameTxtBox;

    @FindBy(id = "credential-password")
    private WebElement credPasswordTxtBox;

    @FindBy(id = "cred-save-changes")
    private WebElement credSaveChangesBtn;

    @FindBy(id = "cred-edit-btn")
    private WebElement credEditBtn;

    @FindBy(id = "cred-delete-link")
    private WebElement credDeleteLink;

    @FindBy(id = "note-edit-btn")
    private WebElement noteEditBtn;

    @FindBy(id = "note-delete-link")
    private WebElement noteDeleteLink;

    @FindBy(id = "credentialModalLabel")
    private WebElement credentialModalLabel;

    public boolean verifyLogin() {
        try {
            webDriverUtil.waitByVisibility(logoutBtn, 10);
            return true;
        } catch (Exception e) {
            return false;
        }
    }


    public boolean verifyHomePage() {
        try {
            webDriverUtil.waitByVisibility(fileTab, 15);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    public void logout() {
        logoutBtn.submit();
    }

    public void openCredentialTab() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOf(credentialTab)).click();
    }

    public void openNoteTab() {
        WebDriverWait wait = new WebDriverWait(driver, 10);
        wait.until(ExpectedConditions.visibilityOf(noteTab)).click();
    }

    public void addCredential(String url, String username, String password) {
        openCredentialTab();
        webDriverUtil.waitByVisibility(addCredBtn, 15);
        addCredBtn.click();
        webDriverUtil.waitByVisibility(credentialModalLabel, 15);
        credUrlTxtBox.sendKeys(url);
        credUserNameTxtBox.sendKeys(username);
        credPasswordTxtBox.sendKeys(password);
        credSaveChangesBtn.click();
    }


    public void editCredential(String username) {
        openCredentialTab();
        webDriverUtil.waitByVisibility(credEditBtn, 15);
        credEditBtn.click();
        webDriverUtil.waitByVisibility(credentialModalLabel, 15);
        credUserNameTxtBox.clear();
        credUserNameTxtBox.sendKeys(username);
        credSaveChangesBtn.click();
    }

    public void deleteCredetial() {
        credDeleteLink.click();
    }

    public void addNote(String title, String desc) {
        openNoteTab();
        webDriverUtil.waitByVisibility(addNoteBtn, 15);
        addNoteBtn.click();
        webDriverUtil.waitByVisibility(noteModalLabel, 15);
        noteTitleTxtBox.sendKeys(title);
        noteDescTextBox.sendKeys(desc);
        noteSaveBtn.click();
    }

    public void editNote(String title) {
        openNoteTab();
        webDriverUtil.waitByVisibility(addNoteBtn, 15);
        noteEditBtn.click();
        webDriverUtil.waitByVisibility(noteModalLabel, 15);
        noteTitleTxtBox.clear();
        noteTitleTxtBox.sendKeys(title);
        noteSaveBtn.click();
    }

    public void deleteNote(){
        noteDeleteLink.click();
    }


}
