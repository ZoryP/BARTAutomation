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
        globalBasePage.RacewayObservations.click();
        globalBasePage.RacewayObservations.sendKeys("1.Surface in good condition.");
        globalBasePage.RacewayObservations.sendKeys(Keys.ENTER);
        globalBasePage.RacewayObservations.sendKeys("2.Scratch.");
        globalBasePage.DiameterObservations.click();
        globalBasePage.DiameterObservations.sendKeys("1.Abrasive wear (frosting, smoothing, glazing).");
        globalBasePage.DiameterObservations.sendKeys(Keys.ENTER);
        globalBasePage.DiameterObservations.sendKeys("2.Chatter marks");
        //ASSERT
    }

    @Test(priority = 21)
    public void CheckAddObservationsInCages() {
        GlobalBasePage globalBasePage = new GlobalBasePage(driver);
        globalBasePage.Cage.click();
        globalBasePage.CageObservations.click();
        globalBasePage.CageObservations.sendKeys("Moisture corrosion spots.");
        //ASSERT
    }

    @Test(priority = 22)
    public void CheckAddObservationsInOtherComponents() {
        GlobalBasePage globalBasePage = new GlobalBasePage(driver);
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("window.scrollTo(0, 100)");
        globalBasePage.InnerRing.click();
        globalBasePage.InnerRingObservations.click();
        globalBasePage.InnerRingObservations.sendKeys("1.Moisture corrosion spots.\n" + "2.Heavy surface wear in contact zone.\n" + "3.Loading zone heavy shifted to one side.\n" + "4.Internal surfaces polished.");
        //ASSERT
    }
    @Test(priority = 23)
    public void CheckTestEquipment() throws InterruptedException  {
        GlobalBasePage globalBasePage = new GlobalBasePage(driver);
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("window.scrollTo(0, 0)");
        globalBasePage.BackgroundInformation.click();
        jsExecutor.executeScript("window.scrollTo(0, 1000)");
        TimeUnit.SECONDS.sleep(2);
        globalBasePage.ConfigureTestEquipment.click();
        TimeUnit.SECONDS.sleep(2);
        globalBasePage.AddRow.click();
        globalBasePage.TestEquipment.click();
        globalBasePage.TestEquipment.sendKeys("TEST 1");
        globalBasePage.TestMethod.click();
        TimeUnit.SECONDS.sleep(2);
        globalBasePage.TestMethod.sendKeys("TEST 2");
        globalBasePage.Operator.click();
        TimeUnit.SECONDS.sleep(2);
        globalBasePage.Operator.sendKeys("TEST 3");
        globalBasePage.Instrument.click();
        TimeUnit.SECONDS.sleep(2);
        globalBasePage.Instrument.sendKeys("TEST 4");
        globalBasePage.DateEquipment.click();
        TimeUnit.SECONDS.sleep(2);
        globalBasePage.DateEquipment.sendKeys("10/12/2023");
        TimeUnit.SECONDS.sleep(2);
        globalBasePage.SaveEquipment.click();
    }
}

