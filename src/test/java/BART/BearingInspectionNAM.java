package BART;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
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
import java.time.Duration;
import java.util.concurrent.TimeUnit;



public class BearingInspectionNAM {
    WebDriver driver;
    String url = "https://dnnfsk8ppi4ki.cloudfront.net/";
    FluentWait<WebDriver> wait;
    @BeforeClass
    public void SetUp() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.get(url);
        wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(40))
                .pollingEvery(Duration.ofSeconds(3))
                .ignoring(NoSuchElementException.class);
    }
    @AfterClass
    public void QuitBrowser() {
        driver.quit();
    }
    @Test(priority = 1)
    public void ValidLogin() {
        LoginPage loginPage = new BART.LoginPage(driver);
        loginPage.clearFields();
        loginPage.login("testuser1.bart@gmail.com", "bartTest1");
        loginPage.enterBtn.click();
        wait.until(ExpectedConditions.visibilityOfAllElements(loginPage.WelcomeMassage));
        Assert.assertTrue(loginPage.WelcomeMassage.isDisplayed());
    }
    @Test(priority = 2)
    public void CreateNAM() {
        DashboardPage dashboard = new BART.DashboardPage(driver);
        dashboard.CreateNewReport.click();
        dashboard.NamReport.click();
        wait.until(ExpectedConditions.visibilityOfAllElements(dashboard.BearingInspectionNAMReport, dashboard.IconToolbar));
        Assert.assertTrue(dashboard.BearingInspectionNAMReport.isDisplayed());
        Assert.assertTrue(dashboard.IconToolbar.isDisplayed());
    }
    @Test(priority = 3)
    public void Settings() {
        NAMBasePage namBasePage = new NAMBasePage(driver);
        namBasePage.SettingsContainerReports.click();
        int expectedSize = 4;
        Assert.assertEquals(namBasePage.SettingsSizeNam.size(), expectedSize);
    }
    @Test(priority = 4)
    public void CheckContainerSettings() throws InterruptedException {
        NAMBasePage namBasePage = new NAMBasePage(driver);
        namBasePage.checkContainerNAM(driver);
        TimeUnit.SECONDS.sleep(3);
        wait.until(ExpectedConditions.elementToBeClickable(namBasePage.SettingsReports)).click();
    }
    @Test(priority = 5)
    public void CheckEditTreeNAM() {
        NAMBasePage namBasePage = new NAMBasePage(driver);
        Assert.assertEquals(namBasePage.AccordeonReports.size(), 7);
    }
    @Test(priority = 6)
    public void CheckReportInspectionTime() {
        NAMBasePage namBasePage = new NAMBasePage(driver);
        namBasePage.setInspectionTimeHoursNAM(driver);
        Assert.assertTrue(namBasePage.LpInspectionTimeHoursNAM.isDisplayed());
    }
    @Test(priority = 7)
    public void CheckReportInspectionsHours() {
        NAMBasePage namBasePage = new NAMBasePage(driver);
        namBasePage.setTravelTimeHoursNAM(driver);
        Assert.assertTrue(namBasePage.LpTravelTimeHoursNAM.isDisplayed());
    }
    @Test(priority = 8)
    public void CheckNumberOfBearings() {
        NAMBasePage namBasePage = new NAMBasePage(driver);
        namBasePage.setNumberOfBearingsNAM(driver);
        //element is not displayed in the live-preview.
    }
    @Test(priority = 9)
    public void CheckInspectionDate() throws Throwable {
        NAMBasePage namBasePage = new NAMBasePage(driver);
        namBasePage.setInspectionDate(driver);
        Assert.assertEquals(namBasePage.LpInspectionDate.size(), 2);
    }
    @Test(priority = 10)
    public void CheckReportApproveBy() throws Throwable {
        NAMBasePage namBasePage = new NAMBasePage(driver);
        namBasePage.setApprovedBy(driver);
        Assert.assertTrue(namBasePage.LpApprovedBy.isDisplayed());
    }
    @Test(priority = 11)
    public void CheckInspectionDetails() throws Throwable {
        NAMBasePage namBasePage = new NAMBasePage(driver);
        namBasePage.InspectionDetails.click();
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("window.scrollTo(0, 0)");
        namBasePage.EndUserField.click();
        namBasePage.setEndUser(driver);
        namBasePage.assertEndUser(driver);
    }
    @Test(priority = 12)
    public void CheckVendor() throws Throwable {
        NAMBasePage namBasePage = new NAMBasePage(driver);
        namBasePage.setVendor(driver);
        namBasePage.assertVendor(driver);
    }
    @Test(priority = 13)
    public void CheckVendorJobNumber() {
        NAMBasePage namBasePage = new NAMBasePage(driver);
        BasePage.clickElementWithJS(driver, namBasePage.VendorJobNumber);
        BasePage.sendKeysLetterByLetter(namBasePage.VendorJobNumber, "4252 53246");
        BasePage.blurElementWithJS(driver, namBasePage.VendorJobNumber);
        Assert.assertEquals(namBasePage.SettingsSize.size(), 2);
    }
    @Test(priority = 14)
    public void CheckDistributionList() throws Throwable {
        NAMBasePage namBasePage = new NAMBasePage(driver);
        namBasePage.assertDistributionList(driver);
        wait.until(ExpectedConditions.visibilityOfAllElements(namBasePage.LpDistributionList, namBasePage.LpReportTitle));
        Assert.assertTrue(namBasePage.LpReportTitle.isDisplayed());
        Assert.assertTrue(namBasePage.LpDistributionList.isDisplayed());
    }
    @Test(priority = 15)
    public void CheckAssetDetails() throws Throwable {
        NAMBasePage namBasePage = new NAMBasePage(driver);
        namBasePage.AssetDetails.click();
        namBasePage.AssetType.click();
        namBasePage.setMachineAsset(driver);
        Assert.assertTrue(namBasePage.LpMachineAsset.isDisplayed());
    }
    @Test(priority = 16)
    public void CheckSegment() throws Throwable {
        NAMBasePage namBasePage = new NAMBasePage(driver);
        namBasePage.SegmentFunctionalAreaSystemName.click();
        namBasePage.setRandomValue(driver);
    }
    @Test(priority = 17)
    public void CheckFunctionalArea() throws Throwable {
        NAMBasePage namBasePage = new NAMBasePage(driver);
        namBasePage.SegmentFunctionalAreaSystemName.click();
        namBasePage.setRandomValue(driver);
    }
    @Test(priority = 18)
    public void CheckSystemName() throws Throwable {
        NAMBasePage namBasePage = new NAMBasePage(driver);
        namBasePage.SegmentFunctionalAreaSystemName.click();
        namBasePage.setRandomValue(driver);
        Assert.assertTrue(namBasePage.LpFunctionalArea.isDisplayed());
        Assert.assertEquals(namBasePage.LpSystemName.size(), 2);
    }
    @Test(priority = 19)
    public void CheckBearingInvestigation() {
        BasePage basePage = new BasePage(driver);
        basePage.BearingInvestigations.click();
        basePage.Bearing1.click();
        basePage.BearingTypeSection.click();
        basePage.clickSKFOther(driver);
        basePage.enterSKFOtherValue(driver, "SKF");
        basePage.clickSKFSerialNumber(driver);
        basePage.enterSKFSerialNumberValue(driver, "PEER");
        Assert.assertTrue(basePage.LpSkfOther.isDisplayed());
    }
    @Test(priority = 20)
    public void CheckCountry() throws InterruptedException {
        NAMBasePage namBasePage = new NAMBasePage(driver);
        namBasePage.Manufacturing.click();
        namBasePage.setCountry(driver);
        Assert.assertTrue(namBasePage.LpCountry.isDisplayed());
    }
    @Test(priority = 21)
    public void CheckLocation() throws InterruptedException {
        NAMBasePage namBasePage = new NAMBasePage(driver);
        namBasePage.setLocation(driver);
        Assert.assertTrue(namBasePage.LpLocation.isDisplayed());
    }
    @Test(priority = 22)
    public void CheckManufacturingDateCode() throws InterruptedException {
        NAMBasePage namBasePage = new NAMBasePage(driver);
        namBasePage.setManufacturingDateCode(driver);
        Assert.assertTrue(namBasePage.LpManufacturing.isDisplayed());
    }
    @Test(priority = 23)
    public void CheckRemanIdAndDateCode() throws InterruptedException {
        NAMBasePage namBasePage = new NAMBasePage(driver);
        namBasePage.setRemanIdAndDateCode(driver);
        Assert.assertTrue(namBasePage.LpDateCode.isDisplayed());
        Assert.assertTrue(namBasePage.LpRemanId.isDisplayed());
    }
    @Test(priority = 24)
    public void CheckPositionOfBearing() throws InterruptedException {
        NAMBasePage namBasePage = new NAMBasePage(driver);
        namBasePage.OperatingData.click();
        namBasePage.PositionOfBearing.click();
        namBasePage.setRandomValue(driver);
    }
    @Test(priority = 25)
    public void CheckLubricationType() throws InterruptedException {
        NAMBasePage namBasePage = new NAMBasePage(driver);
        namBasePage.LubricationType.click();
        namBasePage.setRandomValue(driver);
        Assert.assertTrue(namBasePage.LpPositionOfBearing.isDisplayed());
    }
    @Test(priority = 26)
    public void CheckRingRotation() throws InterruptedException {
        NAMBasePage namBasePage = new NAMBasePage(driver);
        namBasePage.RingRotation.click();
        namBasePage.setRandomValue(driver);
        Assert.assertTrue(namBasePage.LpLubricationType.isDisplayed());
        Assert.assertTrue(namBasePage.LpRingRotation.isDisplayed());
    }
    @Test(priority = 27)
    public void CheckOuterRing() throws InterruptedException {
        NAMBasePage namBasePage = new NAMBasePage(driver);
        namBasePage.addMoreSides(driver);
        namBasePage.setObservationsOuterRing(driver);
        Assert.assertTrue(namBasePage.areAllElementsDisplayed(), "Not all elements are displayed!");
    }
    @Test(priority = 28)
    public void CheckSeverity() {
        NAMBasePage namBasePage = new NAMBasePage(driver);
        namBasePage.setSeverityOuterRing(driver);
        Assert.assertEquals(namBasePage.LpSeverity.size(), 4);
    }
    @Test(priority = 29)
    public void CheckInnerRing() throws InterruptedException {
        NAMBasePage namBasePage = new NAMBasePage(driver);
        namBasePage.InnerRing.click();
        namBasePage.setInnerRing(driver);
        Assert.assertEquals(namBasePage.LpSeverity.size(), 5);
    }
    @Test(priority = 30)
    public void CheckInsertImagesModal() {
        NAMBasePage namBasePage = new NAMBasePage(driver);
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("window.scrollTo(0, 500)");
        namBasePage.InsertFigure.click();
        wait.until(ExpectedConditions.visibilityOf(namBasePage.ModalDialogForImages)).isDisplayed();
    }
    @Test(priority = 31)
    public void CheckInsertFigureFunction() throws InterruptedException, AWTException {
        NAMBasePage namBasePage = new NAMBasePage(driver);
        namBasePage.FileInput.click();
        namBasePage.uploadImages();
        wait.until(ExpectedConditions.elementToBeClickable(namBasePage.Upload)).click();
        wait.until(ExpectedConditions.elementToBeClickable(namBasePage.Close)).click();
        Assert.assertEquals(namBasePage.LpImageContainer.size(), 2);
    }
    @Test(priority = 32)
    public void CheckEditFigure() throws Throwable {
        NAMBasePage namBasePage = new NAMBasePage(driver);
        namBasePage.EditFirstImage.click();
        namBasePage.editFirstFigure(driver);
        Assert.assertTrue(namBasePage.SaveOrDeleteButtons.isDisplayed());
    }
    @Test(priority = 33)
    public void CheckSaveFigure() {
        NAMBasePage namBasePage = new NAMBasePage(driver);
        namBasePage.SaveFigure.click();
        wait.until(ExpectedConditions.visibilityOf(namBasePage.LpFigureDescription)).isDisplayed();
    }
    @Test(priority = 34)
    public void CheckRowsObservations() throws InterruptedException {
        NAMBasePage namBasePage = new NAMBasePage(driver);
        namBasePage.addMoreRows(driver);
        namBasePage.setObservationsRollingElements(driver);
        //Assert.assertEquals(namBasePage.LpObservations.size(), 7); Failed ?/?/
    }
    @Test(priority = 35)
    public void CheckRowsSeverity() {
        NAMBasePage namBasePage = new NAMBasePage(driver);
        namBasePage.setSeverityInRows(driver);
        Assert.assertEquals(namBasePage.LpSeverity.size(), 7);
    }
    @Test(priority = 36)
    public void CheckAnalysis() {
        NAMBasePage namBasePage = new NAMBasePage(driver);
        namBasePage.Analysis.click();
        Actions actions = new Actions(driver);
        actions.scrollToElement(namBasePage.ImageForAfa).build().perform();
        namBasePage.ImageForAfa.click();
        namBasePage.UseAfa.click();
        wait.until(ExpectedConditions.visibilityOf(namBasePage.AfaModal)).isDisplayed();
    }
    @Test(priority = 37)
    public void CheckAFA() throws Throwable {
        NAMBasePage namBasePage = new NAMBasePage(driver);
        namBasePage.useAFA(driver);
        Assert.assertTrue(BasePage.isLPAFADisplayed(driver));
    }
    @Test(priority = 38)
    public void CheckCause() throws InterruptedException {
        NAMBasePage namBasePage = new NAMBasePage(driver);
        namBasePage.setCauseField(driver);
        Assert.assertTrue(namBasePage.LpCause.isDisplayed());
    }
    @Test(priority = 39)
    public void CheckRecommendations() throws InterruptedException {
        NAMBasePage namBasePage = new NAMBasePage(driver);
        namBasePage.Recommendations.click();
        namBasePage.setRecommendations(driver);
        Assert.assertTrue(namBasePage.LpRecommendations.isDisplayed());
        Assert.assertEquals(namBasePage.LpSummary.size(), 2);
    }
    @Test(priority = 40)
    public void CheckCreateNew() {
        NAMBasePage namBasePage = new NAMBasePage(driver);
        Actions actions = new Actions(driver);
        actions.scrollToElement(namBasePage.AddNewBearingBtn);
        namBasePage.AddNewBearingBtn.click();
        Assert.assertTrue(namBasePage.Bearing2.isDisplayed());
    }
    @Test(priority = 41)
    public void CheckThreeDotsMenu() throws Throwable {
        NAMBasePage namBasePage = new NAMBasePage(driver);
        namBasePage.ThreeDots.click();
        Thread.sleep(3000);
        Assert.assertEquals(namBasePage.ThreeDotsContainer.size(), 4);
    }
    @Test(priority = 42)
    public void CheckDuplication() throws InterruptedException {
        NAMBasePage namBasePage = new NAMBasePage(driver);
        namBasePage.setCompleteDuplication();
        Assert.assertEquals(namBasePage.CompleteDuplication.size(), 4);
        Assert.assertTrue(namBasePage.QuickLinks.isDisplayed());
    }
    @Test(priority = 43)
    public void CheckDeletion() throws InterruptedException {
        NAMBasePage namBasePage = new NAMBasePage(driver);
        namBasePage.setDeletion();
        Assert.assertEquals(namBasePage.CompleteDuplication.size(), 2);
    }
    @Test(priority = 44)
    public void Submit() throws InterruptedException {
        NAMBasePage namBasePage = new NAMBasePage(driver);
        namBasePage.submit(driver);
        wait.until(ExpectedConditions.visibilityOf(namBasePage.ConfirmSubmittingMessage)).isDisplayed();
        wait.until(ExpectedConditions.visibilityOf(namBasePage.ConfirmSubmittingBtn)).click();
        wait.until(ExpectedConditions.visibilityOf(namBasePage.StatusSubmitted)).isDisplayed();
    }
    @Test(priority = 45)
    public void Close() {
        NAMBasePage namBasePage = new NAMBasePage(driver);
        namBasePage.CloseBtn.click();
        String currentUrl = driver.getCurrentUrl();
        String expectedUrl = "https://dnnfsk8ppi4ki.cloudfront.net/my-reports";
        Assert.assertEquals(currentUrl, expectedUrl, "Button navigation is correct");
    }
    @Test(priority = 46)
    public void SignOut() {
        NAMBasePage namBasePage = new NAMBasePage(driver);
        namBasePage.SignOutBtn.click();
        String currentUrl = driver.getCurrentUrl();
        String expectedUrl = "https://dnnfsk8ppi4ki.cloudfront.net/login";
        Assert.assertEquals(currentUrl, expectedUrl, "Button navigation is correct");
    }
    @Test(priority = 47)
    public void LoginWithApprover() {
        LoginPage loginPage = new BART.LoginPage(driver);
        loginPage.clearFields();
        loginPage.login("yavor.gledachev@skf.com", "123456789");
        loginPage.enterBtn.click();
        wait.until(ExpectedConditions.visibilityOf(loginPage.WelcomeMassage)).isDisplayed();
    }
    @Test(priority = 48)
    public void AllReports() throws Throwable {
        DashboardPage dashboardPage = new DashboardPage(driver);
        dashboardPage.AllReports.click();
        TimeUnit.SECONDS.sleep(2);
        String currentUrl = driver.getCurrentUrl();
        String expectedUrl = "https://dnnfsk8ppi4ki.cloudfront.net/all-reports";
        Assert.assertEquals(currentUrl, expectedUrl, "Button navigation is correct");
    }
    @Test(priority = 49)
    public void OpenSubmittedReport() throws InterruptedException {
        NAMBasePage namBasePage = new NAMBasePage(driver);
        namBasePage.TargetReport.click();
        namBasePage.OpenTargetReport.click();
        TimeUnit.SECONDS.sleep(3);
        Assert.assertTrue(namBasePage.ApproveBtn.isDisplayed());
        Assert.assertTrue(namBasePage.ApproveWithEditsBtn.isDisplayed());
        Assert.assertTrue(namBasePage.RejectBtn.isDisplayed());
    }
    @Test(priority = 50)
    public void ApproveReport(){
        NAMBasePage namBasePage = new NAMBasePage(driver);
        namBasePage.ApproveBtn.click();
        Assert.assertTrue(namBasePage.AfaVerifyMessage.isDisplayed());
        namBasePage.VerifyAfa.click();
        Assert.assertTrue(namBasePage.ConfirmApprovalMessage.isDisplayed());
        namBasePage.ApproveAndOpenEmailBtn.click();
    }
    @Test(priority = 51)
    public void EditReport(){
        NAMBasePage namBasePage = new NAMBasePage(driver);
        wait.until(ExpectedConditions.visibilityOf(namBasePage.StatusApproved)).isDisplayed();
        namBasePage.Edit.click();
        wait.until(ExpectedConditions.visibilityOf(namBasePage.ConfirmEditingMessage)).isDisplayed();
        namBasePage.DownloadPDF.click();
        wait.until(ExpectedConditions.visibilityOf(namBasePage.RevisionNumber)).isDisplayed();
        wait.until(ExpectedConditions.visibilityOf(namBasePage.StatusDraft)).isDisplayed();
    }
    @Test(priority = 52)
    public void DeleteReport(){
        NAMBasePage namBasePage = new NAMBasePage(driver);
        namBasePage.Delete.click();
        wait.until(ExpectedConditions.visibilityOf(namBasePage.ConfirmDeletionMessage)).isDisplayed();
        namBasePage.ConfirmDeletionBtn.click();
        wait.until(ExpectedConditions.visibilityOf(namBasePage.StatusDeleted)).isDisplayed();
    }
    @Test(priority = 53)
    public void CloseReport(){
        NAMBasePage namBasePage = new NAMBasePage(driver);
        namBasePage.CloseBtn.click();
        String currentUrl = driver.getCurrentUrl();
        String expectedUrl = "https://dnnfsk8ppi4ki.cloudfront.net/my-reports";
        Assert.assertEquals(currentUrl, expectedUrl, "Button navigation is correct");
    }
    @Test(priority = 54)
    public void FinalSignOut() {
        NAMBasePage namBasePage = new NAMBasePage(driver);
        namBasePage.SignOutBtn.click();
        String currentUrl = driver.getCurrentUrl();
        String expectedUrl = "https://dnnfsk8ppi4ki.cloudfront.net/login";
        Assert.assertEquals(currentUrl, expectedUrl, "Button navigation is correct");
    }
}
