package basePage;
import org.openqa.selenium.*;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.io.File;
import java.io.FileReader;
import java.util.Properties;


public class basePage {
    public static WebDriver driver;
    public static String URL;

    public static WebDriver  startBrowser() throws Exception {
        System.out.println("starting the test...");
        System.setProperty("webdriver.chrome.driver", "src/test/drivers/chromedriver");
        driver = new ChromeDriver();
        try {
            driver.get(URL);
        }
        catch (Exception e) {
            e.printStackTrace();
        }
        driver.manage().window().maximize();
        driver.manage().deleteAllCookies();
        return driver;
    }

    public static String getDataFromProperties(String key) throws Exception {
        FileReader reader = new FileReader(new File("src/test/resources/testConfiguration.properties"));
        Properties p = new Properties();
        p.load(reader);
        return p.getProperty(key);
    }

    public static WebDriverWait wait(int timeout) throws Exception {
        WebDriverWait wait = new WebDriverWait(driver, (long)timeout);
        return wait;
    }

    public static void wait(double time) throws Exception {
        Thread.sleep((long)(time * 1000.0D));
    }

    public static void scrollIntoView(WebElement ele) throws Exception {
        JavascriptExecutor js = (JavascriptExecutor)driver;
        js.executeScript("arguments[0].scrollIntoView(true);", new Object[]{ele});
    }

    public int getElementsCount(WebElement element) {
        int count = 0;
        String expression = "";
        try {
            if (element != null) {
                String str = element.toString();
                String[] listString = null;
                if (str.contains("xpath")) {
                    listString = str.split("xpath:");
                    expression = listString[1].substring(0, listString[1].lastIndexOf("", listString[1].length()) - 1).trim();
                    count = driver.findElements(By.xpath(expression)).size();
                } else if (str.contains("css")) {
                    listString = str.split(":");
                    expression = listString[2].substring(0, listString[2].lastIndexOf("", listString[1].length()) - 1).trim();
                    count = driver.findElements(By.cssSelector(expression)).size();
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return count;
    }

    public boolean verifyElementPresent(WebElement ele, String condition) throws Exception {
        wait(2.0);
        try {
            int count = getElementsCount(ele);
            System.out.println(("found elements: " + count));
            if (condition.toLowerCase().contains("not")) {
                Assert.assertTrue(count < 1,"element found");
            } else {
                Assert.assertFalse(count < 1,"element is not found");
            }
        } catch (NoSuchElementException ex) {
            System.out.println(("Element is NOT present"));
        }

        return true;
    }





}