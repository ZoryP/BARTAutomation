package BART;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.FluentWait;

import java.time.Duration;

public class LoginPage {
    WebDriver driver;
    @FindBy(id = "email")
    WebElement email;
    @FindBy(id = "password")
    WebElement password;
    @FindBy(className = "button__text")
    WebElement enterBtn;
    @FindBy(xpath = "//div[@class='login__errors']")
    WebElement ErrorLoginMassage;
    @FindBy(xpath = "//a[normalize-space()='Forgot password?']")
    WebElement ForgotPassword;
    @FindBy(xpath = "//div[@class='welcome-information__page-title']")
    WebElement WelcomeMassage;

    public void login(String email, String password)  {
        this.email.click();
        this.email.sendKeys(email);
        this.password.click();
        this.password.sendKeys(password);
    }
    public void clearFields() {
        email.clear();
        password.clear();
    }
    LoginPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
        FluentWait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(40))
                .pollingEvery(Duration.ofSeconds(5))
                .ignoring(NoSuchElementException.class);
    }
}