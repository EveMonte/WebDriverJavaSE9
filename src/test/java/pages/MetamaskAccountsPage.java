package pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.ui.ExpectedConditions;

public class MetamaskAccountsPage extends AbstractPage {

    private final String accountDropDownMenuClassName = "account-menu__icon";

    @FindBy(className = accountDropDownMenuClassName)
    private WebElement accountDropDownMenu;

    @FindBy(xpath = "//div[contains(string(), 'Создать счет')]/parent::div[contains(@class, 'account-menu__item--clickable')]")
    private WebElement createNewAccountItem;

    @FindBy(xpath = "//div[@class='new-account-create-form']/div//button[contains(@class, 'btn-primary')]")
    private WebElement createNewAccountButton;

    @FindBy(xpath = "//div[@class='account-menu__accounts']/div[contains(@class, 'account-menu__item--clickable')]")
    private WebElement swapToTheAnotherAccount;


    public MetamaskAccountsPage createNewAccount() {
        logger.info("Create New Account");
        createNewAccountItem.click();
        createNewAccountButton.click();
        return this;
    }

    public MetamaskHomePage swapToTheMainAccount(){
        logger.info("Swap To The Main Account");
        wait.until(ExpectedConditions.presenceOfElementLocated(By.className(accountDropDownMenuClassName)));
        accountDropDownMenu.click();
        swapToTheAnotherAccount.click();

        return new MetamaskHomePage(driver);
    }

    public MetamaskAccountsPage(WebDriver driver) {
        super(driver);
    }
}
