package AkarCorp.PageObjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class CartPage extends BasePage {
	WebDriver driver;

	public CartPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = ".cartSection h3")
	List<WebElement> cartItem;

	@FindBy(xpath = "(//button[@class='btn btn-primary'])[3]")
	WebElement checkOut;

	By checkOutButton = By.xpath("(//button[@class='btn btn-primary'])[3]");
	

	public Boolean VerifiyProductPresent(String productName) {
		Boolean isPresent = cartItem.stream().anyMatch(x -> x.getText().equalsIgnoreCase(productName));
		return isPresent;
	}

	public CheckOutPage goToCheckout() {
		waitForElementToBeLocated(checkOutButton);
		scrollToViewAndClick(checkOut);
		CheckOutPage checkoutPage = new CheckOutPage(driver);
		return checkoutPage;
	}

}
