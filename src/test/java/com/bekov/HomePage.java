package com.kuntsev;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;

import java.io.File;
import java.io.IOException;

public class HomePage {


    private WebDriver driver;
    private String baseUrl;
    private boolean acceptNextAlert = true;
    private StringBuffer verificationErrors = new StringBuffer();

    public HomePage(WebDriver driver) {
        this.driver = driver;
    }



    public PostPage clickNewPostButton () throws IOException {
        //add function to take screenshot
        File scrFile = ((TakesScreenshot)driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(scrFile, new File("c:\\temp\\home.png"));
        driver.findElement(By.linkText("New Post")).click();
        driver.get("http://localhost:8090/newPost");
        return new PostPage(driver);
    }



}
