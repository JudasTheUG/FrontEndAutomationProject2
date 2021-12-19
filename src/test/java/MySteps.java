import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.AfterStep;
import cucumber.api.java.Before;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import pages.BasePage;
import pages.Basket;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class MySteps extends BasePage {
    WebSteps websteps;
    WebDriverWait wait;
    int ssCount=0;

    @AfterStep
    public void screenshotTaker(Scenario scenario) throws IOException {
        ssCount++;
        File screenshot = ((TakesScreenshot) websteps.driver).getScreenshotAs(OutputType.FILE);
        FileUtils.copyFile(screenshot,new File ("./target/ScreenShots/image"+ssCount+".png"));
    }

    @After
    public void tearDown(Scenario scenario) throws IOException {
        if (scenario.isFailed()) {
            File screenshot = ((TakesScreenshot) websteps.driver).getScreenshotAs(OutputType.FILE);
            FileUtils.copyFile(screenshot,new File ("./target/ScreenShots/fail_image"+ssCount+".png"));
            String link ="C:\\Users\\asus\\Documents\\WebFunctions\\target\\cucumber-html-report\\index.html";
            System.out.println("<a href=\"" + link + "\">" + link + "</a>");
        }
        
        websteps.page = new Basket();
        //Clean basket after succesfull scenario For fail there is a step in the addToBasket scenario which is checks and empties the basket
        checkAndEmptyTheBasket("product list",1);

        websteps.driver.quit();
    }


    @Before
    public void beforeScenario() {
        System.out.println("*************Before Scenario***********");
    }


    @When("^I see (.*) page$")
    public void seePage(String page) {

        websteps.seePage(page);
    }

    @When("^I initialize (chrome|firefox) driver$")
    public void initializeChromeDriver(String browser) {
        websteps = new WebSteps();

        websteps.initializeDriver(browser);

        wait = new WebDriverWait(websteps.driver,30);
    }

    @When("^I go to \"([^\"]*)\" url$")
    public void gotoURL(String url) {
        websteps.openUrl(url);
    }

    @When("^I wait (.*) element (\\d+) seconds$")
    public void waitElement(String element, int timeout) throws InterruptedException {
        websteps.waitElement(element, timeout);
    }

    @When("^(?:I )?click element: (\\w+(?: \\w+)*) index: (\\d+)$")
    public void clickElement(String element,int index) {
        WebElement object = websteps.findElement(element,index-1);

        if (object != null) {
            object.click();
            System.out.println("Clicked on object-->" + element);
        } else {
            System.out.println("Could not click on object-->" + element);
        }
    }

    @When("^I enter \"([^\"]*)\" text to (.*) text area$")
    public void enterText(String text, String element) throws InterruptedException {
        mouseHover(element);
        WebElement object;
        object = websteps.findElement(element,0);

        if (object != null) {
            object.sendKeys(text);
            System.out.println("The text has been entered.");
        }
    }

    @When("^I click \"([^\"]*)\" keyboard button$")
    public void clickKeyboard(String key) {
        websteps.clickKeyboard(key);
    }

    @When("^I do click \"([^\"]*)\" keyboard button with \"([^\"]*)\" element$")
    public void clickKeyboardWithElement(String key, String element) {
        websteps.clickKeyboardWithElement(key, element);
    }

    @When("^(?:I )?wait for (\\d+) seconds?$")
    public void waitForNSeconds(int seconds) {
        try {
            Thread.sleep((long) seconds * 1000L);
        } catch (Exception e) {
            Assert.fail();
        }
    }

    @When("^I send text \"([^\"]*)\" to  \"([^\"]*)\" element$")
    public void sendText(String value, String element) {
        websteps.enterText(value, element);
    }

    @When("^(?:I )?focus (.*) element$")
    public void mouseHover(String element) throws InterruptedException {
        Actions actions = new Actions(websteps.driver);
        WebElement elem = null;
        elem = websteps.waitElement(element, 5);
        actions.moveToElement(elem).perform();
        Thread.sleep(2000);
    }

    @When("^(?:I )?clean (.*) then I enter \"([^\"]*)\" text$")
    public void cleanField(String element, String text) throws InterruptedException {
        By elem = websteps.page.pageElements.get(element);
        websteps.driver.findElement(elem).clear();
        waitForNSeconds(2);
        enterText(text, element);
    }

    @When("^(?:If)? exist (\\w+(?: \\w+)*)$")
    public void ifExist(String elementKey) throws InterruptedException {
        WebElement element = null;
        element = websteps.waitElement(elementKey, 5);
        try {
            if (element != null) {
                element.click();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @When("^(?:I )verify cargo price (included|not included) then by taking the (.*) element difference from (.*) element$")
    public void controlCargoPrice(String option, String productPrice, String totalPrice) {

        String firstText= websteps.findElement(productPrice,0).getText();
        int firstPosition=firstText.indexOf(" ");
        String firstValue = firstText.substring(0,firstPosition).replace(",",".");

        String secondText= websteps.findElement(totalPrice,0).getText();
        int secondPosition=firstText.indexOf(" ");
        String secondValue = secondText.substring(0,secondPosition).replace(",",".");

        float difference = Math.abs(Float.parseFloat(secondValue) - Float.parseFloat(firstValue));

        if (option.equals("included")) {
            Assert.assertFalse((int)difference == 0);
        } else if (option.equals("not included")) {
            Assert.assertFalse((int)difference != 0);
        }
    }

    @Then("^(?:I )?click on element: (\\w+(?: \\w+)*) index: (\\d+)$")
    public void clickOnElement(String element,int index) {
        WebElement object = websteps.findElement(element,index-1);

        if (object != null) {
            object.click();
            System.out.println("Clicked on object-->" + element);
        } else {
            System.out.println("Could not click on object-->" + element);
        }
    }

    @Then("^(?:I )?check the basket with element: (\\w+(?: \\w+)*) and empty the basket if it has product on index: (\\d+)$")
    public void checkAndEmptyTheBasket(String element,int index) {
        List<WebElement> objects = websteps.findElements(element);
        WebElement delete;
        WebElement secret;
        if (objects != null) {
            for (int i =0;i<objects.size();i++) {
                try{
                    delete=websteps.findElement("delete button",index-1);
                    delete.click();
                    secret= websteps.findElement("secret delete button",index-1);
                    secret.click();
                    waitForNSeconds(5);
                }catch (StaleElementReferenceException e){System.out.println(e);}


            }
            System.out.println("Emptied the basket");
        } else {
            System.out.println("Basket is Empty");
        }
    }

}