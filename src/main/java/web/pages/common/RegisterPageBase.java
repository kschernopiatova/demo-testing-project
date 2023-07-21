package web.pages.common;

import org.openqa.selenium.WebDriver;

public abstract class RegisterPageBase extends HomePageBase {

    public RegisterPageBase(WebDriver driver) {
        super(driver);
    }

    public abstract void fillRegisterForm(String name, String email, String pass);
}
