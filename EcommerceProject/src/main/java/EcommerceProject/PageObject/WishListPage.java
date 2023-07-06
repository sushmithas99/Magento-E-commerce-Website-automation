package EcommerceProject.PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import EcommerceProject.AbstractComponents.AbstractComponent;

public class WishListPage extends AbstractComponent {
	WebDriver driver;

	public WishListPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(id = "email_address")
	private WebElement emailAddressTextBox;
	
	@FindBy(xpath =  "//button[@title='Share Wishlist']")
	private WebElement shareWishListBtn;
	
	@FindBy(xpath =  "//li[@class='success-msg']//span")
	private WebElement successMsg;
	
	
	
	By wishList = By.xpath("parent::h2/following::div//a[@class='link-wishlist']");

	public String getsuccessWLShareMsg(String email) {
		emailAddressTextBox.sendKeys(email);
		shareWishListBtn.click();
		return successMsg.getText();
		
	}

}
