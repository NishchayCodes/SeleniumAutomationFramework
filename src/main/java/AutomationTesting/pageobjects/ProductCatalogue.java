package AutomationTesting.pageobjects;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AutomationTesting.AbstractComponents.AbstractComponent;

public class ProductCatalogue extends AbstractComponent{

WebDriver driver;
	
	//Constructor of landingPage class receiving driver as parameter from Landingpage object in SubmitOrderTest class 
	//
	public ProductCatalogue(WebDriver driver) {
		//passing driver value to parent AbstractComponent through super keyword which is received by Constructor of Abstractcomponent class
		super(driver);
		//Giving driver value fetched from LandingPage object to local driver variable
		this.driver=driver;
		//initElements class initialise and  passes value of driver and this to @FindBy elements below 
		PageFactory.initElements(driver,this);
	}
	
	@FindBy(css=".mb-3")
	List<WebElement> products;	
	
	@FindBy(css=".ng-animating")
	WebElement spinnerElement;
	
	By productBy=By.cssSelector(".mb-3");
	By addToCart=By.cssSelector(".card-body button:last-of-type");
	By toastContainer=By.cssSelector("#toast-container");

	
	
	public List<WebElement> getProductList() {
		waitForElementToAppear(productBy);
		return products;
	}
	public WebElement getProductByName(String productName) {
		WebElement prod = null;
		for(WebElement product:products) {
			
			if(product.findElement(By.cssSelector("b")).getText().equals(productName)){
				prod= product;
				
			}
			
		}
		return prod;
		
		
		
		}
	public void addtoCart(String productName) throws InterruptedException {
//		getProductByName(productName).click();
		WebElement prod=getProductByName(productName);
		prod.findElement(addToCart).click();
		waitForElementToAppear(toastContainer);
		waitForElement(spinnerElement);
		waitForElementToDisappear(spinnerElement);
	}
}
