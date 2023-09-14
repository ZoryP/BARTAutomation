package BART;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.awt.*;
import java.time.Duration;
import java.util.concurrent.TimeUnit;
public class GlobalBearingInvestigation {
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

    @Test(priority = 1)
    public void ValidLogin() {
        BART.LoginPage loginPage = new BART.LoginPage(driver);
        loginPage.clearFields();
        loginPage.login("testuser1.bart@gmail.com", "bartTest1");
        loginPage.enterBtn.click();
        wait.until(ExpectedConditions.visibilityOf(loginPage.WelcomeMassage));
        loginPage.WelcomeMassage.isDisplayed();
    }

    @Test(priority = 2)
    public void CreateGBI() {
        BART.DashboardPage dashboard = new BART.DashboardPage(driver);
        dashboard.CreateNewReport.click();
        dashboard.GlobalReport.click();
        wait.until(ExpectedConditions.visibilityOfAllElements(dashboard.GBIReport, dashboard.IconToolbar));
        Assert.assertTrue(dashboard.GBIReport.isDisplayed());
        Assert.assertTrue(dashboard.IconToolbar.isDisplayed());
    }

    @Test(priority = 3)
    public void Settings() {
        GlobalBasePage globalBasePage = new GlobalBasePage(driver);
        globalBasePage.SettingsContainerReports.click();
        int expectedSize = 6;
        Assert.assertEquals(globalBasePage.SettingContainer.size(), expectedSize);
    }

    @Test(priority = 4)
    public void CheckContainerSettings() throws InterruptedException {
        GlobalBasePage globalBasePage = new GlobalBasePage(driver);
        globalBasePage.checkContainer(driver);
        TimeUnit.SECONDS.sleep(3);
        wait.until(ExpectedConditions.elementToBeClickable(globalBasePage.SettingsReports)).click();
    }

    @Test(priority = 5)
    public void CheckEditTreeGlobal() {
        GlobalBasePage globalBasePage = new GlobalBasePage(driver);
        Assert.assertEquals(globalBasePage.AccordeonReports.size(), 7);
    }

    @Test(priority = 6)
    public void CheckTerNumber() throws Throwable {
        GlobalBasePage globalBasePage = new GlobalBasePage(driver);
        BasePage.clickElementWithJS(driver, globalBasePage.TERNumber);
        Actions actions = new Actions(driver);
        actions.sendKeys(globalBasePage.TERNumber, "TER").perform();
        TimeUnit.SECONDS.sleep(3);
        actions.sendKeys(Keys.ENTER).build().perform();
    }

    @Test(priority = 7)
    public void InsertTerNumber() throws Throwable {
        GlobalBasePage globalBasePage = new GlobalBasePage(driver);
        BasePage.clickElementWithJS(driver, globalBasePage.TerInput);
        Actions actions = new Actions(driver);
        actions.sendKeys(globalBasePage.TerInput, "TA-20191107-53379").perform();
        TimeUnit.SECONDS.sleep(3);
        actions.sendKeys(Keys.ENTER).build().perform();
        globalBasePage.SearchTER.click();
        wait.until(ExpectedConditions.visibilityOf(globalBasePage.TerResult)).isDisplayed();
        wait.until(ExpectedConditions.visibilityOf(globalBasePage.TerCustomer)).isDisplayed();
    }

    @Test(priority = 8)
    public void CheckOtherNumber() throws Throwable {
        GlobalBasePage globalBasePage = new GlobalBasePage(driver);
        BasePage.clickElementWithJS(driver, globalBasePage.TERNumber);
        Actions actions = new Actions(driver);
        actions.sendKeys(globalBasePage.TERNumber, "Other").perform();
        TimeUnit.SECONDS.sleep(3);
        actions.sendKeys(Keys.ENTER).build().perform();
        wait.until(ExpectedConditions.invisibilityOf(globalBasePage.TerResult));
    }

