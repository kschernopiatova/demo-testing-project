package web.pages.desktop;

import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import web.pages.common.RegisterPageBase;

public class RegisterPage extends RegisterPageBase {

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

    public RegisterPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public void fillRegisterForm(String name, String email, String pass) {
        nameField.type(name);
        emailField.type(email);
        passwordField.type(pass);
        checkPassField.type(pass);
        continueButton.click();
    }
}
