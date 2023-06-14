package BART;

import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;
import org.testng.Assert;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;
import java.awt.*;
import java.awt.datatransfer.StringSelection;
import java.awt.event.KeyEvent;
import java.util.List;

public class BearingDamageAnalysis {
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
    //@AfterClass
    // public void QuitBrowser() {

    // driver.quit();
    // }
    @Test(priority = 1)
    public void ValidLogin() throws Throwable {
        BART.LoginPage loginPage = new BART.LoginPage(driver);
        loginPage.clearFields();
        loginPage.login("testuser1.bart@gmail.com", "bartTest1");
        loginPage.enterBtn.click();
        Thread.sleep(4000);
        loginPage.WelcomeMassage.isDisplayed();
    }

    @Test(priority = 2)
    public void CreateBda() throws Throwable {
        BART.DashboardPage dashboard = new BART.DashboardPage(driver);
        dashboard.CreateNewReport.click();
        dashboard.BDAReport.click();
        Thread.sleep(3000);
        dashboard.BearingDamageAnalysisReport.isDisplayed();
        dashboard.IconToolbar.isDisplayed();
    }

    @Test(priority = 3)
    public void CheckAccodeon() {
        List<WebElement> Accordeon = driver.findElements(By.id("Tree/tree_root_branch"));
        Assert.assertEquals(Accordeon.size(), 11);
    }

    WebElement ReportTitle;

    @Test(priority = 4)
    public void ReportDetails() {
        WebElement ReportDetails = driver.findElement(By.xpath("//button[normalize-space()='Report Details']"));
        BART.BDAPage.clickElementWithJS(driver, ReportDetails);
        ReportTitle = driver.findElement(By.xpath("//div[@data-id='reportDetails.reportTitle']//input[@type='text']"));
        BART.BDAPage.clickElementWithJS(driver, ReportTitle);
        BART.BDAPage.sendKeysLetterByLetter(ReportTitle, "test");
        BART.BDAPage.blurElementWithJS(driver, ReportTitle);
        WebElement InvestigationDetails = driver.findElement(By.xpath("//button[normalize-space()='Investigation Details']"));
        BART.BDAPage.clickElementWithJS(driver, InvestigationDetails);
        WebElement LivePreviewEl = driver.findElement(By.xpath("//div[@class='live-preview-key-value__value' and text()='test']"));
        LivePreviewEl.isDisplayed();
    }

    @Test(priority = 5)
    public void InvestigationDetails() {
        BDAPage bdaPage = new BDAPage(driver);
        String terNumber = "1111";
        bdaPage.enterSkfTerNumber(driver, terNumber);
        String scope = "Bearing";
        bdaPage.enterScopeOfInvestigation(driver, scope);
        bdaPage.clickSKFDetails(driver);
        WebElement lpTER = driver.findElement(By.xpath("//div[@class='live-preview-key-value__value' and text()='1111']"));
        WebElement lpScope = driver.findElement(By.xpath("//div[@class='live-preview-key-value__value' and contains(., 'Bearing')]"));
        lpTER.isDisplayed();
        lpScope.isDisplayed();
    }

    @Test(priority = 6)
    public void SKFDetailsCountry() throws Throwable {
        BDAPage bdaPage = new BDAPage(driver);
        String country = "Argentina";
        bdaPage.selectCountry(driver, country);
    }

    @Test(priority = 7)
    public void SKFDetailsCompany() throws Throwable {
        BDAPage bdaPage = new BDAPage(driver);
        bdaPage.selectSKFCountry(driver);
        boolean isElementVisible = bdaPage.isCountryVisible(driver);
        Assert.assertTrue(isElementVisible);
    }

    @Test(priority = 8)
    public void SKFCompanyLocation() throws Throwable {
        BDAPage bdaPage = new BDAPage(driver);
        bdaPage.selectSKFCompany(driver);
        boolean isCompanyVisible = bdaPage.isCompanyVisible(driver);
        Assert.assertTrue(isCompanyVisible);
        boolean isLocationVisible = bdaPage.isLocationVisible(driver);
        Assert.assertTrue(isLocationVisible);
    }

    @Test(priority = 9)
    public void Approval() throws Throwable {
        BDAPage bdaPage = new BDAPage(driver);
        boolean isElementVisible = bdaPage.approvalButton(driver);
        Assert.assertTrue(isElementVisible);
    }

