package EcommerceProject.PageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import EcommerceProject.AbstractComponents.AbstractComponent;

public class MyAccountPage extends AbstractComponent{
	WebDriver driver;
	public MyAccountPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}
		@FindBy(xpath = "//span[text()='Create an Account']")
		private WebElement createAnAccountBtn;
		
		
}
