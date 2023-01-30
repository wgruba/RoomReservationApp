package com.kgs.RoomReservationApp;

import static com.kgs.RoomReservationApp.utils.TestDataProvider.getReservationWithDetails;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.springframework.test.util.AssertionErrors.assertFalse;

import java.time.Duration;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.jdbc.EmbeddedDatabaseConnection;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.test.context.jdbc.Sql;

@Sql({"/db-init.sql", "/reservation-dao-test-init-data.sql"})
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureTestDatabase(
    connection = EmbeddedDatabaseConnection.H2,
    replace = AutoConfigureTestDatabase.Replace.ANY)
class ReservationFunctionalTests {
  @LocalServerPort private int port;

  @Test
  void shouldEditReservationDetails() {
    System.setProperty(
        "webdriver.chrome.driver", "D:\\Intelij_Ultimate\\RoomReservationApp\\chromedriver.exe");
    String user = "Employee1KGW";
    String password = "employee1";
    var expectedReservation = getReservationWithDetails();
    WebDriver driver = new ChromeDriver();
    driver.get("http://localhost:8080/employees/details?id=1");

    var usernameInput = driver.findElement(By.id("username"));
    var passwordInput = driver.findElement(By.id("password"));

    usernameInput.sendKeys(user);
    passwordInput.sendKeys(password);

    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10L));
    var signButton = driver.findElement(By.xpath("//button[@type='submit']"));

    signButton.click();

    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10L));
    var editButton = driver.findElement(By.id("edit_button"));
    editButton.click();

    var buttonNo = driver.findElement(By.id("no"));
    buttonNo.click();


    var buttonYes = driver.findElement(By.id("edit"));

    editButton.click();
    buttonYes.click();

    var emailField = driver.findElement(By.id("email"));
    var firstNameField = driver.findElement(By.id("first_name"));
    var lastNameField = driver.findElement(By.id("last_name"));
    var NoRoom = driver.findElement(By.id("NoRoom"));
    var saveButton = driver.findElement(By.id("save"));
    var statusField = driver.findElement(By.id("status"));

    emailField.clear();
    emailField.sendKeys("AleEmail...");
    NoRoom.clear();
    NoRoom.sendKeys("Room");

    saveButton.click();
    assertTrue(firstNameField.isDisplayed());

    var exampleEmail = "alaGaj@example.com";
    var exampleFName= "Alicja";
    var exampleLName = "Gaj";
    var exampleStatus = "ACTIVE";
    var exampleNoRoom = "1";

    emailField.clear();
    emailField.sendKeys(exampleEmail);

    firstNameField.clear();
    firstNameField.sendKeys(exampleFName);

    lastNameField.clear();
    lastNameField.sendKeys(exampleLName);

    statusField.clear();
    statusField.sendKeys(exampleStatus);

    NoRoom.clear();
    NoRoom.sendKeys(exampleNoRoom);

    saveButton.click();

    var detailsButton = driver.findElement(By.id("details"));
    assertTrue(detailsButton.isDisplayed());

    detailsButton.click();

    var emailText = driver.findElement(By.id("email")).getText();
    var firstNameText = driver.findElement(By.id("first_name")).getText();
    var lastNameText = driver.findElement(By.id("last_name")).getText();
    var statusText = driver.findElement(By.id("status")).getText();

    assertEquals(expectedReservation.reservation().getStatus().toString(), statusText);
    assertEquals(exampleEmail,emailText);
    assertEquals(exampleFName,firstNameText);
    assertEquals(exampleLName,lastNameText);

  }
}
