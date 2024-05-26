package AutomationTesting.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AutomationTesting.AbstractComponents.AbstractComponent;

public class CartPage extends AbstractComponent{

WebDriver driver;
	
	
	
	public CartPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
	    PageFactory.initElements(driver,this);
	}
	
	@FindBy(css=".cartSection h3")
	List<WebElement> cartProducts;
	
	@FindBy(css=".subtotal button")
	WebElement checkOutEle;
	
	
	public boolean verifyProductDisplay(String productName) {
		boolean match = false;
		for(WebElement cartProduct:cartProducts) {
			if(cartProduct.getText().equals(productName)) {
				match=true;
				
			}
		}
		return match;
	
	}
	public CheckOutPage goToChckOut() {
		checkOutEle.click();
		return new CheckOutPage(driver);
	}
}
