package org.llc.e2e;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.llc.pageobjects.HomePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * e2e for validating LLC Calculator application
 */
public class CalculatorTest {
    WebDriver driver;
    HomePage homePage;

    @BeforeAll
    static void setupClass() {
        WebDriverManager.chromedriver().setup();
    }

    @BeforeEach
    void setupTest() throws InterruptedException {
        driver = new ChromeDriver();
        driver.get("http://localhost:8080/");
    }

    @AfterEach
    void teardown() {
        driver.quit();
    }

    @Test
    public void validatePageTitle() {
        assertTrue(driver.getTitle().contains("Invitation Mode"));
    }

    @Test
    public void validateCalculatorPageContent() {
        homePage = new HomePage(driver);

        homePage.verifyHeaderText();
        homePage.verifyInputField();
        homePage.verifyCalculateButton();
        homePage.verifyCampaignBanner();
    }

    @Test
    public void validateCalculatorFunctionality() throws InterruptedException {
        homePage = new HomePage(driver);

        //(Negative scenario) Validate the factorial calculation functionality -- if input is any non digit
        homePage.verifyCalculator_WrongInput();

        //Validate the factorial calculation functionality -- test value 2
        homePage.verifyCalculator_CorrectInput("2");

        //Validate the factorial calculation functionality -- test value 10
        homePage.verifyCalculator_CorrectInput("10");
    }

    @Test
    public void validateCalculatorFunctionality_RandomNumber() throws InterruptedException {
        homePage = new HomePage(driver);

        //Validate the factorial calculation functionality -- with random number between 0-99
        homePage.verifyCalculator_RandomInput();
    }

    public void staticWait() throws InterruptedException {
        Thread.sleep(1000);
    }

}
