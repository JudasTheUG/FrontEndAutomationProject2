package pages;

import org.openqa.selenium.By;

import java.util.Hashtable;

public class Basket extends BasePage {

    public Hashtable<String, By> BasketElements;

    public Basket() {
        super();
        this.BasketElements = new Hashtable<>();
        BasketElements.put("product list", By.xpath("//*[@class=\"pb-basket-item\"]"));
        BasketElements.put("delete button", By.xpath("//*[i[@class=\"i-trash\"]]"));
        BasketElements.put("secret delete button", By.xpath("//button[contains(text(),'Sil')]"));
        this.pageElements = BasketElements;
    }
}