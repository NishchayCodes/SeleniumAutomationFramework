 package AutomationTesting.Tests;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import AutomationTesting.pageobjects.LandingPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class StandAloneTest {
	public static void main(String[] args) {
		String productName="ADIDAS ORIGINAL";
		WebDriverManager.chromedriver().setup();
		WebDriver driver=new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("https://rahulshettyacademy.com/client/");
                //Implicitly wait
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
                  
		
		//Creating LandingPage class object and sending driver as an argument
		LandingPage landingpage=new LandingPage(driver);
		driver.findElement(By.id("userEmail")).sendKeys("nishchay@gmail.com");
		driver.findElement(By.id("userPassword")).sendKeys("Password@123");
		driver.findElement(By.id("login")).click();
		
                //explicit wait
		WebDriverWait wait=new WebDriverWait(driver,Duration.ofSeconds(5));
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));
		
		
		List<WebElement> products=driver.findElements(By.cssSelector(".mb-3"));
//		System.out.println(products);
		for(WebElement product:products) {
			if(product.findElement(By.cssSelector("b")).getText().equals(productName)) {
				product.findElement(By.cssSelector(".card-body button:last-of-type")).click();
			}
		}
	
		wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
		wait.until(ExpectedConditions.visibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
		wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
//		wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//*[@id='toast-container']")));
		driver.findElement(By.cssSelector("[routerlink*='cart']")).click();
		
		List<WebElement> cartProducts=driver.findElements(By.cssSelector(".cartSection h3"));
		for(WebElement cartProduct:cartProducts) {
			if(cartProduct.getText().equals(productName)) {
				driver.findElement(By.cssSelector(".subtotal button")).click();
				
			}
		}
		
	 driver.findElement(By.cssSelector("[placeholder='Select Country']")).sendKeys("India");
	 wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//section[contains(@class,'ta-results')]")));
	 
			 driver.findElement(By.xpath("//button[contains(@class,'ta-item')][2]")).click();
		 
	 
	 driver.findElement(By.xpath("//a[contains(@class,'action__submit')]")).click();
	 
	 String confirmMssg=driver.findElement(By.cssSelector(".hero-primary")).getText();
	 Assert.assertTrue(confirmMssg.equalsIgnoreCase("THANKYOU FOR THE ORDER."));
	 driver.close();
	 
	}

}
