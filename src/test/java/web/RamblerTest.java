package web;

import com.google.common.io.Resources;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import web.simple.WebDriverFactory;
import web.simple.page.LoginPage;

import java.time.Duration;

public class RamblerTest {

    private static final String email = "testautoselenium@rambler.ru";
    private static final String password = "TestAutoSelenium2607";

    //private WebDriver driver;
    private WebDriverWait wait;
    private ThreadLocal<WebDriver> driver = new ThreadLocal<>();

    @BeforeAll
    public static void setChromeBinaries(){
        System.setProperty("webdriver.chrome.driver", Resources.getResource("chromedriver.exe").getPath());
    }

    @BeforeEach
    public void createDriver() {
        driver.set(WebDriverFactory.getWebDriver());
    }

    @AfterEach
    public void disposeDriver() {
        if(driver.get() != null) {
            driver.get().quit();
        }
    }

    @Test
    public void login() {
        Assertions.assertTrue(
                new LoginPage(driver.get())
                        .loginAs(email, password)
                        .isLoggedIn(email)
        );
    }

    @Test
    public void openProfile() {
        Assertions.assertTrue(
                new LoginPage(driver.get())
                        .loginAs(email, password)
                        .openProfile()
                        .isProfileOpened()
        );
    }
}
