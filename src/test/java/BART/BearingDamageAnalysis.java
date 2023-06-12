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
    public void CustomerDetails1() throws Throwable {
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
    }

    @Test(priority = 11)
    public void CustomerDetails2() {
        WebElement LpCompanyAdr = driver.findElement(By.xpath("//div[@class='live-preview-key-value__value' and contains(., 'VIA DELLA')]"));
        WebElement LpCompanyCntr = driver.findElement(By.xpath("//div[@class='live-preview-key-value__value' and contains(., 'Italy')]"));
        LpCompanyAdr.isDisplayed();
        LpCompanyCntr.isDisplayed();
    }

    @Test(priority = 12)
    public void CustomerDetails3() {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("window.scrollBy(0, 800)");
        WebElement CustomerSiteName = driver.findElement(By.xpath("//div[@data-id='customerDetails.site']//input[@type='text']"));
        jsExecutor.executeScript("arguments[0].click();", CustomerSiteName);
        Actions actions = new Actions(driver);
        actions.sendKeys(CustomerSiteName, "www.lodispa.com").perform();
        actions.sendKeys(Keys.ENTER).build().perform();
        BART.BDAPage.blurElementWithJS(driver, CustomerSiteName);
        WebElement LpCustomerSiteName = driver.findElement(By.xpath("//div[@class='live-preview-key-value__value' and contains(., 'www.lodispa.com')]"));
        LpCustomerSiteName.isDisplayed();
    }

    @Test(priority = 13)
    public void AssetDetails() {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        WebElement AssetDetails = driver.findElement(By.xpath("//button[normalize-space()='Asset Details']"));
        jsExecutor.executeScript("arguments[0].click();", AssetDetails);
        WebElement AssetType = driver.findElement(By.xpath("//button[normalize-space()='Asset type / Functional Area / System'] "));
        WebElement Machine = driver.findElement(By.xpath(" //button[normalize-space()='Machine / Asset Details']"));
        AssetType.isDisplayed();
        Machine.isDisplayed();
    }

    @Test(priority = 14)
    public void AssetType() throws Throwable {
        BDAPage bdaPage = new BDAPage(driver);
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        bdaPage.AssetType.click();
        WebElement FunctionalArea = driver.findElement(By.xpath("//div[@data-id='assetDetails.assetTypeOrFunctionalAreaOrSystem.machineOrAssetCode']//div[contains(@class,'css-1hwfws3')]"));
        jsExecutor.executeScript("arguments[0].click();", FunctionalArea);
        Actions actions = new Actions(driver);
        Thread.sleep(2000);
        actions.sendKeys(FunctionalArea, "Air handling unit").perform();
        Thread.sleep(3000);
        actions.sendKeys(Keys.ENTER).build().perform();
        BART.BDAPage.blurElementWithJS(driver, FunctionalArea);
        WebElement LpFunctionalArea = driver.findElement(By.xpath("//div[@class='live-preview-key-value__value' and contains(., 'Air handling unit, Air conditioner (AC)')]"));
        LpFunctionalArea.isDisplayed();
    }

    @Test(priority = 15)
    public void WhereAsset() {
        BDAPage bdaPage = new BDAPage(driver);
        bdaPage.FunctionalAreaNameWhereAssetIsUsed.click();
        bdaPage.FunctionalAreaNameWhereAssetIsUsed.sendKeys("Operations");
        BART.BDAPage.blurElementWithJS(driver, bdaPage.FunctionalAreaNameWhereAssetIsUsed);
        bdaPage.SystemName.click();
        bdaPage.SystemName.sendKeys("Operation 1");
        BART.BDAPage.blurElementWithJS(driver, bdaPage.SystemName);
        WebElement LpFunctionalAreaNameWhereAssetIsUsed = driver.findElement(By.xpath("//div[@class='live-preview-key-value__value' and contains(., 'Operations')]"));
        WebElement LpSystemName = driver.findElement(By.xpath("//div[@class='live-preview-key-value__value' and contains(., 'Operation 1')]"));
        LpFunctionalAreaNameWhereAssetIsUsed.isDisplayed();
        LpSystemName.isDisplayed();
    }
    @Test(priority = 16)
    public void Bearing1() {
        BDAPage bdaPage = new BDAPage(driver);
        bdaPage.BearingInvestigations.click();
        bdaPage.Bearing1.click();
        bdaPage.BearingTypeSection.click();
        BDAPage.clickSKFOther(driver);
        BDAPage.enterSKFOtherValue(driver, "SKF");
        BDAPage.clickSKFSerialNumber(driver);
        BDAPage.enterSKFSerialNumberValue(driver, "PEER");
        WebElement LpSerialNumber = driver.findElement(By.xpath("//div[@class='live-preview-key-value__header live-preview-key-value__bearingheader']//span[contains(text(),'PEER / SKF')]"));
        LpSerialNumber.isDisplayed();
    }
    @Test(priority = 17)
    public void ComponentParts(){
        BDAPage bdaPage = new BDAPage(driver);
    }
}
