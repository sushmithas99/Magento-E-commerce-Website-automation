package EcommerceProject.PageObject;

import javax.xml.xpath.XPath;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import EcommerceProject.AbstractComponents.AbstractComponent;

public class HomePage extends AbstractComponent {
	WebDriver driver;
public String homePageText;
	public HomePage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//div[@class='page-title']/h2")
	private WebElement homePageTitle;
	
	public MobilesPage getHomePageTitle() {
		 homePageText = homePageTitle.getText();
		MobilesPage mobilePage = new MobilesPage(driver);
		return mobilePage ;
	}
}
