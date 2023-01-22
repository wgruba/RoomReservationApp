import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.core.env.Environment;
import org.testcontainers.Testcontainers;
import org.testcontainers.containers.BrowserWebDriverContainer;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class ReservationFunctionalTests {

    static BrowserWebDriverContainer<?> container = new BrowserWebDriverContainer<>()
            .withCapabilities(new ChromeOptions());

    @LocalServerPort
    private int port;

    @BeforeAll
     static void beforeAll(@Autowired Environment environment) {
        Testcontainers.exposeHostPorts(environment.getProperty("local.server.port", Integer.class));
        container.start();
    }

    @Test
    void shouldDisplayMessage() {
        container
                .getWebDriver()
                .get(String.format("http://host.testcontainers.internal:%d/index", port));

        WebElement messageElement = container.getWebDriver().findElement(By.className("btn"));

        assertEquals("Integration Test with Selenium", messageElement.getText());
    }

//    @Test
//    public void getAllReservationsForClient() {
//        System.setProperty("webdriver.chrome.driver", "C:\\Users\\mkazm\\Documents\\chromedriver\\chromedriver.exe");
//        WebDriver driver = new ChromeDriver();
//        driver.get("localhost:8080/reservations");
//
//        driver.get("https://www.selenium.dev/selenium/web/web-form.html");
//        String title = driver.getTitle();
//        assertEquals("Web form", title);
//
//        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));
//
//        WebElement textBox = driver.findElement(By.name("my-text"));
//        WebElement submitButton = driver.findElement(By.cssSelector("button"));
//
//        textBox.sendKeys("Selenium");
//        submitButton.click();
//
//        WebElement message = driver.findElement(By.id("message"));
//        String value = message.getText();
//        assertEquals("Received!", value);
//
//        driver.quit();
//    }
}
