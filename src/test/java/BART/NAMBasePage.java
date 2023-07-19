
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
    @FindBy(xpath = "//button [text ()='Manufacturing']")
    WebElement Manufacturing;

    @FindBy(xpath = "//button [text ()='Operating Data']")
    WebElement OperatingData;
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
    @FindBy(xpath = "(//div[contains(text(),'Select')])[3]")
    WebElement Country;
    @FindBy(xpath = "(//div[contains(text(),'Select')])[3]")
    WebElement Location;
    @FindBy(xpath = "(//div[contains(text(),'Select or free text')])[1]")
    WebElement SegmentFunctionalAreaSystemName;
    @FindBy(xpath = "//*[@id=\"app\"]/div[2]/div/div/div[1]/div[2]/div[4]/div/div[2]/div[2]/div/div/div[2]/div[2]/div[2]/div/div[2]/div/div[2]/div/div/div/div[1]/div[1]")
    WebElement ManufacturingDateCode;
    @FindBy(xpath = "//*[@data-id='bearingInvestigations.bearingInvestigation[0].manufacturing.remanId1']")
    WebElement RemanId;
    @FindBy(xpath = "//*[@data-id='bearingInvestigations.bearingInvestigation[0].manufacturing.dateCode1']")
    WebElement DateCode;
    @FindBy(xpath = "(//div[contains(text(),'Select or free text')])[2]")
    WebElement PositionOfBearing;
    @FindBy(xpath = "(//div[contains(text(),'Select')])[3]")
    WebElement LubricationType;

    @FindBy(xpath = "(//div[contains(text(),'Select')])[3]")
    WebElement RingRotation;
    @FindBy(xpath = "(//*[@id='icon_ellipsis'])[2]")
    WebElement ThreeDots;
    @FindBy(xpath = "//a[@class='navigation__action' and contains(., 'Outer Diameter')]")
    WebElement AddOuterDiameter;
    @FindBy(xpath = "//a[@class='navigation__action' and contains(., 'Side face - marked side')]")
    WebElement AddMarkedSide;
    @FindBy(xpath = "//a[@class='navigation__action' and contains(., 'Side face - opposite side')]")
    WebElement AddOppositeSide;
    @FindBy(xpath = "(//div[contains(text(),'Select or free text')])[2]")
    WebElement AddObservationsOuterRing;
    @FindBy(xpath = "(//div[contains(text(),'Select or free text')])[3]")
    WebElement AddObservationsInnerRing;
    @FindBy(xpath = "(//*[@id=\"Vector\"])[4]")
    WebElement SeverityRacewayOuterRing;

    @FindBy(xpath = "(//*[@id=\"Vector\"])[23]")
    WebElement SeverityRacewayInnerRing;
    @FindBy(xpath = "(//*[@id=\"Vector\"])[6]")
    WebElement SeverityOuterDiameter;
    @FindBy(xpath = "(//*[@id=\"Vector\"])[10]")
    WebElement SeverityMarked;
    @FindBy(xpath = "(//*[@id=\"Vector\"])[15]")
    WebElement SeverityOpposite;

    @FindBy(xpath = "(//div[contains(@class, 'image-selector__overlay')])[7]")
    WebElement InsertFigure;
    @FindBy(className = "severity__header")
    List<WebElement> LpSeverity;
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

    @FindBy(xpath = "//div[@class='live-preview-key-value__header live-preview-key-value__bearingheader' and contains(., 'Wrapping machine, Automatic')]")
    WebElement LpMachineAsset;
    @FindBy(xpath = "//div[@class='live-preview-key-value__value' and contains(., 'Driveline')]")
    WebElement LpFunctionalArea;
    @FindBy(xpath = "//div[@class='live-preview-key-value__value' and contains(., 'Continuous caster')]")
    List<WebElement> LpSystemName;
    @FindBy(xpath = "//div[@class='live-preview-key-value__value' and contains(., 'Bulgaria')]")
    WebElement LpCountry;
    @FindBy(xpath = "//div[@class='live-preview-key-value__value' and contains(., 'Sofia - Tzar Osvoboditel Blvd')]")
    WebElement LpLocation;
    @FindBy(xpath = "//div[@class='live-preview-key-value__value' and contains(., 'W (2000)')]")
    WebElement LpManufacturing;
    @FindBy(xpath = "//div[@class='live-preview-key-value__value' and contains(., '433 33')]")
    WebElement LpRemanId;
    @FindBy(xpath = "//div[@class='live-preview-key-value__value' and contains(., 'S-32')]")
    WebElement LpDateCode;
    @FindBy(xpath = "//div[@class='live-preview-key-value__value' and text()='Tending']")
    WebElement LpPositionOfBearing;
    @FindBy(xpath = "//div[@class='live-preview-key-value__value' and text()='Not known']")
    WebElement LpLubricationType;
    @FindBy(xpath = "//div[@class='live-preview-key-value__value' and text()='Synchronous inner/outer ring rotation']")
    WebElement LpRingRotation;
    @FindBy(xpath = "//div[@class='live-preview-key-value__value' and text()='Surface in good condition, Abrasive wear (frosting, smoothing, glazing), Discoloration']")
    WebElement LpRaceway;
    @FindBy(xpath = "//div[@class='live-preview-key-value__value' and text()='Brinelling']")
    WebElement LpOuterDiameter;
    @FindBy(xpath = "//div[@class='live-preview-key-value__value' and text()='Flutting (washboarding)']")
    WebElement LpMarkedSide;
    @FindBy(xpath = "//div[@class='live-preview-key-value__value' and text()='Moisture corrosion, Forced fracture']")
    WebElement LpOppositeSide;
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
    public void setRandomValue(WebDriver driver) throws InterruptedException{
        Actions actions = new Actions(driver);
        TimeUnit.SECONDS.sleep(2);
        actions.sendKeys(Keys.DOWN).sendKeys(Keys.DOWN).build().perform();
        TimeUnit.SECONDS.sleep(2);
        actions.sendKeys(Keys.ENTER).build().perform();
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("window.scrollTo(0, 600)");
    }
    public void setCountry(WebDriver driver) throws InterruptedException{
        BasePage.clickElementWithJS(driver, Country);
        Actions actions = new Actions(driver);
        actions.sendKeys(Country, "Bulgaria");
        TimeUnit.SECONDS.sleep(2);
        actions.sendKeys(Keys.ENTER).build().perform();
        BasePage.blurElementWithJS(driver, Country);
    }
    public void setLocation(WebDriver driver) throws InterruptedException {
        BasePage.clickElementWithJS(driver, Location);
        Actions actions = new Actions(driver);
        actions.sendKeys(Location, "Sofia");
        TimeUnit.SECONDS.sleep(2);
        actions.sendKeys(Keys.ENTER).build().perform();
        BasePage.blurElementWithJS(driver, Location);
    }
    public void setManufacturingDateCode(WebDriver driver) throws InterruptedException{
        Actions actions = new Actions(driver);
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("arguments[0].click();", ManufacturingDateCode);
        actions.sendKeys(ManufacturingDateCode, "2000").perform();
        TimeUnit.SECONDS.sleep(2);
        actions.sendKeys(Keys.ENTER).build().perform();
    }
    public void setRemanIdAndDateCode(WebDriver driver) throws InterruptedException{
        BasePage.clickElementWithJS(driver, RemanId);
        Actions actions = new Actions(driver);
        actions.sendKeys(RemanId, "433 33");
        TimeUnit.SECONDS.sleep(2);
        actions.sendKeys(Keys.ENTER).build().perform();
        BasePage.blurElementWithJS(driver, RemanId);
        BasePage.clickElementWithJS(driver, DateCode);
        actions.sendKeys(DateCode, "S-32");
        TimeUnit.SECONDS.sleep(2);
        actions.sendKeys(Keys.ENTER).build().perform();
        BasePage.blurElementWithJS(driver, DateCode);
        SaveButton.click();
    }
    public void addMoreSides (WebDriver driver) throws InterruptedException {
        NAMBasePage namBasePage = new NAMBasePage(driver);
        namBasePage.Components.click();
        namBasePage.OuterRing.click();
        TimeUnit.SECONDS.sleep(1);
        namBasePage.ThreeDots.click();
        namBasePage.AddOuterDiameter.click();
        TimeUnit.SECONDS.sleep(1);
        namBasePage.ThreeDots.click();
        namBasePage.AddMarkedSide.click();
        TimeUnit.SECONDS.sleep(1);
        namBasePage.ThreeDots.click();
        namBasePage.AddOppositeSide.click();
    }
    public void setObservationsOuterRing(WebDriver driver) throws InterruptedException{
        BasePage.clickElementWithJS(driver, AddObservationsOuterRing);
        Actions actions = new Actions(driver);
        actions.sendKeys(AddObservationsOuterRing, "Surface");
        TimeUnit.SECONDS.sleep(2);
        actions.sendKeys(Keys.ENTER).build().perform();
        actions.sendKeys("Abrasive");
        TimeUnit.SECONDS.sleep(2);
        actions.sendKeys(Keys.ENTER).build().perform();
        actions.sendKeys("Discoloration");
        TimeUnit.SECONDS.sleep(2);
        actions.sendKeys(Keys.ENTER).build().perform();
        BasePage.blurElementWithJS(driver, AddObservationsOuterRing);

        actions.sendKeys(AddObservationsOuterRing, "Brinelling");
        TimeUnit.SECONDS.sleep(2);
        actions.sendKeys(Keys.ENTER).build().perform();
        BasePage.blurElementWithJS(driver, AddObservationsOuterRing);

        actions.sendKeys(AddObservationsOuterRing, "Flutting");
        TimeUnit.SECONDS.sleep(2);
        actions.sendKeys(Keys.ENTER).build().perform();
        BasePage.blurElementWithJS(driver, AddObservationsOuterRing);

        actions.sendKeys(AddObservationsOuterRing, "Moisture");
        TimeUnit.SECONDS.sleep(2);
        actions.sendKeys(Keys.ENTER).build().perform();
        actions.sendKeys("Forced");
        TimeUnit.SECONDS.sleep(2);
        actions.sendKeys(Keys.ENTER).build().perform();
        BasePage.blurElementWithJS(driver, AddObservationsOuterRing);
    }
    public void setInnerRing(WebDriver driver) throws InterruptedException {
        BasePage.clickElementWithJS(driver, AddObservationsInnerRing);
        Actions actions = new Actions(driver);
        actions.sendKeys(AddObservationsInnerRing, "Chatter");
        TimeUnit.SECONDS.sleep(2);
        actions.sendKeys(Keys.ENTER).build().perform();
        actions.sendKeys("Washboarding");
        TimeUnit.SECONDS.sleep(2);
        actions.sendKeys(Keys.ENTER).build().perform();
        BasePage.blurElementWithJS(driver, AddObservationsInnerRing);
        SeverityRacewayInnerRing.click();
    }
    public void setSeverityOuterRing(WebDriver driver) {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("window.scrollTo(0, 200)");
        SeverityRacewayOuterRing.click();
        jsExecutor.executeScript("window.scrollTo(0, 400)");
        SeverityOuterDiameter.click();
        jsExecutor.executeScript("window.scrollTo(0, 600)");
        SeverityMarked.click();
        jsExecutor.executeScript("window.scrollTo(0, 800)");
        SeverityOpposite.click();
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
    public boolean areAllElementsDisplayed() {
        return LpOppositeSide.isDisplayed()
                && LpRaceway.isDisplayed()
                && LpOuterDiameter.isDisplayed()
                && LpMarkedSide.isDisplayed();

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
