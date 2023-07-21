package web.pages.common;

import org.openqa.selenium.WebDriver;

public abstract class LoginPageBase extends HomePageBase {

    public LoginPageBase(WebDriver driver) {
        super(driver);
    }

    public abstract HomePageBase loginUser(String email, String pass);

    public abstract void enterEmail(String email);

    public abstract void clickContinueButton();

    public abstract void enterPassword(String pass);

    public abstract void clickSignInButton();
}
