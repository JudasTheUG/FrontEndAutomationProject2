package pages;

import org.openqa.selenium.By;

import java.util.Hashtable;

public class HomePage extends BasePage {

    public Hashtable<String, By> homeElements;

    public HomePage() {
        super();
        this.homeElements = new Hashtable<>();
        homeElements.put("sign in title", By.xpath("//*[p[contains(text(),'Giriş Yap')]]"));
        homeElements.put("login button", By.xpath("//*[p[contains(text(),'Giriş Yap')]]"));
        homeElements.put("search input box", By.xpath("//*[@class=\"search-box\"]"));
        homeElements.put("search button", By.xpath("//*[@class=\"search-icon\"]"));
        homeElements.put("my account title", By.xpath("//*[p[contains(text(),'Hesab')]]"));
        //homeElements.put("book, music, movie, hobby category title", By.xpath("//*[(text()='Kitap, Müzik, Film, Hobi')]//parent::span"));
        //homeElements.put("remote control vehicles link button", By.xpath("//*[(text()='Uzaktan Kumandalı Araçlar')]//parent::a"));
        //homeElements.put("delivery today title", By.xpath("//*[@title='Bugün Teslimat']"));
        //homeElements.put("details button", By.xpath("//a[@class='sf-DeliveryTooltip-2zrc0']//parent::div"));
        this.pageElements = homeElements;
    }
}
