package pages;

import org.openqa.selenium.By;

import java.util.Hashtable;

public class SearchResultsPage extends BasePage {

    public Hashtable<String, By> searchResultsElements;

    public SearchResultsPage() {
        this.searchResultsElements = new Hashtable<>();
        searchResultsElements.put("model", By.xpath("//*[div[contains(text(),'Model')]]"));
        searchResultsElements.put("model option", By.xpath("//div[contains(text(),'Kulaküstü')]"));
        searchResultsElements.put("add to cart button", By.xpath("//button[@class=\"add-to-basket-button\"]"));
        searchResultsElements.put("continue to cart button", By.xpath("//*[div[@class=\"go-to-basket-button visible\"]]"));
        searchResultsElements.put("order", By.xpath("//select"));
        searchResultsElements.put("order item", By.xpath("//*[@value=\"BEST_SELLER\"]"));

        this.pageElements = searchResultsElements;
    }
}