package web.pages.common;

import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import web.components.desktop.Header;

public abstract class LoginPageBase extends AbstractPage {

    @FindBy(id = "navbar")
    private Header header;

    @FindBy(id = "ap_email")
    private ExtendedWebElement emailField;

    @FindBy(id = "continue-announce")
    private ExtendedWebElement continueButton;

    @FindBy(id = "createAccountSubmit")
    private ExtendedWebElement createAccButton;

    @FindBy(id = "ap_password")
    private ExtendedWebElement passwordField;

    @FindBy(id = "signInSubmit")
    private ExtendedWebElement signInButton;

    public LoginPageBase(WebDriver driver) {
        super(driver);
    }

    public HomePageBase loginUser(String email, String pass) {
        enterEmail(email);
        clickContinueButton();
        enterPassword(pass);
        clickSignInButton();
        return initPage(getDriver(), HomePageBase.class);
    }

    public void enterEmail(String email) {
        emailField.type(email);
    }

    public void clickContinueButton() {
        continueButton.clickByActions();
    }

    public void enterPassword(String pass) {
        passwordField.type(pass);
    }

    public void clickSignInButton() {
        signInButton.click();
    }
}
