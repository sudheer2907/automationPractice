package oneframe.cucumber.testrunner;

import org.apache.log4j.PropertyConfigurator;
import org.junit.runner.RunWith;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;

import com.oneframe.cucumber.base.utils.WebDriverFactory;
import com.oneframe.cucumber.base.utils.reporting.GenerateReport;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import cucumber.api.testng.CucumberFeatureWrapper;
import cucumber.api.testng.TestNGCucumberRunner;

import io.cucumber.testng.AbstractTestNGCucumberTests;

@RunWith(Cucumber.class)
@CucumberOptions(monochrome = true, features = {"src//test//resources//features"},
    glue = {"oneframe.cucumber.stepdefinitions"},
    plugin = {"pretty", "html:target/test-report/cucumber", "json:target/test-report/cucumber.json",
        "rerun:target/rerun.txt"},
    tags = {"@TestInputForms"})
public class TestRunner extends AbstractTestNGCucumberTests {
  private TestNGCucumberRunner testNGCucumberRunner;
  private static String scenarioName;

  /**
   * setUpClass.
   */

  @BeforeClass(alwaysRun = true)
  public void setUpClass() {
    PropertyConfigurator.configure("log4j.properties");
    System.setProperty("log4j.configurationFile", "log4j.properties");
    testNGCucumberRunner = new TestNGCucumberRunner(this.getClass());
  }

  @Test(groups = "cucumber", description = "Runs Cucumber Feature", dataProvider = "features")
  public void feature(CucumberFeatureWrapper cucumberFeature) {
    scenarioName = cucumberFeature.getCucumberFeature().getPath();
    System.out.println("************** Executing scenario *********" + scenarioName);
    testNGCucumberRunner.runCucumber(cucumberFeature.getCucumberFeature());

  }

  @DataProvider
  public Object[][] features() {
    return testNGCucumberRunner.provideFeatures();
  }


  /**
   * Teardownclass.
   */
  @AfterClass(alwaysRun = true)
  public void tearDownClass() {
    testNGCucumberRunner.finish();
    GenerateReport.generateReport("oneFrame", "target/test-report");
    WebDriverFactory.closeWindow();
  }



  @Override
  @DataProvider(parallel = true)
  public Object[][] scenarios() {
    return super.scenarios();
  }
}
