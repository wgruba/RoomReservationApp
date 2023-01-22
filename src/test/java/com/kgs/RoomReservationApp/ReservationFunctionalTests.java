package com.kgs.RoomReservationApp;

import static com.kgs.RoomReservationApp.utils.TestDataProvider.getReservationWithDetails;
import static org.junit.jupiter.api.Assertions.assertEquals;

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

  //    Scenario: Display reservation details page
  //    Given I am on the login page
  //    When I fill the field User with <user>
  //    And I fill the field Password with <password>
  //    And I press "SIGN IN" button
  //    Then I should be on the Reservations list page
  //    When I press "SZCZEGÓŁY" button of <reservation>
  //    Then I should be on <reservation> details page

  @Test
  void shouldDisplayReservationDetailsPage() {
    System.setProperty(
        "webdriver.chrome.driver", "/home/m.kazmierczak/Documents/chromedriver/chromedriver");
    String user = "j.smith@mail.com";
    String password = "pass";
    var expectedReservation = getReservationWithDetails();
    WebDriver driver = new ChromeDriver();
    driver.get(String.format("http://localhost:%d/reservations/", port));

    var usernameInput = driver.findElement(By.id("username"));
    var passwordInput = driver.findElement(By.id("password"));

    usernameInput.sendKeys(user);
    passwordInput.sendKeys(password);

    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10L));
    var signButton = driver.findElement(By.className("btn"));
    signButton.click();

    driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10L));
    var detailsButton = driver.findElement(By.id("details"));
    detailsButton.click();

    var statusText = driver.findElement(By.id("status")).getText();
    var roomTypeText = driver.findElement(By.id("room_type")).getText();

    assertEquals(expectedReservation.reservation().getStatus().toString(), statusText);
    assertEquals(expectedReservation.roomType(), roomTypeText);
  }
}
