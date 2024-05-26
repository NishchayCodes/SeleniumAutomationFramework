package AutomationTesting.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AutomationTesting.AbstractComponents.AbstractComponent;

public class LandingPage extends AbstractComponent {
	
	WebDriver driver;
	
	//Constructor of landingPage class receiving driver as parameter from Landingpage object in SubmitOrderTest class 
	//
	public LandingPage(WebDriver driver) {
		//passing driver value to parent AbstractComponent through super keyword which is received by Constructor of Abstractcomponent class
		super(driver);
		//Giving driver value fetched from LandingPage object to local driver variable
		this.driver=driver;
		//initElements class initialise and  passes value of driver and this to @FindBy elements below 
		PageFactory.initElements(driver,this);
	}
	
	@FindBy(id="userEmail")
	WebElement userEmail;
	
	@FindBy(id="userPassword")
	WebElement password;
    
	@FindBy(id="login")
	WebElement login;
	
	@FindBy(css="[class*='flyInOut']")
	WebElement errorMessage;
	
	public ProductCatalogue loginApplication(String id, String pwd) {
		userEmail.sendKeys(id);
		password.sendKeys(pwd);
		login.click();
		ProductCatalogue productcatalogue=new ProductCatalogue(driver);
		return productcatalogue;
	}
	public String getErrorMessage() throws InterruptedException {
		waitForElement(errorMessage);	
		return errorMessage.getText();
	}
	public void goTo() {
		driver.get("https://rahulshettyacademy.com/client/");
	}
}
