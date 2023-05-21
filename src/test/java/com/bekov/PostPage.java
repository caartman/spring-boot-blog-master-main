package com.kuntsev;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.io.IOException;

public class PostPage {
    private WebDriver driver;
    private String baseUrl;
    private boolean acceptNextAlert = true;
    private StringBuffer verificationErrors = new StringBuffer();
    JavascriptExecutor js;



    public PostPage(WebDriver driver) {
        this.driver = driver;
    }

    public void SetTitle(String title) {
        driver.findElement(By.id("title")).click();
        driver.findElement(By.id("title")).clear();
        driver.findElement(By.id("title")).sendKeys(title);
    }
    public void SetPostBody(String body) {
        driver.findElement(By.id("body")).clear();
        driver.findElement(By.id("body")).sendKeys(body);
    }
    public BlogPage AddPost(String title, String body) throws IOException {

        driver.findElement(By.xpath("//input[@value='Save']")).click();;
        SetTitle(title);
        SetPostBody(body);
        return clickSaveButton();
    }
    public BlogPage clickSaveButton() throws IOException {
        //add function to take screenshot
        File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile, new File("c:\\temp\\post.png"));
        driver.findElement(By.xpath("//input[@value='Save']")).click();
        driver.get("http://localhost:8090/blog/user");
        return new BlogPage(driver);
    }
}