    @Test(priority = 10)
    public void CustomerDetails1() throws Throwable {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        WebElement CustomerDetails = driver.findElement(By.xpath("//button[normalize-space()='Customer Details']"));
        BART.BDAPage.clickElementWithJS(driver, CustomerDetails);
        WebElement Customer = driver.findElement(By.xpath("(//div[contains(text(),'Type 3 characters')])"));
        jsExecutor.executeScript("arguments[0].click();", Customer);
        Actions actions = new Actions(driver);
        actions.sendKeys(Customer, "LODI SPA").perform();
        Thread.sleep(3000);
        actions.sendKeys(Keys.ENTER).build().perform();
        Thread.sleep(3000);
        WebElement LpCompany = driver.findElement(By.xpath("//div[@class='live-preview-key-value__value' and contains(., 'LODI SPA')]"));
        LpCompany.isDisplayed();
    }

    @Test(priority = 11)
    public void CustomerDetails2() {
        WebElement LpCompanyAdr = driver.findElement(By.xpath("//div[@class='live-preview-key-value__value' and contains(., 'VIA DELLA')]"));
        WebElement LpCompanyCntr = driver.findElement(By.xpath("//div[@class='live-preview-key-value__value' and contains(., 'Italy')]"));
        LpCompanyAdr.isDisplayed();
        LpCompanyCntr.isDisplayed();
    }

    @Test(priority = 12)
    public void CustomerDetails3() {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("window.scrollBy(0, 800)");
        WebElement CustomerSiteName = driver.findElement(By.xpath("//div[@data-id='customerDetails.site']//input[@type='text']"));
        jsExecutor.executeScript("arguments[0].click();", CustomerSiteName);
        Actions actions = new Actions(driver);
        actions.sendKeys(CustomerSiteName, "www.lodispa.com").perform();
        actions.sendKeys(Keys.ENTER).build().perform();
        BART.BDAPage.blurElementWithJS(driver, CustomerSiteName);
        WebElement LpCustomerSiteName = driver.findElement(By.xpath("//div[@class='live-preview-key-value__value' and contains(., 'www.lodispa.com')]"));
        LpCustomerSiteName.isDisplayed();
    }

    @Test(priority = 13)
    public void AssetDetails() {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        WebElement AssetDetails = driver.findElement(By.xpath("//button[normalize-space()='Asset Details']"));
        jsExecutor.executeScript("arguments[0].click();", AssetDetails);
        WebElement AssetType = driver.findElement(By.xpath("//button[normalize-space()='Asset type / Functional Area / System'] "));
        WebElement Machine = driver.findElement(By.xpath(" //button[normalize-space()='Machine / Asset Details']"));
        AssetType.isDisplayed();
        Machine.isDisplayed();
    }

    @Test(priority = 14)
    public void AssetType() throws Throwable {
        BDAPage bdaPage = new BDAPage(driver);
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        bdaPage.AssetType.click();
        WebElement FunctionalArea = driver.findElement(By.xpath("//div[@data-id='assetDetails.assetTypeOrFunctionalAreaOrSystem.machineOrAssetCode']//div[contains(@class,'css-1hwfws3')]"));
        jsExecutor.executeScript("arguments[0].click();", FunctionalArea);
        Actions actions = new Actions(driver);
        Thread.sleep(2000);
        actions.sendKeys(FunctionalArea, "Air handling unit").perform();
        Thread.sleep(3000);
        actions.sendKeys(Keys.ENTER).build().perform();
        BART.BDAPage.blurElementWithJS(driver, FunctionalArea);
        WebElement LpFunctionalArea = driver.findElement(By.xpath("//div[@class='live-preview-key-value__value' and contains(., 'Air handling unit, Air conditioner (AC)')]"));
        LpFunctionalArea.isDisplayed();
    }

    @Test(priority = 15)
    public void WhereAsset() {
        BDAPage bdaPage = new BDAPage(driver);
        bdaPage.FunctionalAreaNameWhereAssetIsUsed.click();
        bdaPage.FunctionalAreaNameWhereAssetIsUsed.sendKeys("Operations");
        BART.BDAPage.blurElementWithJS(driver, bdaPage.FunctionalAreaNameWhereAssetIsUsed);
        bdaPage.SystemName.click();
        bdaPage.SystemName.sendKeys("Operation 1");
        BART.BDAPage.blurElementWithJS(driver, bdaPage.SystemName);
        WebElement LpFunctionalAreaNameWhereAssetIsUsed = driver.findElement(By.xpath("//div[@class='live-preview-key-value__value' and contains(., 'Operations')]"));
        WebElement LpSystemName = driver.findElement(By.xpath("//div[@class='live-preview-key-value__value' and contains(., 'Operation 1')]"));
        LpFunctionalAreaNameWhereAssetIsUsed.isDisplayed();
        LpSystemName.isDisplayed();
    }

