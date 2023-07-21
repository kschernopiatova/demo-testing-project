package web.pages.desktop;

import com.zebrunner.carina.utils.factory.DeviceType;
import web.components.desktop.Header;
import web.pages.common.HomePageBase;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

@DeviceType(pageType = DeviceType.Type.DESKTOP, parentClass = HomePageBase.class)
public class HomePage extends HomePageBase {

    @FindBy(id = "navbar")
    private Header header;

    public HomePage(WebDriver driver) {
        super(driver);
    }
}
