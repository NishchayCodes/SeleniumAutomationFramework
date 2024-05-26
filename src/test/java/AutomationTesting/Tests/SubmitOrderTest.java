package AutomationTesting.Tests;

import java.io.File;
import java.io.IOException;
import java.time.Duration;
import java.util.HashMap;
import java.util.List;
import AutomationTesting.TestComponents.*;
import org.apache.commons.io.FileUtils;
import org.openqa.selenium.By;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import AutomationTesting.TestComponents.BaseTest;
import AutomationTesting.pageobjects.CartPage;
import AutomationTesting.pageobjects.CheckOutPage;
import AutomationTesting.pageobjects.Confirmationpage;
import AutomationTesting.pageobjects.LandingPage;
import AutomationTesting.pageobjects.OrdersPage;
import AutomationTesting.pageobjects.ProductCatalogue;
import io.github.bonigarcia.wdm.WebDriverManager;

public class SubmitOrderTest extends BaseTest {
	String productName="ADIDAS ORIGINAL";
	@Test(dataProvider="getData",groups= {"Purchase"},retryAnalyzer=Retry.class)
	public void submitOrder(HashMap<String,String> input) throws IOException, InterruptedException {
		
		ProductCatalogue productcatalogue=landingpage.loginApplication(input.get("email"),input.get("password") );
        List<WebElement> products=productcatalogue.getProductList();
        productcatalogue.addtoCart(input.get("product"));
        CartPage cartPage=productcatalogue.goToCartPage();
        boolean match=cartPage.verifyProductDisplay(input.get("product"));
        Assert.assertTrue(match);
        CheckOutPage chkoutPage=cartPage.goToChckOut();
        chkoutPage.selectCountry("India");
        Confirmationpage confirmationPage=chkoutPage.placeOrder();
        String confirmMssg=confirmationPage.getConfirmationMssg();
        Assert.assertTrue(confirmMssg.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
        
        
   	}
	@Test(dependsOnMethods= {"submitOrder"})
	public void orderHistoryTest() throws InterruptedException {
		ProductCatalogue productcatalogue=landingpage.loginApplication("nishchay@gmail.com", "Password@123");
		OrdersPage orderspage=productcatalogue.goToOrdersPage();
		Assert.assertTrue(orderspage.verifyOrdersDisplay(productName));
		
	}
	@DataProvider
	public Object[][] getData(){
		HashMap<String,String> map=new HashMap<String,String>();
		map.put("email","nishchay@gmail.com");
		map.put("password","Password@123");
		map.put("product","ADIDAS ORIGINAL");
		
		HashMap<String,String> map1=new HashMap<String,String>();
		map1.put("email","ravish@gmail.com");
		map1.put("password","Password@123");
		map1.put("product","ZARA COAT 3");
	
		
    	return new Object[][] {{map},{map1}} ;
		
		}
	
//	
//	@DataProvider
//	public Object[][] getData(){
//		return new Object[][] {{"nishchay@gmail.com","Password@123","ADIDAS ORIGINAL"},{"ravish@gmail.com","Password@123","ZARA COAT 3"}} ;
//		
//		
//	}
}