    @Test(priority = 16)
    public void Bearing1() {
        BDAPage bdaPage = new BDAPage(driver);
        bdaPage.BearingInvestigations.click();
        bdaPage.Bearing1.click();
        bdaPage.BearingTypeSection.click();
        BDAPage.clickSKFOther(driver);
        BDAPage.enterSKFOtherValue(driver, "SKF");
        BDAPage.clickSKFSerialNumber(driver);
        BDAPage.enterSKFSerialNumberValue(driver, "PEER");
        WebElement LpSerialNumber = driver.findElement(By.xpath("//div[@class='live-preview-key-value__header live-preview-key-value__bearingheader']//span[contains(text(),'PEER / SKF')]"));
        LpSerialNumber.isDisplayed();
    }
    @Test(priority = 17)
    public void BearingType() throws Throwable {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("window.scrollTo(0, 0)");
        WebElement SKFCustomerDescription = driver.findElement(By.xpath("//div[@class='accordion']//div[@class='accordion__content accordion__content--is-open']//div[@class='accordion__content-form']//div[@class='componentContainer']//div//div[@class='se-wrapper-inner se-wrapper-wysiwyg sun-editor-editable']"));
        jsExecutor.executeScript("arguments[0].click();", SKFCustomerDescription);
        Actions actions = new Actions(driver);
        Thread.sleep(2000);
        actions.sendKeys(SKFCustomerDescription, "TEST").perform();
        Thread.sleep(2000);
        BDAPage.blurElementWithJS(driver, SKFCustomerDescription);
        //assert

    }
    @Test(priority = 18)
    public void ComponentParts() {
        WebElement ComponentParts = driver.findElement(By.xpath("//button[normalize-space()='Component Parts Investigation']"));
        ComponentParts.click();
        WebElement RollingElements = driver.findElement(By.xpath("//button[normalize-space()='Rolling Elements']"));
        RollingElements.click();
        List<WebElement> ComponentSize = driver.findElements(By.cssSelector(".accordion-menu-container"));
        Assert.assertEquals(ComponentSize.size(), 9);
    }
    @Test(priority = 19)
    public void UploadImage() throws Throwable {
        BDAPage bdaPage = new BDAPage(driver);
        bdaPage.UploadImg.click();
        WebElement ModalElement = driver.findElement(By.xpath("//div[@class='dropzone-inputLabel']"));
        ModalElement.isDisplayed();
        WebElement fileInput = driver.findElement(By.xpath("//div[@role='presentation']"));
        fileInput.click();
        String[] filePaths = {
                "\"C:\\Users\\ZornicaPetkova\\Desktop\\New folder\\2 - Copy.jpg\"",
                "\"C:\\Users\\ZornicaPetkova\\Desktop\\New folder\\4.jpg\"",
                "\"C:\\Users\\ZornicaPetkova\\Desktop\\New folder\\R - Copy - Copy.gif\""
        };
            Thread.sleep(2000);
        Robot robot = new Robot();
        for (String filePath : filePaths) {
            StringSelection stringSelection = new StringSelection(filePath);
            Toolkit.getDefaultToolkit().getSystemClipboard().setContents(stringSelection, null);
            robot.keyPress(KeyEvent.VK_CONTROL);
            robot.keyPress(KeyEvent.VK_V);
            robot.keyRelease(KeyEvent.VK_V);
            robot.keyRelease(KeyEvent.VK_CONTROL);
            robot.keyPress(KeyEvent.VK_ENTER);
            robot.keyRelease(KeyEvent.VK_ENTER);
            Thread.sleep(2000);
        }
    }
    @Test(priority = 20)
    public void Upload() throws Throwable {
        BDAPage bdaPage = new BDAPage(driver);
        bdaPage.Upload.click();
        Thread.sleep(3000);
        bdaPage.Close.click();
        Thread.sleep(3000);
        List<WebElement> LpImageContainer = driver.findElements(By.className("live-preview-images-item__media-container"));
        Assert.assertEquals(LpImageContainer.size(), 2);
    }
    @Test(priority = 21)
    public void Analysis() throws Throwable {
        BDAPage.clickAnalysisButton(driver);
        Thread.sleep(2000);
        BDAPage.selectForAFA(driver);
        Assert.assertTrue(BDAPage.isAFABtnEnabled(driver));
        Assert.assertTrue(BDAPage.isAFAModalDisplayed(driver));
    }
    @Test(priority = 22)
    public void DoneAFA() throws Throwable {
        BDAPage.clickFailureMode(driver);
        BDAPage.clickDoneButton(driver);
        Thread.sleep(4000);
        Assert.assertTrue(BDAPage.isThankYouMessageDisplayed(driver));
        BDAPage.clickCloseAFAButton(driver);
        Thread.sleep(3000);
        Assert.assertTrue(BDAPage.isLPAFADisplayed(driver));
    }
    @Test(priority = 23)
    public void Cause() throws Throwable {
        BDAPage bdaPage = new BDAPage(driver);
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("window.scrollBy(0, 300)");
        BDAPage.clickCauseElement(driver);
        Thread.sleep(2000);
        bdaPage.cause(driver);
        Assert.assertTrue(BDAPage.isLpCauseDisplayed(driver));
    }
    @Test(priority = 24)
    public void CreateNewBrng() {
        BDAPage bdaPage = new BDAPage(driver);
        bdaPage.clickAddNewButton(driver);
        bdaPage.clickAddNewEditButton(driver);
        bdaPage.clickLpAddNew(driver);
    }
    @Test(priority = 25)
    public void ThreeDots() throws Throwable {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        BDAPage bdaPage = new BDAPage(driver);
        jsExecutor.executeScript("window.scrollTo(0, 0)");
        bdaPage.clickThreeDots(driver);
        Thread.sleep(3000);
        List<WebElement> AllActions = driver.findElements(By.className("navigation__action"));
        Assert.assertEquals(AllActions.size(), 4);
    }
    @Test(priority = 26)
    public void Duplicate() throws Throwable {
        BDAPage bdaPage = new BDAPage(driver);
        bdaPage.duplicate(driver);
        Assert.assertEquals(bdaPage.lpBearings.size(), 2);
    }
    @Test(priority = 27)
    public void DeleteBearings() throws Throwable {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        Thread.sleep(2000);
        WebElement ThreeDotsThird = driver.findElement(By.xpath("(//*[@id='icon_ellipsis'])[2]"));
        ThreeDotsThird.click();
        Thread.sleep(2000);
        WebElement Delete = driver.findElement(By.xpath("//a[normalize-space()='Delete']"));
        jsExecutor.executeScript("arguments[0].click();", Delete);
        Thread.sleep(3000);
        WebElement YesDelete = driver.findElement(By.xpath("//button[@class='button react-modal__action-button button--background-gray']"));
        YesDelete.click();
        WebElement ThreeDotsSecond = driver.findElement(By.xpath("(//*[@id='icon_ellipsis'])[2]"));
        ThreeDotsSecond.click();
        Thread.sleep(2000);
        WebElement Delete2 = driver.findElement(By.xpath("//a[normalize-space()='Delete']"));
        jsExecutor.executeScript("arguments[0].click();", Delete2);
        Thread.sleep(3000);
        WebElement YesDelete2 = driver.findElement(By.xpath("//button[@class='button react-modal__action-button button--background-gray']"));
        YesDelete2.click();
        List<WebElement> lpBearings = driver.findElements(By.xpath("//div[@class='live-preview-key-value__value' and contains(., 'Moisture corrosion (5.3.2) based on Augmented Failure Analysis')]"));
        Assert.assertEquals(lpBearings.size(), 1);
    }

