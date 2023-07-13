package BART;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.FluentWait;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.time.Duration;
import java.util.List;
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
        BART.LoginPage loginPage = new BART.LoginPage(driver);
        loginPage.clearFields();
        loginPage.login("testuser1.bart@gmail.com", "bartTest1");
        loginPage.enterBtn.click();
        wait.until(ExpectedConditions.visibilityOfAllElements(loginPage.WelcomeMassage));
        Assert.assertTrue(loginPage.WelcomeMassage.isDisplayed());
    }

    @Test(priority = 2)
    public void CreateNAM() {
        BART.DashboardPage dashboard = new BART.DashboardPage(driver);
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
        List<WebElement> SettingsSize = driver.findElements(By.cssSelector(".settings-menu__action"));
        int expectedSize = 4;
        Assert.assertEquals(SettingsSize.size(), expectedSize);
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
        List<WebElement> Accordeon = driver.findElements(By.id("Tree/tree_root_branch"));
        Assert.assertEquals(Accordeon.size(), 7);
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
        //ASSERT
    }

    @Test(priority = 9)
    public void CheckInspectionDate() throws Throwable {
        NAMBasePage namBasePage = new NAMBasePage(driver);
        namBasePage.setInspectionDate(driver);
        List<WebElement> LpInspectionDate = driver.findElements(By.xpath("//div[contains(@class, 'valueWithSpace')]/span"));
        Assert.assertEquals(LpInspectionDate.size(), 2);
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
        namBasePage.EndUserField.click();
        namBasePage.setEndUser(driver);
        WebElement LpEndUser = driver.findElement(By.xpath("(//div[contains(@class, 'live-preview-key-value')]/div)[24]"));
        String expectedEndUser = "Contact\n" + "Paul Grey, Stacy Taylor, Tim Tolley, Matt Strand\n" + " 434-299-7337\n" + " matthew.strand@gapac.com\n" +
                " stacy.taylor@gapac.com\n" +
                " dmvanval@gapac.com\n" +
                " christopher.ey@gapac.com\n" +
                " trtolley@gapac.com\n" +
                " fgbranch@gapac.com";
        Assert.assertEquals(LpEndUser.getText(), expectedEndUser);
        WebElement LpDistributor = driver.findElement(By.xpath("(//div[contains(@class, 'live-preview-key-value')]/div)[28]"));
        String expectedDistributor = "Contact\n" +
                "Jim Rebok\n" +
                " 540-362-7695\n" +
                " jim.rebok@motion-ind.com";
        Assert.assertEquals(LpDistributor.getText(), expectedDistributor);
    }

    @Test(priority = 12)
    public void CheckVendor() throws Throwable {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("window.scrollTo(0, 600)");
        NAMBasePage namBasePage = new NAMBasePage(driver);
        BasePage.clickElementWithJS(driver, namBasePage.Vendor);
        Actions actions = new Actions(driver);
        actions.sendKeys(namBasePage.Vendor, "Valmet");
        Thread.sleep(3000);
        actions.sendKeys(Keys.ENTER).build().perform();
        BasePage.blurElementWithJS(driver, namBasePage.Vendor);
    }

    @Test(priority = 13)
    public void CheckVendorJobNumber() {
        NAMBasePage namBasePage = new NAMBasePage(driver);
        BasePage.clickElementWithJS(driver, namBasePage.VendorJobNumber);
        BasePage.sendKeysLetterByLetter(namBasePage.VendorJobNumber, "+44 4252 53246");
        BasePage.blurElementWithJS(driver, namBasePage.VendorJobNumber);
    }
    @Test(priority = 14)
    public void CheckDistributionList() throws Throwable {
        NAMBasePage namBasePage = new NAMBasePage(driver);
        BasePage.clickElementWithJS(driver, namBasePage.DistributionList);
        Actions actions = new Actions(driver);
        actions.sendKeys(namBasePage.DistributionList, "Keith.E.Meyers@skf.com");
        Thread.sleep(3000);
        actions.sendKeys(Keys.ENTER).build().perform();
        BasePage.blurElementWithJS(driver, namBasePage.DistributionList);
    }
}
