package testwwa.selenium.test;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import static org.assertj.core.api.Assertions.assertThat;
public class FirstTestSelenium {
    public WebDriver driver; //pole driver, które będziemy określać potem w kodzie - jako ChromeDriver
    String devToUrl = "https://dev.to/";
    @Before
    public void Setup(){
        System.setProperty("webdriver.chrome.driver","chromedriver/chromedriver.exe");
        driver = new ChromeDriver(); //otworzy nam nową przeglądarkę Chrome - nowo utworzny obiekt klasy ChromeDriver
        driver.manage().window().maximize(); //maksymalizuje okno przegladarki
        driver.get(devToUrl); //przechodzi do strony dev.to
    }
    @Test
    public void DevToIsOpenInTheBrowser(){
        String currentUrl = driver.getCurrentUrl();
        assertThat(currentUrl).isEqualTo(devToUrl);
    }
    @Test
    public void GoToWeekAndSelectFirstPost(){
        WebElement week = driver.findElement(By.cssSelector("#on-page-nav-controls > div > nav > a:nth-child(2)")); //szuka elementu week
        week.click(); // klika na element week
        WebDriverWait wait = new WebDriverWait(driver,5); //tworzy nowy obiekt wait - który posłuży do zatrzymania przeglądarki
        wait.until(ExpectedConditions.urlToBe("https://dev.to/top/week")); //przeglądarka czeka tak długo aż, url będzie "https://dev.to/top/week"
        WebElement firstPost = driver.findElement(By.className("crayons-story__title"));//szuka elementu firstPost
        firstPost.click(); //klika element firstPost
    }
    @After
    public void CleanUp(){}
}
