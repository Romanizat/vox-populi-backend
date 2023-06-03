package romanizat.voxpopuli;


import org.junit.jupiter.api.*;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class VoxPopuliApplicationTests {
    static {
        System.setProperty("webdriver.chrome.driver", "chromedriver_win32\\chromedriver.exe");
    }

    private final static WebDriver chromeDriver = new ChromeDriver();

    private String login() throws InterruptedException {
        final String url = "http://localhost:4200/";
        chromeDriver.navigate().to(url);
        WebElement usernameField = chromeDriver.findElement(By.xpath("//*[@id=\"mat-input-0\"]"));
        usernameField.sendKeys("admin");
        WebElement passwordField = chromeDriver.findElement(By.xpath("//*[@id=\"mat-input-1\"]"));
        passwordField.sendKeys("admin");
        WebElement loginButton = chromeDriver.findElement(By.xpath(
                "//*[@id=\"mat-tab-content-0-0\"]/div/form/button/span[3]"));
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
        eventDateField.sendKeys("15/6/2023");
        WebElement eventLocationField = chromeDriver.findElement(By.id("eventLocation"));
        eventLocationField.sendKeys("IT355");
        WebElement saveEventButton = chromeDriver.findElement(By.xpath(
                "//*[@id=\"createEvent\"]/span[2]"));
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
        WebElement deleteEventButton = chromeDriver.findElement(By.xpath(
                "/html/body/app-root/mat-drawer-container/mat-drawer-content/app-event-details/div[2]/div[3]/button/span[5]"));
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
        Thread.sleep(2000);
        WebElement saveEventButton = chromeDriver.findElement(By.id("createEvent"));
        saveEventButton.click();
        Thread.sleep(1000);
        WebElement snackBar = chromeDriver.findElement(By.tagName("simple-snack-bar"));
        return snackBar.getAttribute("innerHTML");
    }

    @Test
    @Order(1)
    void loginTest() throws InterruptedException {
        Assertions.assertEquals("http://localhost:4200/events", login());
    }

    @Test
    @Order(2)
    void createEventTest() throws InterruptedException {
        Assertions.assertTrue(createEvent().contains("Selenium Test"));
    }

    @Test
    @Order(3)
    void deleteEventTest() throws InterruptedException {
        Assertions.assertFalse(deleteEvent().contains("Selenium Test"));
    }

    @Test
    @Order(4)
    void createEventWithoutDataTest() throws InterruptedException {
        Assertions.assertTrue(createEventWithoutData().contains("Please enter all of the required fields"));
    }

    @AfterAll
    static void afterAll() {
        chromeDriver.close();
    }
}