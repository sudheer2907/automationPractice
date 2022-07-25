package oneframe.cucumber.stepdefinitions;

import com.oneframe.cucumber.base.utils.WebDriverFactory;
import com.oneframe.cucumber.base.utils.seleniumeasypages.HomePage;

import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;


public class TestHomePage {

  HomePage homePage;

  @Given("^I launch and login to the application$")
  public void i_launch_and_login_to_the_application() throws Exception {
    WebDriverFactory.openApplication();
    homePage = new HomePage();
    homePage.acceptAnAlert();
    WebDriverFactory.waitForPageToLoad(30);
    homePage = new HomePage();
  }

  @And("^I click on tab (.*)$")
  public void i_click_on_tab(String tabNameString) throws Exception {
    homePage.clickonTab(tabNameString);
  }
}
