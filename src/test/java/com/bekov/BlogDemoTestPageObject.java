package com.bekov;
import org.junit.*;

import static java.time.Duration.ofSeconds;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
public class BlogDemoTestPageObject {
    private WebDriver driver;
    private String baseUrl;
    private boolean acceptNextAlert = true;
    private StringBuffer verificationErrors = new StringBuffer();
    JavascriptExecutor js;
    @Before
    public void setUp() throws Exception {
        System.setProperty("webdriver.chrome.driver", "C:\\Users\\bekov\\Documents\\univer\\2022\\TA\\chromedriver_win32\\chromedriver.exe");
        driver = new ChromeDriver();
        js = (JavascriptExecutor) driver;
        baseUrl = "https://www.google.com/";
        driver.get("http://localhost:8090/login");
        //driver.manage().timeouts().implicitlyWait(60);
        js = (JavascriptExecutor) driver;
    }

    @Test
    public void testUntitledTestCase() throws Exception {

        LoginPage loginpage = new LoginPage(driver);
        HomePage homePage =  loginpage.Login("user", "password");
        PostPage postPage = homePage.clickNewPostButton();
        BlogPage blogPage = postPage.AddPost("New Mega Post", "Post_Body");
    }

   @After
    public void tearDown() throws Exception {
        driver.quit();
        String verificationErrorString = verificationErrors.toString();
        if (!"".equals(verificationErrorString)) {
            Assert.fail(verificationErrorString);
        }
    }

    private boolean isElementPresent(By by) {
        try {
            driver.findElement(by);
            return true;
        } catch (NoSuchElementException e) {
            return false;
        }
    }

    private boolean isAlertPresent() {
        try {
            driver.switchTo().alert();
            return true;
        } catch (NoAlertPresentException e) {
            return false;
        }
    }

    private String closeAlertAndGetItsText() {
        try {
            Alert alert = driver.switchTo().alert();
            String alertText = alert.getText();
            if (acceptNextAlert) {
                alert.accept();
            } else {
                alert.dismiss();
            }
            return alertText;
        } finally {
            acceptNextAlert = true;
        }
    }
}
