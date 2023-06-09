package BART;

import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

public class BearingInspectionNAM {
    WebDriver driver;
    String url = "https://dnnfsk8ppi4ki.cloudfront.net/";
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
                .pollingEvery(Duration.ofSeconds(3))
                .ignoring(NoSuchElementException.class);
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
    public void CheckSettings() {
        NAMBasePage namBasePage = new NAMBasePage(driver);
        namBasePage.SettingsContainerNAM.click();
        int expectedSize = 4;
        Assert.assertEquals(namBasePage.SettingsSizeNam.size(), expectedSize);
    }
    @Test(priority = 4)
    public void CheckContainerSettings() throws InterruptedException {
        NAMBasePage namBasePage = new NAMBasePage(driver);
        namBasePage.checkContainerNAM(driver);
        TimeUnit.SECONDS.sleep(3);
        wait.until(ExpectedConditions.elementToBeClickable(namBasePage.SettingsNAM)).click();
    }
    @Test(priority = 5)
    public void CheckEditTreeNAM() {
        NAMBasePage namBasePage = new NAMBasePage(driver);
        Assert.assertEquals(namBasePage.AccordeonNam.size(), 7);
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
        wait.until(ExpectedConditions.visibilityOfAllElements(namBasePage.LpDistributionList,namBasePage.LpReportTitle));
        Assert.assertTrue(namBasePage.LpReportTitle.isDisplayed());
        Assert.assertTrue(namBasePage.LpDistributionList.isDisplayed());
    }
    @Test(priority = 15)
    public void CheckAssetDetails() throws Throwable {
        NAMBasePage namBasePage = new NAMBasePage(driver);
        namBasePage.AssetDetails.click();
        namBasePage.AssetType.click();
        namBasePage.setMachineAsset(driver);
        //assert
    }
}
