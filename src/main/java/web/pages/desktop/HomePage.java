package web.pages.desktop;

import com.zebrunner.carina.utils.factory.DeviceType;
import org.openqa.selenium.WebDriver;
import web.pages.common.HomePageBase;

@DeviceType(pageType = DeviceType.Type.DESKTOP, parentClass = HomePageBase.class)
public class HomePage extends HomePageBase {

    public HomePage(WebDriver driver) {
        super(driver);
    }
}
