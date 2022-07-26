package web;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class RamblerTest {

    private static final String email = "testautoselenium@rambler.ru";
    private static final String password = "TestAutoSelenium2607";
    private static final String BASE_URL = "https://www.rambler.ru";

    private By openLogin = By.xpath("//span[text()='Вход']");
    private By loginIFrame = By.xpath("//div[@data-id-frame]/iframe");
    private By loginBox = By.id("login");
    private By passwordBox = By.id("password");
    private By processLogin = By.xpath("//*[text()='Войти']");
    private By loggedInEmail = By.xpath("//span[@class='rui__35skI']");

    @Test
    public void login(){
        WebDriver driver = new ChromeDriver();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));

        driver.get(BASE_URL);
        driver.findElement(openLogin).click();

        wait.until(ExpectedConditions.presenceOfElementLocated(loginIFrame));
        driver.switchTo().frame(driver.findElement(loginIFrame));
        wait.until(ExpectedConditions.presenceOfElementLocated(loginBox));
        driver.findElement(loginBox).sendKeys(email);
        wait.until(ExpectedConditions.presenceOfElementLocated(passwordBox));
        driver.findElement(passwordBox).sendKeys(password);
        driver.findElement(processLogin).click();

        Assertions.assertDoesNotThrow(
                () -> wait.until(ExpectedConditions.presenceOfElementLocated(loggedInEmail))
        );

        driver.quit();

    }

}
