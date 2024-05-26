package AutomationTesting.AbstractComponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import AutomationTesting.pageobjects.CartPage;
import AutomationTesting.pageobjects.OrdersPage;

public class AbstractComponent {

	WebDriver driver;
	public AbstractComponent(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver,this);
	}

	@FindBy(css="[routerlink*='cart']")
	WebElement cartHeader;

	@FindBy(css="[routerlink*='myorders']")
	WebElement ordersHeader;
	
    
	public void waitForElementToAppear(By findBy) {
	WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(2));
	wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
}
	public void waitForElement(WebElement ele) throws InterruptedException {
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOf(ele));
//		Thread.sleep(3000);
	}
	public void waitForElementToDisappear(WebElement ele) throws InterruptedException {
//		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(2));
//		wait.until(ExpectedConditions.invisibilityOf(ele));
		Thread.sleep(2000);
	}
	public  CartPage goToCartPage() {
		cartHeader.click();
		CartPage cartPage=new CartPage(driver);
		return cartPage;
	}
	public  OrdersPage goToOrdersPage() {
		ordersHeader.click();
		OrdersPage ordersPage=new OrdersPage(driver);
		return ordersPage;
	}
}
