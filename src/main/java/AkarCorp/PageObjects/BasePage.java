package AkarCorp.PageObjects;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class BasePage {

	WebDriver driver;
	WebDriverWait wait;
	JavascriptExecutor js;


	public BasePage(WebDriver driver) {
		this.driver = driver;
		this.wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		this.js = (JavascriptExecutor) driver;
		PageFactory.initElements(driver, this);

	}

	@FindBy(css = "[routerLink*='cart']")
	WebElement cartPage;
	
	@FindBy(css = "[routerLink*='myorders']")
	WebElement orderPage;
	
	@FindBy(css =".fa.fa-sign-out")
	WebElement signOutPage;
	
	By cartPageNav = By.cssSelector("[routerLink*='cart']");
		

	/** Page Navigation **/

	
	public CartPage goToCartPage() {
		waitForElementToBeClickable(cartPageNav);
		cartPage.click();
		CartPage cartPage = new CartPage(driver);
		return cartPage;
	}
	
	public OrderPage goToOrdersPage() {
		orderPage.click();
		OrderPage orderPage = new OrderPage(driver);
		return orderPage;
		
	}
	
	public LandingPage userSignOut() throws InterruptedException {
		scrollToViewAndClick(signOutPage);
		LandingPage landingPage = new LandingPage(driver);
		return landingPage;
	
	}

	public void waitForElementToAppear(By findBy) {
		wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
	}

	public void waitForElementToBeLocated(By findBy) {
		wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(findBy));
	}

	public void waitForElementToBeClickable(By findBy) {
		wait.until(ExpectedConditions.elementToBeClickable(findBy));
	}

	public void waitForElementToDisappear(WebElement ele) throws InterruptedException {
		/*
		 * //WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
		 * wait.until(ExpectedConditions.invisibilityOf(ele));
		 */
		// workaround to handle code
		Thread.sleep(1000);
	}
	
	public void waitForWebElementToappear(WebElement findBy) {
		wait.until(ExpectedConditions.visibilityOf(findBy));
	}
	

	public void scrollToViewAndClick(WebElement ele) {
		//js.executeScript("window.scrollBy(0,130)");
		waitForWebElementToappear(ele);
		js.executeScript("arguments[0].click();", ele);
	}
	
	public void implicitWaitMaxBrowser() {
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
		driver.manage().window().maximize();
	}
	
}
