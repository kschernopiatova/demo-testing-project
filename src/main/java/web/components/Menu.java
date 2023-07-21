package web.components;

import com.zebrunner.carina.webdriver.decorator.ExtendedWebElement;
import com.zebrunner.carina.webdriver.gui.AbstractUIObject;
import org.openqa.selenium.SearchContext;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class Menu extends AbstractUIObject {

    @FindBy(xpath = ".//ul[@data-menu-id='1']//a[@class='hmenu-item']/div | .//ul[@data-menu-id='1']//a[@class='hmenu-item' and text()]")
    private List<ExtendedWebElement> mainMenuItems;

    @FindBy(xpath = ".//ul[@data-menu-id!='1']//a")
    private List<ExtendedWebElement> subMenuItems;

    @FindBy(xpath = ".//div[text()='main menu']")
    private List<ExtendedWebElement> backToMenuButtons;

    @FindBy(xpath = ".//div[text()='see all']")
    private List<ExtendedWebElement> seeAllButtons;

    @FindBy(xpath = ".//div[text()='see less']")
    private List<ExtendedWebElement> seeLessButtons;

    public Menu(WebDriver driver, SearchContext searchContext) {
        super(driver, searchContext);
    }

    public void showAllMenuItems() {
        seeAllButtons.forEach(ExtendedWebElement::click);
    }

    public void openMenuItem(String itemTitle) {
        showAllMenuItems();
        mainMenuItems.stream()
                .filter(item -> itemTitle.equals(item.getText()))
                .findFirst().orElseThrow().click();
    }

    public void openSubMenuItem(String itemTitle) {
        subMenuItems.stream()
                .filter(item -> itemTitle.equals(item.getText()))
                .findFirst().orElseThrow().click();
    }

    public void goBackToMainMenu() {
        backToMenuButtons.stream()
                .filter(item -> item.isVisible(5))
                .findFirst()
                .orElseThrow().click();
    }
}
