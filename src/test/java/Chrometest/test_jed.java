package Chrometest;

import org.openqa.selenium.By;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.*;
import java.time.Duration;
import java.time.LocalDate;
import java.util.List;

public class test_jed {
    public String burl = "http://google.com";
    public String qst = "Hello World";
    public WebDriver drv;
    @Test
    public void chromecase(){
        System.setProperty("webdriver.chrome.driver", "/home/Dendrobates/Dokumenty/extlib/chromedriver");
        drv = new ChromeDriver();
        drv.get(burl);

        drv.manage().deleteCookieNamed ("CONSENT");
        drv.manage().addCookie(new Cookie("CONSENT","YES+shp.gws-"+ LocalDate.now().toString().replace("-","")+"-0-RC2.en+FX+374"));
        drv.navigate().refresh();
        drv.manage().window().maximize();
        WebElement query = drv.findElement(By.name("q"));
        query.sendKeys(qst);
        query.submit();
        Duration tmo = Duration.ofSeconds(5);
        WebDriverWait seWait = new WebDriverWait(drv, tmo );
        seWait.until(ExpectedConditions.presenceOfElementLocated(By.id("rso")));
        List<WebElement> elements = drv.findElement(By.id("rso")).findElements(By.xpath("//div[@class='g']"));

        for(WebElement element: elements){
            System.out.println(element.getText());
        }

        drv.quit();
    }
}
