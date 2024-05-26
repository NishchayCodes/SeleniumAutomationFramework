package AutomationTesting.pageobjects;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AutomationTesting.AbstractComponents.AbstractComponent;

public class Confirmationpage extends AbstractComponent {
WebDriver driver;
	
	public Confirmationpage(WebDriver driver) {
		super(driver);
		this.driver=driver;
	    PageFactory.initElements(driver,this);
	}

	@FindBy(css=".hero-primary")
	WebElement confirmMssg;
	
	public String getConfirmationMssg() {
		return confirmMssg.getText();
	}
}
