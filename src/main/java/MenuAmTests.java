import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

public class MenuAmTests {
    public WebDriver driver;

    @BeforeClass
    public void setUp() {
        // Set up the WebDriver instance
        System.setProperty("webdriver.chrome.driver", "/path/to/chromedriver");
        driver = new ChromeDriver();
    }

    @Test
    public void testMenuAmTitle() {
        // Navigate to the website
        driver.get("https://menu.am/en");

        // Verify that the page title is correct
        String expectedTitle = "Menu.am - Order food online in Armenia";
        String actualTitle = driver.getTitle();
        Assert.assertEquals(actualTitle, expectedTitle);
    }

    @Test
    public void testMenuAmSearch() {
        // Navigate to the website
        driver.get("https://menu.am/en");

        // Find the search box and search button
        WebElement searchBox = driver.findElement(By.xpath("//input[@name='s']"));
        WebElement searchButton = driver.findElement(By.xpath("//button[@class='btn btn-secondary']"));

        // Enter a search query and click the search button
        String searchQuery = "pizza";
        searchBox.sendKeys(searchQuery);
        searchButton.click();

        // Verify that the search results page is displayed
        String expectedUrl = "https://menu.am/en/search?q=pizza";
        String actualUrl = driver.getCurrentUrl();
        Assert.assertEquals(actualUrl, expectedUrl);

        // Verify that the search results contain the search query
        WebElement searchResults = driver.findElement(By.xpath("//div[@class='restaurant_list']"));
        Assert.assertTrue(searchResults.getText().contains(searchQuery));
    }

    @AfterClass
    public void tearDown() {
        // Close the browser
        driver.quit();
    }
}
