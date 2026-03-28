package rahulshettyacademy.AbstractComponents;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import rahulshettyacademy.pageobjects.CartPage;
import rahulshettyacademy.pageobjects.OrderPage;

public class AbstractComponent {
	WebDriver driver;
	
	public AbstractComponent(WebDriver driver) {
		this.driver=driver;
		PageFactory.initElements(driver, this);
	}
	@FindBy(css="[routerlink*='cart']")
	WebElement cartHeader;
	
	@FindBy(css="[routerlink*='myorders']")
	WebElement orderHeader;
	
	@FindBy(css = ".ngx-spinner-overlay")
	WebElement spinner;

	
	//driver.findElement(By.cssSelector("[routerlink*='cart']")).click();

	public void waitForElementToAppear(By findBy) {	
     WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
    wait.until(ExpectedConditions.visibilityOfElementLocated(findBy));
    
	}
	
	public void waitForWebElementToAppear(WebElement findBy) {	
	     WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
	    wait.until(ExpectedConditions.visibilityOf(findBy));
	    
		}
		
	public CartPage goToCartPage() throws InterruptedException {
		waitForElementToDisappear(spinner);
		cartHeader.click();
		CartPage cartPage= new CartPage(driver);
		return cartPage; 
	}
	
	public OrderPage goToOrdersPage() {
		orderHeader.click();
		OrderPage orderPage= new OrderPage(driver);
		return orderPage; 
	}
	
	
	
	
	public void waitForElementToDisappear(WebElement ele) throws InterruptedException {
		Thread.sleep(1000);
//		WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
//        wait.until(ExpectedConditions.invisibilityOf(ele));
	}
	 public void scrollPageDown() {
	        JavascriptExecutor js = (JavascriptExecutor) driver;
	        js.executeScript("window.scroll(0,700);");
	    }
	//  For By locator
	 public void waitForElementToBeClickable(By findBy) {
	     WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
	     wait.until(ExpectedConditions.elementToBeClickable(findBy));
	 }

	 //  For WebElement
	 public void waitForElementToBeClickable(WebElement element) {
	     WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
	     wait.until(ExpectedConditions.elementToBeClickable(element));
	 }

}
