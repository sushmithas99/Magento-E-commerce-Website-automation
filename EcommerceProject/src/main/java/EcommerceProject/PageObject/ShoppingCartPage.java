package EcommerceProject.PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import EcommerceProject.AbstractComponents.AbstractComponent;

public class ShoppingCartPage extends AbstractComponent {
	WebDriver driver;
	public String shipCost;
	public String actualGrandTotalCost;
	public String expectedGrandTotalCost;

	public ShoppingCartPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = "li.error-msg span")
	private WebElement errorMsg;

	@FindBy(id = "empty_cart_button")
	private WebElement emptyCart;

	@FindBy(css = "div.page-title h1")
	private WebElement emptyMsg;

	@FindBy(id = "country")
	private WebElement countrySelectWE;

	@FindBy(id = "region_id")
	private WebElement stateSelectWE;

	@FindBy(id = "postcode")
	private WebElement postcode;

	@FindBy(xpath = "//button[@title='Estimate']")
	private WebElement estimate;

	@FindBy(css = "label span[class='price']")
	private WebElement shippingCostWE;

	@FindBy(id = "s_method_flatrate_flatrate")
	private WebElement shippingCostRadioBtn;

	@FindBy(name = "do")
	private WebElement updateShippingCostBtn;

	@FindBy(xpath = "//td/following-sibling::td/span[@class='price']")
	private WebElement subtotal;

	@FindBy(xpath = "//td/strong/span")
	private WebElement grandTotal;
	
	@FindBy(xpath = "//ul[@class='checkout-types bottom']//button[@title='Proceed to Checkout']")
	private WebElement proccedToCheckout;

	By QTYField = By.xpath("../parent::td/following-sibling::td/input[@title='Qty']");
	By updateBtn = By.xpath("following-sibling::button");

	public String getErrorMsgOnAddingMoreQTY(String numOfQTY, String deviceName) throws InterruptedException {

//		driver.navigate().refresh();
		WebElement QTYTextBox = getProdNameWE(deviceName).findElement(QTYField);
		QTYTextBox.clear();
		QTYTextBox.sendKeys(numOfQTY);
		QTYTextBox.findElement(updateBtn).click();
		String errorMSG = errorMsg.getText();
		return errorMSG;

	}

	public String getEmptyMessage() {
		emptyCart.click();
		return emptyMsg.getText();
	}

	public CheckoutPage proceedToCheckout(String countryName, String stateName, String postCode) throws InterruptedException {
		Thread.sleep(1000);
		Select selectCountry = new Select(countrySelectWE);
		selectCountry.selectByVisibleText(countryName);
		Thread.sleep(1000);
		Select selectState = new Select(stateSelectWE);
		selectState.selectByVisibleText(stateName);
		postcode.sendKeys(postCode);
		estimate.click();
		shipCost = shippingCostWE.getText();
		String split = shipCost.split("\\$")[1];
		double shippingCost = Double.parseDouble(split);
		shippingCostRadioBtn.click();
		updateShippingCostBtn.click();
		double subtotalCost = Double.parseDouble((subtotal.getText()).split("\\$")[1]);
		actualGrandTotalCost = "$" + String.valueOf(shippingCost + subtotalCost) + "0";
		expectedGrandTotalCost = grandTotal.getText();
		CheckoutPage checkoutPage = new CheckoutPage(driver);
		proccedToCheckout.click();
		return checkoutPage;

	}

}
