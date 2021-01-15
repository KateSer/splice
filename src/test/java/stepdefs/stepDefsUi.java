package stepdefs;

import CodePackage.pluginsUi;
import basePage.basePage;




import cucumber.api.java.en.And;
import org.openqa.selenium.support.PageFactory;


public class stepDefsUi extends basePage {

//    @Given("^I start the browser$")
//    public void i_start_the_browser() throws Exception {
//        System.out.println("I am starting browser");
//        startBrowser();
//        wait(3.0);
//    }

    @And("^I wait for '(.*?)' seconds$")
    public void I_set_wait_time(String value) throws Exception {
        wait(Double.parseDouble(value));
    }

    @And("^I navigate to Free Plugins page$")
    public void I_navigate_to_free_plugins_page() throws Exception {
        pluginsUi ca = PageFactory.initElements(driver, pluginsUi.class);
        ca.clickPluginsNavBar();
        ca.clickFreePluginSidebar();
    }

    @And("^I verify that I am on '(.*?)' page$")
    public void I_verify_I_am_on_the_page(String h1Header) throws Exception {
        pluginsUi ca = PageFactory.initElements(driver, pluginsUi.class);
        ca.verifyPageHeader(h1Header);
    }

    @And("^I can see text '(.*?)' search results are displayed on the page$")
    public void I_can_see_number_of_search_results_on_the_page(String text) throws Exception {
        pluginsUi ca = PageFactory.initElements(driver, pluginsUi.class);
        ca.verifyH2SearchResultsText(text);
    }

    @And("^I verify '(.*?)' app plugin cards are loaded on the page$")
    public void I_can_verify_number_of_app_plugin_cards_on_the_page(Integer expectedNumber) throws Exception {
        pluginsUi ca = PageFactory.initElements(driver, pluginsUi.class);
        ca.verifyNumberOfPluginCardsOnThePage(expectedNumber);
    }

    @And("^I (can|can not) see filter panel on the free plugins page$")
    public void I_can_verify_filter_panel_on_free_plugins_page(String condition) throws Exception {
        pluginsUi ca = PageFactory.initElements(driver, pluginsUi.class);
        ca.verifyFilterPanelOnFreePluginsPage(condition);
    }

    @And("^I click on '(.*?)' search filter tag$")
    public void I_click_on_search_filter_tag(String tagName) throws Exception {
        pluginsUi ca = PageFactory.initElements(driver, pluginsUi.class);
        ca.clickFilterTagByName(tagName);
    }


}

