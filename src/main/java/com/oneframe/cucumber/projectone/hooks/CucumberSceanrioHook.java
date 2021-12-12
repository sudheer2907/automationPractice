package com.oneframe.cucumber.oneframebase.hooks;

import com.oneframe.cucumber.oneframebase.utils.LogPrinter;
import com.oneframe.cucumber.oneframebase.utils.WebDriverFactory;

import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;

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
