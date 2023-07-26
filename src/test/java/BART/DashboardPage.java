package BART;

import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.Assert;

import java.util.List;

public class DashboardPage {
    WebDriver driver;

    @FindBy(xpath = "//button[@class='navigation__action navigation__action--toggler']")
    WebElement CreateNewReport;

    @FindBy(xpath = "//a[normalize-space()='My Reports']")
    WebElement MyReports;

    @FindBy(xpath = "//a[normalize-space()='All Reports']")
    WebElement AllReports;

    @FindBy(css = ".application-feedback__toggler")
    WebElement Share;

    @FindBy(xpath = "//button[@class='header-settings__toggler']//*[name()='svg']")
    WebElement Settings;

    @FindBy(xpath = "//button[@class='user-bar__sign-out']")
    WebElement SignOut;

    @FindBy(xpath = "//a[normalize-space()='Analytics']")
    WebElement Analytics;

    @FindBy(xpath = "//a[normalize-space()='Find NAM Inspection Reports']")
    WebElement FindNAM;

    @FindBy(css = ".button-bar")
    WebElement IconToolbar;

    @FindBy(xpath = "//h3[@class='live-preview__header' and contains(., 'Bearing Damage Analysis Report')]")
    WebElement BearingDamageAnalysisReport;

    @FindBy(xpath = "//h3[@class='live-preview__header' and contains(., 'Bearing Inspection - NAM')]")
    WebElement BearingInspectionNAMReport;
    @FindBy(css = "#buttonClose")
    WebElement Close;

    @FindBy(css = "#create__bda__button")
    WebElement BDAReport;

    @FindBy(css = "#create__nam__button")
    WebElement NamReport;

    @FindBy(xpath = "//a[@class='welcome-information__help-link'][normalize-space()='Open user guide']")
    WebElement UserGuide;

    @FindBy(xpath = "//a[@class='welcome-information__help-link'][normalize-space()='Ask questions on Yammer']")
    WebElement Yammer;



    public void duplicateReport (WebDriver driver) throws Throwable {
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

    DashboardPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
}