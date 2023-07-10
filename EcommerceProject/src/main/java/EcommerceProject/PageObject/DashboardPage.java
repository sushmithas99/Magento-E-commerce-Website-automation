package EcommerceProject.PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import EcommerceProject.AbstractComponents.AbstractComponent;

public class DashboardPage extends AbstractComponent {
	WebDriver driver;

	public DashboardPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath =  "//li/a[text()='My Wishlist']")
	private WebElement myWishlistLink;

	public void clickOnWishlistLink() {
		myWishlistLink.click();
	}

}
