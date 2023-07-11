package EcommerceProject.Tests;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.Keys;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.Pdf;
import org.openqa.selenium.PrintsPage;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.print.PrintOptions;
import org.testng.Assert;
import org.testng.annotations.Test;

import EcommerceProject.PageObject.CheckoutPage;
import EcommerceProject.PageObject.DashboardPage;
import EcommerceProject.PageObject.DetailsPage;
import EcommerceProject.PageObject.MobilesPage;
import EcommerceProject.PageObject.MyAccountPage;
import EcommerceProject.PageObject.MyOrdersPage;
import EcommerceProject.PageObject.ShoppingCartPage;
import EcommerceProject.PageObject.TvPage;
import EcommerceProject.PageObject.WishListPage;
import EcommerceProject.TestComponents.BaseTest;

public class LiveTechPanda extends BaseTest {
	String productName = "Sony Xperia";
	String secondProdName = "IPhone";
	String qty = "1000";
	String actualHomePageTitle = "THIS IS DEMO SITE FOR   ";
	String actualMobilePageTitle = "MOBILE";
	String actualErrorMsg = "Some of the products cannot be ordered in requested quantity.";
	String actualEmptyCartMsg = "SHOPPING CART IS EMPTY";
	String fnameV = "ecbntryJDH";
	String lnameV = "s";
	String email = "efxftyu3@tpg.com.au";
	String shareWishlistEmail = "efxfchb3@tpg.com.au";
	String pswdV = "G@6bxiJGHYpe5Dkg";
	String expectedSuccfulMsg = "Thank you for registering with Main Website Store.";
	String tvName = "LG LCD";
	
	String address = "ABC";
	String city = "NewYork";
	String zipcode = "542836";
	String country = "United States";
	String state = "New York";
	String phNum = "1234567891";

	
	WishListPage wishlistPage;
	

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
	public void verifyUserCanCreateAccountAndShareWishList() throws InterruptedException {
		MobilesPage mobilePage = homePage.getMobilePageObject();
//		Thread.sleep(1000);
		MyAccountPage myAccountPage= mobilePage.goToMyAccount();
		myAccountPage.createAccount(fnameV, lnameV, email, pswdV, pswdV);
//		Thread.sleep(100000);
		String actualSuccfulRegMsg = myAccountPage.getSuccfulRegMsg();
		assertEquals(actualSuccfulRegMsg, expectedSuccfulMsg);
		TvPage tvPage = new TvPage(driver);
		tvPage.goToTvTab();
		wishlistPage = tvPage.getWishlistPageObject(tvName);
		String actualSuccessMsg = wishlistPage.getsuccessWLShareMsg(shareWishlistEmail);
		System.out.println(actualSuccessMsg);
	}
	@Test(dependsOnMethods = {"verifyUserCanCreateAccountAndShareWishList"})
	public void verifyUserAbleToPurchaseUsingRegEmail() throws InterruptedException {
		MobilesPage mobilePage = homePage.getMobilePageObject();
		MyAccountPage myAccountPage= mobilePage.goToMyAccount();
		DashboardPage dashboardPage = myAccountPage.login(email, pswdV);
		dashboardPage.clickOnWishlistLink();
		ShoppingCartPage shoppingCartPage = wishlistPage.clickOnAddToCart();
		CheckoutPage checkoutPage = shoppingCartPage.proceedToCheckout(country,state,zipcode);
		String actualGrandTotalCost = shoppingCartPage.actualGrandTotalCost;
		String expectedGrandTotalCost = shoppingCartPage.expectedGrandTotalCost;	
		assertEquals(actualGrandTotalCost, expectedGrandTotalCost);
		String actualOrderConfirmMsg = checkoutPage.placeOrder(address,city,zipcode,state,country,phNum);
		System.out.println(actualOrderConfirmMsg);
		
		
	}
	@Test(dependsOnMethods = {"verifyUserAbleToPurchaseUsingRegEmail"})
	public void verifyUserAbleToSaveOrderHistoryAsPDF() throws InterruptedException, IOException {
		MobilesPage mobilePage = homePage.getMobilePageObject();
		MyAccountPage myAccountPage= mobilePage.goToMyAccount();
		DashboardPage dashboardPage = myAccountPage.login(email, pswdV);
		MyOrdersPage myOrderPage = dashboardPage.clickOnMyOrdersLink();
		myOrderPage.clickOnPrintOrderLink();
		getwindowsId();
		driver.switchTo().window(childWindow);
		Actions action = new Actions(driver);
		Thread.sleep(5000);
		action.sendKeys(Keys.ESCAPE).build().perform();
		Pdf pdf = ((PrintsPage) driver).print(new PrintOptions());
		Files.write(Paths.get("./OrderPage.pdf"),OutputType.BYTES.convertFromBase64Png(pdf.getContent()));
		
		
		
		
	}
}
