package com.oneframe.cucumber.oneframebase.utils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;

import org.apache.commons.io.FileUtils;
import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.TakesScreenshot;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebDriverException;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.WindowType;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.TestException;

public abstract class WebDriverFactory {

  private WebDriverFactory() {
    LogPrinter.printLog("Can not be intantiated");
  }

  private static WebDriver driver;
  static String path;

  /**
   * Launch application and navigate to the base url to start the execution.
   *
   * @author sudheer.singh
   */
  public static void openApplication() {
    String bwoserNameString = Utilities.getEnvironmentProperties("browser");
    path = System.getProperty("user.dir");
    switch (bwoserNameString.toUpperCase()) {
      case "CHROME":
        System.setProperty("webdriver.chrome.driver", path + "/drivers/chromedriver.exe");
        setDriver(new ChromeDriver());
        break;
      case "FIREFOX":
        setDriver(new FirefoxDriver());
        break;
      default:
        Assert.fail("Wrong browser Name provided.");
    }
    getDriver().manage().window().maximize();
    getDriver().get(Utilities.getEnvironmentProperties("baseUrl"));
  }

  /**
   * Wait for Page to Load.
   *
   * @param timeout - page load time out.
   * @author sudheer.singh
   */
  public static void waitForPageToLoad(int timeout) {
    new WebDriverWait(driver, timeout).until((WebDriver driver) -> ((JavascriptExecutor) driver)
        .executeScript("return document.readyState").toString().equals("complete"));
  }

  /**
   * get web driver instance.
   *
   * @return - web driver.
   */
  public static WebDriver getDriver() {
    return driver;
  }

  /**
   * Set web driver.
   *
   * @param driver - web driver.
   */
  public static void setDriver(WebDriver driver) {
    WebDriverFactory.driver = driver;
  }

  /**
   * Close all the window.
   */
  public static void closeWindow() {
    getDriver().close();
    getDriver().quit();
    WebDriverFactory.driver = null;
  }

  /**
   * Highlight web element while ececution.
   *
   * @param element - web element to be highlighted
   * @throws InterruptedException - if in case of interrupted exception.
   * @author sudheer.singh
   */
  public static void highlight(WebElement element) throws InterruptedException {
    JavascriptExecutor js = (JavascriptExecutor) getDriver();
    js.executeScript("arguments[0].setAttribute('style', 'border: 3px solid yellow;');", element);
    TimeUnit.SECONDS.sleep(1);
    js.executeScript("arguments[0].setAttribute('style','border: solid 2px white');", element);
  }

  /**
   * Take screenshots before test case failed.
   *
   * @param driver - web driver.
   * @param screenshotname - name of screenshot.
   * @author sudheer.singh
   */
  public static void captureScreenShot(WebDriver driver, String screenshotname) {
    String path = System.getProperty("user.dir");
    File src = ((TakesScreenshot) driver).getScreenshotAs(OutputType.FILE);
    try {
      FileUtils.copyFile(src,
          new File(path + "/target/test-report/sceenshots/" + screenshotname + ".jpeg"));
    } catch (IOException e) {
      LogPrinter.printLog("Unable to take screenshots");
      LogPrinter.printLog(e.getMessage());
    }
  }

  /**
   * Click on the web element.
   *
   * @param element - web element.
   * @author sudheer.singh
   */
  public static void clickWebElement(WebElement element) {
    if (!element.isEnabled()) {
      throw new WebDriverException(element.getText() + " is not clickable");
    }
    try {
      highlight(element);
      element.click();
    } catch (Exception e) {
      captureScreenShot(WebDriverFactory.getDriver(), element.getText());
    }
  }

  /**
   * enter text into text field.
   *
   * @param element - web element.
   * @param text - text to be entered into text box.
   * @author sudheer.singh
   */
  public static void sendKeys(WebElement element, String text) {
    if (!element.isEnabled()) {
      throw new WebDriverException("WebElement " + element + " is not editabe as It is disabled");
    }
    try {
      highlight(element);
      element.sendKeys(text);
    } catch (Exception e) {
      LogPrinter.printLog(e.getMessage());
    }
  }

  /**
   * Clear text box.
   *
   * @param element - {@link WebElement}
   * @author sudheer.singh
   */
  public void clearTextBox(WebElement element) {
    try {
      element.clear();
    } catch (Exception e) {
      LogPrinter.printLog(
          String.format("The following element could not be cleared: [%s]", element.getText()));
    }
  }

  /**
   * Select element or drop-down value from a drop-down.
   *
   * @param element - web element.
   * @param dropDownValue - drop value to be selected.
   * @author sudheer.singh
   */
  public static void selectElementByvalue(WebElement element, String dropDownValue) {
    if (!element.isEnabled()) {
      throw new WebDriverException("WebElement " + element + " is not editabe as It is disabled");
    }
    try {
      highlight(element);
      Select sel = new Select(element);
      sel.selectByValue(dropDownValue);
    } catch (Exception e) {
      LogPrinter.printLog(e.getMessage());
    }
  }

  /**
   * Wait for an element to be visible.
   *
   * @param element - web element.
   * @param timeOut - waiting time period.
   * @author sudheer.singh
   */
  public static void waitForAnElementToBeVisible(WebElement element, int timeOut) {
    WebDriverWait wait = new WebDriverWait(driver, timeOut);
    wait.until(ExpectedConditions.visibilityOf(element));
  }

  /**
   * Wait for an element to be visible.
   *
   * @param element - web element.
   * @param timeOut - waiting time period.
   * @author sudheer.singh
   */
  public static void waitForAnElementToBeClickable(WebElement element, int timeOut) {
    WebDriverWait wait = new WebDriverWait(driver, timeOut);
    wait.until(ExpectedConditions.elementToBeClickable(element));
  }
  
