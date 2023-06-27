package BART;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

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

    @FindBy(css = ".live-preview__header")
    WebElement BearingDamageAnalysisReport;

    @FindBy(id = "buttonClose")
    WebElement Close;

    @FindBy(id = "create__bda__button")
    WebElement BDAReport;

    @FindBy(xpath = "//a[@class='welcome-information__help-link'][normalize-space()='Open user guide']")
    WebElement UserGuide;

    @FindBy(xpath = "//a[@class='welcome-information__help-link'][normalize-space()='Ask questions on Yammer']")
    WebElement Yammer;


    DashboardPage(WebDriver driver) {
        this.driver = driver;
        PageFactory.initElements(driver, this);
    }
}