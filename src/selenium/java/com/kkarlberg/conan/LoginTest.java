package com.kkarlberg.conan;

import java.util.List;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;

public class LoginTest {

    private static WebDriver webDriver;

    @BeforeClass
    public static void init() {
        webDriver = new HtmlUnitDriver(true);
    }

    @Test
    public void testLogin() {
        //do the login
        webDriver.get("http://localhost:8383/ConanTheDeployer2/");
        System.err.println(webDriver.getTitle());
        WebElement loginLink = webDriver.findElement(By.linkText("Log in"));
        Assert.assertNotNull(loginLink);
        System.err.println("===========");
        System.err.println(webDriver.getCurrentUrl());
        System.err.println("===========");

        loginLink.click();
        System.err.println("===========");
        System.err.println(webDriver.getCurrentUrl());
        System.err.println("===========");

        WebElement user = webDriver.findElement(By.name("username"));
        WebElement pass = webDriver.findElement(By.name("password"));
        WebElement loginBtn = webDriver.findElement(By.name("submit"));
        Assert.assertNotNull(user);
        Assert.assertNotNull(pass);
        Assert.assertNotNull(loginBtn);

        user.sendKeys("conan");
        pass.sendKeys("kalle");
        loginBtn.click();
        System.err.println("===========");
        System.err.println(webDriver.getCurrentUrl());
        System.err.println("===========");

        List<WebElement> elems = webDriver.findElements(By.linkText("admin"));
        System.err.println("===========");
        System.err.println(elems.size());
        for (WebElement e : elems) {
            System.err.println(e.getTagName());
        }
        System.err.println("===========");

    }
}