  /**
   * Refresh current page.
   */
  public static void refresh() {
    driver.navigate().refresh();
  }

  /**
   * Mouse hover over an element.
   *
   * @param element - web element.
   * @author sudheer.singh
   */
  public static void mouseHoverOnElement(WebElement element) {
    Actions actions = new Actions(driver);
    actions.moveToElement(element).build().perform();
  }

  /**
   * Wait for Element to be visible on Screen before performing any action and Ignoring any Selenium
   * Exception.
   *
   * @param elementLocator - web element.
   * @param timeout - timeout period.
   * @param klass - class
   */
  public static void waitForElementForVisibilityIgnoringException(WebElement elementLocator,
      int timeout, Class<? extends WebDriverException> klass) {
    WebDriverWait wait = new WebDriverWait(driver, timeout);
    wait.ignoring(klass).until(ExpectedConditions.visibilityOf(elementLocator));
  }

  /**
   * Verify if element is visible or not.
   *
   * @param webElement - WebElement to be identified if visible or not.
   * @param timeOut - waiting time period.
   * @return - true if webelement is visible else false.
   * @author Sudheer.Singh
   */
  public static boolean isElementVisible(WebElement webElement, int timeOut) {
    try {
      WebDriverWait wait = new WebDriverWait(driver, timeOut);
      wait.until(ExpectedConditions.visibilityOf(webElement));
      return true;
    } catch (org.openqa.selenium.NoSuchElementException e) {
      return false;
    }
  }

  /**
   * Switch to child or parent window.
   *
   * @param windowHandle - window to be switche into.
   * @author sudheer.singh
   */
  public static void switchToWindow(String windowHandle) {
    if (windowHandle == null) {
      LogPrinter.printLog("No second window in the browser is present");
      return;
    }
    driver.switchTo().window(windowHandle);
  }

  /**
   * Switch into frame.
   *
   * @param elementLocator - web element.
   * @author sudheer.singh
   */
  public static void switchToFrameInDom(WebElement elementLocator) {
    driver.switchTo().frame(elementLocator);
  }

  /**
   * Close then current open window.
   */
  public static void closeCurrentBrowserWindow() {
    driver.close();
  }
  
  /**
   * Get page title of opened window.
   *
   * @return - page title.
   * @author sudheer.singh
   */
  public static String getPageTitle() {
    try {
      LogPrinter.printLog(String.format("The title of the page is: %s\\n\\n", driver.getTitle()));
      return driver.getTitle();
    } catch (Exception e) {
      throw new TestException(String.format("Current page title is: %s", driver.getTitle()));
    }
  }

  /**
   * Get current URL of the page.
   *
   * @return - current url of the page.
   */
  public String getCurrentUrl() {
    return driver.getCurrentUrl();
  }

  /**
   * Get internal text of the WebElement.
   *
   * @param element - web element.
   * @return - text of the element.
   * @author sudheer.singh
   */
  public String getElementText(WebElement element) {
    return element.getText();
  }

  public static void scrollThePageDown(int pixel) {
    JavascriptExecutor jse = (JavascriptExecutor) driver;
    jse.executeScript("window.scrollBy(0," + pixel + ")");
  }

  public static void scrollThePageUp(int pixel) {
    JavascriptExecutor jse = (JavascriptExecutor) driver;
    jse.executeScript("window.scrollBy(0,-" + pixel + ")");
  }

  public static void openUrlInNewWindow(String applicationUrl) {
    driver.switchTo().newWindow(WindowType.WINDOW).get(applicationUrl);
  }

  public static void openNewTab(String applicationUrl) {
    driver.switchTo().newWindow(WindowType.TAB).get(applicationUrl);
  }

  public static String getNewlyOpenedTabTitle() {
    ArrayList<String> tabs2 = null;
    String pageTitle = null;
    try {
      tabs2 = new ArrayList<String>(driver.getWindowHandles());
      driver.switchTo().window(tabs2.get(1));
      waitForPageToLoad(10);
      pageTitle = driver.getTitle();
    } catch (Exception e) {
      e.printStackTrace();
    } finally {
      try {
        driver.close();
        driver.switchTo().window(tabs2.get(0));
      } catch (Exception e) {
        System.err.println("No New Tab found");
      }
    }
    return pageTitle;
  }

  public static String getFontSizeOfTheWebElement(WebElement webElement) {
    return webElement.getCssValue("font-size");
  }

  public static boolean isElementClickable(WebElement webElement, int timeOut) {
    try {
      WebDriverWait wait = new WebDriverWait(driver, timeOut);
      wait.until(ExpectedConditions.elementToBeClickable(webElement));
      return true;
    } catch (org.openqa.selenium.ElementClickInterceptedException e) {
      return false;
    }
  }

  public static int getCountOfWebElememt(String xpath, int timeOut) {
    return driver.findElements(By.xpath(xpath)).size();
  }

  public static void clickUsingJs(WebElement webElement) throws InterruptedException {
    waitForPageToLoad(45);
    waitForAnElementToBeVisible(webElement, 45);
    waitForAnElementToBeClickable(webElement, 45);
    try {
      highlight(webElement);
      JavascriptExecutor executor = (JavascriptExecutor) driver;
      executor.executeScript("arguments[0].click();", webElement);
    } catch (Exception e) {
      Actions action =new Actions(driver);
      highlight(webElement);
      action.moveToElement(webElement).click().build().perform();
    }
  }

  
}
