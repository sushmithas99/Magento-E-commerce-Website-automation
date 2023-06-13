package EcommerceProject.Tests;

import org.testng.Assert;
import org.testng.annotations.Test;

import EcommerceProject.PageObject.HomePage;
import EcommerceProject.TestComponents.BaseTest;

public class LiveTechPanda extends BaseTest {
@Test
public void verifyItemsInMobileListPageIsSortedByName() throws Exception{
	HomePage homePage = landingPage();
	String pageTitle = homePage.getHomePageTitle();
	Assert.assertEquals("THIS IS DEMO SITE FOR   ",pageTitle);
}
}