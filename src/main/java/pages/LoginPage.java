package pages;

import org.openqa.selenium.By;

import java.util.Hashtable;

public class LoginPage extends BasePage {

    public Hashtable<String, By> loginElements;

    public LoginPage() {
        super();
        this.loginElements = new Hashtable<>();
        loginElements.put("email address bar", By.id("login-email"));
        loginElements.put("password bar", By.id("login-password-input"));
        loginElements.put("submit login button", By.cssSelector("#login-register button.submit"));
        this.pageElements = loginElements;
    }
}
