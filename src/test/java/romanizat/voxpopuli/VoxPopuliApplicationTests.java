package romanizat.voxpopuli;


import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class VoxPopuliApplicationTests {
    static {
        System.setProperty("webdriver.chrome.driver", "chromedriver_win32\\chromedriver.exe");
    }

    private final static WebDriver chromeDriver = new ChromeDriver();


    private final String url = "http://localhost:4200/";

    private String login() throws InterruptedException {
        chromeDriver.navigate().to(url);
        WebElement usernameField = chromeDriver.findElement(By.xpath("//*[@id=\"mat-input-0\"]"));
        usernameField.sendKeys("admin");
        WebElement passwordField = chromeDriver.findElement(By.xpath("//*[@id=\"mat-input-1\"]"));
        passwordField.sendKeys("admin");
        WebElement loginButton = chromeDriver.findElement(By.xpath("//*[@id=\"mat-tab-content-0-0\"]/div/form/button/span[1]"));
        loginButton.click();
        System.out.println(chromeDriver.getCurrentUrl());
        Thread.sleep(2000);
        return chromeDriver.getCurrentUrl();
    }

    @Test
    void loginTest() throws InterruptedException {
        Assertions.assertEquals("http://localhost:4200/events", this.login());
        chromeDriver.close();
    }

}