    @Test(priority = 28)
    public void ConclusionAndRecommendations() throws Throwable {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        Thread.sleep(2000);
        WebElement Conclusions = driver.findElement(By.xpath("//button[normalize-space()='Conclusions and recommendations']"));
        Conclusions.click();
        WebElement Recommendations = driver.findElement(By.xpath("//div[@data-id='summary.recommendations']//div[@class='se-wrapper-inner se-wrapper-wysiwyg sun-editor-editable']"));
        jsExecutor.executeScript("arguments[0].click();", Recommendations);
        Actions actions = new Actions(driver);
        Thread.sleep(2000);
        actions.sendKeys(Recommendations, "TEST").perform();
        BDAPage.blurElementWithJS(driver, Recommendations);
        WebElement Save = driver.findElement(By.xpath("//*[name()='polygon' and @id='Fill-6']"));
        Save.click();
        Thread.sleep(3000);

    }@Test(priority = 29)
    public void Submit() throws Throwable {
        JavascriptExecutor jsExecutor = (JavascriptExecutor) driver;
        jsExecutor.executeScript("window.scrollTo(0, 0)");
        driver.navigate().refresh();
        Thread.sleep(3000);
        WebElement Submit = driver.findElement(By.id("buttonSubmit"));
        String script = "var event = document.createEvent('MouseEvent');"
                + "event.initEvent('mouseover', true, true);"
                + "arguments[0].dispatchEvent(event);";
        jsExecutor.executeScript(script, Submit);
        Thread.sleep(3000);
        Submit.click();
        Thread.sleep(4000);
        WebElement ConfirmMassage = driver.findElement(By.xpath("//div[@class='react-modal__text' and contains(., 'Are you sure you want to submit the report?')]"));
        ConfirmMassage.isDisplayed();
        WebElement ConfirmBtn = driver.findElement(By.xpath("//span[@class='button__text' and contains(., 'Submit and open Email')]"));
        ConfirmBtn.click();
        Thread.sleep(4000);
        WebElement StatusSubmitted = driver.findElement(By.xpath("//div[@class='live-preview-status-confidential__status' and contains(., 'Submitted')]"));
        StatusSubmitted.isDisplayed();
    }
}




