package web.pages.desktop;

import com.zebrunner.carina.utils.factory.DeviceType;
import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import web.components.desktop.Header;
import web.pages.common.HomePageBase;
import web.pages.common.LoginPageBase;

@DeviceType(pageType = DeviceType.Type.DESKTOP, parentClass = LoginPageBase.class)
public class LoginPage extends LoginPageBase {

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

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public HomePageBase loginUser(String email, String pass) {
        enterEmail(email);
        clickContinueButton();
        enterPassword(pass);
        clickSignInButton();
        return initPage(getDriver(), HomePageBase.class);
    }

    @Override
    public void enterEmail(String email) {
        emailField.type(email);
    }

    @Override
    public void clickContinueButton() {
        continueButton.click();
    }

    @Override
    public void enterPassword(String pass) {
        passwordField.type(pass);
    }

    @Override
    public void clickSignInButton() {
        signInButton.click();
    }
}
