package ru.yandex.market;

import org.junit.AfterClass;
import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.yandex.qatools.allure.annotations.Description;
import ru.yandex.qatools.allure.annotations.Step;
import ru.yandex.qatools.allure.annotations.Title;

import java.io.File;


@Title("Test Yandex.Market")
@Description("Search for the second item among the list of Acer products")
public class YandexTest {

    private static WebDriver driver;

    public String secondElement;
    public void setSecondElement(String secondElement)
    {
        this.secondElement = secondElement;
    }

    public String getSecondElement()
    {
        return this.secondElement;
    }

    public String name;


    @Title("The class containing the settings")
    @Description("Driver location, driver location, opening the browser to the whole window, start page")
    @BeforeClass
    public static void setup(){
        File file = new File("C:\\webdriver\\IEDriverServer.exe"); // IEDriver version 3.1.0.0
        System.setProperty("webdriver.ie.driver", file.getAbsolutePath());
        driver = new InternetExplorerDriver();
        driver.manage().window().maximize();
        driver.get("https://www.yandex.ru/");
    }

    @Test
    @Step
    @Title("Search for the required item")
    @Description("Element search")
    public void search() {
        WebDriverWait waitMarket = new WebDriverWait(driver, 10);
        WebElement market = waitMarket.until(ExpectedConditions.elementToBeClickable(By.cssSelector("a[data-id*='market']")));
        market.click();

        WebDriverWait waitPc = new WebDriverWait(driver, 10);
        WebElement pc = waitPc.until(ExpectedConditions.elementToBeClickable(By.cssSelector("a[href*='54425']")));
        pc.click();

        WebDriverWait waitTablet = new WebDriverWait(driver, 10);
        WebElement tablet = waitTablet.until(ExpectedConditions.elementToBeClickable(By.cssSelector("a.catalog-menu__list-item[href*='54545']")));
        tablet.click();

        WebElement writeOt = driver.findElement(By.cssSelector("#glf-pricefrom-var"));
        writeOt.clear();
        writeOt.sendKeys("20000");

        WebElement writeDo = driver.findElement(By.cssSelector("#glf-priceto-var"));
        writeDo.clear();
        writeDo.sendKeys("25000");

        WebDriverWait waiLabelAcer = new WebDriverWait(driver, 10);
        WebElement acer = waiLabelAcer.until(ExpectedConditions.elementToBeClickable(By.cssSelector("label[for*='glf-7893318-267101']")));
        acer.click();

        WebElement clickOnButton = driver.findElement(By.cssSelector("div.n-filter-panel-aside__controls button"));
        clickOnButton.click();
    }

    @Test
    @Step
    @Title("Search second element")
    @Description("Second Element")
    public void saveNameSecondElement(){
        WebDriverWait nameSecondOne = new WebDriverWait(driver, 10);
        WebElement searchNameElement = nameSecondOne.until(ExpectedConditions.elementToBeClickable(By.cssSelector("div.n-snippet-list.n-snippet-list_type_vertical.island.metrika.i-bem.snippet-list_js_inited.metrika_js_inited [data-id*='model']:nth-child(2) span.snippet-card__header-text")));
        setSecondElement(searchNameElement.getText());
    }

    @Test
    @Step
    @Title("Search second element")
    @Description("search for the second item in the search bar")
    public void SearchSecondElement(){
        WebElement marketsearch = driver.findElement(By.cssSelector("span#market-search input#header-search"));
        marketsearch.click();
        marketsearch.sendKeys(secondElement);

        WebElement searchButton = driver.findElement(By.cssSelector("span.search2__button button"));
        searchButton.click();
    }

    @Test
    @Step
    @Title("Search and save name element")
    @Description("Name found element")
    public void SearchSecondElementForSave(){
        WebDriverWait nameSecond = new WebDriverWait(driver, 10);
        WebElement searchNameElement = nameSecond.until(ExpectedConditions.elementToBeClickable(By.cssSelector("div.n-product-title h1")));
        getSecondElement();
    }

    @Test
    @Step
    @Title("Comparison")
    @Description("Comparison")
    public void ComparisonElement(){
        Assert.assertEquals(secondElement, name);
    }

    @AfterClass
    @Title("Close browser")
    public static void EndTest(){
        driver.quit();
    }
}
