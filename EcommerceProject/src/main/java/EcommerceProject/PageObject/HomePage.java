package EcommerceProject.PageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import EcommerceProject.AbstractComponents.AbstractComponent;

public class HomePage extends AbstractComponent {
	public WebDriver driver;
	
	public HomePage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
//
	@FindBy(xpath = "//div[@class='page-title']/h2")
	private WebElement homePageTitle;
	
	public String getHomePageTitle() {
		String homePageText = homePageTitle.getText();
		return homePageText;
	}

	public MobilesPage getMobilePageObject() {
//		
		MobilesPage mobilePage = new MobilesPage(driver);
		return mobilePage;
//		threadLocalDriver.set(mobilePage);
//		return homePageText;

	}
}

//\
