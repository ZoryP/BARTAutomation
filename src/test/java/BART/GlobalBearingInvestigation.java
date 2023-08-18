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

    //@AfterClass
    //public void QuitBrowser() {
    //  driver.quit();
    // }
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
        wait.until(ExpectedConditions.visibilityOf(globalBasePage.TerInput)).isDisplayed();
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
    public void CheckSKFDetails()  throws Throwable{
        GlobalBasePage globalBasePage = new GlobalBasePage(driver);
        globalBasePage.setLocation(driver);
        wait.until(ExpectedConditions.visibilityOf(globalBasePage.LpCountry)).isDisplayed();
        wait.until(ExpectedConditions.visibilityOf(globalBasePage.LpCompany)).isDisplayed();
        wait.until(ExpectedConditions.visibilityOf(globalBasePage.LpLocation)).isDisplayed();
    }
    @Test(priority = 9)
    public void CheckApprover() throws Throwable {
        GlobalBasePage globalBasePage = new GlobalBasePage(driver);
        globalBasePage.Approver.click();
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        Actions actions = new Actions(driver);
        actions.scrollToElement(globalBasePage.ApproverName);
        Thread.sleep(3000);
        jsExecutor.executeScript("arguments[0].click();", globalBasePage.ApproverName);
        actions.sendKeys(globalBasePage.ApproverName, "Yavor Gledachev").perform();
        TimeUnit.SECONDS.sleep(3);
        actions.sendKeys(Keys.ENTER).build().perform();
    }
}
