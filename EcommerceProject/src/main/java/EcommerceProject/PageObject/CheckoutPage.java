package EcommerceProject.PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import EcommerceProject.AbstractComponents.AbstractComponent;

public class CheckoutPage extends AbstractComponent {
	WebDriver driver;

	public CheckoutPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(id = "billing:street1")
	private WebElement address;
	
	@FindBy(id = "billing:city")
	private WebElement city;
	
	@FindBy(id = "billing:postcode")
	private WebElement zipcode;
	
	@FindBy(id = "billing:region_id")
	private WebElement stateSelectorWE;
	
	@FindBy(id = "billing:country_id")
	private WebElement countrySelectorWE;
	
	@FindBy(id = "billing:telephone")
	private WebElement phNum;
	
	@FindBy(css = "#billing-buttons-container button")
	private WebElement billingInfoContinueBtn;
	
	@FindBy(css = "#shipping-method-buttons-container button")
	private WebElement shippingMethodContinueBtn;
	
	@FindBy(css = "#payment-buttons-container button")
	private WebElement paymentInfoContinueBtn;
	
	@FindBy(css = "#review-buttons-container button")
	private WebElement placeorderBtn;
	
	@FindBy(xpath = "//div[@class='col-main']/p")
	private WebElement orderIdText;
	
	@FindBy(id = "p_method_checkmo")
	private WebElement paymentMethodRadioBtn;
	
	By wishList = By.xpath("parent::h2/following::div//a[@class='link-wishlist']");

	public String placeOrder(String addressV,String cityV,String zipcodeV, String stateV, String countryV, String phNumV ) {
		address.sendKeys(addressV);
		city.sendKeys(cityV);
		zipcode.sendKeys(zipcodeV);
		Select stateselector = new Select(stateSelectorWE);
		stateselector.selectByVisibleText(stateV);
		Select countrySelector = new Select(countrySelectorWE);
		countrySelector.selectByVisibleText(countryV);
		phNum.sendKeys(phNumV);
		billingInfoContinueBtn.click();
		shippingMethodContinueBtn.click();
		paymentMethodRadioBtn.click();
		paymentInfoContinueBtn.click();
		placeorderBtn.click();
		return orderIdText.getText();


	}

}
