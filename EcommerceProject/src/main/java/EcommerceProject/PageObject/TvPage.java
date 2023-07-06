package EcommerceProject.PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import EcommerceProject.AbstractComponents.AbstractComponent;

public class TvPage extends AbstractComponent {
	WebDriver driver;

	public TvPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(name = "save_and_share")
	private WebElement shareWishlistBtn;

	By wishList = By.xpath("parent::h2/following::div//a[@class='link-wishlist']");

	public WishListPage getWishlistPageObject(String productName) {
		WebElement prodName = getProdNameWE(productName);
		prodName.findElement(wishList).click();
		shareWishlistBtn.click();
		WishListPage wishListPage = new WishListPage(driver);
		return wishListPage;
	}

}
