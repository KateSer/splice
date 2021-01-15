package stepdefs;

import CodePackage.pluginsApi;
import basePage.basePage;
import cucumber.api.java.en.And;
import io.cucumber.datatable.DataTable;

import java.util.List;
import java.util.Map;

public class stepDefsApi  extends basePage {
    pluginsApi fsp = new pluginsApi();

    @And("^I execute request to GET list of plugins$")
    public void I_execute_request_to_get_list_of_plugins(DataTable dataTable) throws Exception {
        fsp.getListOfPlugins(dataTable);
    }

    @And("^I verify for sale plugins response contains total '(.*?)' results$")
    public void I_verify_total_number_of_for_sale_plugins_displayed(String expectedNumber) throws Exception {
        fsp.verifyTotalNumberOfForSaleResults(expectedNumber);
    }

    @And("^I verify matching tags are displayed in for sale plugins response$")
    public void I_verify_tags_with_values_in_for_sale_plugins_response(List<Map<String, String>> dataTable) throws Exception {
        fsp.verifyTagsWithValuesForSalePluginsResults(dataTable);
    }
}
