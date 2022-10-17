package AkarCorp.PageObjects;

import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class OrderPage extends BasePage {
	WebDriver driver;
	
	public OrderPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
	
	@FindBy(css = "tr td:nth-child(3)")
	List<WebElement> orderItems;
	
	public boolean verifyOrderHistory(String productName) {
		Boolean isPresent = orderItems.stream().anyMatch(x -> x.getText().equalsIgnoreCase(productName));
		return isPresent;
	}
	
	

}
