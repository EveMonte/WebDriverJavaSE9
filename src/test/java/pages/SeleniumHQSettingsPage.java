package pages;

import model.Contact;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import service.ContactCreator;

public class SeleniumHQSettingsPage extends AbstractPage {
    private final String accountDropDownMenuClassName = "account-menu__icon";

    @FindBy (xpath = "//div[text()='Настройки']/parent::div")
    WebElement openSettings;

    @FindBy(className = accountDropDownMenuClassName)
    private WebElement accountDropDownMenu;

    @FindBy(xpath = "//div[text()='Контактная информация']/parent::div/parent::button")
    private WebElement openContactInfo;

    @FindBy(xpath = "//button[text()='Сохранить']")
    private WebElement saveContact;

    @FindBy(className = "address-book__link")
    private WebElement addContact;

    @FindBy(id = "nickname")
    private WebElement nicknameInput;

    @FindBy(className = "ens-input__wrapper__input")
    private WebElement addressInput;

    @FindBy(className = "send__select-recipient-wrapper__group-item__title")
    private WebElement createdContact;

    @FindBy(xpath = "//div[@class='popover-header__title']/button")
    private WebElement closeNewsPopupWindow;


    public SeleniumHQSettingsPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public SeleniumHQSettingsPage openPage() {
        closeNewsPopupWindow.click();
        accountDropDownMenu.click();
        openSettings.click();
        openContactInfo.click();


        return this;
    }

    public Contact addContact() throws InterruptedException {
        Contact newContact = ContactCreator.createContact();
        addContact.click();

        nicknameInput.sendKeys(newContact.getContactName());
        addressInput.sendKeys(newContact.getContactAddress());
        Thread.sleep(3000);

        saveContact.click();
        newContact.setActualContactName(createdContact.getText());
         return newContact;
    }
}
