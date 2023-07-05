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
	MobilesPage mp;

	public WebDriver driver;

	public MobilesPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(css = ".page-title")
	private WebElement mobilePageTitle;

	@FindBy(xpath = "//div[@class='category-products']/div[@class='toolbar']//select[@title='Sort By']")
	private WebElement sortByDropdown;

	@FindBy(xpath = "//h2/a")
	private List<WebElement> productNamesWE;
	
	@FindBy(css = "button[title='Compare']")
	private WebElement compareBtn;
	
	@FindBy(css = "button[title = 'Close Window']")
	public WebElement closeChildWindow;

	String cmnProdDetailsXpath = "parent::h2/following-sibling::div";

	By listPagePrice = By.xpath(cmnProdDetailsXpath + "//span[@class='price']");

	By addToCartBtn = By.xpath(cmnProdDetailsXpath + "/button");
	By addToCompare = By.xpath(cmnProdDetailsXpath + "//a[@class='link-compare']");

	public String getMobilePageTitle() {
		String mobileMenuPageTitle = mobilePageTitle.getText();
		return mobileMenuPageTitle;
	}

	public void selectByVisText(String dropdownText) {
		Select select = new Select(sortByDropdown);
		select.selectByVisibleText(dropdownText);

	}

	public List<String> getProductsNames() {
		List<String> productNames = new ArrayList<String>();
		productNamesWE.forEach(var -> productNames.add(var.getText()));
		return productNames;
	}

	public String getListPagePrice(String productName) {
		String productPrice = getProdNameWE(productName).findElement(listPagePrice).getText();
		return productPrice;

	}

	public ShoppingCartPage getShoppingCartPageObject(String deviceName) {
		getProdNameWE(deviceName).findElement(addToCartBtn).click();
		ShoppingCartPage shoppingCartPage = new ShoppingCartPage(driver);
		return shoppingCartPage;
	}

	public void clickOnCompareBtn(String firstProd, String secondProd) {
		getProdNameWE(firstProd).findElement(addToCompare).click();
		getProdNameWE(secondProd).findElement(addToCompare).click();
		compareBtn.click();
	}
	
	public MyAccountPage getMyAccountPageObject() {
		goToMyAccountTab();
		MyAccountPage myAccountPage = new MyAccountPage(driver);
		return myAccountPage;
	}

}
