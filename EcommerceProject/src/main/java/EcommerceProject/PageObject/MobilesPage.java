package EcommerceProject.PageObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import EcommerceProject.AbstractComponents.AbstractComponent;

public class MobilesPage extends AbstractComponent {

	public WebDriver driver;
	public WebElement productName;

	public MobilesPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = ".page-title")
	private WebElement mobilePageTitle;

	@FindBy(xpath = "//div[@class='category-products']/div[@class='toolbar']//select[@title='Sort By']")
	private WebElement sortByDropdown;

	@FindBy(css = "h2 a")
	private List<WebElement> productNamesWE;

	@FindBy(className = "price")
	private WebElement detailsPagePrice;

	By listPagePrice = By.xpath("parent::h2/following-sibling::div//span[@class='price']");
	By detailsPage = By.xpath("../parent::div/preceding-sibling::a");

	public String getMobilePageTitle() {
		String mobileMenuPageTitle = mobilePageTitle.getText();
		return mobileMenuPageTitle;
	}

	public Select getDropdownSelector() {
		Select select = new Select(sortByDropdown);
		return select;
	}

	public List<String> getProductsNames() {
		List<String> productNames = new ArrayList<String>();
		productNamesWE.forEach(var -> productNames.add(var.getText()));
		return productNames;
	}

	public String getListPagePrice(String deviceName) {

		productName = productNamesWE.stream().filter(s -> s.getText().equalsIgnoreCase(deviceName)).findFirst()
				.orElse(null);
		String productPrice = productName.findElement(listPagePrice).getText();
		return productPrice;

	}

	public String getDetailsPagePrice() {
		productName.findElement(detailsPage).click();
		return detailsPagePrice.getText();
		
	}

}
