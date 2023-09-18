package BART;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DashboardPage extends BasePage {
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

    @FindBy(xpath = "//h3[@class='live-preview__header' and contains(., 'Global Bearing Investigation')]")
    WebElement GBIReport;
    @FindBy(css = "#buttonClose")
    WebElement Close;

    @FindBy(css = "#create__bda__button")
    WebElement BDAReport;

    @FindBy(css = "#create__nam__button")
    WebElement NamReport;
    @FindBy(css = "#create__gbi__button")
    WebElement GlobalReport;

    @FindBy(xpath = "//a[@class='welcome-information__help-link'][normalize-space()='Open user guide']")
    WebElement UserGuide;

    @FindBy(xpath = "//a[@class='welcome-information__help-link'][normalize-space()='Ask questions on Yammer']")
    WebElement Yammer;

    @FindBy(xpath = "(//div[contains(text(),'Kristina')])[1]")
    WebElement TargetReportSmoke;

    @FindBy(xpath = "(//span[@class='button__text' and contains(., 'Open Report')])[1]")
    WebElement OpenReportSmoke;

    DashboardPage(WebDriver driver) {
        super(driver);
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
}