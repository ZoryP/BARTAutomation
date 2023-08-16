package BART;


import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;

public class GlobalBasePage extends BasePage {
    GlobalBasePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }
}