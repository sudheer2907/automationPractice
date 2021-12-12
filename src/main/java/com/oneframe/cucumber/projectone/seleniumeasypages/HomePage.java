package com.oneframe.cucumber.projectone.seleniumeasypages;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import com.oneframe.cucumber.oneframebase.utils.LogPrinter;
import com.oneframe.cucumber.oneframebase.utils.WebDriverFactory;

public class HomePage {

  public HomePage() {
    PageFactory.initElements(WebDriverFactory.getDriver(), this);
  }

  @FindBy(xpath = "//a[@class='dropdown-toggle'][contains(text(),'Input Forms')]")
  private WebElement tabInputForms;

  @FindBy(xpath = "//a[@class='dropdown-toggle'][contains(text(),'Progress Bars')]")
  private WebElement tabProgressBars;

  @FindBy(xpath = "//a[@class='dropdown-toggle'][contains(text(),'Table')]")
  private WebElement tabTable;
  
  @FindBy(xpath = "//a[@class='dropdown-toggle'][contains(text(),'List Box')]")
  private WebElement tabListBox;

  @FindBy(xpath = "//a[@class='dropdown-toggle'][contains(text(),'Alerts & Modals')]")
  private WebElement tabAlertsAndModals;

  @FindBy(xpath = "//a[@class='dropdown-toggle'][contains(text(),'Others')]")
  private WebElement tabOthers;

  @FindBy(xpath = "//a[@class='at-cv-button at-cv-lightbox-yesno at-cm-no-button']")
  private WebElement acceptAnAlert;

  public void acceptAnAlert() {
    WebDriverFactory.waitForAnElementToBeVisible(acceptAnAlert, 20);
    WebDriverFactory.clickWebElement(acceptAnAlert);
  }

  /**
   * Click on tab.
   *
   * @param tabString - tab name.
   * @throws Exception - exception if occured any.
   * @author sudheer.singh
   */
  public void clickonTab(String tabString) {
    switch (tabString) {
      case "Input Forms":
        WebDriverFactory.clickWebElement(tabInputForms);
        break;
      case "Progress Bars":
        WebDriverFactory.clickWebElement(tabProgressBars);
        break;
      case "Table":
        WebDriverFactory.clickWebElement(tabTable);
        break;
      case "Alerts & Modals":
        WebDriverFactory.clickWebElement(tabAlertsAndModals);
        break;
      case "List Box":
        WebDriverFactory.clickWebElement(tabListBox);
        break;
      case "Others":
        WebDriverFactory.clickWebElement(tabOthers);
        break;
      default:
        LogPrinter.printLog("Wrong Sub tab name is provided.");
    }
  }
}
