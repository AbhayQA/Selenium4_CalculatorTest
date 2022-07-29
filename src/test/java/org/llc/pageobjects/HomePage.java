package org.llc.pageobjects;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * The page object for LLC Calculator home page
 *
 * Consists of objects and actions
 */
public class HomePage {

	WebDriver driver;

	//The element locators/identifiers in the page
	By header = By.cssSelector("div[class='row mb-3'] h1 center");
	By inputField = By.id("input");
	By calculateButton = By.id("run");
	By output = By.id("output");
	By campaignBanner = By.xpath("//img[@id=\"id1\"]");

	public HomePage(WebDriver driver) {
		this.driver = driver;
	}

	//Validations of header and texts
	public void verifyHeaderText() {
		driver.findElement(header).isDisplayed();
		String headerText = driver.findElement(header).getText();
		assertEquals(headerText, "Best, most awesome factorial calculator!");
	}

	//Validations of input field
	public void verifyInputField() {
		driver.findElement(inputField).isDisplayed();
	}

	//Validations of Calculate button
	public void verifyCalculateButton() {
		driver.findElement(calculateButton).isDisplayed();
	}

	//Validations of Campaign banner
	public void verifyCampaignBanner() {
		driver.findElement(campaignBanner).isDisplayed();
		assertTrue(driver.findElement(campaignBanner).getAttribute("src").contains("/cdn/main/assets/"));
	}

	//Validations of Calculator functionality - Negative scenario by sending non digit value
	public void verifyCalculator_WrongInput() {
		driver.findElement(inputField).sendKeys("aa");
		driver.findElement(calculateButton).click();
		assertTrue(driver.findElement(output).getText().equals(""));
	}

	//Validations of Calculator functionality - Positive scenario by sending digit value
	public void verifyCalculator_CorrectInput(String value) {
		driver.findElement(inputField).clear();
		driver.findElement(inputField).sendKeys(value);
		driver.findElement(calculateButton).click();
		String factorialTextOnScreen = "The factorial value is : "+(factorial(Integer.parseInt(value)));
		assertTrue(driver.findElement(output).getText().equals(factorialTextOnScreen));
	}

	//Validations of Calculator functionality - Positive scenario by sending digit value
	public void verifyCalculator_RandomInput() {
		int randomNumber = getRandomNumberInRange();
		driver.findElement(inputField).clear();
		driver.findElement(inputField).sendKeys(String.valueOf(randomNumber));
		driver.findElement(calculateButton).click();
		String factorialTextOnScreen = "The factorial value is : "+(factorial(randomNumber));
		assertTrue(driver.findElement(output).getText().equals(factorialTextOnScreen));
	}

	//Find a random number for input
	static int getRandomNumberInRange() {
		Random r = new Random();
		return r.ints(0, (99 + 1)).limit(1).findFirst().getAsInt();
	}

	//Calculate factorial of input values
	static int factorial(int n) {
		if (n == 0)
			return 1;
		return n*factorial(n-1);
	}
}
