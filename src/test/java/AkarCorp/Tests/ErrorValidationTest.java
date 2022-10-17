package AkarCorp.Tests;

import java.io.IOException;

import org.testng.Assert;
import org.testng.annotations.Test;

import AkarCorp.PageObjects.CartPage;
import AkarCorp.PageObjects.ProductCatalog;
import AkarCorp.TestComponents.BaseTest;
import AkarCorp.TestComponents.RetryTest;

public class ErrorValidationTest extends BaseTest {
	String productName = "ZARA COAT 3";

	@Test(groups= {"ErrorHandling"}, retryAnalyzer = RetryTest.class)
	public void errorValidationLogin() throws InterruptedException, IOException {

		landingPage.loginApplication("patrickbateman@email.com", "1P@trick1");
		String expectedMessage = landingPage.getErrorMessage();
		Assert.assertEquals("Incorrect email or password1.", expectedMessage);
	}
	
	
	@Test(groups= {"ErrorHandling"})
	public void errorValidationSubmitOrder() throws InterruptedException, IOException {
		ProductCatalog productCatalog = landingPage.loginApplication("patrickbateman@email.com", "P@trick1");
		productCatalog.addProductToCart(productName);
		CartPage cartPage = productCatalog.goToCartPage();
		Boolean isPresent = cartPage.VerifiyProductPresent(productName);
		Assert.assertTrue(isPresent); //check and make sure random product 
	}


}
