package rahulshettyacademy.tests;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import io.github.bonigarcia.wdm.WebDriverManager;
import rahulshettyacademy.pageobjects.LandingPage;

public class StandAloneTest {

	public static void main(String[] args) {
		String productName = "ZARA COAT 3";
	WebDriverManager.chromedriver().setup();
    WebDriver driver= new ChromeDriver();
    driver.manage().window().maximize();
    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
    driver.get("https://rahulshettyacademy.com/client");
    LandingPage landingPage = new LandingPage(driver);
    //Enter email,password and enter button
    driver.findElement(By.id("userEmail")).sendKeys("Borariya@gmail.com");
    driver.findElement(By.id("userPassword")).sendKeys("IamKing@000");
    driver.findElement(By.id("login")).click();
    //wait 
	WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
    
	wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".mb-3")));
    List<WebElement> products =driver.findElements(By.cssSelector(".mb-3"));
    
	WebElement prod = products.stream().filter(product->product.findElement(By.cssSelector("b")).getText().equals(productName)).findFirst().orElse(null);
	prod.findElement(By.cssSelector(".card-body button:last-of-type")).click();
	//wait until message appear products add to cart id:toast-container

	wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector("#toast-container")));
	
	wait.until(ExpectedConditions.invisibilityOf(driver.findElement(By.cssSelector(".ng-animating"))));
	driver.findElement(By.cssSelector("[routerlink*='cart']")).click();
	List<WebElement>cartProducts=driver.findElements(By.cssSelector(".cartSection h3"));
	Boolean match = cartProducts.stream().anyMatch(cartProduct-> cartProduct.getText().equalsIgnoreCase(productName));
	Assert.assertTrue(match);
	
	
	JavascriptExecutor js = (JavascriptExecutor)driver;
	js.executeScript("window.scroll(0,700);");
	wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(".totalRow button")));
	driver.findElement(By.cssSelector(".totalRow button")).click();

	Actions a = new Actions(driver);
	WebElement element=driver.findElement(By.cssSelector("input[placeholder='Select Country']"));
    a.sendKeys(element,"india").build().perform();
    wait.until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(".ta-results")));
    driver.findElement(By.xpath("(//button[contains(@class,'ta-item')])[2]")).click();
    //enter submit button
    driver.findElement(By.cssSelector(".action__submit")).click();
    String ConfirmMessage = driver.findElement(By.cssSelector(".hero-primary")).getText();
    Assert.assertTrue(ConfirmMessage.equalsIgnoreCase("Thankyou for the order."));
    driver.quit();
	}

}
