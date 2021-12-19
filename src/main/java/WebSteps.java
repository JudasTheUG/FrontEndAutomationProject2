import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import pages.*;

import java.nio.file.FileSystems;
import java.util.List;

public class WebSteps implements MainSteps {
    public WebDriver driver;
    public BasePage page;

    public void initializeDriver(String browser) {
        if (browser.equals("chrome")) {
            ChromeOptions options = new ChromeOptions();
            options.addArguments("--start-maximized");
            System.setProperty("webdriver.chrome.driver", FileSystems.getDefault().getPath("").toAbsolutePath().toString() + "\\chromedriver_win32\\chromedriver.exe");
            driver = new ChromeDriver(options);
        } else if (browser.equals("firefox")) {
            System.setProperty("webdriver.gecko.driver", FileSystems.getDefault().getPath("").toAbsolutePath().toString() + "\\geckodriver\\geckodriver.exe");
            driver = new FirefoxDriver();
        }
    }

    public void seePage(String pageName) {
        switch (pageName) {
            case "home":
                page = new HomePage();
                break;
            case "login":
                page = new LoginPage();
                break;
            case "search results":
                page = new SearchResultsPage();
                break;
            case "basket":
                page = new Basket();
                break;
            default:
                break;
        }
    }

    public void openUrl(String url) {
        this.driver.navigate().to(url);
        System.out.println(url + " address was reached.");
    }



    public WebElement findElement(String element, int index) {
        try {
            WebElement object;

            By elem = page.pageElements.get(element);

            if (elem == null) {
                elem = page.commonElements.get(element);
            }
            object = driver.findElements(elem).get(index);
            System.out.println("Object found : " + element);
            return object;
        } catch (Exception e) {
            //Assert.fail("Element is not found!");
            return null;
        }
    }

    public List<WebElement> findElements(String element) {
        try {
            By elem = page.pageElements.get(element);

            if (elem == null) {
                elem = page.commonElements.get(element);
            }
            List<WebElement> allElements = driver.findElements(elem);
            System.out.println("Object found : " + element);
            return allElements;
        } catch (Exception e) {
            //Assert.fail("Element is not found!");
            return null;
        }
    }

    public WebElement waitElement(String element, int timeout) throws InterruptedException {
        WebElement object;
        for (int i = 0; i < timeout; i++) {

            object = findElement(element, 0);
            if (object != null) {
                Thread.sleep(2000);
                return object;
            } else {
                Thread.sleep(2000);
            }
        }
        Assert.fail("Waiting element is not found!");
        return null;
    }

    public void clickElement(String element, int index) throws InterruptedException {
        WebElement object = waitElement(element, 30);

        if (object != null) {

            findElement(element, index).click();

            System.out.println("Clicked on object-->" + element);
        } else {
            System.out.println("Could not click on object-->" + element);
        }
    }

    public void enterText(String text, String element) {
        WebElement object;
        object = findElement(element, 0);

        if (object != null) {
            object.sendKeys(text);
            System.out.println("The text has been entered.");
        }
    }

    public void clickKeyboard(String key) {
        Actions action = new Actions(driver);
        switch (key) {
            case "ENTER":
                action.sendKeys(Keys.ENTER).build().perform();
                System.out.println(key + " clicked.");
                break;
            case "ESC":
                action.sendKeys(Keys.ESCAPE).build().perform();
                System.out.println(key + " clicked.");
                break;
            case "TAB":
                action.sendKeys(Keys.TAB).build().perform();
                System.out.println(key + " clicked.");
                break;
            case "PAGE_DOWN":
                action.sendKeys(Keys.PAGE_DOWN).build().perform();
                System.out.println(key + " clicked.");
                break;
            case "PAGE_UP":
                action.sendKeys(Keys.PAGE_UP).build().perform();
                System.out.println(key + " clicked.");
                break;
            default:
                break;
        }
    }

    public void clickKeyboardWithElement(String key, String element) {
        WebElement object = findElement(element, 0);
        if (object != null) {
            switch (key) {
                case "ENTER":
                    object.sendKeys(Keys.ENTER);
                    break;
                case "TAB":
                    object.sendKeys(Keys.TAB);
                    break;
                case "PAGE_DOWN":
                    object.sendKeys(Keys.PAGE_DOWN);
                    break;
                case "PAGE_UP":
                    object.sendKeys(Keys.PAGE_UP);
                    break;
                default:
                    break;
            }
        }
    }
}
