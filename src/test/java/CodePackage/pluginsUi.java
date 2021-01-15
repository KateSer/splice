package CodePackage;

import basePage.basePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import org.testng.Assert;

import java.util.List;


public class pluginsUi extends basePage {
    @FindBy(css = "[data-qa='navbar-plugins']")
    WebElement pluginsNavBar;
    @FindBy(css = "[data-qa='freePlugins']")
    WebElement freePuginsSideBar;
    @FindBy(xpath = "//app-plugin-card")
    List<WebElement> appPluginCards;
    @FindBy(xpath = "//app-plugins-filter-panel")
    WebElement appPluginsFilterPanel;

    public static String getRandomString(int length) {
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < length; ++i) {
            char c = (char) ((int) (Math.random() * 26.0D + 65.0D));
            sb.append(c);
        }
        return sb.toString().toLowerCase();
    }

    public String replaceRandomData(String valueTobeReplaced) throws Exception {
        if (valueTobeReplaced.toLowerCase().contains("random_string")) {
            valueTobeReplaced = valueTobeReplaced.replace("random_string", getRandomString(7));
        }
        return valueTobeReplaced;
    }

    public void verifyPageHeader(String header) throws Exception {
        wait(2.0);
        header = replaceRandomData(header);
        Assert.assertTrue((driver.findElements(By.xpath("//nav//h1[contains(text(),'" + header + "')]"))).size() > 0, header + " page is not displayed");

    }

    public void clickPluginsNavBar()throws Exception {
        pluginsNavBar.click();
        wait(3.0);
    }

    public void clickFreePluginSidebar() throws Exception {
        freePuginsSideBar.click();
        wait(3.0);
    }

    public void verifyH2SearchResultsText(String text) {
        Assert.assertTrue((driver.findElements(By.xpath("//h2[contains(text(),'" + text + "')]"))).size() > 0, text + "  is not displayed");
    }

    public void verifyNumberOfPluginCardsOnThePage(Integer expectedNumber) {
        Integer actualNumber = appPluginCards.size();
        Assert.assertEquals(actualNumber, expectedNumber);
    }

    public void verifyFilterPanelOnFreePluginsPage(String condition) throws Exception {
        verifyElementPresent(appPluginsFilterPanel, condition);
    }

    public void clickFilterTagByName(String tagName) throws Exception {
        driver.findElement(By.xpath("//app-plugins-tag//span[contains(text(),'" + tagName + "')]")).click();
        wait(3.0);
    }


}
