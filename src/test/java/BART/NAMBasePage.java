
package BART;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.FluentWait;
import org.testng.Assert;

import java.time.Duration;
import java.util.List;
import java.util.concurrent.TimeUnit;

public class NAMBasePage extends BasePage{

    @FindBy(id = "Tree/tree_root_branch")
    List<WebElement> AccordeonNam;
    @FindBy(css = ".settings-menu__action")
    List<WebElement> SettingsSizeNam;
    @FindBy(xpath = "//button[normalize-space()='Inspection Details']")
    WebElement InspectionDetails;

    @FindBy(xpath = "//div[contains(text(),'end users')]")
    WebElement EndUserField;
    @FindBy(xpath = "//button[normalize-space()='Report Details']")
    WebElement ReportDetailsSection;
    @FindBy(xpath = "//div[@data-id='reportDetails.inspectionTime']//input[@type='number']")
    WebElement InspectionTimeHoursNAM;
    @FindBy(xpath = "//div[@data-id='reportDetails.travelTime']//input[@type='number']")
    WebElement TravelTimeHoursNAM;
    @FindBy(xpath = "//div[@data-id='reportDetails.numberOfBearingsInvestigated']//input[@type='text']")
    WebElement NumberOfBearings;
    @FindBy(xpath = "//*[@id=\"app\"]/div[2]/div/div/div[1]/div[2]/div[1]/div[2]/div/div[5]/div/div[2]/div/div[1]/div/input")
    WebElement InspectionDate;

    @FindBy(css = "div[data-validation-id='reportDetails.reportApprovedBy.name'] div[class=' css-1hwfws3']")
    WebElement ApprovedByBtn;

    @FindBy(xpath = "//div[@data-validation-id='skfDetails.endUser.vendor']//div[contains(@class,'css-1hwfws3')]")
    WebElement Vendor;
    @FindBy(xpath = "//div[@data-id='skfDetails.vendorJobNumber']//input[@type='text']")
    WebElement VendorJobNumber;
    @FindBy(xpath = "//div[@class='live-preview-key-value__value' and contains(., '4252 53246')]")
    List<WebElement> SettingsSize;
    @FindBy(xpath = "//div[contains(@class, 'valueWithSpace')]/span")
    List<WebElement> LpInspectionDate;

    @FindBy(xpath = "(//div[@class=' css-1wa3eu0-placeholder' and contains(., 'Select or free text')])[1]")
    WebElement DistributionList;
    @FindBy(xpath = "//div[@class='live-preview-key-value__value' and text()='2']")
    WebElement LpInspectionTimeHoursNAM;
    @FindBy(xpath = "//div[@class='live-preview-key-value__value' and text()='4']")
    WebElement LpTravelTimeHoursNAM;

    @FindBy(xpath = "//div[@class='live-preview-key-value__value' and contains(., 'yavor.gledachev@skf.com')]")
    WebElement LpApprovedBy;
    @FindBy(xpath = "//div[@class='live-preview-key-value__value' and contains(., 'Keith.E.Meyers@skf.com')]")
    WebElement LpDistributionList;

    @FindBy(xpath = "//div[@class='live-preview-key-value__value' and contains(., 'Georgia Pacific, Big Island - Job 4252 53246')]")
    WebElement LpReportTitle;
    @FindBy(id = "buttonSettings")
    WebElement SettingsNAM;
    @FindBy(id = "buttonSettings")
    WebElement SettingsContainerNAM;

