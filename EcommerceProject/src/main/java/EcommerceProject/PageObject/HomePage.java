package EcommerceProject.PageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import EcommerceProject.AbstractComponents.AbstractComponent;

public class HomePage extends AbstractComponent {
	public WebDriver driver;
	public MobilesPage mobilePage = new MobilesPage(driver);
	public static ThreadLocal<MobilesPage> threadLocalDriver = new ThreadLocal<MobilesPage>();
	public String homePageText;

	public HomePage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//div[@class='page-title']/h2")
	private WebElement homePageTitle;

	public String getHomePageTitle() {
		homePageText = homePageTitle.getText();
//		return mobilePage;
//		threadLocalDriver.set(mobilePage);
		return homePageText;

	}

//	public MobilesPage getMobilePage() {
//		return threadLocalDriver.get();
//	}
}
