package BART;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.time.Duration;
import java.util.List;

public class BearingDamageAnalysis {
    WebDriver driver;
    String url = "https://dnnfsk8ppi4ki.cloudfront.net";
    FluentWait<WebDriver> wait;
    @BeforeClass
    public void SetUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.get(url);
        wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(40))
                .pollingEvery(Duration.ofSeconds(5))
                .ignoring(NoSuchElementException.class);
    }
    @AfterClass
    public void QuitBrowser() {
    driver.quit();

    }
    @Test(priority = 1)
    public void ValidLogin()  {
        BART.LoginPage loginPage = new BART.LoginPage(driver);
        loginPage.clearFields();
        loginPage.login("testuser1.bart@gmail.com", "bartTest1");
        loginPage.enterBtn.click();
        wait.until(ExpectedConditions.visibilityOf(loginPage.WelcomeMassage));
        loginPage.WelcomeMassage.isDisplayed();
    }

    @Test(priority = 2)
    public void CreateBda() {
        BART.DashboardPage dashboard = new BART.DashboardPage(driver);
        dashboard.CreateNewReport.click();
        dashboard.BDAReport.click();
        wait.until(ExpectedConditions.visibilityOfAllElements(dashboard.BearingDamageAnalysisReport,dashboard.IconToolbar));
        Assert.assertTrue(dashboard.BearingDamageAnalysisReport.isDisplayed());
        Assert.assertTrue(dashboard.IconToolbar.isDisplayed());
    }

    @Test(priority = 3)
    public void CheckSettings() {
        WebElement Settings = driver.findElement(By.cssSelector("#buttonSettings"));
        Settings.click();
        List<WebElement> SettingsSize = driver.findElements(By.cssSelector(".settings-menu__action"));
        Assert.assertEquals(SettingsSize.size(), 6);
    }

    @Test(priority = 4)
    public void CheckContainerSettings() throws Throwable {
        BDABasePage basePageReports = new BDABasePage(driver);
        basePageReports.checkContainerBDA(driver);
        Thread.sleep(3000);
        WebElement Settings = driver.findElement(By.cssSelector("#buttonSettings"));
        Settings.click();
    }

    @Test(priority = 5)
    public void CheckAccodeonBDA() {
        List<WebElement> Accordeon = driver.findElements(By.cssSelector("#Tree/tree_root_branch"));
        Assert.assertEquals(Accordeon.size(), 11);
    }

    WebElement ReportTitle;

    @Test(priority = 6)
    public void CheckReportDetails() {
        WebElement ReportDetails = driver.findElement(By.xpath("//button[normalize-space()='Report Details']"));
        BasePage.clickElementWithJS(driver, ReportDetails);
        ReportTitle = driver.findElement(By.xpath("//div[@data-id='reportDetails.reportTitle']//input[@type='text']"));
        BasePage.clickElementWithJS(driver, ReportTitle);
        BasePage.sendKeysLetterByLetter(ReportTitle, "test");
        BasePage.blurElementWithJS(driver, ReportTitle);
        WebElement InvestigationDetails = driver.findElement(By.xpath("//button[normalize-space()='Investigation Details']"));
        BasePage.clickElementWithJS(driver, InvestigationDetails);
        WebElement LivePreviewEl = driver.findElement(By.xpath("//div[@class='live-preview-key-value__value' and text()='test']"));
        LivePreviewEl.isDisplayed();
    }

    @Test(priority = 7)
    public void CheckInvestigationDetails() {
        BDABasePage basePageReports = new BDABasePage(driver);
        String terNumber = "1";
        basePageReports.enterSkfTerNumber(driver, terNumber);
        String scope = "Bearing";
        basePageReports.enterScopeOfInvestigation(driver, scope);
        basePageReports.clickSKFDetails(driver);
        WebElement lpTER = driver.findElement(By.xpath("//div[@class='live-preview-key-value__value' and text()='1']"));
        WebElement lpScope = driver.findElement(By.xpath("//div[@class='live-preview-key-value__value' and contains(., 'Bearing')]"));
        lpTER.isDisplayed();
        lpScope.isDisplayed();
    }

    @Test(priority = 8)
    public void CheckSKFDetailsCountry() throws Throwable {
        BDABasePage basePageReports = new BDABasePage(driver);
        String country = "Argentina";
        basePageReports.selectCountry(driver, country);
    }

    @Test(priority = 9)
    public void CheckSKFDetailsCompany() throws Throwable {
        BDABasePage basePageReports = new BDABasePage(driver);
        basePageReports.selectSKFCountry(driver);
        boolean isElementVisible = basePageReports.isCountryVisible(driver);
        Assert.assertTrue(isElementVisible);
    }

    @Test(priority = 10)
    public void CheckSKFCompanyLocation() throws Throwable {
        BDABasePage basePageReports = new BDABasePage(driver);
        basePageReports.selectSKFCompany(driver);
        boolean isCompanyVisible = basePageReports.isCompanyVisible(driver);
        Assert.assertTrue(isCompanyVisible);
        boolean isLocationVisible = basePageReports.isLocationVisible(driver);
        Assert.assertTrue(isLocationVisible);
    }

    @Test(priority = 11)
    public void RequiredApproval() throws Throwable {
        BDABasePage basePageReports = new BDABasePage(driver);
        boolean isElementVisible = basePageReports.approvalButton(driver);
        Assert.assertTrue(isElementVisible);
    }

    @Test(priority = 12)
    public void CheckCustomer() throws Throwable {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        WebElement CustomerDetails = driver.findElement(By.xpath("//button[normalize-space()='Customer Details']"));
        BasePage.clickElementWithJS(driver, CustomerDetails);
        WebElement Customer = driver.findElement(By.xpath("(//div[contains(text(),'Type 3 characters')])"));
        jsExecutor.executeScript("arguments[0].click();", Customer);
        Actions actions = new Actions(driver);
        actions.sendKeys(Customer, "LODI SPA").perform();
        Thread.sleep(3000);
        actions.sendKeys(Keys.ENTER).build().perform();
        Thread.sleep(3000);
        WebElement LpCompany = driver.findElement(By.xpath("//div[@class='live-preview-key-value__value' and contains(., 'LODI SPA')]"));
        LpCompany.isDisplayed();
    }

    @Test(priority = 13)
    public void CheckCustomerDetails() {
        WebElement LpCompanyAdr = driver.findElement(By.xpath("//div[@class='live-preview-key-value__value' and contains(., 'VIA DELLA')]"));
        WebElement LpCompanyCntr = driver.findElement(By.xpath("//div[@class='live-preview-key-value__value' and contains(., 'Italy')]"));
        LpCompanyAdr.isDisplayed();
        LpCompanyCntr.isDisplayed();
    }

    @Test(priority = 14)
    public void CheckCustomerSiteName() {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("window.scrollBy(0, 800)");
        WebElement CustomerSiteName = driver.findElement(By.xpath("//div[@data-id='customerDetails.site']//input[@type='text']"));
        jsExecutor.executeScript("arguments[0].click();", CustomerSiteName);
        Actions actions = new Actions(driver);
        actions.sendKeys(CustomerSiteName, "www.lodispa.com").perform();
        actions.sendKeys(Keys.ENTER).build().perform();
        BasePage.blurElementWithJS(driver, CustomerSiteName);
        WebElement LpCustomerSiteName = driver.findElement(By.xpath("//div[@class='live-preview-key-value__value' and contains(., 'www.lodispa.com')]"));
        LpCustomerSiteName.isDisplayed();
    }

    @Test(priority = 15)
    public void CheckAssetDetailsSection() {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        WebElement AssetDetails = driver.findElement(By.xpath("//button[normalize-space()='Asset Details']"));
        jsExecutor.executeScript("arguments[0].click();", AssetDetails);
        WebElement AssetType = driver.findElement(By.xpath("//button[normalize-space()='Asset type / Functional Area / System'] "));
        WebElement Machine = driver.findElement(By.xpath(" //button[normalize-space()='Machine / Asset Details']"));
        AssetType.isDisplayed();
        Machine.isDisplayed();
    }

    @Test(priority = 16)
    public void CheckAssetType() throws Throwable {
        BasePage basePage = new BasePage(driver);
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        basePage.AssetType.click();
        jsExecutor.executeScript("arguments[0].click();", basePage.MachineAssetType);
        Actions actions = new Actions(driver);
        Thread.sleep(2000);
        actions.sendKeys(basePage.MachineAssetType, "Air handling unit").perform();
        Thread.sleep(3000);
        actions.sendKeys(Keys.ENTER).build().perform();
        BasePage.blurElementWithJS(driver, basePage.MachineAssetType);
        WebElement LpFunctionalArea = driver.findElement(By.xpath("//div[@class='live-preview-key-value__value' and contains(., 'Air handling unit, Air conditioner (AC)')]"));
        LpFunctionalArea.isDisplayed();
    }

    @Test(priority = 17)
    public void CheckAssetDetails() {
        BDABasePage basePageReports = new BDABasePage(driver);
        basePageReports.FunctionalAreaNameWhereAssetIsUsed.click();
        basePageReports.FunctionalAreaNameWhereAssetIsUsed.sendKeys("Operations");
        BasePage.blurElementWithJS(driver, basePageReports.FunctionalAreaNameWhereAssetIsUsed);
        basePageReports.SystemName.click();
        basePageReports.SystemName.sendKeys("Operation 1");
        BasePage.blurElementWithJS(driver, basePageReports.SystemName);
        WebElement LpFunctionalAreaNameWhereAssetIsUsed = driver.findElement(By.xpath("//div[@class='live-preview-key-value__value' and contains(., 'Operations')]"));
        WebElement LpSystemName = driver.findElement(By.xpath("//div[@class='live-preview-key-value__value' and contains(., 'Operation 1')]"));
        LpFunctionalAreaNameWhereAssetIsUsed.isDisplayed();
        LpSystemName.isDisplayed();
    }

    @Test(priority = 18)
    public void CheckFirstBearing() {
        BasePage basePage = new BasePage(driver);
        basePage.BearingInvestigations.click();
        BDABasePage bdaBasePage = new BDABasePage(driver);
        basePage.Bearing1.click();
        bdaBasePage.BearingTypeSection.click();
        basePage.clickSKFOther(driver);
        basePage.enterSKFOtherValue(driver, "SKF");
        basePage.clickSKFSerialNumber(driver);
        basePage.enterSKFSerialNumberValue(driver, "PEER");
        Assert.assertTrue(basePage.LpSkfOther.isDisplayed());
    }

    @Test(priority = 19)
    public void CheckBearingType() throws Throwable {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("window.scrollTo(0, 0)");
        WebElement SKFCustomerDescription = driver.findElement(By.xpath("//div[@class='accordion']//div[@class='accordion__content accordion__content--is-open']//div[@class='accordion__content-form']//div[@class='componentContainer']//div//div[@class='se-wrapper-inner se-wrapper-wysiwyg sun-editor-editable']"));
        jsExecutor.executeScript("arguments[0].click();", SKFCustomerDescription);
        Actions actions = new Actions(driver);
        Thread.sleep(2000);
        actions.sendKeys(SKFCustomerDescription, "TEST").perform();
        Thread.sleep(2000);
        BasePage.blurElementWithJS(driver, SKFCustomerDescription);

    }

    @Test(priority = 20)
    public void CheckComponentParts() {
        WebElement ComponentParts = driver.findElement(By.xpath("//button[normalize-space()='Component Parts Investigation']"));
        ComponentParts.click();
        WebElement RollingElements = driver.findElement(By.xpath("//button[normalize-space()='Rolling Elements']"));
        RollingElements.click();
        List<WebElement> ComponentSize = driver.findElements(By.cssSelector(".accordion-menu-container"));
        Assert.assertEquals(ComponentSize.size(), 9);
    }

    @Test(priority = 21)
    public void CheckUploadImages() throws Throwable {
        BDABasePage basePage = new BDABasePage(driver);
        basePage.UploadImg.click();
        Assert.assertTrue(basePage.ModalDialogForImages.isDisplayed());
        Thread.sleep(4000);
        basePage.FileInput.click();
        String[] filePaths = {
                "\"C:\\Users\\ZornicaPetkova\\Desktop\\NAM.jpg\"",
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

    @Test(priority = 22)
    public void AssertImages() {
        BDABasePage basePage = new BDABasePage(driver);
        wait.until(ExpectedConditions.elementToBeClickable(basePage.Upload)).click();
        wait.until(ExpectedConditions.elementToBeClickable(basePage.Close)).click();
        Assert.assertEquals(basePage.LpImageContainer.size(), 2);
    }

    @Test(priority = 23)
    public void CheckAnalysis() throws Throwable {
        BDABasePage.clickAnalysisButton(driver);
        Thread.sleep(2000);
        BDABasePage.selectForAFA(driver);
        Assert.assertTrue(BDABasePage.isAFABtnEnabled(driver));
        Assert.assertTrue(BDABasePage.isAFAModalDisplayed(driver));
    }

    @Test(priority = 24)
    public void DoneAFA() throws Throwable {
        BasePage.clickFailureMode(driver);
        BasePage.clickDoneButton(driver);
        Thread.sleep(4000);
        Assert.assertTrue(BasePage.isThankYouMessageDisplayed(driver));
        BasePage.clickCloseAFAButton(driver);
        Thread.sleep(3000);
        Assert.assertTrue(BDABasePage.isLPAFADisplayed(driver));
    }

    @Test(priority = 25)
    public void CheckCause() throws Throwable {
        BDABasePage basePageReports = new BDABasePage(driver);
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("window.scrollBy(0, 300)");
        BDABasePage.clickCauseElement(driver);
        Thread.sleep(2000);
        basePageReports.cause(driver);
        Assert.assertTrue(BDABasePage.isLpCauseDisplayed(driver));
    }

    @Test(priority = 26)
    public void CreateNewBearing() {
        BDABasePage basePageReports = new BDABasePage(driver);
        basePageReports.clickAddNewButton(driver);
        basePageReports.clickAddNewEditButton(driver);
        basePageReports.clickLpAddNew(driver);
    }

    @Test(priority = 27)
    public void CheckThreeDotsMenu() throws Throwable {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        BDABasePage basePageReports = new BDABasePage(driver);
        jsExecutor.executeScript("window.scrollTo(0, 0)");
        basePageReports.ThreeDots.click();
        Thread.sleep(3000);
        Assert.assertEquals(basePageReports.ThreeDotsContainer.size(), 4);
    }
    @Test(priority = 28)
    public void DuplicateBearing() throws Throwable {
        BDABasePage basePageReports = new BDABasePage(driver);
        basePageReports.duplicate(driver);
        Assert.assertEquals(basePageReports.lpBearingsSize2.size(), 2);
    }
    @Test(priority = 29)
    public void DeleteFirstBearing() throws Throwable {
        BDABasePage basePageReports = new BDABasePage(driver);
        basePageReports.deleteFirstBearing(driver);
    }
    @Test(priority = 30)
    public void DeleteSecondBearing() throws Throwable {
        BDABasePage basePageReports = new BDABasePage(driver);
        basePageReports.deleteSecondBearing(driver);
        Assert.assertEquals(basePageReports.lpBearings.size(), 1);
        WebElement Save = driver.findElement(By.cssSelector("#buttonReload"));
        Save.click();
    }
    @Test(priority = 31)
    public void CheckConclusionAndRecommendations() throws Throwable {
        BDABasePage basePageReports = new BDABasePage(driver);
        basePageReports.conclusionsAndRec(driver);
        basePageReports.lpRecommendations.isDisplayed();
    }
    @Test(priority = 32)
    public void Submit() throws Throwable {
        BDABasePage basePageReports = new BDABasePage(driver);
        basePageReports.submit(driver);
        Thread.sleep(3000);
        basePageReports.ConfirmSubmittingMessage.isDisplayed();
        basePageReports.ConfirmSubmittingBtn.click();
        Thread.sleep(4000);
        basePageReports.StatusSubmitted.isDisplayed();
    }
    @Test(priority = 33)
    public void CheckIconToolbarSubmit() {
        BDABasePage basePageReports = new BDABasePage(driver);
        List<WebElement> IconToolbar = driver.findElements(By.cssSelector(".button-bar__group"));
        Assert.assertEquals(IconToolbar.size(), 3);
        Assert.assertTrue(basePageReports.isApproveVisible(driver));
        Assert.assertTrue(basePageReports.isApproveWithEditsVisible(driver));
        Assert.assertTrue(basePageReports.isRejectVisible(driver));
    }
    @Test(priority = 34)
    public void Approve() {
        BDABasePage basePageReports = new BDABasePage(driver);
        basePageReports.approve(driver);
    }
    @Test(priority = 35)
    public void ApproveAndEdit() throws Throwable {
        BDABasePage basePageReports = new BDABasePage(driver);
        basePageReports.approveAndEdit(driver);
    }
    @Test(priority = 36)
    public void EditReport() throws Throwable {
        BDABasePage basePageReports = new BDABasePage(driver);
        basePageReports.editReport(driver);
    }
    @Test(priority = 37)
    public void DeleteReport() throws Throwable {
        BDABasePage basePageReports = new BDABasePage(driver);
        basePageReports.deleteReport(driver);
    }
    @Test(priority = 38)
    public void CloseReport() {
        WebElement CloseBtn = driver.findElement(By.cssSelector("#buttonClose"));
        CloseBtn.click();
        String currentUrl = driver.getCurrentUrl();
        String expectedUrl = "https://dnnfsk8ppi4ki.cloudfront.net/my-reports";
        Assert.assertEquals(currentUrl, expectedUrl, "Button navigation is correct");
    }
    @Test(priority = 39)
    public void SignOut() {
        WebElement SignOut = driver.findElement(By.xpath("//*[@class='user-bar__sign-out' and contains(., 'Sign out')]"));
        SignOut.click();
        String currentUrl = driver.getCurrentUrl();
        String expectedUrl = "https://dnnfsk8ppi4ki.cloudfront.net/login";
        Assert.assertEquals(currentUrl, expectedUrl, "Button navigation is correct");
    }
}
