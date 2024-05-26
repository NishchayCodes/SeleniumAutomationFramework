package AutomationTesting.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import AutomationTesting.AbstractComponents.AbstractComponent;

public class CheckOutPage extends AbstractComponent {
	
	WebDriver driver;
	
	public CheckOutPage(WebDriver driver) {
		super(driver);
		this.driver=driver;
	    PageFactory.initElements(driver,this);
	}
  @FindBy(css="[placeholder='Select Country']")
  WebElement selectCntryEle;
  
  @FindBy(xpath="//button[contains(@class,'ta-item')][2]")
  WebElement cntryValue;
  
  @FindBy(xpath="//a[contains(@class,'action__submit')]")
  WebElement placeOrderEle;
  
By section=By.xpath("//section[contains(@class,'ta-results')]");

  public void selectCountry(String country) {
	  selectCntryEle.sendKeys(country);
	  waitForElementToAppear(section);
	  cntryValue.click();

  }
  public Confirmationpage placeOrder() {
	  placeOrderEle.click();
	  return new Confirmationpage(driver);
  }
}
