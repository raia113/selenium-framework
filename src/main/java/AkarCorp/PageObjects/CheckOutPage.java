package AkarCorp.PageObjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CheckOutPage extends BasePage {
	WebDriver driver;

	public CheckOutPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = ".action__submit")
	WebElement placeSubmit;

	@FindBy(css = "[placeholder='Select Country']")
	WebElement country;

	@FindBy(xpath = "(//button[contains(@class,'ta-item')])[2]")
	WebElement selectCountry;

	By waitCountry = By.cssSelector("[placeholder='Select Country']");
	By checkOutButton = By.xpath("(//button[@class='btn btn-primary'])[3]");
	By submitButton = By.cssSelector(".action__submit");
	By searchResults = By.cssSelector(".ta-results");

	public ConfirmationPage placeOrder() {
		waitForElementToBeClickable(submitButton);
		scrollToViewAndClick(placeSubmit);
		ConfirmationPage confirmaton = new ConfirmationPage(driver);
		return confirmaton;
	}

	public void selectCountry(String countryName) {
		waitForElementToBeClickable(waitCountry);
		scrollToViewAndClick(country);
		//Actions a = new Actions(driver);
		waitForElementToBeClickable(waitCountry);
		//a.sendKeys(country, countryName).build().perform();
		country.sendKeys(countryName);
		waitForElementToAppear(searchResults);
		selectCountry.click();
	}

}
