package BART;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.FluentWait;
import java.time.Duration;

public class BasePage {
    @FindBy(xpath = "//button[normalize-space()='Asset Details']")
    WebElement AssetDetails;
    @FindBy(xpath = "//button[normalize-space()='Asset type / Functional Area / System'] ")
    WebElement AssetType;
    @FindBy(xpath = "//div[@data-id='assetDetails.assetTypeOrFunctionalAreaOrSystem.machineOrAssetCode']//div[contains(@class,'css-1hwfws3')]")
    WebElement MachineAssetType;
    @FindBy(xpath = "//button[normalize-space()='Bearing Investigations']")
    WebElement BearingInvestigations;
    public static void clickElementWithJS(WebDriver driver, WebElement element) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("arguments[0].click();", element);
    }
    public static void blurElementWithJS(WebDriver driver, WebElement element) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("arguments[0].blur();", element);
    }
    public static boolean isElementVisibleWithJS(WebDriver driver, WebElement element) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        return (boolean) jsExecutor.executeScript("return arguments[0].offsetWidth > 0 && arguments[0].offsetHeight > 0;", element);
    }
    public static void sendKeysLetterByLetter(WebElement el, String keys) {
        el.click();
        for (int i = 0; i < keys.length(); i++) {
            el.sendKeys(String.valueOf(keys.charAt(i)));
        }
    }
    BasePage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        FluentWait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(40))
                .pollingEvery(Duration.ofSeconds(5))
                .ignoring(NoSuchElementException.class);
    }
}
