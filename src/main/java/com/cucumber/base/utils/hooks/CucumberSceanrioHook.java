package com.cucumber.base.utils.hooks;

import com.cucumber.base.utils.LogPrinter;
import com.cucumber.base.utils.WebDriverFactory;

import cucumber.api.Scenario;
import io.cucumber.java.After;
import io.cucumber.java.Before;

public class CucumberSceanrioHook {

  @Before
  public void beforeSceanrio(Scenario scenario) {
    LogPrinter.printLog("Execution Started, Executing: " + scenario.getName());
  }

  @After
  public void afterSceanrio(Scenario scenario) {
    WebDriverFactory.closeWindow();
  }
}
