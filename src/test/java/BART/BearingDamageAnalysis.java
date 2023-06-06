package BART;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import java.util.List;


public class BearingDamageAnalysis {
    WebDriver driver;
    String url = "https://d2xob39w6dc2ow.cloudfront.net/";

    @BeforeClass
    public void SetUp() {
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--remote-allow-origins=*");
        driver = new ChromeDriver(options);
        driver.manage().window().maximize();
        driver.get(url);
    }

    //@AfterClass
    // public void QuitBrowser() {

    // driver.quit();
    // }

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
        dashboard.BDAReport.click();
        Thread.sleep(3000);
        dashboard.BearingDamageAnalysisReport.isDisplayed();
        dashboard.IconToolbar.isDisplayed();
    }

    @Test(priority = 3)
    public void CheckAccodeon() {
        List<WebElement> Accordeon = driver.findElements(By.id("Tree/tree_root_branch"));
        Assert.assertEquals(Accordeon.size(), 11);
    }
    WebElement ReportTitle;

    @Test(priority = 4)
    public void ReportDetails() {
        WebElement ReportDetails = driver.findElement(By.xpath("//button[normalize-space()='Report Details']"));
        BART.BDAPage.clickElementWithJS(driver, ReportDetails);
        ReportTitle = driver.findElement(By.xpath("//div[@data-id='reportDetails.reportTitle']//input[@type='text']"));
        BART.BDAPage.clickElementWithJS(driver, ReportTitle);
        BART.BDAPage.sendKeysLetterByLetter(ReportTitle, "test");
        BART.BDAPage.blurElementWithJS(driver, ReportTitle);
        WebElement InvestigationDetails = driver.findElement(By.xpath("//button[normalize-space()='Investigation Details']"));
        BART.BDAPage.clickElementWithJS(driver, InvestigationDetails);
        WebElement LivePreviewEl = driver.findElement(By.xpath("//div[@class='live-preview-key-value__value' and text()='test']"));
        boolean isElementVisible = BDAPage.isElementVisibleWithJS(driver, LivePreviewEl);
        Assert.assertTrue(isElementVisible);
    }

    @Test(priority = 5)
    public void InvestigationDetails() {
        WebElement SkfTerNmr = driver.findElement(By.xpath("//div[@data-id='investigationDetails.terNumber']//input[@type='text']"));
        BART.BDAPage.clickElementWithJS(driver, SkfTerNmr);
        BART.BDAPage.sendKeysLetterByLetter(SkfTerNmr, "1111");
        WebElement ScopeOfInv = driver.findElement(By.xpath("//div[@data-id='investigationDetails.scopeOfInvestigation']//p"));
        BART.BDAPage.clickElementWithJS(driver, ScopeOfInv);
        BART.BDAPage.sendKeysLetterByLetter(ScopeOfInv, "Bearing");
        BART.BDAPage.blurElementWithJS(driver, ScopeOfInv);
        WebElement SKFDetails = driver.findElement(By.xpath("//button[normalize-space()='SKF Details']"));
        BART.BDAPage.clickElementWithJS(driver, SKFDetails);
        WebElement LivePreviewEl = driver.findElement(By.xpath("//div[@class='live-preview-key-value__value' and text()='1111']"));
        boolean isElementVisible = BDAPage.isElementVisibleWithJS(driver, LivePreviewEl);
        Assert.assertTrue(isElementVisible);
        WebElement LivePreviewEl2 = driver.findElement(By.xpath("//div[@class='live-preview-key-value__value' and contains(., 'Bearing')]"));
        boolean isElementVisibleTo = BDAPage.isElementVisibleWithJS(driver, LivePreviewEl2);
        Assert.assertTrue(isElementVisibleTo);
    }

    @Test(priority = 6)
    public void SKFDetails() throws Throwable {
        WebElement CountryInd = driver.findElement(By.xpath("(//div[contains(text(),'Select')])[2]"));
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        Thread.sleep(3000);
        jsExecutor.executeScript("arguments[0].click();", CountryInd);
        Actions actions = new Actions(driver);
        actions.sendKeys(CountryInd, "Argentina").perform();
        actions.sendKeys(Keys.ENTER).build().perform();
    }
}

