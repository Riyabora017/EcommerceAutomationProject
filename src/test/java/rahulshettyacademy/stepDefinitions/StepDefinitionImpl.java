package rahulshettyacademy.stepDefinitions;

import java.io.IOException;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.testng.Assert;
import org.testng.AssertJUnit;

import io.cucumber.java.PendingException;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import rahulshettyacademy.TestComponents.BaseTest;
import rahulshettyacademy.pageobjects.CartPage;
import rahulshettyacademy.pageobjects.CheckoutPage;
import rahulshettyacademy.pageobjects.ConfirmationPage;
import rahulshettyacademy.pageobjects.LandingPage;
import rahulshettyacademy.pageobjects.ProductCatalogue;

public class StepDefinitionImpl extends BaseTest {
    
    public LandingPage landingPage; 
    public ProductCatalogue productcatalogue;
    public ConfirmationPage confirmationPage;
    public CheckoutPage checkoutPage;
    @Given("I landed on Ecommerce Page")
    public void I_landed_on_Ecommerce_Page() throws IOException {
       
    	landingPage = launchApplication();
    }

    @Given("^Logged in with username (.+) and password (.+)$")
    public void logged_in_username_and_password(String username, String password) {
        productcatalogue = landingPage.loginApplication(username, password);   
    }
                
    @When("^I add product (.+) to Cart$")
    public void i_add_product_to_cart(String productName) throws InterruptedException {
       List<WebElement> products = productcatalogue.getProductList();
       productcatalogue.addProductToCart(productName.replace("\"", "").trim());
    }
    
    @When("^Checkout (.+) and submit the order$")
    public void checkout_submit_order(String productName) throws InterruptedException {
    	   CartPage cartPage = productcatalogue.goToCartPage();
    	    
    	    Boolean match = cartPage.VerifyProductDisplay(productName);
    		Assert.assertTrue(match);
    		checkoutPage = cartPage.goToCheckout();

    		checkoutPage.selectCountry("india");
    		confirmationPage = checkoutPage.submitOrder();	
    }
    
    @Then("{string} message is displayed on ConfirmationPage")
    public void message_is_displayed_on_ConfirmationPage(String string){
        String confirmMessage = confirmationPage.getConfirmationMessage();
        Assert.assertTrue(confirmMessage.equalsIgnoreCase(string));
        driver.close();  
       }
  
    @Then("{string} message is displayed")
    public void something_message_is_displayed(String expectedMessage) throws Throwable {
        
    	Assert.assertEquals( expectedMessage, landingPage.getErrorMessage());
        driver.close(); 
    }
}
