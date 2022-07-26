package web.simple.page;

import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;

public class ProfilePage extends BaseLoggedInPage{
    private By profilePageTitle = By.xpath("//h1[text()='Test Auto']");

    public ProfilePage(WebDriver driver) {
        super(driver);

        driver.switchTo().defaultContent();
    }

    public boolean isProfileOpened(){
        try {
            return waitVisibility(profilePageTitle).isDisplayed();
        } catch (NoSuchElementException e){
            e.printStackTrace();
            return false;
        }
    }
}
