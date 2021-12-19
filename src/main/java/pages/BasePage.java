package pages;

import org.openqa.selenium.By;

import java.util.Dictionary;
import java.util.Hashtable;

public abstract class BasePage {
    public Dictionary<String, By> pageElements;
    public Dictionary<String, By> commonElements;

    public BasePage() {
        this.pageElements = new Hashtable<>();
        this.commonElements = new Hashtable<>();
        commonElements.put("homepage button", By.xpath("//a[@id=\"logo\"]"));
        commonElements.put("pop up", By.xpath("//iframe[@id=\"rtb02\"]"));
        commonElements.put("overlay", By.xpath("//div[@class=\"overlay\"]"));
        commonElements.put("close image button", By.cssSelector("#gender-popup-modal svg"));
        commonElements.put("my cart button", By.xpath("//*[p[contains(text(),'Sepetim')]]"));

    }
}