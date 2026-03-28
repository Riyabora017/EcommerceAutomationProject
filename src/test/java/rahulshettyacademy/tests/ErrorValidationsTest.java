package rahulshettyacademy.tests;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.AssertJUnit;
import org.testng.annotations.Test;

import com.aventstack.extentreports.ExtentReports;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

import rahulshettyacademy.TestComponents.BaseTest;
import rahulshettyacademy.TestComponents.Retry;
import rahulshettyacademy.pageobjects.CartPage;
import rahulshettyacademy.pageobjects.ProductCatalogue;

public class ErrorValidationsTest extends BaseTest {
	
	@Test(groups= {"ErrorHandling"},retryAnalyzer=Retry.class)
	public void LoginErrorValidation() throws InterruptedException, IOException {
	    landingPage.loginApplication("Borariya@gmail.com","IamKi000");    
		Assert.assertEquals("Incorrect email password.", landingPage.getErrorMessage());
    }
	
	
	
	@Test
	public void ProductErrorValidation() throws InterruptedException, IOException {
		
		String productName = "ZARA COAT 3";   
    ProductCatalogue productcatalogue = landingPage.loginApplication("Borariya@gmail.com","IamKing@000");    
    
    List<WebElement>products=productcatalogue.getProductList();
    productcatalogue.addProductToCart(productName);
     CartPage cartPage = productcatalogue.goToCartPage();
    
    Boolean match = cartPage.VerifyProductDisplay("ZARA COAT 33");
	Assert.assertFalse(match);
	
	}



}
