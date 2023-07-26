package BART;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.FluentWait;
import java.util.List;
import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
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
    @FindBy(xpath = "//button[normalize-space()='Bearing 1']")
    WebElement Bearing1;
    @FindBy(xpath = "//button [text ()='Component Parts Investigation']")
    WebElement Components;
    @FindBy(xpath = "//button [text ()='Outer Ring']")
    WebElement OuterRing;
    @FindBy(xpath = "//button [text ()='Rolling Elements']")
    WebElement RollingElements;
    @FindBy(xpath = "//button [text ()='Analysis']")
    WebElement Analysis;
    @FindBy(xpath = "//div[@class='react-modal__header ai-modal__header']")
    WebElement AfaModal;
    @FindBy(xpath = "//button [text ()='Inner Ring']")
    WebElement InnerRing;
    @FindBy(xpath = "//button[normalize-space()='Bearing Type']")
    WebElement BearingTypeSection;
    @FindBy(xpath = "//div[@class='live-preview-key-value__header live-preview-key-value__bearingheader']//span[contains(text(),'PEER / SKF')]")
    WebElement LpSkfOther;
    @FindBy(xpath = "//*[name()='polygon' and @id='Fill-6']")
    WebElement SaveButton;

    @FindBy(css = ".live-preview-images-item__media-container")
    List<WebElement> LpImageContainer;
    @FindBy(css = ".react-modal__header.upload-modal__header")
    WebElement ModalDialogForImages;
    @FindBy(css = ".dropzone-inputLabel")
    WebElement FileInput;
    @FindBy(xpath = "//span[@class='button__text' and contains(., 'Upload figures')]")
    WebElement Upload;
    @FindBy(xpath = "//span[@class='button__text' and contains(., 'Close')]")
    WebElement Close;


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
    public void clickSKFDetails(WebDriver driver) {
        WebElement SKFDetails = driver.findElement(By.xpath("//button[normalize-space()='SKF Details']"));
        BasePage.clickElementWithJS(driver, SKFDetails);
    }

    public void clickSKFOther(WebDriver driver) {
        WebElement skfOther = driver.findElement(By.xpath("//div[@class='accordion']//div[@class='accordion__content accordion__content--is-open']//div[@class='accordion__content-form']//div[@class='componentContainer']//div//div[@class='componentContainer']//div//div[contains(@class,'css-1hwfws3')]"));
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("arguments[0].click();", skfOther);
    }
    public void enterSKFOtherValue(WebDriver driver, String value) {
        WebElement skfOtherInput = driver.findElement(By.xpath("//div[@class='accordion']//div[@class='accordion__content accordion__content--is-open']//div[@class='accordion__content-form']//div[@class='componentContainer']//div//div[@class='componentContainer']//div//div[contains(@class,'css-1hwfws3')]"));
        Actions actions = new Actions(driver);
        actions.sendKeys(skfOtherInput, value).perform();
        actions.sendKeys(Keys.ENTER).build().perform();

    }
    public void clickSKFSerialNumber(WebDriver driver) {
        WebElement skfSerialNumber = driver.findElement(By.xpath("//div[@class='accordion']//div[@class='accordion__content accordion__content--is-open']//div[@class='accordion__content-form']//div[@class='componentContainer']//div//div[@class='componentContainer']//div//div[contains(@class,'css-1wa3eu0-placeholder')][normalize-space()='Select or free text']"));
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("arguments[0].click();", skfSerialNumber);
    }
    public void enterSKFSerialNumberValue(WebDriver driver, String value) {
        WebElement skfSerialNumberInput = driver.findElement(By.xpath("//div[@class='accordion']//div[@class='accordion__content accordion__content--is-open']//div[@class='accordion__content-form']//div[@class='componentContainer']//div//div[@class='componentContainer']//div//div[contains(@class,'css-1wa3eu0-placeholder')][normalize-space()='Select or free text']"));
        Actions actions = new Actions(driver);
        actions.sendKeys(skfSerialNumberInput, value).perform();
        actions.sendKeys(Keys.ENTER).build().perform();
    }
    public static void sendKeysLetterByLetter(WebElement el, String keys) {
        el.click();
        for (int i = 0; i < keys.length(); i++) {
            el.sendKeys(String.valueOf(keys.charAt(i)));
        }
    }
    public void uploadImages(WebDriver driver) throws InterruptedException, AWTException {
        String[] filePaths = {
                "\"C:\\Users\\ZornicaPetkova\\Desktop\\istockphoto-1498223365-1024x1024.jpg\"",
                "\"C:\\Users\\ZornicaPetkova\\Desktop\\New folder\\R - Copy - Copy.gif\"",
                "\"C:\\Users\\ZornicaPetkova\\Desktop\\3 - Copy.jpg\""
        };
        Thread.sleep(4000);
        Robot robot = new Robot();
        for (String filePath : filePaths) {
            StringSelection stringSelection = new StringSelection(filePath);
            Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);
            robot.keyPress(KeyEvent.VK_CONTROL);
            robot.keyPress(KeyEvent.VK_V);
            robot.keyRelease(KeyEvent.VK_V);
            robot.keyRelease(KeyEvent.VK_CONTROL);
            robot.keyPress(KeyEvent.VK_ENTER);
            robot.keyRelease(KeyEvent.VK_ENTER);
            Thread.sleep(6000);
        }
    }
    BasePage(WebDriver driver) {
        PageFactory.initElements(driver, this);
        FluentWait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(40))
                .pollingEvery(Duration.ofSeconds(5))
                .ignoring(NoSuchElementException.class);
    }
    public static void clickFailureMode(WebDriver driver) {
        WebElement failureMode = driver.findElement(By.xpath("//label[@for='failure0']"));
        failureMode.click();
    }
    public static void clickDoneButton(WebDriver driver) {
        WebElement doneButton = driver.findElement(By.xpath("//span[@class='button__text' and contains(., 'Done')]"));
        doneButton.click();
    }
    public static boolean isThankYouMessageDisplayed(WebDriver driver) {
        WebElement thankYouMsg = driver.findElement(By.xpath("//div[@class='react-modal__body update-modal__body' and contains(., 'Thank you for using the Augmented Failure Analysis')]"));
        return thankYouMsg.isDisplayed();
    }
    public static void clickCloseAFAButton(WebDriver driver) {
        WebElement closeAFA = driver.findElement(By.xpath("//span[@class='button__text' and contains(., 'Close')]"));
        closeAFA.click();
    }
    public static boolean isLPAFADisplayed(WebDriver driver) {
        WebElement lpAFA = driver.findElement(By.xpath("//div[@class='live-preview-key-value__value' and contains(., 'Moisture corrosion (5.3.2) based on Augmented Failure Analysis')]"));
        return lpAFA.isDisplayed();
    }
}