    @Test(priority = 9)
    public void CheckSKFDetails() throws Throwable {
        GlobalBasePage globalBasePage = new GlobalBasePage(driver);
        globalBasePage.setLocation(driver);
        wait.until(ExpectedConditions.visibilityOf(globalBasePage.LpCountry)).isDisplayed();
        wait.until(ExpectedConditions.visibilityOf(globalBasePage.LpCompany)).isDisplayed();
        wait.until(ExpectedConditions.visibilityOf(globalBasePage.LpLocation)).isDisplayed();
    }

    @Test(priority = 10)
    public void CheckApprover() throws Throwable {
        GlobalBasePage globalBasePage = new GlobalBasePage(driver);
        globalBasePage.Approver.click();
        globalBasePage.setApprover(driver);
        wait.until(ExpectedConditions.visibilityOf(globalBasePage.LpApprover)).isDisplayed();
    }

    @Test(priority = 11)
    public void CheckDistributionAndDelegation() throws Throwable {
        GlobalBasePage globalBasePage = new GlobalBasePage(driver);
        globalBasePage.setDistributionDelegation(driver);
        TimeUnit.SECONDS.sleep(2);
        Assert.assertEquals(globalBasePage.EditTreeDelegation.size(), 4);
    }

    @Test(priority = 12)
    public void CheckBearingInvestigationSelectFields() {
        GlobalBasePage globalBasePage = new GlobalBasePage(driver);
        globalBasePage.setBearingTypeSelectSectionFields(driver);
        //ASSERT
    }

    @Test(priority = 13)
    public void CheckDateOfReciept() throws Throwable {
        GlobalBasePage globalBasePage = new GlobalBasePage(driver);
        globalBasePage.setDateOfReciept(driver);
        //ASSERT
    }

    @Test(priority = 14)
    public void CheckBearingInvestigationFreeTextFields() {
        GlobalBasePage globalBasePage = new GlobalBasePage(driver);
        globalBasePage.setBearingTypeFreeSectionFields(driver);
        //ASSERT
    }

    @Test(priority = 15)
    public void CheckGreasingMethod() {
        GlobalBasePage globalBasePage = new GlobalBasePage(driver);
        globalBasePage.setGreasingMethod(driver);
        //ASSERT
    }

    @Test(priority = 16)
    public void CheckCountryOfManufacture() {
        GlobalBasePage globalBasePage = new GlobalBasePage(driver);
        globalBasePage.setCountryOfManufacture(driver);
        //ASSERT
    }

    @Test(priority = 17)
    public void CheckInvestigationDetails() {
        GlobalBasePage globalBasePage = new GlobalBasePage(driver);
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("window.scrollTo(0, 0)");
        globalBasePage.InvestigationDetails.click();
        globalBasePage.setPurposeAndScope(driver);
        //ASSERT
    }

    @Test(priority = 18)
    public void CheckCompanyName() throws InterruptedException {
        GlobalBasePage globalBasePage = new GlobalBasePage(driver);
        globalBasePage.CompanyName.click();
        Actions actions = new Actions(driver);
        actions.sendKeys(globalBasePage.CompanyName, "COLORADO").perform();
        TimeUnit.SECONDS.sleep(4);
        actions.sendKeys(Keys.ENTER).build().perform();
        //ASSERT
    }

    @Test(priority = 19)
    public void CheckBearingInvestigation() throws InterruptedException {
        GlobalBasePage globalBasePage = new GlobalBasePage(driver);
        globalBasePage.BearingInvestigations.click();
        TimeUnit.SECONDS.sleep(2);
        globalBasePage.addMoreSides(driver);
        //ASSERT
    }

