package EcommerceProject.PageObject;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import EcommerceProject.AbstractComponents.AbstractComponent;

public class MyOrdersPage extends AbstractComponent {
	WebDriver driver;

	public MyOrdersPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath =  "//a[text()='View Order']")
	private WebElement viewOrderLink;
	
	@FindBy(xpath =  "//a[text()='Print Order']")
	private WebElement printOrderLink;

	public void clickOnPrintOrderLink() {
		viewOrderLink.click();
		printOrderLink.click();
		
	}
	
	
	

}
