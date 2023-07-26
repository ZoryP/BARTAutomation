package BART;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
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


public class LoginDashboardTests{
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
    public void ValidLogin() throws Throwable {
        BART.LoginPage loginPage = new BART.LoginPage(driver);
        loginPage.clearFields();
        loginPage.login("testuser1.bart@gmail.com", "bartTest1");
        loginPage.enterBtn.click();
        Thread.sleep(4000);
        loginPage.WelcomeMassage.isDisplayed();
    }

    @Test(priority = 5)
    public void Dashboard() {
        DashboardPage dashboardPage = new DashboardPage(driver);
        String currentUrl = driver.getCurrentUrl();
        String expectedUrl = "https://dnnfsk8ppi4ki.cloudfront.net/dashboard";
        Assert.assertEquals(currentUrl, expectedUrl, "Button navigation is correct");
        dashboardPage.Yammer.isDisplayed();
        dashboardPage.UserGuide.isDisplayed();
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("window.scrollBy(0, 500)");
        List<WebElement> RecentReports = driver.findElements(By.cssSelector(".table__header-cell"));
        Assert.assertEquals(RecentReports.size(), 7);

    }

    @Test(priority = 6)
    public void Header() throws Throwable {
        BART.DashboardPage bartPageDev = new BART.DashboardPage(driver);
        Thread.sleep(2000);
        bartPageDev.CreateNewReport.isEnabled();
        bartPageDev.MyReports.isEnabled();
        bartPageDev.AllReports.isEnabled();
        bartPageDev.Analytics.isEnabled();
        bartPageDev.FindNAM.isEnabled();
        bartPageDev.Share.isEnabled();
        bartPageDev.Settings.isEnabled();
        bartPageDev.SignOut.isEnabled();
    }

    @Test(priority = 7)
    public void Settings() {
        BART.DashboardPage bartPageDev = new BART.DashboardPage(driver);
        bartPageDev.Settings.click();
        WebElement LanguageButton = driver.findElement(By.xpath("//button[@class='header-settings-language__toggler']"));
        LanguageButton.click();
        List<WebElement> LanguageMenu = driver.findElements(By.cssSelector(".header-settings-language__change-action"));
        Assert.assertEquals(LanguageMenu.size(), 19);
    }

    @Test(priority = 8)
    public void CreateNewReport() {
        BART.DashboardPage bartPageDev = new BART.DashboardPage(driver);
        bartPageDev.CreateNewReport.click();
        List<WebElement> ReportsMenu = driver.findElements(By.cssSelector(".navigation__sub-link"));
        Assert.assertEquals(ReportsMenu.size(), 5);
    }

    @Test(priority = 9)
    public void OpenReport() throws Throwable {
        BART.DashboardPage dashboardPage = new BART.DashboardPage(driver);
        dashboardPage.MyReports.click();
        Thread.sleep(3000);
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

    @Test(priority = 10)
    public void AllReports() throws Throwable{
        BART.DashboardPage bartPageDev = new BART.DashboardPage(driver);
        bartPageDev.AllReports.click();
        Thread.sleep(2000);
        String currentUrl = driver.getCurrentUrl();
        String expectedUrl = "https://dnnfsk8ppi4ki.cloudfront.net/all-reports";
        Assert.assertEquals(currentUrl, expectedUrl, "Button navigation is correct");
    }

    @Test(priority = 11)
    public void SearchBarAllReports() {
        List<WebElement> SearchbarAll = driver.findElements(By.cssSelector(".filter-group__toggler"));
        Assert.assertEquals(SearchbarAll.size(), 11);
        List<WebElement> ResultsAll = driver.findElements(By.cssSelector(".table__header-cell"));
        Assert.assertEquals(ResultsAll.size(), 7);
    }
    @Test(priority = 12)
    public void MyReports() {
        BART.DashboardPage bartPageDev = new BART.DashboardPage(driver);
        bartPageDev.MyReports.click();
        String currentUrl = driver.getCurrentUrl();
        String expectedUrl = "https://dnnfsk8ppi4ki.cloudfront.net/my-reports";
        Assert.assertEquals(currentUrl, expectedUrl, "Button navigation is correct");
    }
    @Test(priority = 13)
    public void SearchBarMyReports(){
        List<WebElement> SearchbarMy = driver.findElements(By.cssSelector(".filter-group__toggler"));
        Assert.assertEquals(SearchbarMy.size(), 11);
        List<WebElement> ResultsAll = driver.findElements(By.cssSelector(".table__header-cell"));
        Assert.assertEquals(ResultsAll.size(), 7);
}

    @Test(priority = 14)
    public void DuplicateReport() throws Throwable {
        BART.DashboardPage bartPageDev = new BART.DashboardPage(driver);
        bartPageDev.duplicateReport(driver);
    }
}