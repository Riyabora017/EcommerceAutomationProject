package rahulshettyacademy.tests;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.AssertJUnit;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import rahulshettyacademy.TestComponents.BaseTest;
import rahulshettyacademy.pageobjects.CartPage;
import rahulshettyacademy.pageobjects.CheckoutPage;
import rahulshettyacademy.pageobjects.ConfirmationPage;
import rahulshettyacademy.pageobjects.OrderPage;
import rahulshettyacademy.pageobjects.ProductCatalogue;

public class SubmitOrderTest extends BaseTest {
	String productName = "ZARA COAT 3";
	
	@Test(dataProvider="getData",groups= {"Purchase"})
	public void submitOrder(HashMap<String,String>input) throws InterruptedException, IOException {
	
		
		//LandingPage landingPage= launchApplication();
    
    ProductCatalogue productcatalogue = landingPage.loginApplication(input.get("email"),input.get("password"));    
    
    List<WebElement>products=productcatalogue.getProductList();
    productcatalogue.addProductToCart(input.get("product"));
     CartPage cartPage = productcatalogue.goToCartPage();
    
    Boolean match = cartPage.VerifyProductDisplay(input.get("product"));
	Assert.assertTrue(match);
	CheckoutPage checkoutPage = cartPage.goToCheckout();

	checkoutPage.selectCountry("india");
	ConfirmationPage confirmationPage = checkoutPage.submitOrder();	
    String ConfirmMessage =confirmationPage.getConfirmationMessage();
    AssertJUnit.assertTrue(ConfirmMessage.equalsIgnoreCase("Thankyou for the order."));
	}
   
	

	 @Test(dependsOnMethods= {"submitOrder"})
	 public void OrderHistory() {
	     // ZARA COAT 3

	     ProductCatalogue productcatalogue = landingPage.loginApplication("Borariya@gmail.com","IamKing@000");    
	     OrderPage ordersPage = productcatalogue.goToOrdersPage();
	     Assert.assertTrue(ordersPage.VerifyOrderDisplay(productName));
	 }
	 
	
	 //ExtentReports
	 
	 
	 
	 @DataProvider
	 public Object[][] getData() throws IOException {
//		 
		    
		    List<HashMap<String, String>> data = getJsonDataTOMap(System.getProperty("user.dir") + "\\src\\test\\java\\rahulshettyacademy\\data\\PurchaseOrder.json");
		    return new Object[][] { {data.get(0)}, {data.get(1)} };
	 }


	 //@DataProvider
	// public Object[][] getData() {
	  // return new Object[][] { {"Borariya@gmail.com","IamKing@000","ZARA COAT 3"}, {"rashi1@gmail.com","Rashi@000","ADIDAS ORIGINAL"} };
	 
	 //HashMap<String,String> map = new HashMap<String,String>();
//	 map.put("email","Borariya@gmail.com");
//	 map.put("password","IamKing@000");
//	 map.put("product","ZARA COAT 3");
//	 
//	 HashMap<String,String> map1 = new HashMap<String,String>();
//	    map1.put("email","rashi1@gmail.com");
//	    map1.put("password","Rashi@000");
//	    map1.put("product","ADIDAS ORIGINAL");
	 
	 
	 
}
