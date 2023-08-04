package BART;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

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
        BDABasePage bdaBasePage = new BDABasePage(driver);
        bdaBasePage.SettingsBtn.click();
        int expectedSize = 6;
        Assert.assertEquals(bdaBasePage.SettingContainer.size(), expectedSize);
    }
    @Test(priority = 4)
    public void CheckContainerSettings() throws Throwable {
        BDABasePage bdaBasePage = new BDABasePage(driver);
        bdaBasePage.checkContainerBDA(driver);
        Thread.sleep(3000);
        bdaBasePage.SettingsBtn.click();
    }
    @Test(priority = 5)
    public void CheckAccodeonBDA() {
        List<WebElement> Accordeon = driver.findElements(By.cssSelector(".accordion__trigger-icon"));
        Assert.assertEquals(Accordeon.size(), 13);
    }
    @Test(priority = 6)
    public void CheckReportDetails() {
        BDABasePage bdaBasePage = new BDABasePage(driver);
        bdaBasePage.checkReportDetails(driver);
        Assert.assertTrue(bdaBasePage.LpReportDetails.isDisplayed());
    }
    @Test(priority = 7)
    public void CheckInvestigationDetails() {
        BDABasePage bdaBasePage = new BDABasePage(driver);
        String terNumber = "1";
        bdaBasePage.enterSkfTerNumber(driver, terNumber);
        String scope = "Bearing";
        bdaBasePage.enterScopeOfInvestigation(driver, scope);
        bdaBasePage.clickSKFDetails(driver);
        Assert.assertTrue(bdaBasePage.LpTer.isDisplayed());
        Assert.assertTrue(bdaBasePage.LpScope.isDisplayed());
    }
    @Test(priority = 8)
    public void CheckSKFDetailsCountry() throws Throwable {
        BDABasePage bdaBasePage = new BDABasePage(driver);
        String country = "Argentina";
        bdaBasePage.selectCountry(driver, country);
    }
    @Test(priority = 9)
    public void CheckSKFDetailsCompany() throws Throwable {
        BDABasePage bdaBasePage= new BDABasePage(driver);
        bdaBasePage.selectSKFCountry(driver);
        boolean isElementVisible = bdaBasePage.isCountryVisible(driver);
        Assert.assertTrue(isElementVisible);
    }
    @Test(priority = 10)
    public void CheckSKFCompanyLocation() throws Throwable {
        BDABasePage bdaBasePage = new BDABasePage(driver);
        bdaBasePage.selectSKFCompany(driver);
        boolean isCompanyVisible = bdaBasePage.isCompanyVisible(driver);
        Assert.assertTrue(isCompanyVisible);
        boolean isLocationVisible = bdaBasePage.isLocationVisible(driver);
        Assert.assertTrue(isLocationVisible);
    }
    @Test(priority = 11)
    public void RequiredApproval() throws Throwable {
        BDABasePage bdaBasePage = new BDABasePage(driver);
        boolean isElementVisible = bdaBasePage.approvalButton(driver);
        Assert.assertTrue(isElementVisible);
    }
    @Test(priority = 12)
    public void CheckCustomer() throws Throwable {
        BDABasePage bdaBasePage = new BDABasePage(driver);
        bdaBasePage.checkCustomer(driver);
        Assert.assertTrue(bdaBasePage.LpCompany.isDisplayed());
        Assert.assertTrue(bdaBasePage.LpCompanyAdress.isDisplayed());
        Assert.assertTrue(bdaBasePage.LpCompanyCountry.isDisplayed());
    }
    @Test(priority = 14)
    public void CheckCustomerSiteName() throws InterruptedException {
        BDABasePage bdaBasePage = new BDABasePage(driver);
        bdaBasePage.checkCustomerSiteName(driver);
        Assert.assertTrue(bdaBasePage.LpCustomerSiteName.isDisplayed());
    }
    @Test(priority = 15)
    public void CheckAssetDetailsSection() {
        NAMBasePage namBasePage = new NAMBasePage(driver);
        BDABasePage bdaBasePage = new BDABasePage(driver);
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("arguments[0].click();", namBasePage.AssetDetails);
        Assert.assertTrue(namBasePage.AssetType.isDisplayed());
        Assert.assertTrue(bdaBasePage.MachineAsset.isDisplayed());
    }
    @Test(priority = 16)
    public void CheckAssetType() throws Throwable {
        BDABasePage bdaBasePage = new BDABasePage(driver);
        bdaBasePage.checkAssetType(driver);
        Assert.assertTrue(bdaBasePage.LpFunctionalArea.isDisplayed());
    }
    @Test(priority = 17)
    public void CheckAssetDetails() {
        BDABasePage bdaBasePages = new BDABasePage(driver);
        bdaBasePages.FunctionalAreaNameWhereAssetIsUsed.click();
        bdaBasePages.FunctionalAreaNameWhereAssetIsUsed.sendKeys("Operations");
        BasePage.blurElementWithJS(driver, bdaBasePages.FunctionalAreaNameWhereAssetIsUsed);
        bdaBasePages.SystemName.click();
        bdaBasePages.SystemName.sendKeys("Operation 1");
        BasePage.blurElementWithJS(driver, bdaBasePages.SystemName);
        Assert.assertTrue(bdaBasePages.LpFunctionalAreaWhereAssetIsUsed.isDisplayed());
        Assert.assertTrue(bdaBasePages.LpSystemName.isDisplayed());
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
        BDABasePage bdaBasePages = new BDABasePage(driver);
        bdaBasePages.checkBearingType(driver);
    }
    @Test(priority = 20)
    public void CheckComponentParts() {
        BDABasePage bdaBasePage = new BDABasePage(driver);
        bdaBasePage.checkComponentsParts(driver);
        List<WebElement> ComponentSize = driver.findElements(By.cssSelector(".accordion-menu-container"));
        Assert.assertEquals(ComponentSize.size(), 9);
    }
    @Test(priority = 21)
    public void CheckUploadImages() throws Throwable {
        BDABasePage bdaBasePage = new BDABasePage(driver);
        bdaBasePage.UploadImg.click();
        Assert.assertTrue(bdaBasePage.ModalDialogForImages.isDisplayed());
        String firstTabHandle = driver.getWindowHandle();
        Thread.sleep(4000);
        bdaBasePage.FileInput.click();
        bdaBasePage.uploadImages();
//        driver.switchTo().newWindow(WindowType.TAB);
//        ((JavascriptExecutor) driver).executeScript("window.open();");
//        Set<String> windowHandles = driver.getWindowHandles();
//        String secondTabHandle = windowHandles.stream().filter(handle -> !handle.equals(firstTabHandle)).findFirst().orElse(null);
//        driver.switchTo().window(secondTabHandle);
//        driver.get("https://s3-eu-west-1.amazonaws.com/bart-dev-images/8586/076INCNppUkCKXDj1YrG2IglKAejy4uRWhoberOwRf4g9tTMUM81T32GRxpLAz6i.jpeg");
//        WebElement Picture = driver.findElement(By.xpath("//body//img"));
//        driver.switchTo().window(firstTabHandle);
//        WebElement targetElement = driver.findElement(By.cssSelector(".dropzone-inputLabel"));
//        Actions actions = new Actions(driver);
//        actions.moveToElement(Picture).perform();
//        actions.clickAndHold().perform();
//        actions.moveToElement(targetElement).perform();
//        actions.release().perform();
    }
    @Test(priority = 22)
    public void AssertImages() {
        BDABasePage bdaBasePage = new BDABasePage(driver);
        wait.until(ExpectedConditions.elementToBeClickable(bdaBasePage.Upload)).click();
        wait.until(ExpectedConditions.elementToBeClickable(bdaBasePage.Close)).click();
        Assert.assertEquals(bdaBasePage.LpImageContainer.size(), 2);
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
        BDABasePage bdaBasePage = new BDABasePage(driver);
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("window.scrollBy(0, 300)");
        BDABasePage.clickCauseElement(driver);
        Thread.sleep(2000);
        bdaBasePage.cause(driver);
        Assert.assertTrue(BDABasePage.isLpCauseDisplayed(driver));
    }
    @Test(priority = 26)
    public void CreateNewBearing() {
        BDABasePage bdaBasePage = new BDABasePage(driver);
        bdaBasePage.clickAddNewButton(driver);
        bdaBasePage.clickAddNewEditButton(driver);
        bdaBasePage.clickLpAddNew(driver);
    }
    @Test(priority = 27)
    public void CheckThreeDotsMenu() throws Throwable {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        BDABasePage bdaBasePage = new BDABasePage(driver);
        jsExecutor.executeScript("window.scrollTo(0, 0)");
        bdaBasePage.ThreeDots.click();
        Thread.sleep(3000);
        Assert.assertEquals(bdaBasePage.ThreeDotsContainer.size(), 4);
    }
    @Test(priority = 28)
    public void DuplicateBearing() throws Throwable {
        BDABasePage bdaBasePage = new BDABasePage(driver);
        bdaBasePage.duplicate(driver);
        Assert.assertEquals(bdaBasePage.lpBearingsSize2.size(), 2);
    }
    @Test(priority = 29)
    public void DeleteFirstBearing() throws Throwable {
        BDABasePage bdaBasePage = new BDABasePage(driver);
        bdaBasePage.deleteFirstBearing(driver);
    }
    @Test(priority = 30)
    public void DeleteSecondBearing() throws Throwable {
        BDABasePage bdaBasePage = new BDABasePage(driver);
        bdaBasePage.deleteSecondBearing(driver);
        Assert.assertEquals(bdaBasePage.lpBearings.size(), 1);
        WebElement SaveBtn = driver.findElement(By.cssSelector("#buttonReload"));
        SaveBtn.click();
    }
    @Test(priority = 31)
    public void CheckConclusionAndRecommendations() throws Throwable {
        BDABasePage bdaBasePage = new BDABasePage(driver);
        bdaBasePage.conclusionsAndRec(driver);
        bdaBasePage.lpRecommendations.isDisplayed();
    }
    @Test(priority = 32)
    public void Submit() throws Throwable {
        BDABasePage bdaBasePage = new BDABasePage(driver);
        bdaBasePage.submit(driver);
        Thread.sleep(3000);
        bdaBasePage.ConfirmSubmittingMessage.isDisplayed();
        bdaBasePage.ConfirmSubmittingBtn.click();
        Thread.sleep(4000);
        bdaBasePage.StatusSubmitted.isDisplayed();
    }
    @Test(priority = 33)
    public void CheckIconToolbarSubmit() {
        BDABasePage bdaBasePage = new BDABasePage(driver);
        List<WebElement> IconToolbar = driver.findElements(By.cssSelector(".button-bar__group"));
        Assert.assertEquals(IconToolbar.size(), 3);
        Assert.assertTrue(bdaBasePage.isApproveVisible(driver));
        Assert.assertTrue(bdaBasePage.isApproveWithEditsVisible(driver));
        Assert.assertTrue(bdaBasePage.isRejectVisible(driver));
    }
    @Test(priority = 34)
    public void Approve() {
        BDABasePage bdaBasePage = new BDABasePage(driver);
        bdaBasePage.approve(driver);
    }
    @Test(priority = 35)
    public void ApproveAndEdit() throws Throwable {
        BDABasePage bdaBasePage = new BDABasePage(driver);
        bdaBasePage.approveAndEdit(driver);
    }
    @Test(priority = 36)
    public void EditReport() throws Throwable {
        BDABasePage bdaBasePage = new BDABasePage(driver);
        bdaBasePage.editReport(driver);
    }
    @Test(priority = 37)
    public void DeleteReport() throws Throwable {
        BDABasePage bdaBasePage = new BDABasePage(driver);
        bdaBasePage.deleteReport(driver);
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
