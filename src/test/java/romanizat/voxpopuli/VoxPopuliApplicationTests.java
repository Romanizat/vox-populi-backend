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
        Thread.sleep(2000);
        return chromeDriver.getCurrentUrl();
    }

    private String createEvent() throws InterruptedException {
        login();
        WebElement createEventButton = chromeDriver.findElement(By.xpath("/html/body/app-root/mat-drawer-container/mat-drawer-content/app-view-events/div/div/button"));
        createEventButton.click();
        Thread.sleep(1000);
        WebElement eventNameField = chromeDriver.findElement(By.id("eventName"));
        eventNameField.sendKeys("Selenium Test");
        WebElement eventDateField = chromeDriver.findElement(By.id("eventDate"));
        eventDateField.sendKeys("2/2/2022");
        WebElement eventLocationField = chromeDriver.findElement(By.id("eventLocation"));
        eventLocationField.sendKeys("SE201");
        WebElement saveEventButton = chromeDriver.findElement(By.xpath("//*[@id=\"mat-dialog-0\"]/app-create-event/div/form/div/button/span[1]"));
        saveEventButton.click();
        Thread.sleep(1000);
        WebElement tableBody = chromeDriver.findElement(By.xpath("/html/body/app-root/mat-drawer-container/mat-drawer-content/app-view-events/div/table/tbody"));
        Thread.sleep(1000);
        return tableBody.getAttribute("innerHTML");
    }

    @Test
    void loginTest() throws InterruptedException {
        Assertions.assertEquals("http://localhost:4200/events", login());
        chromeDriver.close();
    }

    @Test
    void createEventTest() throws InterruptedException {
        Assertions.assertTrue(createEvent().contains("Selenium Test"));
        chromeDriver.close();
    }

}