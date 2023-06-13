package EcommerceProject.TestComponents;

import java.io.FileInputStream;
import java.io.IOException;
import java.time.Duration;
import java.util.Properties;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.testng.annotations.BeforeTest;

import EcommerceProject.PageObject.HomePage;
import io.github.bonigarcia.wdm.WebDriverManager;

public class BaseTest {
	public WebDriver driver;
	public String URL = "http://live.techpanda.org/";

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
	@BeforeTest(alwaysRun = true)
	public HomePage landingPage() throws IOException, InterruptedException {
		 driver = initializeDriver();
		 
		 HomePage homePage = new HomePage(driver);
		 driver.get(URL);
		 Thread.sleep(5000);
		 return homePage;
	}

}