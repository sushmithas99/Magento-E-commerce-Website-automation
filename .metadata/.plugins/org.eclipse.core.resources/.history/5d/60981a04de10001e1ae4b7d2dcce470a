package EcommerceProject.Tests;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.Collections;
import java.util.List;

import org.openqa.selenium.support.ui.Select;
import org.testng.Assert;
import org.testng.annotations.Test;

import EcommerceProject.PageObject.MobilesPage;
import EcommerceProject.TestComponents.BaseTest;

public class LiveTechPanda extends BaseTest {
	String productName = "Sony Xperia";
	String qty = "1000";
	String actualHomePageTitle = "THIS IS DEMO SITE FOR   ";
	String actualMobilePageTitle = "MOBILE";
	String actualErrorMsg = "Some of the products cannot be ordered in requested quantity.";
	String actualEmptyCartMsg = "SHOPPING CART IS EMPTY";
	@Test
	public void verifyItemsInMobileListPageIsSortedByName() throws Exception {
		MobilesPage mobilePage = homePage.getMobilePageObject();
		Assert.assertEquals(actualHomePageTitle, homePage.getHomePageTitle());
		mobilePage.goToMobilesTab();
		String mobilePageTitle = mobilePage.getMobilePageTitle();
		assertEquals(actualMobilePageTitle, mobilePageTitle);
		List<String> productNames = mobilePage.getProductsNames();
		Collections.sort(productNames);
		Select select = mobilePage.getDropdownSelector();
		select.selectByIndex(1);
		List<String> sortedProductsNames = mobilePage.getProductsNames();
		assertTrue(productNames.equals(sortedProductsNames));
	}

	@Test
	public void verifyCostOfTheProductIsSameInListPageAndDetailsPage() {
		MobilesPage mobilePage = homePage.getMobilePageObject();
		mobilePage.goToMobilesTab();
		String priceInListPage = mobilePage.getListPagePrice(productName);
		String detailsPagePrice = mobilePage.getDetailsPagePrice();
		assertEquals(priceInListPage, detailsPagePrice);
		
	}
	@Test
	public void verifyUserCannotAddMoreNumProductsThanTheProductAvailableInStore() throws InterruptedException {
		MobilesPage mobilePage = homePage.getMobilePageObject();
		mobilePage.goToMobilesTab();
		String expectedErrorMSG = mobilePage.getErrorMsgOnAddingMoreQTY(qty,productName);
		assertEquals(actualErrorMsg, expectedErrorMSG);
		String expectedEmptyCartMessage = mobilePage.getEmptyMessage();
		assertEquals( actualEmptyCartMsg,expectedEmptyCartMessage );
		
		
	}
}
