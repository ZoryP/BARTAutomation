package BART;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;

public class BearingInspectionNAM {
    WebDriver driver;
    String url = "https://dnnfsk8ppi4ki.cloudfront.net/";

    @BeforeClass
    public void SetUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.get(url);
    }

    @Test(priority = 1)
    public void ValidLogin() throws Throwable {
        BART.LoginPage loginPage = new BART.LoginPage(driver);
        loginPage.clearFields();
        loginPage.login("testuser1.bart@gmail.com", "bartTest1");
        loginPage.enterBtn.click();
        Thread.sleep(4000);
        loginPage.WelcomeMassage.isDisplayed();
    }

    @Test(priority = 2)
    public void CreateBda() throws Throwable {
        BART.DashboardPage dashboard = new BART.DashboardPage(driver);
        dashboard.CreateNewReport.click();
        dashboard.NamReport.click();
        Thread.sleep(3000);
        dashboard.BearingInspectionNAMReport.isDisplayed();
        dashboard.IconToolbar.isDisplayed();
    }

    @Test(priority = 3)
    public void CheckSettings() {
        WebElement Settings = driver.findElement(By.id("buttonSettings"));
        Settings.click();
        List<WebElement> SettingsSize = driver.findElements(By.cssSelector(".settings-menu__action"));
        Assert.assertEquals(SettingsSize.size(), 4);
    }

    @Test(priority = 4)
    public void CheckContainerSettings() throws Throwable {
        BASEPageReports basePageReports = new BASEPageReports(driver);
        basePageReports.checkContainerNAM(driver);
        Thread.sleep(2000);
        WebElement Settings = driver.findElement(By.id("buttonSettings"));
        Settings.click();
    }

    @Test(priority = 5)
    public void CheckcontainerNAM() {
        List<WebElement> Accordeon = driver.findElements(By.id("Tree/tree_root_branch"));
        Assert.assertEquals(Accordeon.size(), 7);
    }

    WebElement ReportTitle;
    WebElement InspectionTimeTimeHours;

    @Test(priority = 6)
    public void CheckReportDetailsTitle() {
        WebElement ReportDetails = driver.findElement(By.xpath("//button[normalize-space()='Report Details']"));
        BASEPageReports.clickElementWithJS(driver, ReportDetails);
        ReportTitle = driver.findElement(By.xpath("//div[@data-id='reportDetails.reportTitle']//input[@type='text']"));
        BASEPageReports.clickElementWithJS(driver, ReportTitle);
        BASEPageReports.sendKeysLetterByLetter(ReportTitle, "test NAM");
        BASEPageReports.blurElementWithJS(driver, ReportTitle);
        WebElement LivePreviewTitle = driver.findElement(By.xpath("//div[@class='live-preview-key-value__value' and text()='test NAM']"));
        LivePreviewTitle.isDisplayed();
    }

    @Test(priority = 7)
    public void CheckReportDetailsTime() {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("window.scrollTo(0, 600)");
        WebElement InspectionTimeHours = driver.findElement(By.xpath("//div[@data-id='reportDetails.inspectionTime']//input[@type='number']"));
        BASEPageReports.clickElementWithJS(driver, InspectionTimeHours);
        BASEPageReports.sendKeysLetterByLetter(InspectionTimeHours, "2");
        BASEPageReports.blurElementWithJS(driver, InspectionTimeHours);
        WebElement LivePreviewHours = driver.findElement(By.xpath("//div[@class='live-preview-key-value__value' and text()='2']"));
        LivePreviewHours.isDisplayed();
    }
}