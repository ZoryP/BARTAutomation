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
    String url = "https://dnnfsk8ppi4ki.cloudfront.net/";

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
        BDAPage bdaPage = new BDAPage(driver);
        String terNumber = "1111";
        bdaPage.enterSkfTerNumber(driver, terNumber);
        String scope = "Bearing";
        bdaPage.enterScopeOfInvestigation(driver, scope);
        bdaPage.clickSKFDetails(driver);
        WebElement lpTER = driver.findElement(By.xpath("//div[@class='live-preview-key-value__value' and text()='1111']"));
        boolean isElementVisible = BDAPage.isElementVisibleWithJS(driver, lpTER);
        Assert.assertTrue(isElementVisible);
        WebElement lpScope = driver.findElement(By.xpath("//div[@class='live-preview-key-value__value' and contains(., 'Bearing')]"));
        boolean isElementVisibleTo = BDAPage.isElementVisibleWithJS(driver, lpScope);
        Assert.assertTrue(isElementVisibleTo);
    }

    @Test(priority = 6)
    public void SKFDetailsCountry() throws Throwable {
        BDAPage bdaPage = new BDAPage(driver);
        String country = "Argentina";
        bdaPage.selectCountry(driver, country);

    }

    @Test(priority = 7)
    public void SKFDetailsCompany() throws Throwable {
        BDAPage bdaPage = new BDAPage(driver);
        bdaPage.selectSKFCountry(driver);
        boolean isElementVisible = bdaPage.isCountryVisible(driver);
        Assert.assertTrue(isElementVisible);
    }

    @Test(priority = 8)
    public void SKFCompanyLocation() throws Throwable {
        BDAPage bdaPage = new BDAPage(driver);
        bdaPage.selectSKFCompany(driver);
        boolean isCompanyVisible = bdaPage.isCompanyVisible(driver);
        Assert.assertTrue(isCompanyVisible);
        boolean isLocationVisible = bdaPage.isLocationVisible(driver);
        Assert.assertTrue(isLocationVisible);
    }

    @Test(priority = 9)
    public void Approval() throws Throwable {
        BDAPage bdaPage = new BDAPage(driver);
        boolean isElementVisible = bdaPage.approvalButton(driver);
        Assert.assertTrue(isElementVisible);
    }

    @Test(priority = 10)
    public void CustomerDetails() throws Throwable {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        WebElement CustomerDetails = driver.findElement(By.xpath("//button[normalize-space()='Customer Details']"));
        BART.BDAPage.clickElementWithJS(driver, CustomerDetails);
        WebElement Customer = driver.findElement(By.xpath("(//div[contains(text(),'Type 3 characters')])"));
        jsExecutor.executeScript("arguments[0].click();", Customer);
        Actions actions = new Actions(driver);
        actions.sendKeys(Customer, "LODI SPA").perform();
        Thread.sleep(3000);
        actions.sendKeys(Keys.ENTER).build().perform();
        Thread.sleep(3000);
        WebElement LpCompany = driver.findElement(By.xpath("//div[@class='live-preview-key-value__value' and contains(., 'LODI SPA')]"));
        boolean isElementVisible = BDAPage.isElementVisibleWithJS(driver, LpCompany);
        Assert.assertTrue(isElementVisible);
        WebElement LpCompanyAdr = driver.findElement(By.xpath("//div[@class='live-preview-key-value__value' and contains(., 'VIA DELLA')]"));
        boolean isElementVisibleTo = BDAPage.isElementVisibleWithJS(driver, LpCompanyAdr);
        Assert.assertTrue(isElementVisibleTo);
        WebElement LpCompanyCntr = driver.findElement(By.xpath("//div[@class='live-preview-key-value__value' and contains(., 'Italy')]"));
        boolean isElementVisibleTo2 = BDAPage.isElementVisibleWithJS(driver, LpCompanyCntr);
        Assert.assertTrue(isElementVisibleTo2);
    }
}