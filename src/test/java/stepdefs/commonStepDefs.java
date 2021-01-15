package stepdefs;

import basePage.basePage;
import cucumber.api.Scenario;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;


import java.util.Collection;

public class commonStepDefs extends basePage {
//    public static String URL;
    public static Scenario currentScenario;
    public static String scenarioTag = "";
    public static String scenarioName = "";

    @And("^I am api user without account$")
    public void I_am_api_user(){
        System.out.println("api user should start the session");
    }

    @And("^I am ui user without account$")
    public void I_am_ui_user(){
        System.out.println("ui user should start the session");
    }

    @After()
    public void closeBrowser(Scenario scenario) {
        boolean isApiTest = false;
        Collection<String> tags = scenario.getSourceTagNames();
        for (String tag : tags) {
            if (tag.toLowerCase().contains("@api")) {
                isApiTest = true;
                break;
            }
        }if (!isApiTest) {
            driver.quit();
        }

    }

    @Before
    public void initialize(Scenario scenario) throws Exception {
        boolean isApiTest = false;
        currentScenario = scenario;
        scenarioTag = scenario.getSourceTagNames().toString();
        scenarioName = scenario.getName();
        URL =getDataFromProperties("URL");
//        URL=getDataFromProperties("URL");
        System.out.println("scenarioTag:"+ scenarioTag);
        System.out.println("selected url:"+ URL);
        Collection<String> tags = scenario.getSourceTagNames();
        for (String tag : tags) {
            if (tag.toLowerCase().contains("@api")) {
                isApiTest = true;
                URL=getDataFromProperties("APIURL");
                break;
            }
        }
        if (!isApiTest) {
            startBrowser();
        }
    }
}
