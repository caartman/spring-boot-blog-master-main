package com.bekov;
import org.openqa.selenium.*;
public class BlogPage {
    private WebDriver driver;
    private String baseUrl;
    private boolean acceptNextAlert = true;
    private StringBuffer verificationErrors = new StringBuffer();
    JavascriptExecutor js;

    public BlogPage(WebDriver driver) {
        this.driver = driver;
    }
}
