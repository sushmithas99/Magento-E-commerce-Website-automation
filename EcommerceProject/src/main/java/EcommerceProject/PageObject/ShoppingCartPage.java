package EcommerceProject.PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import EcommerceProject.AbstractComponents.AbstractComponent;

public class ShoppingCartPage extends AbstractComponent {
	WebDriver driver;

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


}
