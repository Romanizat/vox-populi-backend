package romanizat.voxpopuli;


import org.junit.jupiter.api.AfterAll;
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
        //TODO: Uncomment login method if performing single test
        //login();
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

    private String deleteEvent() throws InterruptedException {
        //TODO: Uncomment login method if performing single test
        //login();
        WebElement eventDetailsButton = chromeDriver.findElement(
                By.xpath("/html/body/app-root/mat-drawer-container/mat-drawer-content/app-view-events/div/table/tbody/tr[2]/td[4]/mat-icon"));
        eventDetailsButton.click();
        Thread.sleep(2000);
        WebElement deleteEventButton = chromeDriver.findElement(By.xpath("/html/body/app-root/mat-drawer-container/mat-drawer-content/app-event-details/div[4]/button"));
        deleteEventButton.click();
        Thread.sleep(1000);
        WebElement tableBody = chromeDriver.findElement(By.xpath("/html/body/app-root/mat-drawer-container/mat-drawer-content/app-view-events/div/table/tbody"));
        return tableBody.getAttribute("innerHTML");
    }


    private String createEventWithoutData() throws InterruptedException {
        //TODO: Uncomment login method if performing single test
        //login();
        WebElement createEventButton = chromeDriver.findElement(By.xpath("/html/body/app-root/mat-drawer-container/mat-drawer-content/app-view-events/div/div/button"));
        createEventButton.click();
        Thread.sleep(1000);
        WebElement saveEventButton = chromeDriver.findElement(By.xpath("//*[@id=\"mat-dialog-0\"]/app-create-event/div/form/div/button/span[1]"));
        saveEventButton.click();
        Thread.sleep(1000);
        WebElement snackBar = chromeDriver.findElement(By.xpath("//*[@id=\"cdk-overlay-2\"]/snack-bar-container/div/div/simple-snack-bar"));
        return snackBar.getAttribute("innerHTML");
    }

    @Test
    void loginTest() throws InterruptedException {
        Assertions.assertEquals("http://localhost:4200/events", login());
    }

    @Test
    void createEventTest() throws InterruptedException {
        Assertions.assertTrue(createEvent().contains("Selenium Test"));
    }

    @Test
    void deleteEventTest() throws InterruptedException {
        Assertions.assertFalse(deleteEvent().contains("Selenium Test"));
    }

    @Test
    void createEventWithoutDataTest() throws InterruptedException {
        Assertions.assertTrue(createEventWithoutData().contains("Please enter all of the required fields"));
    }

    @AfterAll
    static void afterAll() {
        chromeDriver.close();
    }
}