package web.pages.common;

import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

public abstract class RegisterPageBase extends AbstractPage {

    @FindBy(id = "ap_customer_name")
    private ExtendedWebElement nameField;

    @FindBy(id = "ap_email")
    private ExtendedWebElement emailField;

    @FindBy(id = "ap_password")
    private ExtendedWebElement passwordField;

    @FindBy(id = "ap_password_check")
    private ExtendedWebElement checkPassField;

    @FindBy(id = "continue")
    private ExtendedWebElement continueButton;

    public RegisterPageBase(WebDriver driver) {
        super(driver);
    }

    public void enterName(String name) {
        nameField.type(name);
    }

    public void enterEmail(String email) {
        emailField.type(email);
    }

    public void enterPassword(String pass) {
        passwordField.type(pass);
        checkPassField.type(pass);
    }

    public void registerUser(String name, String email, String pass) {
        enterName(name);
        enterEmail(email);
        enterPassword(pass);
        continueButton.click();
    }
}
