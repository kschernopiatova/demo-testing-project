package web.pages.desktop;

import com.zebrunner.carina.utils.factory.DeviceType;
import org.openqa.selenium.WebDriver;
import web.pages.common.LoginPageBase;

@DeviceType(pageType = DeviceType.Type.DESKTOP, parentClass = LoginPageBase.class)
public class LoginPage extends LoginPageBase {

    public LoginPage(WebDriver driver) {
        super(driver);
    }
}
