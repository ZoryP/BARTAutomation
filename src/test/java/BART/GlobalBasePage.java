package BART;


import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;
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
    @FindBy(xpath = "(//div[contains(text(),'Select')])[4]")
    WebElement GBICountryFields;
    @FindBy(xpath = "//div[@class='live-preview-key-value__value' and text()='Bulgaria']")
    WebElement LpCountry;
    @FindBy(xpath = "//div[@class='live-preview-key-value__value' and text()='SKF BULGARIA LTD']")
    WebElement LpCompany;
    @FindBy(xpath = "//div[@class='live-preview-key-value__value' and text()='Sofia - ti Septemvri str']")
    WebElement LpLocation;
    @FindBy(xpath = "//label[@for='skfDetails.reportNeedsApproval.needsApproval.yes']")
    WebElement Approver;
    @FindBy(xpath = "(//div[contains(text(),'Select')])[11]")
    WebElement ApproverName;
    @FindBy(xpath = "//div[@class='live-preview-key-value__value' and contains(., 'Yavor Gledachev')]")
    WebElement LpApprover;
    @FindBy(xpath = "(//div[@class=' css-1wa3eu0-placeholder' and contains(., 'Select or free text')])[4]")
    WebElement DistributionDelegation;
    @FindBy(css = ".css-12jo7m5")
    List<WebElement> EditTreeDelegation;
    @FindBy(xpath = "(//div[contains(text(),'Select ...')])[8]")
    WebElement BearingTypeSectionFields;
    @FindBy(xpath = "//*[@id=\"app\"]/div[2]/div/div/div[1]/div[2]/div[5]/div/div[2]/div[2]/div/div/div[2]/div[1]/div[2]/div/div[3]/div/div[2]/div/div[1]/div/input")
    WebElement DateOfReciept;

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
    public void setApprover(WebDriver driver) throws InterruptedException {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        Actions actions = new Actions(driver);
        actions.scrollToElement(ApproverName);
        Thread.sleep(3000);
        jsExecutor.executeScript("arguments[0].click();", ApproverName);
        actions.sendKeys(ApproverName, "Yavor Gledachev").perform();
        TimeUnit.SECONDS.sleep(3);
        actions.sendKeys(Keys.ENTER).build().perform();
    }
    public void setDistributionDelegation(WebDriver driver){
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        Actions actions = new Actions(driver);
        jsExecutor.executeScript("arguments[0].click();", DistributionDelegation);
        actions.sendKeys(DistributionDelegation, "tobias").perform();
        actions.sendKeys(Keys.ENTER).build().perform();
        actions.sendKeys("Keith").perform();
        actions.sendKeys(Keys.ENTER).build().perform();
        jsExecutor.executeScript("arguments[0].click();", DistributionDelegation);
        actions.sendKeys(DistributionDelegation, "Andreas").perform();
        actions.sendKeys(Keys.ENTER).build().perform();
        actions.sendKeys("Marcus").perform();
        actions.sendKeys(Keys.ENTER).build().perform();
    }
    public void setGBICountryFields(WebDriver driver){
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        Actions actions = new Actions(driver);
        jsExecutor.executeScript("arguments[0].click();", BearingTypeSectionFields);
        actions.sendKeys(BearingTypeSectionFields, "Bulgaria").perform();
        actions.sendKeys(Keys.ENTER).build().perform();
        jsExecutor.executeScript("arguments[0].click();",BearingTypeSectionFields);
        actions.sendKeys(BearingTypeSectionFields, "SKF BULGARIA").perform();
        actions.sendKeys(Keys.ENTER).build().perform();
        jsExecutor.executeScript("arguments[0].click();",BearingTypeSectionFields);
        actions.sendKeys(BearingTypeSectionFields, "Sofia").perform();
        actions.sendKeys(Keys.ENTER).build().perform();
    }
    GlobalBasePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
    }
}