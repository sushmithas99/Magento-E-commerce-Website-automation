package EcommerceProject.PageObject;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.Select;

import EcommerceProject.AbstractComponents.AbstractComponent;

public class MobilesPage extends AbstractComponent {

	WebDriver driver;

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
	


	@FindBy(xpath = "/following-sibling::div//span[@class='price']")
	private WebElement price;

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
	
	public String getPrice(String deviceName) {
		String priceOfDevice = null;
		if(getProductsNames().equals(deviceName)) {
			priceOfDevice = price.getText();
			
		}
		return priceOfDevice;
		
		
	}

}