    public void setInspectionTimeHoursNAM(WebDriver driver){
        BasePage.clickElementWithJS(driver, ReportDetailsSection);
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("window.scrollTo(0, 600)");
        BasePage.clickElementWithJS(driver, InspectionTimeHoursNAM);
        BasePage.sendKeysLetterByLetter(InspectionTimeHoursNAM, "2");
        BasePage.blurElementWithJS(driver, InspectionTimeHoursNAM);
    }
    public void setTravelTimeHoursNAM(WebDriver driver){
        BasePage.clickElementWithJS(driver, TravelTimeHoursNAM);
        BasePage.sendKeysLetterByLetter(TravelTimeHoursNAM, "4");
        BasePage.blurElementWithJS(driver, TravelTimeHoursNAM);
    }
    public void setNumberOfBearingsNAM(WebDriver driver){
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("window.scrollTo(0, 300)");
        BasePage.clickElementWithJS(driver, NumberOfBearings);
        BasePage.sendKeysLetterByLetter(NumberOfBearings, "6");
        BasePage.blurElementWithJS(driver, NumberOfBearings);
    }
    public void setInspectionDate(WebDriver driver) throws InterruptedException {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("window.scrollTo(0, 500)");
        TimeUnit.SECONDS.sleep(3);
        Actions actions = new Actions(driver);
        actions.sendKeys(InspectionDate, Keys.DOWN);
        TimeUnit.SECONDS.sleep(3);
        actions.sendKeys(InspectionDate, Keys.ENTER).perform();
    }
    public void setApprovedBy(WebDriver driver) throws InterruptedException {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        Actions actions = new Actions(driver);
        actions.scrollToElement(ApprovedByBtn);
        Thread.sleep(3000);
        jsExecutor.executeScript("arguments[0].click();", ApprovedByBtn);
        actions.sendKeys(ApprovedByBtn, "Yavor Gledachev").perform();
        TimeUnit.SECONDS.sleep(3);
        actions.sendKeys(Keys.ENTER).build().perform();
    }
    public void setEndUser(WebDriver driver) throws InterruptedException {
        Actions actions = new Actions(driver);
        actions.sendKeys(EndUserField, "Georgia").perform();
        TimeUnit.SECONDS.sleep(4);
        actions.sendKeys(Keys.ENTER).build().perform();
    }
    public void assertEndUser(WebDriver driver){
        WebElement LpEndUser = driver.findElement(By.xpath("(//div[contains(@class, 'live-preview-key-value')]/div)[24]"));
        String expectedEndUser = "Contact\n" + "Paul Grey, Stacy Taylor, Tim Tolley, Matt Strand\n" + " 434-299-7337\n" + " matthew.strand@gapac.com\n" + " stacy.taylor@gapac.com\n" + " dmvanval@gapac.com\n" + " christopher.ey@gapac.com\n" + " trtolley@gapac.com\n" +
                " fgbranch@gapac.com";
        Assert.assertEquals(LpEndUser.getText(), expectedEndUser);
        WebElement LpDistributor = driver.findElement(By.xpath("(//div[contains(@class, 'live-preview-key-value')]/div)[28]"));
        String expectedDistributor = "Contact\n" + "Jim Rebok\n" + " 540-362-7695\n" +
                " jim.rebok@motion-ind.com";
        Assert.assertEquals(LpDistributor.getText(), expectedDistributor);
    }
    public void setVendor(WebDriver driver)throws InterruptedException {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("window.scrollTo(0, 600)");
        BasePage.clickElementWithJS(driver, Vendor);
        Actions actions = new Actions(driver);
        actions.sendKeys(Vendor, "Valmet");
        TimeUnit.SECONDS.sleep(3);
        actions.sendKeys(Keys.ENTER).build().perform();
        BasePage.blurElementWithJS(driver, Vendor);
    }
    public void setMachineAsset(WebDriver driver)throws InterruptedException{
        BasePage basePage = new BasePage(driver);
        BasePage.clickElementWithJS(driver, basePage.MachineAssetType);
        Actions actions = new Actions(driver);
        actions.sendKeys(basePage.MachineAssetType, "Automatic");
        TimeUnit.SECONDS.sleep(3);
        actions.sendKeys(Keys.ENTER).build().perform();
        BasePage.blurElementWithJS(driver, basePage.MachineAssetType);
    }
    public void assertVendor(WebDriver driver){
        WebElement LpVendor = driver.findElement(By.xpath("(//div[contains(@class, 'live-preview-key-value')]/div)[33]"));
        String expectedVendor = "Contact\n" + "Renee Martin\n" + " 803-293-2140\n" + " suanne.sheppard@valmet.com\n" + " barry.jackson@valmet.com\n" + " cheryll.delmundo@valmet.com\n" + " ricky.boyd@valmet.com\n" +
                " renee.martin@valmet.com";
        Assert.assertEquals(LpVendor.getText(), expectedVendor);
    }
    public void assertDistributionList(WebDriver driver)throws InterruptedException{
        BasePage.clickElementWithJS(driver, DistributionList);
        Actions actions = new Actions(driver);
        actions.sendKeys(DistributionList, "Keith.E.Meyers@skf.com");
        TimeUnit.SECONDS.sleep(3);
        actions.sendKeys(Keys.ENTER).build().perform();
        BasePage.blurElementWithJS(driver,DistributionList);
    }
    public void checkContainerNAM(WebDriver driver) {
        WebElement FigureNumbering = driver.findElement(By.xpath("//span[@class='checkbox__label-span' and contains(., 'Enable automatic figure numbering?')]"));
        WebElement PageBreak = driver.findElement(By.xpath("//span[@class='checkbox__label-span' and contains(., 'Insert page break for each bearing in the report?')]"));
        WebElement AutoScroll = driver.findElement(By.xpath("//span[@class='checkbox__label-span' and contains(., 'Disable autoscroll?')]"));
        WebElement Summary = driver.findElement(By.xpath("//span[@class='checkbox__label-span' and contains(., 'Show SUMMARY only at end of report?')]"));
        FigureNumbering.isEnabled();
        PageBreak.isEnabled();
        AutoScroll.isEnabled();
        Summary.isEnabled();
    }
    NAMBasePage(WebDriver driver) {
        super(driver);
        PageFactory.initElements(driver, this);
        FluentWait<WebDriver> wait = new FluentWait<>(driver)
                .withTimeout(Duration.ofSeconds(40))
                .pollingEvery(Duration.ofSeconds(5))
                .ignoring(NoSuchElementException.class);
    }
}
