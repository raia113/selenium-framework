package AkarCorp.Tests;

import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import AkarCorp.PageObjects.CartPage;
import AkarCorp.PageObjects.CheckOutPage;
import AkarCorp.PageObjects.ConfirmationPage;
import AkarCorp.PageObjects.OrderPage;
import AkarCorp.PageObjects.ProductCatalog;
import AkarCorp.TestComponents.BaseTest;

public class SubmitOrderTest extends BaseTest {
	//String productName = "ZARA COAT 3";
	@Test(dataProvider="getDataForSubmitOrder", groups={"Purchase"})
	public void orderSubmit(HashMap<String, String> input) throws InterruptedException, IOException {
		
		
		ProductCatalog productCatalog = landingPage.loginApplication(input.get("email"), input.get("password")); //need to understand these object webbings

		productCatalog.addProductToCart(input.get("product"));
		CartPage cartPage = productCatalog.goToCartPage();
		Boolean isPresent = cartPage.VerifiyProductPresent(input.get("product"));
		Assert.assertTrue(isPresent);

		CheckOutPage checkout = cartPage.goToCheckout();
		checkout.selectCountry("India");
		ConfirmationPage confPage = checkout.placeOrder();

		String confirmedMessage = confPage.getConfirmationMessage();
		Assert.assertTrue(confirmedMessage.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
		//landingPage.userSignOut();
		
	}
	
	//using hardcoded StringsExample
	@Test(dataProvider="getDataForVerifyOrder", groups={"Purchase"}, dependsOnMethods = {"orderSubmit"})
	public void orderHistoryVerify(String email, String password, String productName) {
		ProductCatalog productCatalog = landingPage.loginApplication(email, password); //P@ul@llen1
		Boolean matched = productCatalog.goToOrdersPage().verifyOrderHistory(productName);
		Assert.assertTrue(matched);	
		
	}
	
	@DataProvider
	public Object[][] getDataForSubmitOrder() throws IOException {
		String filename1 =System.getProperty("user.dir")+"\\src\\test\\java\\AkarCorp\\Data\\PurchaseOrder.json";
		List<HashMap<String, String>> data = getJsonDataToMap(filename1);
		return new Object [] [] {{data.get(0)},{data.get(1)}};
	} 
	
	@DataProvider
	public Object[][] getDataForVerifyOrder() throws IOException {
		return new Object [] [] {{"patrickbateman@email.com","P@trick1","ZARA COAT 3"},{"paulallen@email.com","P@ul@llen1","ADIDAS ORIGINAL"}};
	} 

}
