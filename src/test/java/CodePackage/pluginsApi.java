package CodePackage;

import basePage.basePage;


import io.cucumber.datatable.DataTable;
import io.restassured.RestAssured;
import io.restassured.specification.RequestSpecification;
import io.restassured.response.Response;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;


import org.testng.Assert;
import stepdefs.commonStepDefs;
import stepdefs.stepDefsUi;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

public class pluginsApi extends basePage {
//    public forSalePluginsApi() {
//    }
    public static Response response;
    public String URL = commonStepDefs.URL;

    private String getListOfPlugins = "www/plugins";

    /** @param dataTable
     *  pricing and tags should be present in the table*/
    public Response getListOfPlugins(DataTable dataTable) throws Exception {
        Map<String, String> map = new HashMap<>();
        map = dataTable.asMap(String.class, String.class);
        String attrType = map.get("responseType");
        HashMap<String, String> params = new HashMap<String, String>();
        params.put("page", "1");
        params.put("per_page", "30");
        params.put("pricing", map.get("pricing"));
        params.put("native", "0");
        if(!Objects.isNull(map.get("tags")) ) {
            params.put("tags", map.get("tags"));
        }

        System.out.println("path - "+getListOfPlugins);
        URL=getDataFromProperties("APIURL");
        System.out.println("URL - "+URL);
        RequestSpecification request =  initRequest(URL, getListOfPlugins, params).given();
        response = request.get();
        int responseCode= response.getStatusCode();
        Assert.assertEquals(responseCode,200, "reponse code is "+responseCode+"; body- "+response.getBody().asString());
        return response;
    }
    /** @getListOfForSalePlugins() should be executed before*/
    public void verifyTotalNumberOfForSaleResults(String expectedNumber) throws Exception{
        String body = response.getBody().asString();
        JSONParser parser = new JSONParser();
        JSONObject obj =(JSONObject) parser.parse(body);
        String actual = obj.get("total_hits").toString();
        Assert.assertTrue(actual.equals(expectedNumber),"expected "+expectedNumber+", but found "+actual);
    }
    /** @getListOfForSalePlugins() should be executed before*/
    public void verifyTagsWithValuesForSalePluginsResults(List<Map<String, String>> dataTable) throws Exception {
        String body = response.getBody().asString();
        JSONParser parser = new JSONParser();
        JSONObject obj =(JSONObject) parser.parse(body);
        JSONObject matchingTags = (JSONObject) obj.get("matching_tags");
        for (int i = 0; i < dataTable.size(); i++) {
            Map<String, String> map = dataTable.get(i);
            String tag = map.get("tag");
            String expectedValue = map.get("value");
            String actualValue= matchingTags.get(tag).toString();
            Assert.assertTrue(expectedValue.equals(actualValue),"expected - "+tag+":"+expectedValue+", but found "+actualValue);
        }

    }



    ///
    public static RequestSpecification initRequest(String baseURI, String resourcePath, HashMap<String, String> queryParams) {
        RestAssured.baseURI = baseURI;
        RestAssured.basePath = resourcePath;
        RequestSpecification request = RestAssured.given().queryParams(queryParams);
        return request;
    }

}
