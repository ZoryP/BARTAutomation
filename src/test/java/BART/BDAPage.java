package BART;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;


public class BDAPage {

    @FindBy(xpath = "//span[normalize-space()='Upload figures']")
    WebElement Upload;

    @FindBy(xpath = "//span[normalize-space()='Close']")
    WebElement Close;
    @FindBy(xpath = "//button[normalize-space()='Asset type / Functional Area / System'] ")
    WebElement AssetType;
    @FindBy(xpath = "//button[normalize-space()='Bearing Investigations']")
    WebElement BearingInvestigations;
    @FindBy(xpath = "//*[@id=\"app\"]/div[2]/div/div/div[1]/div[2]/div[6]/div/div[2]/div[2]/div/div/div[2]/div[8]/div[2]/div[3]/div/div[2]/div/div[1]/div/div/div[4]/div/div[2]/div[2]/label")
    WebElement UploadImg;
    @FindBy(xpath = "//button[normalize-space()='Bearing 1']")
    WebElement Bearing1;
    @FindBy(xpath = "//button[normalize-space()='Bearing Type']")
    WebElement BearingTypeSection;
    @FindBy(xpath = "//div[@data-id='assetDetails.assetTypeOrFunctionalAreaOrSystem.functionalAreaName']//input[@type='text']")
    WebElement FunctionalAreaNameWhereAssetIsUsed;
    @FindBy(xpath = "//div[@data-id='assetDetails.assetTypeOrFunctionalAreaOrSystem.systemNameWhereAssetIsUsed']//input[@type='text']")
    WebElement SystemName;

    public static void clickElementWithJS(WebDriver driver, WebElement element) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("arguments[0].click();", element);
    }

    public static void blurElementWithJS(WebDriver driver, WebElement element) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("arguments[0].blur();", element);
    }

    public static void sendKeysLetterByLetter(WebElement el, String keys) {
        el.click();
        for (int i = 0; i < keys.length(); i++) {
            el.sendKeys(String.valueOf(keys.charAt(i)));
        }
    }

    public static boolean isElementVisibleWithJS(WebDriver driver, WebElement element) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        return (boolean) jsExecutor.executeScript("return arguments[0].offsetWidth > 0 && arguments[0].offsetHeight > 0;", element);
    }

    public boolean approvalButton(WebDriver driver) throws Throwable {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("window.scrollBy(0, 800)");
        Thread.sleep(3000);
        WebElement approveNo = driver.findElement(By.xpath("//label[@for='skfDetails.reportNeedsApproval.needsApproval.no']"));
        jsExecutor.executeScript("arguments[0].click();", approveNo);
        WebElement livePreviewEl6 = driver.findElement(By.xpath("//div[@class='live-preview-key-value__value' and contains(., 'testuser1.bart@gmail.com')]"));
        return BDAPage.isElementVisibleWithJS(driver, livePreviewEl6);
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
        return BDAPage.isElementVisibleWithJS(driver, LpCompany);
    }

    public boolean isLocationVisible(WebDriver driver) {
        WebElement LpLocation = driver.findElement(By.xpath("//div[@class='live-preview-key-value__value' and contains(., 'SKF Solution Factory')]"));
        return BDAPage.isElementVisibleWithJS(driver, LpLocation);
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
        return BDAPage.isElementVisibleWithJS(driver, LpCountry);
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
        BART.BDAPage.clickElementWithJS(driver, skfTerNmr);
        BART.BDAPage.sendKeysLetterByLetter(skfTerNmr, terNumber);
    }

    public void enterScopeOfInvestigation(WebDriver driver, String scope) {
        WebElement scopeOfInv = driver.findElement(By.xpath("//div[@data-id='investigationDetails.scopeOfInvestigation']//p"));
        BART.BDAPage.clickElementWithJS(driver, scopeOfInv);
        BART.BDAPage.sendKeysLetterByLetter(scopeOfInv, scope);
        BART.BDAPage.blurElementWithJS(driver, scopeOfInv);
    }

    public void clickSKFDetails(WebDriver driver) {
        WebElement SKFDetails = driver.findElement(By.xpath("//button[normalize-space()='SKF Details']"));
        BART.BDAPage.clickElementWithJS(driver, SKFDetails);
    }
    public static void clickSKFOther(WebDriver driver) {
        WebElement skfOther = driver.findElement(By.xpath("//div[@class='accordion']//div[@class='accordion__content accordion__content--is-open']//div[@class='accordion__content-form']//div[@class='componentContainer']//div//div[@class='componentContainer']//div//div[contains(@class,'css-1hwfws3')]"));
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("arguments[0].click();", skfOther);
    }

    public static void enterSKFOtherValue(WebDriver driver, String value) {
        WebElement skfOtherInput = driver.findElement(By.xpath("//div[@class='accordion']//div[@class='accordion__content accordion__content--is-open']//div[@class='accordion__content-form']//div[@class='componentContainer']//div//div[@class='componentContainer']//div//div[contains(@class,'css-1hwfws3')]"));
        Actions actions = new Actions(driver);
        actions.sendKeys(skfOtherInput, value).perform();
        actions.sendKeys(Keys.ENTER).build().perform();

    }

    public static void clickSKFSerialNumber(WebDriver driver) {
        WebElement skfSerialNumber = driver.findElement(By.xpath("//div[@class='accordion']//div[@class='accordion__content accordion__content--is-open']//div[@class='accordion__content-form']//div[@class='componentContainer']//div//div[@class='componentContainer']//div//div[contains(@class,'css-1wa3eu0-placeholder')][normalize-space()='Select or free text']"));
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("arguments[0].click();", skfSerialNumber);
    }

    public static void enterSKFSerialNumberValue(WebDriver driver, String value) {
        WebElement skfSerialNumberInput = driver.findElement(By.xpath("//div[@class='accordion']//div[@class='accordion__content accordion__content--is-open']//div[@class='accordion__content-form']//div[@class='componentContainer']//div//div[@class='componentContainer']//div//div[contains(@class,'css-1wa3eu0-placeholder')][normalize-space()='Select or free text']"));
        Actions actions = new Actions(driver);
        actions.sendKeys(skfSerialNumberInput, value).perform();
        actions.sendKeys(Keys.ENTER).build().perform();
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

    public static boolean isAFAModalDisplayed(WebDriver driver) throws Throwable{
        Thread.sleep(7000);
        WebElement afaModal = driver.findElement(By.xpath("//div[@class='react-modal__header ai-modal__header']"));
        return afaModal.isDisplayed();
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

    BDAPage(WebDriver driver) {

        PageFactory.initElements(driver, this);
    }
}
