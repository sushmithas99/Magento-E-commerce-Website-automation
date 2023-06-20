package EcommerceProject.TestComponents;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.BeforeTest;

import EcommerceProject.PageObject.HomePage;
import EcommerceProject.PageObject.MobilesPage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {
	public WebDriver driver;
	public String URL = "http://live.techpanda.org/";
//	HomePage homePage = new HomePage(driver);
//	ThreadLocal<HomePage> threadLocalHomePage = new ThreadLocal<HomePage>();

	public WebDriver initializeDriver() throws IOException {
		FileInputStream fis = new FileInputStream(
				System.getProperty("user.dir") + "\\src\\main\\java\\EcommerceProject\\Resources\\Data.properties");
		Properties prop = new Properties();
		prop.load(fis);
		String browserName = System.getProperty("browser") != null ? System.getProperty("browser")
				: prop.getProperty("driver");
		if (browserName.equalsIgnoreCase("chrome")) {
			ChromeOptions options = new ChromeOptions();
			if (browserName.contains("headless")) {
				options.addArguments("headless");
			}
			driver = WebDriverManager.chromedriver().capabilities(options).create();

		}
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(5));
		return driver;
	}

//	public HomePage getHomePage() {
//		return threadLocalHomePage.get();
//	}

	@BeforeTest
	public void landingPage() throws IOException, InterruptedException {
		driver = initializeDriver();
		driver.get(URL);
		threadLocalHomePage.set(homePage);

	}

}