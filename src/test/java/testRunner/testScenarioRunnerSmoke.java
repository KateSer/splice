package testRunner;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;


// testScenarioRunner will execute all tests with @Sanity tag
@RunWith(Cucumber.class)
@CucumberOptions(features={"src/test/resources/features"}, glue={"stepdefs"},tags={"@Sanity"})
public class testScenarioRunnerSmoke {

}
