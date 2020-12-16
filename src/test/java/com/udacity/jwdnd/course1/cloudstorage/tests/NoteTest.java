package com.udacity.jwdnd.course1.cloudstorage.tests;

import com.github.javafaker.Faker;
import com.udacity.jwdnd.course1.cloudstorage.pages.HomePage;
import com.udacity.jwdnd.course1.cloudstorage.pages.LoginPage;
import com.udacity.jwdnd.course1.cloudstorage.pages.ResultPage;
import com.udacity.jwdnd.course1.cloudstorage.pages.SignUpPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.lang3.RandomStringUtils;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import static java.util.concurrent.TimeUnit.SECONDS;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class NoteTest {
    Logger logger = LoggerFactory.getLogger(CredentialsTest.class);
    @LocalServerPort
    private int port;

    private WebDriver driver;
    private SignUpPage signUpPage;
    private LoginPage loginPage;
    private HomePage homePage;
    private ResultPage resultPage;
    private String userName;
    private String password;
    private Faker faker;

    @BeforeAll
    static void beforeAll() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    public void init() throws InterruptedException {
        this.driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(30, SECONDS);
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(60, SECONDS);
        driver.manage().timeouts().setScriptTimeout(45, SECONDS);
        signUpPage = new SignUpPage(driver);
        loginPage = new LoginPage(driver);
        homePage = new HomePage(driver);
        resultPage = new ResultPage(driver);
        faker = new Faker();


        signUpPage.openSignUpPage("http://localhost:" + this.port + "/signup");
        Assertions.assertTrue(signUpPage.isSignUpPage());
        userName = faker.name().username();
        password = RandomStringUtils.randomAlphanumeric(12);
        signUpPage.signUpUser(faker.name().firstName(), faker.name().lastName(), userName, password);
        logger.info("UserName is : " + userName);
        logger.info("Password is : " + password);
        Thread.sleep(2000);
        loginPage.login(userName, password);
        Assertions.assertTrue(homePage.verifyLogin());
    }

    @AfterEach
    public void afterEach() {
        if (this.driver != null) {
            driver.quit();
        }
    }

    @Test
    public void addNote() throws InterruptedException {
        driver.navigate().refresh();
        String title = "This is note demo";
        homePage.addNote(title, faker.lorem().paragraph());
        Thread.sleep(2000);
        resultPage.returnBack();
        Thread.sleep(2000);
        homePage.openNoteTab();
        Thread.sleep(2000);
        Assertions.assertTrue(driver.getPageSource().contains(title));
    }

    @Test
    public void editNote() throws InterruptedException {
        driver.navigate().refresh();
        String title = "This is note demo";
        homePage.addNote(title, faker.lorem().paragraph());
        Thread.sleep(2000);
        resultPage.returnBack();
        Thread.sleep(2000);
        homePage.openNoteTab();
        Thread.sleep(2000);
        Assertions.assertTrue(driver.getPageSource().contains(title));

        title = "This is new Title";
        homePage.editNote(title);

        Thread.sleep(2000);
        resultPage.returnBack();
        Thread.sleep(2000);
        homePage.openNoteTab();
        Thread.sleep(2000);
        Assertions.assertTrue(driver.getPageSource().contains(title));
    }

    @Test
    public void deleteNote() throws InterruptedException {
        driver.navigate().refresh();
        String title = "This is note demo";
        homePage.addNote(title, faker.lorem().paragraph());
        Thread.sleep(2000);
        resultPage.returnBack();
        Thread.sleep(2000);
        homePage.openNoteTab();
        Thread.sleep(2000);
        Assertions.assertTrue(driver.getPageSource().contains(title));

        homePage.deleteNote();
        Thread.sleep(2000);

        resultPage.returnBack();
        Thread.sleep(2000);
        homePage.openNoteTab();
        Thread.sleep(2000);
        Assertions.assertFalse(driver.getPageSource().contains(title));
    }
}
