package EcommerceProject.Tests;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.Collections;
import java.util.List;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

import EcommerceProject.PageObject.HomePage;
import EcommerceProject.PageObject.MobilesPage;
import EcommerceProject.TestComponents.BaseTest;

public class LiveTechPanda extends BaseTest {
	String productName = "Sony Xperia";
	HomePage homep;
	MobilesPage mp;
	@Test
	public void verifyItemsInMobileListPageIsSortedByName() throws Exception {
		 homep = getHomePage();
		Assert.assertEquals("THIS IS DEMO SITE FOR   ",homep.getHomePageTitle());
		mp.goToMobilesTab();
		String mobilePageTitle = mp.getMobilePageTitle();
		assertEquals("MOBILE", mobilePageTitle);
		List<String> productNames = mp.getProductsNames();
		Collections.sort(productNames);
		Select select = mp.getDropdownSelector();
		select.selectByIndex(1);
		List<String> sortedProductsNames =  mp.getProductsNames();
		assertTrue(productNames.equals(sortedProductsNames));
		Thread.sleep(1000);
	}
//	@Test
//	public void verifyCostOfTheProductIsSameInListPageAndDetailsPage() {
//		 mobilePage1 = getHomePage().getMobilePage();
//		 mobilePage1.goToMobilesTab();
//		 String price = mobilePage1.getPrice(productName);
//		 mobilePage1.clickOnAddToCartBtn();
//		
//	}
}
