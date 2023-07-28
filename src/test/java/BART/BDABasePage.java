package BART;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.FluentWait;

import java.time.Duration;
import java.util.List;


public class BDABasePage extends BasePage {

    @FindBy(xpath = "//*[@id=\"app\"]/div[2]/div/div/div[1]/div[2]/div[6]/div/div[2]/div[2]/div/div/div[2]/div[8]/div[2]/div[3]/div/div[2]/div/div[1]/div/div/div[4]/div/div[2]/div[2]/label")
    WebElement UploadImg;
    @FindBy(xpath = "//div[@data-id='assetDetails.assetTypeOrFunctionalAreaOrSystem.functionalAreaName']//input[@type='text']")
    WebElement FunctionalAreaNameWhereAssetIsUsed;
    @FindBy(xpath = "//div[@data-id='assetDetails.assetTypeOrFunctionalAreaOrSystem.systemNameWhereAssetIsUsed']//input[@type='text']")
    WebElement SystemName;

    public void checkContainerBDA (WebDriver driver){
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

    public boolean approvalButton(WebDriver driver) throws Throwable {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("window.scrollBy(0, 800)");
        Thread.sleep(3000);
        WebElement approveNo = driver.findElement(By.xpath("//label[@for='skfDetails.reportNeedsApproval.needsApproval.no']"));
        jsExecutor.executeScript("arguments[0].click();", approveNo);
        WebElement livePreviewEl6 = driver.findElement(By.xpath("//div[@class='live-preview-key-value__value' and contains(., 'testuser1.bart@gmail.com')]"));
        return BasePage.isElementVisibleWithJS(driver, livePreviewEl6);
    }

    public void selectSKFCompany(WebDriver driver) throws InterruptedException {
        WebElement companyInd = driver.findElement(By.xpath("(//div[contains(text(),'Select')])[2]"));
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        Thread.sleep(3000);
        jsExecutor.executeScript("arguments[0].click();", companyInd);
        Actions actions = new Actions(driver);
        actions.sendKeys(companyInd, "SKF").perform();
        actions.sendKeys(Keys.ENTER).build().perform();
        Thread.sleep(3000);
    }

    public boolean isCompanyVisible(WebDriver driver) {
        WebElement LpCompany = driver.findElement(By.xpath("//div[@class='live-preview-key-value__value' and contains(., 'SKF ARGENTINA S.A.')]"));
        return BasePage.isElementVisibleWithJS(driver, LpCompany);
    }

    public boolean isLocationVisible(WebDriver driver) {
        WebElement LpLocation = driver.findElement(By.xpath("//div[@class='live-preview-key-value__value' and contains(., 'SKF Solution Factory')]"));
        return BasePage.isElementVisibleWithJS(driver, LpLocation);
    }

    public void selectSKFCountry(WebDriver driver) throws InterruptedException {
        WebElement companyInd = driver.findElement(By.xpath("(//div[contains(text(),'Select')])[2]"));
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        Thread.sleep(3000);
        jsExecutor.executeScript("arguments[0].click();", companyInd);
        Actions actions = new Actions(driver);
        actions.sendKeys(companyInd, "SKF").perform();
        actions.sendKeys(Keys.ENTER).build().perform();
    }

    public boolean isCountryVisible(WebDriver driver) {
        WebElement LpCountry = driver.findElement(By.xpath("//div[@class='live-preview-key-value__value' and contains(., 'Argentina')]"));
        return BasePage.isElementVisibleWithJS(driver, LpCountry);
    }

    public void selectCountry(WebDriver driver, String country) throws InterruptedException {
        WebElement countryInd = driver.findElement(By.xpath("(//div[contains(text(),'Select')])[2]"));
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        Thread.sleep(3000);
        jsExecutor.executeScript("arguments[0].click();", countryInd);
        Actions actions = new Actions(driver);
        actions.sendKeys(countryInd, country).perform();
        actions.sendKeys(Keys.ENTER).build().perform();
    }

    public void enterSkfTerNumber(WebDriver driver, String terNumber) {
        WebElement skfTerNmr = driver.findElement(By.xpath("//div[@data-id='investigationDetails.terNumber']//input[@type='text']"));
        BasePage.clickElementWithJS(driver, skfTerNmr);
        BasePage.sendKeysLetterByLetter(skfTerNmr, terNumber);
    }

    public void enterScopeOfInvestigation(WebDriver driver, String scope) {
        WebElement scopeOfInv = driver.findElement(By.xpath("//div[@data-id='investigationDetails.scopeOfInvestigation']//p"));
        BasePage.clickElementWithJS(driver, scopeOfInv);
        BasePage.sendKeysLetterByLetter(scopeOfInv, scope);
        BasePage.blurElementWithJS(driver, scopeOfInv);
    }

    public static void clickAnalysisButton(WebDriver driver) {
        WebElement analysisButton = driver.findElement(By.xpath("//button[normalize-space()='Analysis']"));
        analysisButton.click();
    }

    public static void selectForAFA(WebDriver driver) {
        WebElement selectForAFA = driver.findElement(By.xpath("//*[@id=\"app\"]/div[2]/div/div/div[1]/div[2]/div[6]/div/div[2]/div[2]/div/div/div[2]/div[10]/div[2]/div/div[3]/div/div[4]/div/div[2]/div[1]/figure[1]"));
        selectForAFA.click();
    }

    public static boolean isAFABtnEnabled(WebDriver driver) {
        WebElement afaButton = driver.findElement(By.xpath("(//span[@class='button__text' and contains(., 'Augmented Failure Analysis')])[1]"));
        afaButton.click();
        return afaButton.isEnabled();
    }

    public static boolean isAFAModalDisplayed(WebDriver driver) throws Throwable {
        Thread.sleep(7000);
        WebElement afaModal = driver.findElement(By.xpath("//div[@class='react-modal__header ai-modal__header']"));
        return afaModal.isDisplayed();
    }

    public static void clickCauseElement(WebDriver driver) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("window.scrollBy(0, 300)");
        WebElement cause = driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[2]/div[6]/div[1]/div[2]/div[2]/div[1]/div[1]/div[2]/div[10]/div[2]/div[1]/div[3]/div[1]/div[7]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]"));
        jsExecutor.executeScript("arguments[0].click();", cause);
    }

    public static boolean isLpCauseDisplayed(WebDriver driver) {
        WebElement lpCause = driver.findElement(By.xpath("//div[@class='live-preview-key-value__value' and contains(., 'Presence of water')]"));
        return lpCause.isDisplayed();
    }

    public void clickAddNewButton(WebDriver driver) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("window.scrollTo(0, 0)");
        WebElement addNewButton = driver.findElement(By.cssSelector("#addBearingButton"));
        addNewButton.click();
    }

    public void clickAddNewEditButton(WebDriver driver) {
        WebElement addNewEditButton = driver.findElement(By.xpath("//button[normalize-space()='Bearing 2']"));
        addNewEditButton.click();
    }

    public void clickLpAddNew(WebDriver driver) {
        WebElement lpAddNew = driver.findElement(By.xpath("//span[@class='first' and contains(., 'Bearing 2')]"));
        lpAddNew.click();
    }


    public List<WebElement> lpBearingsSize2;

    public void duplicate(WebDriver driver) throws Throwable {
        BDABasePage bdaBasePage = new BDABasePage(driver);
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        Thread.sleep(3000);
        jsExecutor.executeScript("arguments[0].click();", bdaBasePage.DuplicateButton);
        Thread.sleep(3000);
        WebElement DuplicatedBearing = driver.findElement(By.xpath("//div[@class='live-preview-key-value__header live-preview-key-value__bearingheader' and contains(., 'Bearing 3')]"));
        DuplicatedBearing.isDisplayed();
        lpBearingsSize2 = driver.findElements(By.xpath("//div[@class='live-preview-key-value__value' and contains(., 'Moisture corrosion (5.3.2) based on Augmented Failure Analysis')]"));
    }

    public void cause(WebDriver driver) throws Throwable {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        WebElement cause = driver.findElement(By.xpath("/html[1]/body[1]/div[1]/div[1]/div[2]/div[1]/div[1]/div[1]/div[2]/div[6]/div[1]/div[2]/div[2]/div[1]/div[1]/div[2]/div[10]/div[2]/div[1]/div[3]/div[1]/div[7]/div[1]/div[2]/div[1]/div[1]/div[1]/div[1]"));
        jsExecutor.executeScript("arguments[0].click();", cause);
        Actions actions = new Actions(driver);
        Thread.sleep(2000);
        actions.sendKeys(cause, "Presence of water").perform();
        actions.sendKeys(Keys.ENTER).build().perform();
        Thread.sleep(2000);
    }

    public void deleteFirstBearing(WebDriver driver) throws Throwable {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        Thread.sleep(2000);
        WebElement ThreeDotsThird = driver.findElement(By.xpath("(//*[@id='icon_ellipsis'])[2]"));
        ThreeDotsThird.click();
        Thread.sleep(2000);
        WebElement Delete = driver.findElement(By.xpath("//a[normalize-space()='Delete']"));
        jsExecutor.executeScript("arguments[0].click();", Delete);
        Thread.sleep(3000);
        YesDelete.click();
    }

    public List<WebElement> lpBearings;

    public void deleteSecondBearing(WebDriver driver) throws Throwable {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        WebElement ThreeDotsSecond = driver.findElement(By.xpath("(//*[@id='icon_ellipsis'])[2]"));
        ThreeDotsSecond.click();
        Thread.sleep(2000);
        WebElement Delete2 = driver.findElement(By.xpath("//a[normalize-space()='Delete']"));
        jsExecutor.executeScript("arguments[0].click();", Delete2);
        Thread.sleep(3000);
        WebElement YesDelete2 = driver.findElement(By.xpath("//button[@class='button react-modal__action-button button--background-gray']"));
        YesDelete2.click();
        lpBearings = driver.findElements(By.xpath("//div[@class='live-preview-key-value__value' and contains(., 'Moisture corrosion (5.3.2) based on Augmented Failure Analysis')]"));
    }

    public WebElement lpRecommendations;

    public void conclusionsAndRec(WebDriver driver) throws Throwable {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        Thread.sleep(3000);
        WebElement Conclusions = driver.findElement(By.xpath("//button[normalize-space()='Conclusions and recommendations']"));
        Conclusions.click();
        WebElement Recommendations = driver.findElement(By.xpath("//div[@data-id='summary.recommendations']//div[@class='se-wrapper-inner se-wrapper-wysiwyg sun-editor-editable']"));
        jsExecutor.executeScript("arguments[0].click();", Recommendations);
        Actions actions = new Actions(driver);
        actions.sendKeys(Recommendations, "TEST").perform();
        BasePage.blurElementWithJS(driver, Recommendations);
        Thread.sleep(3000);
        lpRecommendations = driver.findElement(By.xpath("//div[@class='live-preview-key-value__value' and contains(., 'TEST')]"));
    }

    public void submit(WebDriver driver) throws Throwable {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        SaveButton.click();
        Thread.sleep(3000);
        driver.navigate().refresh();
        Thread.sleep(3000);
        WebElement Submit = driver.findElement(By.cssSelector("#buttonSubmit"));
        String script = "var event = document.createEvent('MouseEvent');"
                + "event.initEvent('mouseover', true, true);"
                + "arguments[0].dispatchEvent(event);";
        jsExecutor.executeScript(script, Submit);
        Thread.sleep(3000);
        Submit.click();

    }

    public boolean isApproveVisible(WebDriver driver) {
        WebElement elementApprove = driver.findElement(By.cssSelector("#Icons/icon_approve"));
        return elementApprove.isDisplayed();
    }

    public boolean isApproveWithEditsVisible(WebDriver driver) {
        WebElement elementApprovewithEdits = driver.findElement(By.cssSelector("#Icons/icon_approve_edit"));
        return elementApprovewithEdits.isDisplayed();
    }

    public boolean isRejectVisible(WebDriver driver) {
        WebElement elementReject = driver.findElement(By.cssSelector("#buttonReject"));
        return elementReject.isDisplayed();

    }

    public void approve(WebDriver driver) {
        WebElement ApproveReport = driver.findElement(By.cssSelector("#Icons/icon_approve"));
        ApproveReport.click();
        WebElement VerifyAfa = driver.findElement(By.xpath("//div[@class='react-modal__body update-modal__body' and contains(., 'Have you verified the Failure Mode results for each bearing?')]"));
        VerifyAfa.isDisplayed();
        WebElement YesApprove = driver.findElement(By.xpath("//span[@class='button__text' and contains(., 'Yes')]"));
        YesApprove.click();
        WebElement ApproveMessage = driver.findElement(By.xpath("//div[@class='react-modal__text' and contains(., 'Are you sure you want to approve the report?')]"));
        ApproveMessage.isDisplayed();
        WebElement ApproveAndOpenEmail = driver.findElement(By.xpath("//span[@class='button__text' and contains(., 'Approve and open Email')]"));
        ApproveAndOpenEmail.click();
    }

    public void approveAndEdit(WebDriver driver) throws Throwable {
        Thread.sleep(13000);
        WebElement StatusApprove = driver.findElement(By.xpath("//div[@class='live-preview-status-confidential__status' and contains(., 'Approved')]"));
        StatusApprove.isDisplayed();
        WebElement EditButton = driver.findElement(By.cssSelector("#Icons/icon_edit"));
        EditButton.isDisplayed();
        WebElement DistributePDF = driver.findElement(By.cssSelector("#buttonDistributePdf"));
        DistributePDF.isDisplayed();
    }

    public void editReport(WebDriver driver) throws Throwable {
        WebElement EditButton = driver.findElement(By.cssSelector("#Icons/icon_edit"));
        EditButton.click();
        WebElement ConfirmMessage = driver.findElement(By.xpath("//div[@class='react-modal__text' and contains(., 'Are you sure you want to EDIT this APPROVED report? Make sure you save the original report as a PDF to a folder before editing.')]"));
        ConfirmMessage.isDisplayed();
        WebElement DownloadPDF = driver.findElement(By.xpath("//span[@class='button__text' and contains(., 'Download PDF and continue editing')]"));
        DownloadPDF.click();
        Thread.sleep(4000);
        WebElement RevisionNumber = driver.findElement(By.xpath("//h4[@class='live-preview__report-revision-number' and contains(., '01')]"));
        RevisionNumber.isDisplayed();
        WebElement StatusDraft = driver.findElement(By.xpath("//div[@class='live-preview-status-confidential__status' and contains(., 'Draft')]"));
        StatusDraft.isDisplayed();
    }
    public void deleteReport(WebDriver driver) throws Throwable {
        WebElement Delete = driver.findElement(By.cssSelector("#buttonDelete"));
        Delete.click();
        Thread.sleep(3000);
        WebElement ConfirmMessage = driver.findElement(By.xpath("//div[@class='icon-message__text' and contains(., 'Do you want to delete report')]"));
        ConfirmMessage.isDisplayed();
        WebElement Confirm = driver.findElement(By.xpath("//span[@class='button__text' and contains(., 'Yes')]"));
        Confirm.click();
        Thread.sleep(3000);
        WebElement DeleteMessage = driver.findElement(By.xpath("//div[@class='form-container' and contains(., 'This report is deleted. Please contact a BART administrator to restore deleted report.')]"));
        DeleteMessage.isDisplayed();
}

    BDABasePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
              FluentWait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(40))
                .pollingEvery(Duration.ofSeconds(5))
                .ignoring(NoSuchElementException.class);
    }
}