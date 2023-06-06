package BART;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.ITestResult;
import org.testng.annotations.AfterClass;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.List;


public class BartSmokeTest {
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

    @AfterClass
    public void QuitBrowser() {

        driver.quit();
    }
    @AfterMethod
    public void CloseBrowser(ITestResult result) throws IOException {
        if (ITestResult.FAILURE == result.getStatus()) {
            try {
                String dateTime = LocalDateTime.now().toString();
                File screen = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
                FileUtils.copyFile(screen, new File("./Screenshots/" + result.getName() +
                        dateTime.replace(":", "_") + ".png"));
            } catch (Exception e) {
                System.out.println("Success");
            }
        }
    }

    @Test(priority = 1)
    public void PageTitle() throws Throwable {
        Thread.sleep(2000);
        Assert.assertEquals(driver.getTitle(), "BART 5.2");
    }

    @Test(priority = 2)
    public void PageUrl() throws Throwable {
        Thread.sleep(2000);
        Assert.assertEquals(driver.getCurrentUrl(), "https://dnnfsk8ppi4ki.cloudfront.net/");
    }

    @Test(priority = 3)
    public void InvalidLogin() throws Throwable {
        BART.LoginPage loginpage = new BART.LoginPage(driver);
        loginpage.login("testuser1.bart@gmail.com", "bartTest");
        loginpage.enterBtn.click();
        Thread.sleep(2000);
        loginpage.ErrorLoginMassage.isDisplayed();
        loginpage.ForgotPassword.isEnabled();
    }

    @Test(priority = 4)
    public void ValidLogin () throws Throwable {
        BART.LoginPage loginPage = new BART.LoginPage(driver);
        loginPage.clearFields();
        loginPage.login("testuser1.bart@gmail.com","bartTest1");
        loginPage.enterBtn.click();
        Thread.sleep(4000);
        loginPage.WelcomeMassage.isDisplayed();
    }

    @Test(priority = 5)
    public void Header() throws Throwable {
        BART.DashboardPage bartPageDev = new BART.DashboardPage(driver);
        Thread.sleep(2000);
        bartPageDev.CreateNewReport.isDisplayed();
        bartPageDev.MyReports.isDisplayed();
        bartPageDev.AllReports.isDisplayed();
        bartPageDev.Analytics.isDisplayed();
        bartPageDev.FindNAM.isDisplayed();
        bartPageDev.Share.isDisplayed();
        bartPageDev.Settings.isDisplayed();
        bartPageDev.SignOut.isDisplayed();
    }

    @Test(priority = 6)
    public void Settings() {
        BART.DashboardPage bartPageDev = new BART.DashboardPage(driver);
        bartPageDev.Settings.click();
        WebElement LanguageButton = driver.findElement(By.xpath("//button[@class='header-settings-language__toggler']"));
        LanguageButton.click();
        List<WebElement> LanguageMenu = driver.findElements(By.cssSelector(".header-settings-language__change-action"));
        Assert.assertEquals(LanguageMenu.size(), 19);
    }

    @Test(priority = 7)
    public void CreateNewReport()  {
        BART.DashboardPage bartPageDev = new BART.DashboardPage(driver);
        bartPageDev.CreateNewReport.click();
        List<WebElement> ReportsMenu = driver.findElements(By.cssSelector(".navigation__sub-link"));
        Assert.assertEquals(ReportsMenu.size(), 5);
    }

    @Test(priority = 8)
    public void OpenReport() throws Throwable {
        BART.DashboardPage dashboardPage = new BART.DashboardPage(driver);
        WebElement row = driver.findElement(By.xpath("//*[@class='LinesEllipsis  ' and contains(text(),'test')]"));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", row);
        WebElement targetElement = driver.findElement(By.xpath("//tr[@class='table__body-row table__body-row--is-active']//span[@class='button__text'][normalize-space()='Open Report']"));
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", targetElement);
        Thread.sleep(3000);
        dashboardPage.BearingDamageAnalysisReport.isDisplayed();              /*Live Preview of the report is displayed*/
        dashboardPage.IconToolbar.isDisplayed();      /*Icon Toolbar and Edit box is displayed*/
        dashboardPage.Close.click();
    }

    @Test(priority = 9)
    public void MyReports() {
        BART.DashboardPage bartPageDev = new BART.DashboardPage(driver);
        bartPageDev.MyReports.click();
        String currentUrl = driver.getCurrentUrl();
        String expectedUrl = "https://dnnfsk8ppi4ki.cloudfront.net/my-reports";
        Assert.assertEquals(currentUrl, expectedUrl, "Button navigation is correct");
    }

    @Test(priority = 10)
    public void DuplicateReport() throws Throwable {
        WebElement elements = driver.findElement(By.xpath("//*[@class='LinesEllipsis  ' and contains(text(),'test')]"));
        JavascriptExecutor js = (JavascriptExecutor) driver;
        js.executeScript("arguments[0].click();", elements);
        WebElement targetElement = driver.findElement(By.xpath("//tr[@class='table__body-row table__body-row--is-active']//span[@class='button__text'][normalize-space()='Duplicate']"));
        JavascriptExecutor executor = (JavascriptExecutor) driver;
        executor.executeScript("arguments[0].click();", targetElement);
        Thread.sleep(20000);
        Actions actions = new Actions(driver);
        actions.keyDown(Keys.CONTROL).sendKeys(Keys.F5).keyUp(Keys.CONTROL).build().perform();
        List<WebElement> duplicatedReport = driver.findElements(By.xpath("//*[@class='LinesEllipsis  ' and contains(text(),'test')]"));
        Assert.assertTrue(duplicatedReport.size() > 0);
    }
}