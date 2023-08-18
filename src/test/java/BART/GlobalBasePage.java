package BART;


import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.concurrent.TimeUnit;

public class GlobalBasePage extends BasePage {

    @FindBy(xpath = "//div[@data-validation-id='TERorAMFRorOther.gbiTypeSelect']//div[contains(@class,'css-1hwfws3')]")
    WebElement TERNumber;
    @FindBy(xpath = "//input[@placeholder='TER number']")
    WebElement TerInput;
    @FindBy(xpath = "//button[contains(text(),'Search TER')]")
    WebElement SearchTER;
    @FindBy(xpath = "//div[@class='live-preview-key-value__header' and contains(., 'Purpose / Scope of investigation')]")
    WebElement TerResult;
    @FindBy(xpath = "//div[@class='live-preview-key-value__value' and contains(., 'SAIC')]")
    WebElement TerCustomer;
    @FindBy(xpath = "(//div[contains(text(),'Select')])[1]")
    WebElement GBICountryFields;
    @FindBy(xpath = "//div[@class='live-preview-key-value__value' and text()='Bulgaria']")
    WebElement LpCountry;
    @FindBy(xpath = "//div[@class='live-preview-key-value__value' and text()='SKF BULGARIA LTD']")
    WebElement LpCompany;
    @FindBy(xpath = "//div[@class='live-preview-key-value__value' and text()='Sofia - ti Septemvri str']")
    WebElement LpLocation;
    @FindBy(xpath = "//label[@for='skfDetails.reportNeedsApproval.needsApproval.yes']")
    WebElement Approver;
    @FindBy(xpath = "(//div[contains(text(),'Select')])[5]")
    WebElement ApproverName;
    public void setLocation(WebDriver driver) throws InterruptedException {
        SKFDetails.click();
        BasePage.clickElementWithJS(driver, GBICountryFields);
        Actions actions = new Actions(driver);
        actions.sendKeys(GBICountryFields, "Bulgaria");
        TimeUnit.SECONDS.sleep(2);
        actions.sendKeys(Keys.ENTER).build().perform();
        BasePage.blurElementWithJS(driver, GBICountryFields);
        BasePage.clickElementWithJS(driver, GBICountryFields);
        actions.sendKeys(GBICountryFields, "SKF BULGARIA LTD");
        TimeUnit.SECONDS.sleep(2);
        actions.sendKeys(Keys.ENTER).build().perform();
        BasePage.blurElementWithJS(driver, GBICountryFields);
        BasePage.clickElementWithJS(driver, GBICountryFields);
        actions.sendKeys(GBICountryFields, "Septemvri");
        TimeUnit.SECONDS.sleep(2);
        actions.sendKeys(Keys.ENTER).build().perform();
        BasePage.blurElementWithJS(driver, GBICountryFields);
    }

    GlobalBasePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }
}