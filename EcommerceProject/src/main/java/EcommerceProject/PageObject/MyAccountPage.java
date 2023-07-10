package EcommerceProject.PageObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import EcommerceProject.AbstractComponents.AbstractComponent;

public class MyAccountPage extends AbstractComponent {
	WebDriver driver;

	public MyAccountPage(WebDriver driver) {
		super(driver);
		this.driver = driver;
		PageFactory.initElements(driver, this);
	}

	@FindBy(xpath = "//span[text()='Create an Account']")
	private WebElement createAnAccountBtn;

	@FindBy(id = "firstname")
	private WebElement fname;

	@FindBy(id = "lastname")
	private WebElement lname;

	@FindBy(id = "email_address")
	private WebElement email;

	@FindBy(id = "password")
	private WebElement password;

	@FindBy(id = "confirmation")
	private WebElement confirmPassword;
	
	@FindBy(css = "button[title='Register']")
	private WebElement registerBtn;
	
	@FindBy(xpath = "//li//span")
	private WebElement successRegMsg;
	
	@FindBy(name = "login[username]")
	private WebElement loginEmail;
	
	@FindBy(name = "login[password]")
	private WebElement loginPwd;
	
	@FindBy(name = "send")
	private WebElement loginBtn;

	public void createAccount(String fnameV, String lnameV, String emailV, String pswdV, String confirmPswdV) {
		createAnAccountBtn.click();
		fname.sendKeys(fnameV);
		lname.sendKeys(lnameV);
		email.sendKeys(emailV);
		password.sendKeys(pswdV);
		confirmPassword.sendKeys(confirmPswdV);
		registerBtn.click();
		
	}
	public String getSuccfulRegMsg() {
		return successRegMsg.getText();
	}
	public DashboardPage login(String email, String password) {
		loginEmail.sendKeys(email);
		loginPwd.sendKeys(password);
		loginBtn.click();
		DashboardPage dashboardPage = new DashboardPage(driver);
		return dashboardPage;
		
	}
}
