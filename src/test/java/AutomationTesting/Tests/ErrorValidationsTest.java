package AutomationTesting.Tests;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.annotations.Test;

import AutomationTesting.TestComponents.*;
import AutomationTesting.pageobjects.CartPage;
import AutomationTesting.pageobjects.ProductCatalogue;

public class ErrorValidationsTest extends BaseTest {
	
	@Test(groups= {"ErrorHandling"},retryAnalyzer=Retry.class)
	public void LoginErrorValidation() throws IOException, InterruptedException {
		String productName="ADIDAS ORIGINAL";
		landingpage.loginApplication("nishchay@gmail.com", "password@123");
		Assert.assertEquals( landingpage.getErrorMessage(),"Incorrect email or password.");
      
   	}
	@Test
	public void ProductErrorValidation() throws InterruptedException {
		String productName="ADIDAS ORIGINAL";
		ProductCatalogue productcatalogue=landingpage.loginApplication("ravish@gmail.com", "Password@123");
        List<WebElement> products=productcatalogue.getProductList();
        productcatalogue.addtoCart(productName);
        CartPage cartPage=productcatalogue.goToCartPage();
        boolean match=cartPage.verifyProductDisplay("ABIDAS ORIGINALS");
        Assert.assertFalse(match);
	}
}


