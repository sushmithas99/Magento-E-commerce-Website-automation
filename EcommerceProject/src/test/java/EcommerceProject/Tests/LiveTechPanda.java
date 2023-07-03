package EcommerceProject.Tests;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.util.Collections;
import java.util.List;
import org.testng.Assert;
import org.testng.annotations.Test;
import EcommerceProject.PageObject.DetailsPage;
import EcommerceProject.PageObject.MobilesPage;
import EcommerceProject.PageObject.ShoppingCartPage;
import EcommerceProject.TestComponents.BaseTest;

public class LiveTechPanda extends BaseTest {
	String productName = "Sony Xperia";
	String secondProdName = "IPhone";
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
		mobilePage.selectByVisText("Name");
		List<String> sortedProductsNames = mobilePage.getProductsNames();
		assertTrue(productNames.equals(sortedProductsNames));
	}

	@Test
	public void verifyCostOfTheProductIsSameInListPageAndDetailsPage() {
		MobilesPage mobilePage = homePage.getMobilePageObject();
		mobilePage.goToMobilesTab();
		String priceInListPage = mobilePage.getListPagePrice(productName);
		DetailsPage detailsPage = new DetailsPage(driver);
		String detailsPagePrice = detailsPage.getDetailsPagePrice(productName);
		assertEquals(priceInListPage, detailsPagePrice);

	}

	@Test
	public void verifyUserCannotAddMoreNumProductsThanTheProductAvailableInStore() throws InterruptedException {
		MobilesPage mobilePage = homePage.getMobilePageObject();
		mobilePage.goToMobilesTab();
		ShoppingCartPage shoppingCartPage = mobilePage.getShoppingCartPageObject(productName);
		String expectedErrorMSG = shoppingCartPage.getErrorMsgOnAddingMoreQTY(qty, productName);
		assertEquals(actualErrorMsg, expectedErrorMSG);
		String expectedEmptyCartMessage = shoppingCartPage.getEmptyMessage();
		assertEquals(actualEmptyCartMsg, expectedEmptyCartMessage);

	}

	@Test
	public void verifyUserIsAbleToCompareTwoProducts() throws InterruptedException {
		MobilesPage mobilePage = homePage.getMobilePageObject();
		mobilePage.goToMobilesTab();
		mobilePage.clickOnCompareBtn(productName, secondProdName);
		getwindowsId();
		driver.switchTo().window(childWindow);
		List<String> productsNames = mobilePage.getProductsNames();
		assertTrue(productsNames.get(0).equalsIgnoreCase(productName));
		assertTrue(productsNames.get(1).equalsIgnoreCase(secondProdName));
		mobilePage.closeChildWindow.click();
		driver.switchTo().window(parentWindow);
	}
	
	@Test
	public void verifyUserCanCreateAccountAndShareWishList() {
		MobilesPage mobilePage = homePage.getMobilePageObject();
	}
}
