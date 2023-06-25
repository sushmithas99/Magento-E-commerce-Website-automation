package EcommerceProject.AbstractComponents;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class DetailsPage extends AbstractComponent {
	WebDriver driver;

	public DetailsPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(className = "price")
	private WebElement detailsPagePrice;
	
	By detailsPage = By.xpath("../parent::div/preceding-sibling::a");

	public String getDetailsPagePrice(String productName) {
		getProdNameWE(productName).findElement(detailsPage).click();
		return detailsPagePrice.getText();

	}
}
