package com.udacity.jwdnd.course1.cloudstorage.tests;

import com.github.javafaker.Faker;
import com.udacity.jwdnd.course1.cloudstorage.pages.HomePage;
import com.udacity.jwdnd.course1.cloudstorage.pages.LoginPage;
import com.udacity.jwdnd.course1.cloudstorage.pages.SignUpPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import java.util.concurrent.TimeUnit;

import static java.util.concurrent.TimeUnit.SECONDS;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class LoginSignUpTest {
    Logger logger = LoggerFactory.getLogger(LoginSignUpTest.class);


    @LocalServerPort
    private int port;

    private WebDriver driver;
    private SignUpPage signUpPage;
    private LoginPage loginPage;
    private HomePage homePage;
    private String userName;
    private String password;
    private Faker faker;

    @BeforeAll
    static void beforeAll() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    public void init() {
        this.driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(10, SECONDS);
        driver.manage().window().maximize();
        driver.manage().timeouts().pageLoadTimeout(20, SECONDS);
        driver.manage().timeouts().setScriptTimeout(15, SECONDS);
        signUpPage = new SignUpPage(driver);
        loginPage = new LoginPage(driver);
        homePage = new HomePage(driver);
        faker = new Faker();
    }

    @AfterEach
    public void afterEach() {
        if (this.driver != null) {
            driver.quit();
        }
    }

    @Test
    public void verifyLoginPage() {
        driver.get("http://localhost:" + this.port + "/login");
        Assertions.assertEquals("Login", driver.getTitle());
    }

    @Test
    public void verifyItsAlwaysLoginPageWithoutSignIn() {
        loginPage.openLoginPage("http://localhost:" + this.port + "/login");
        Assertions.assertEquals("Login", driver.getTitle());
        signUpPage.openSignUpPage("http://localhost:" + this.port + "/home");
        Assertions.assertEquals("Login", driver.getTitle());
        driver.get("http://localhost:" + this.port + "/h2");
        Assertions.assertEquals("Login", driver.getTitle());
    }

    @Test
    public void SignUpAndLoginTest() throws InterruptedException {
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

    @Test
    public void loginLogoutTest() throws InterruptedException {
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
        homePage.logout();
        Assertions.assertEquals("Login", driver.getTitle());
    }
}

