package com.cucumber.stepdefinitions;

import java.io.IOException;
import java.net.UnknownHostException;

import com.cucumber.base.utils.LogPrinter;
import com.cucumber.base.utils.Utilities;

import cucumber.api.Scenario;
import io.cucumber.java.After;
import io.cucumber.java.Before;

/**
 * {@link CucumberSceanrioHook} class will re-initailize the DatabaseBean before
 * and after each scenario to resolve the conflicts if occurred during the
 * scenarios execution.
 */
public class CucumberSceanrioHook {

  @Before
    public void beforeSceanrio(Scenario scenario) throws UnknownHostException {
      LogPrinter.printLog("Execution Started---");
      LogPrinter.printLog("Application Name: " + Utilities.getEnvironmentProperties("ApplicationName"));
      LogPrinter.printLog("Scenario Name: " + scenario.getName());
    }

    @After
    public void afterSceanrio(Scenario scenario) throws IOException {
      LogPrinter.printLog("Execution Status: " + scenario.getStatus());
    }
}