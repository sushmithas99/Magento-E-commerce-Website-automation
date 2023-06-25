package EcommerceProject.AbstractComponents;

import java.time.Duration;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AbstractComponent {
	public WebDriverWait wait;
	WebDriver driver;

	public AbstractComponent(WebDriver driver) {
		this.driver = driver;
		PageFactory.initElements(driver, this);

	}

	@FindBy(xpath = "//a[text()='Mobile']")
	private WebElement mobile;

	@FindBy(xpath = "//h2/a")
	private List<WebElement> productNamesWE;

	public void goToMobilesTab() {
		mobile.click();
	}

	public void waitForElementToApear(By locator) {
		wait = new WebDriverWait(driver, Duration.ofSeconds(30));
		wait.until(ExpectedConditions.refreshed(ExpectedConditions.elementToBeClickable(locator)));
	}

	public WebElement getProdNameWE(String devicename) {
		WebElement productName = productNamesWE.stream().filter(s -> s.getText().equalsIgnoreCase(devicename))
				.findFirst().orElse(null);
		return productName;

	}
}
