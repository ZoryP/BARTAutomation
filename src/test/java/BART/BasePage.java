package BART;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.FluentWait;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.time.Duration;
import java.util.List;

public class BasePage {
    @FindBy(xpath = "(//*[@class='button button--icon-only' and contains(., 'Approve')])[1]")
    WebElement ApproveBtn;
    @FindBy(xpath = "//*[@class='button button--icon-only' and contains(., 'Approve With Edits')]")
    WebElement ApproveWithEditsBtn;
    @FindBy(xpath = "//*[@class='button button--icon-only' and contains(., 'Reject')]")
    WebElement RejectBtn;
    @FindBy(xpath = "//*[@class='button button--icon-only' and contains(., 'Close')]")
    WebElement CloseBtn;
    @FindBy(xpath = "//div[@class='react-modal__body update-modal__body' and contains(., 'Have you verified the Failure Mode results for each bearing?')]")
    WebElement AfaVerifyMessage;
    @FindBy(xpath = "//span[@class='button__text' and contains(., 'Yes')]")
    WebElement VerifyAfa;
    @FindBy(xpath = "//div[@class='react-modal__text' and contains(., 'Are you sure you want to approve the report?')]")
    WebElement ConfirmApprovalMessage;
    @FindBy(xpath = "//span[@class='button__text' and contains(., 'Approve and open Email')]")
    WebElement ApproveAndOpenEmailBtn;
    @FindBy(xpath = "//*[@class='user-bar__sign-out' and contains(., 'Sign out')]")
    WebElement SignOutBtn;
    @FindBy(css = "#buttonSubmit")
    WebElement Submit;
    @FindBy(css = "#buttonReload")
    WebElement Reload;
    @FindBy(css = "#buttonEdit")
    WebElement Edit;
    @FindBy(css = "#buttonDelete")
    WebElement Delete;
    @FindBy(xpath = "//div[@class='react-modal__text' and contains(., 'Are you sure you want to submit the report?')]")
    WebElement ConfirmSubmittingMessage;
    @FindBy(xpath = "//div[@class='icon-message__text' and contains(., 'Do you want to delete report')]")
    WebElement ConfirmDeletionMessage;
    @FindBy(xpath = "//span[@class='button__text' and contains(., 'Yes')]")
    WebElement ConfirmDeletionBtn;
    @FindBy(xpath = "//span[@class='button__text' and contains(., 'Submit and open Email')]")
    WebElement ConfirmSubmittingBtn;
    @FindBy(xpath = "//div[@class='react-modal__text' and contains(., 'Are you sure you want to EDIT this APPROVED report? Make sure you save the original report as a PDF to a folder before editing.')]")
    WebElement ConfirmEditingMessage;
    @FindBy(xpath =  "//span[@class='button__text' and contains(., 'Download PDF and continue editing')]")
    WebElement DownloadPDF;
    @FindBy(xpath = "//h4[@class='live-preview__report-revision-number' and contains(., '01')]")
    WebElement RevisionNumber;
    @FindBy(xpath = "//div[@class='live-preview-status-confidential__status' and contains(., 'Submitted')]")
    WebElement StatusSubmitted;
    @FindBy(xpath = "//div[@class='live-preview-status-confidential__status' and contains(., 'Approved')]")
    WebElement StatusApproved;
    @FindBy(xpath = "//div[@class='live-preview-status-confidential__status' and contains(., 'Draft')]")
    WebElement StatusDraft;
    @FindBy(xpath = "//div[@class='live-preview-status-confidential__status' and contains(., 'Deleted')]")
    WebElement StatusDeleted;
    @FindBy(xpath = "//button[normalize-space()='Asset Details']")
    WebElement AssetDetails;
    @FindBy(xpath = "//button[@class='button react-modal__action-button button--background-gray']")
    WebElement YesDelete;
    @FindBy(css = "#addBearingButton")
    WebElement AddNewBearingBtn;
    @FindBy(xpath = "(//*[@id='icon_ellipsis'])[1]")
    WebElement ThreeDots;
    @FindBy(xpath = "(//*[@id='icon_ellipsis'])[2]")
    WebElement ThreeDotsSecond;
    @FindBy(css = ".navigation__action")
    List<WebElement> ThreeDotsContainer;
    @FindBy(css = ".case-bearingquicklinks-actions")
    WebElement QuickLinks;
    @FindBy(xpath = "//a[@class='navigation__action' and contains(., 'Duplicate')]")
    WebElement DuplicateButton;
    @FindBy(xpath = "//a[@class='navigation__action' and contains(., 'Delete')]")
    WebElement DeleteBearingButton;
    @FindBy(xpath = "//button[normalize-space()='Asset type / Functional Area / System'] ")
    WebElement AssetType;
    @FindBy(xpath = "//div[@data-id='assetDetails.assetTypeOrFunctionalAreaOrSystem.machineOrAssetCode']//div[contains(@class,'css-1hwfws3')]")
    WebElement MachineAssetType;
    @FindBy(xpath = "//button[normalize-space()='Bearing Investigations']")
    WebElement BearingInvestigations;
    @FindBy(xpath = "//button[normalize-space()='Bearing 1']")
    WebElement Bearing1;
    @FindBy(xpath = "//button [text ()='Bearing 2']")
    WebElement Bearing2;
    @FindBy(xpath = "//button [text ()='Component Parts Investigation']")
    WebElement Components;
    @FindBy(xpath = "//button [text ()='SKF Details']")
    WebElement SKFDetails;
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
    @FindBy(css = "#buttonSettings")
    WebElement SettingsReports;
    @FindBy(css = "#buttonSettings")
    WebElement SettingsContainerReports;
    @FindBy(css = ".settings-menu__action")
    List<WebElement> SettingContainer;
    @FindBy(id = "Tree/tree_root_branch")
    List<WebElement> AccordeonReports;

    public void checkContainer (WebDriver driver){
        WebElement Confidential = driver.findElement(By.xpath("//span[@class='checkbox__label-span' and contains(., 'Is the report confidential?')]"));
        WebElement FigureNumbering = driver.findElement(By.xpath("//span[@class='checkbox__label-span' and contains(., 'Enable automatic figure numbering?')]"));
        WebElement Appendix = driver.findElement(By.xpath("//span[@class='checkbox__label-span' and contains(., 'Exclude ISO appendix from the report?')]"));
        WebElement PageBreak = driver.findElement(By.xpath("//span[@class='checkbox__label-span' and contains(., 'Insert page break for each bearing in the report?')]"));
        WebElement AutoScroll = driver.findElement(By.xpath("//span[@class='checkbox__label-span' and contains(., 'Disable autoscroll?')]"));
        WebElement ConcAndRecommendations = driver.findElement(By.xpath("//span[@class='checkbox__label-span' and contains(., 'Show CONCLUSIONS AND RECOMMENDATIONS at beginning and end of report?')]"));
        Confidential.isEnabled();
        FigureNumbering.isEnabled();
        Appendix.isEnabled();
        PageBreak.isEnabled();
        AutoScroll.isEnabled();
        ConcAndRecommendations.isEnabled();
    }

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
    public void uploadImages() throws InterruptedException, AWTException {
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