    @Test(priority = 20)
    public void CheckAddObservationsInOuterRing() {
        GlobalBasePage globalBasePage = new GlobalBasePage(driver);
        globalBasePage.setObservationsOuterRing(driver);
        //ASSERT
    }
    @Test(priority = 21)
    public void CheckInsertImagesModal() {
        NAMBasePage namBasePage = new NAMBasePage(driver);
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("window.scrollTo(0, 500)");
        namBasePage.InsertFigure.click();
//        wait.until(ExpectedConditions.visibilityOf(namBasePage.ModalDialogForImages)).isDisplayed();
    }
    @Test(priority = 22)
    public void CheckInsertFigureFunction() throws InterruptedException, AWTException {
        NAMBasePage namBasePage = new NAMBasePage(driver);
        namBasePage.FileInput.click();
        namBasePage.uploadImages();
        wait.until(ExpectedConditions.elementToBeClickable(namBasePage.Upload)).click();
        wait.until(ExpectedConditions.elementToBeClickable(namBasePage.Close)).click();
//        Assert.assertEquals(namBasePage.LpImageContainer.size(), 2);
    }
    @Test(priority = 23)
    public void CheckAddObservationsInCages() {
        GlobalBasePage globalBasePage = new GlobalBasePage(driver);
        globalBasePage.Cage.click();
        globalBasePage.CageObservations.click();
        globalBasePage.CageObservations.sendKeys("Moisture corrosion spots.");
        //ASSERT
    }
    @Test(priority = 24)
    public void CheckAddObservationsInOtherComponents() {
        GlobalBasePage globalBasePage = new GlobalBasePage(driver);
        globalBasePage.setComponentsParts(driver);
        //ASSERT
    }
    @Test(priority = 25)
    public void CheckTestEquipment() throws InterruptedException {
        GlobalBasePage globalBasePage = new GlobalBasePage(driver);
        globalBasePage.setTestEquipment(driver);
        //ASSERT
    }
    @Test(priority = 26)
    public void CheckAnalysis() {
        GlobalBasePage globalBasePage = new GlobalBasePage(driver);
        globalBasePage.Analysis.click();
        globalBasePage.Findings.click();
        globalBasePage.Findings.sendKeys("Global Bearing Inspection");
        //ASSERT
    }
    @Test(priority = 27)
    public void CheckObservations() {
        GlobalBasePage globalBasePage = new GlobalBasePage(driver);
        globalBasePage.PrimaryObservations.click();
        globalBasePage.PrimaryObservations.sendKeys("Global Bearing Inspection");
        //ASSERT
    }
    @Test(priority = 28)
    public void CheckAfaAnalysis() {
        NAMBasePage namBasePage = new NAMBasePage(driver);
        GlobalBasePage globalBasePage = new GlobalBasePage(driver);
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("window.scrollTo(0, 700)");
        globalBasePage.ImageForAnalysis.click();
        namBasePage.UseAfa.click();
        wait.until(ExpectedConditions.visibilityOf(namBasePage.AfaModal)).isDisplayed();
    }
    @Test(priority = 29)
    public void CheckAFA() throws Throwable {
        NAMBasePage namBasePage = new NAMBasePage(driver);
        namBasePage.useAFA(driver);
        Assert.assertTrue(BasePage.isLPAFADisplayed(driver));
    }
    @Test(priority = 30)
    public void CheckCause() throws InterruptedException {
        GlobalBasePage globalBasePage = new GlobalBasePage(driver);
        Actions actions = new Actions(driver);
        actions.scrollToElement(globalBasePage.CauseGlobal);
        actions.click(globalBasePage.CauseGlobal);
        actions.sendKeys(globalBasePage.CauseGlobal, "Presence of corrosive liquid");
        TimeUnit.SECONDS.sleep(2);
        actions.sendKeys(Keys.ENTER).build().perform();
        //ASSERT
    }
    @Test(priority = 31)
    public void CheckConclusions() throws InterruptedException {
        GlobalBasePage globalBasePage = new GlobalBasePage(driver);
        globalBasePage.ConclusionsAndRecommendations.click();
        globalBasePage.Disposition.click();
        Actions actions = new Actions(driver);
        actions.sendKeys(globalBasePage.Disposition,"The parts will be sent");
        TimeUnit.SECONDS.sleep(2);
        actions.sendKeys(Keys.ENTER).build().perform();
    }
    @Test(priority = 32)
    public void CheckCreateNew() {
        NAMBasePage namBasePage = new NAMBasePage(driver);
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("window.scrollTo(0, 200)");
        namBasePage.AddNewBearingBtn.click();
        namBasePage.BearingInvestigations.click();
        Assert.assertTrue(namBasePage.Bearing2.isDisplayed());
    }
    @Test(priority = 33)
    public void CheckThreeDotsMenu() throws Throwable {
        NAMBasePage namBasePage = new NAMBasePage(driver);
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("window.scrollTo(0, 200)");
        namBasePage.ThreeDots.click();
        Thread.sleep(3000);
        Assert.assertEquals(namBasePage.ThreeDotsContainer.size(), 4);
    }
    @Test(priority = 34)
    public void CheckDuplication() throws InterruptedException {
        NAMBasePage namBasePage = new NAMBasePage(driver);
        GlobalBasePage globalBasePage = new GlobalBasePage(driver);
        namBasePage.setCompleteDuplication();
        Assert.assertEquals(globalBasePage.CompleteDuplicationGBI.size(), 4);
        Assert.assertTrue(namBasePage.QuickLinks.isDisplayed());
    }
    @Test(priority = 35)
    public void CheckDeletion() throws InterruptedException {
        GlobalBasePage globalBasePage = new GlobalBasePage(driver);
        NAMBasePage namBasePage = new NAMBasePage(driver);
        namBasePage.setDeletion();
        Assert.assertEquals(globalBasePage.CompleteDuplicationGBI.size(), 2);
    }
    @Test(priority = 36)
    public void Submit() throws InterruptedException {
        NAMBasePage namBasePage = new NAMBasePage(driver);
        namBasePage.submit(driver);
        wait.until(ExpectedConditions.visibilityOf(namBasePage.ConfirmSubmittingMessage)).isDisplayed();
        wait.until(ExpectedConditions.visibilityOf(namBasePage.ConfirmSubmittingBtn)).click();
        wait.until(ExpectedConditions.visibilityOf(namBasePage.StatusSubmitted)).isDisplayed();
    }
    @Test(priority = 37)
    public void Close() {
        NAMBasePage namBasePage = new NAMBasePage(driver);
        namBasePage.CloseBtn.click();
        String currentUrl = driver.getCurrentUrl();
        String expectedUrl = "https://dnnfsk8ppi4ki.cloudfront.net/my-reports";
        Assert.assertEquals(currentUrl, expectedUrl, "Button navigation is correct");
    }
    @Test(priority = 38)
    public void SignOut() {
        NAMBasePage namBasePage = new NAMBasePage(driver);
        namBasePage.SignOutBtn.click();
        String currentUrl = driver.getCurrentUrl();
        String expectedUrl = "https://dnnfsk8ppi4ki.cloudfront.net/login";
        Assert.assertEquals(currentUrl, expectedUrl, "Button navigation is correct");
    }
    @Test(priority = 39)
    public void LoginWithApprover() {
        LoginPage loginPage = new BART.LoginPage(driver);
        loginPage.clearFields();
        loginPage.login("yavor.gledachev@skf.com", "123456789");
        loginPage.enterBtn.click();
        wait.until(ExpectedConditions.visibilityOf(loginPage.WelcomeMassage)).isDisplayed();
    }
    @Test(priority = 40)
    public void AllReports() throws Throwable {
        DashboardPage dashboardPage = new DashboardPage(driver);
        dashboardPage.AllReports.click();
        TimeUnit.SECONDS.sleep(2);
        String currentUrl = driver.getCurrentUrl();
        String expectedUrl = "https://dnnfsk8ppi4ki.cloudfront.net/all-reports";
        Assert.assertEquals(currentUrl, expectedUrl, "Button navigation is correct");
    }
}

