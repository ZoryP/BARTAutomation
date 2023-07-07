package BART;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
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
    public void CreateNAM() throws Throwable {
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
    public void CheckEditTreeNAM() {
        List<WebElement> Accordeon = driver.findElements(By.id("Tree/tree_root_branch"));
        Assert.assertEquals(Accordeon.size(), 7);
    }


    @Test(priority = 6)
    public void CheckReportInspectionTime() {
        WebElement ReportDetails = driver.findElement(By.xpath("//button[normalize-space()='Report Details']"));
        BASEPageReports.clickElementWithJS(driver, ReportDetails);
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("window.scrollTo(0, 600)");
        WebElement InspectionTimeHours = driver.findElement(By.xpath("//div[@data-id='reportDetails.inspectionTime']//input[@type='number']"));
        BASEPageReports.clickElementWithJS(driver, InspectionTimeHours);
        BASEPageReports.sendKeysLetterByLetter(InspectionTimeHours, "2");
        BASEPageReports.blurElementWithJS(driver, InspectionTimeHours);
        WebElement LivePreviewInspectionHours = driver.findElement(By.xpath("//div[@class='live-preview-key-value__value' and text()='2']"));
        LivePreviewInspectionHours.isDisplayed();
    }

    @Test(priority = 7)
    public void CheckReportInspectionsHours() {
        WebElement TravelTimeHours = driver.findElement(By.xpath("//div[@data-id='reportDetails.travelTime']//input[@type='number']"));
        BASEPageReports.clickElementWithJS(driver, TravelTimeHours);
        BASEPageReports.sendKeysLetterByLetter(TravelTimeHours, "4");
        BASEPageReports.blurElementWithJS(driver, TravelTimeHours);
        WebElement LivePreviewTravelHours = driver.findElement(By.xpath("//div[@class='live-preview-key-value__value' and text()='4']"));
        LivePreviewTravelHours.isDisplayed();
    }

    @Test(priority = 8)
    public void CheckNumberOfBearings() {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("window.scrollTo(0, 300)");
        WebElement NumberOfBearings = driver.findElement(By.xpath("//div[@data-id='reportDetails.numberOfBearingsInvestigated']//input[@type='text']"));
        BASEPageReports.clickElementWithJS(driver, NumberOfBearings);
        BASEPageReports.sendKeysLetterByLetter(NumberOfBearings, "6");
        BASEPageReports.blurElementWithJS(driver, NumberOfBearings);

    }
    @Test(priority = 9)
    public void CheckInspectionDate() throws Throwable {
        WebElement InspectionDate = driver.findElement(By.xpath("//*[@id=\"app\"]/div[2]/div/div/div[1]/div[2]/div[1]/div[2]/div/div[5]/div/div[2]/div/div[1]/div/input"));
        InspectionDate.click();
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("window.scrollTo(0, 500)");
        Thread.sleep(2000);
        Actions actions = new Actions(driver);
        actions.sendKeys(InspectionDate, Keys.DOWN);
        Thread.sleep(2000);
        actions.sendKeys(InspectionDate, Keys.ENTER).perform();
        List<WebElement> Accordeon = driver.findElements(By.xpath("//div[contains(@class, 'valueWithSpace')]/span"));
        Assert.assertEquals(Accordeon.size(), 2);
    }
    @Test(priority = 10)
    public void CheckReportApproveBy() {

    }
}