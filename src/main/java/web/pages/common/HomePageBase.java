package web.pages.common;

import com.zebrunner.carina.webdriver.gui.AbstractPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import web.components.desktop.Header;

public abstract class HomePageBase extends AbstractPage {

    @FindBy(id = "navbar")
    private Header header;

    public HomePageBase(WebDriver driver) {
        super(driver);
    }

    public LoginPageBase goToLoginPage() {
        header.openLoginPage();
        return initPage(getDriver(), LoginPageBase.class);
    }

    public boolean isUserLogined(String userName) {
        return header.isUserLogined(userName);
    }
}
