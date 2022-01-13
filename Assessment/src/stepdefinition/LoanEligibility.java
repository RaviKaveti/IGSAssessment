package stepdefinition;

import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.KeyEvent;
import java.util.Iterator;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.WebDriver;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Select;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import cucumber.api.java.After;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

@SuppressWarnings("unused")
public class LoanEligibility {

	public static WebDriver driver;

	String applType = "//label[contains(text(),'Single')]";
	String noofDependents = "//*[@title='Number of dependants']/../select";

	@BeforeTest
	@Given("^User is landing on the ANZ Loan Calculator page$")
	public void User_is_landing_on_the_ANZ_Loan_Calculator_page() throws InterruptedException
	{

		System.setProperty("webdriver.chrome.driver", "D:\\SeleniumJars\\chromedriver_win32\\chromedriver.exe");
		driver = new ChromeDriver();
		driver.get("https://www.anz.com.au/personal/home-loans/calculators-tools/much-borrow/");
		driver.manage().timeouts().implicitlyWait(5000, TimeUnit.SECONDS);
		driver.manage().window().maximize();

	}


	@When ("^User clicks on (.*) selects (.*) and clicks on (.*) under Your details section$")
	public void User_clicksonAppltype_selectsNoofDepdants_clicksonProptopay(String Applicationtype, int NumberofDependants, String Propertyyouwouldliketobuy)
	{
		driver.findElement(By.xpath(applType)).sendKeys(Applicationtype);
		WebElement wnoofdpdts = driver.findElement(By.xpath(noofDependents));

		if(wnoofdpdts.isDisplayed())
		{
			wnoofdpdts.click();
		}

		driver.findElement(By.xpath("//*[@title='Number of dependants']/../select/option["+NumberofDependants+"]")).click();

		driver.findElement(By.xpath("//label[contains(text(),'Home to live in')]")).sendKeys(Propertyyouwouldliketobuy);
	}

	@And ("^Also enters (.*) per year and (.*) per year under Your earnings section$")
	public void Also_entersYourincomepy_and_Yourotherincomepy_underYourearnssec(String Yourincome, String Yourotherincome)
	{
		driver.findElement(By.xpath("//label[contains(text(),'Your annual income (before tax)')]/../div/input")).sendKeys(Yourincome);
		driver.findElement(By.xpath("//label[contains(text(),'Your annual other income (optional)')]/../div/input")).sendKeys(Yourotherincome);
	}

	@And("^Lastly enters (.*)per month (.*)per month (.*)per month (.*)per month and (.*) under Your expenses")
	public void Lastlyenters_Expensedetails_underYourexpenses(String Livingexpenses, String Currenthomeloanrepayments, String Otherloanrepayments, String Othercommitments, String Totalcreditcardlimits)
	{
		driver.findElement(By.xpath("//label[contains(text(),'Monthly living expenses ')]/../div/input")).sendKeys(Livingexpenses);
		driver.findElement(By.xpath("//label[contains(text(),'Current home loan')]/../div/input")).sendKeys(Currenthomeloanrepayments);
		driver.findElement(By.xpath("//label[contains(text(),'Other loan')]/../div/input")).sendKeys(Otherloanrepayments);
		driver.findElement(By.xpath("//label[contains(text(),'Other monthly commitments')]/../div/input")).sendKeys(Othercommitments);
		driver.findElement(By.xpath("//label[contains(text(),'Total credit card limits')]/../div/input")).sendKeys(Totalcreditcardlimits);
	}

	@And("^User clicks on Work out how much I could borrow$")
	public void Userclickson_WorkouthowmuchIcouldborrow() throws InterruptedException
	{

		WebElement workoutbtn = driver.findElement(By.xpath("//button[contains(text(),'Work out how much I could borrow')]"));
		workoutbtn.click();

		Thread.sleep(3000);
	}

	@Then("^User would be shown the estimated amount$")
	public void User_wouldbe_shownthe_estimatedamount() throws InterruptedException
	{
		String expectamt = "$479000";
		WebElement resultantvalue = driver.findElement(By.xpath("//span[@class='borrow__result__text__amount homeloan__borrow__text']"));
		resultantvalue.click();
		String result = resultantvalue.getText();
		//System.out.println("result:   " +result);
		String actualamt = result.replace(",", "");

		//System.out.println("amount :"+actualamt);
		if(expectamt.equalsIgnoreCase(actualamt))
		{
			System.out.println("Both Expected amount and Actual amount match");
		}
		else
		{
			System.out.println("Both Expected amount : "+expectamt+"  and Actual amount : "+actualamt+" NOT match");
		}
	}

	@And("User clicks on Start Over button")
	public void User_clicks_on_Start_Over_button()
	{
		
		System.out.println("test..");

		WebElement startover = driver.findElement(By.xpath("//span[@class='borrow__result__text__amount homeloan__borrow__text']/following::div[1]/button"));
		startover.click();
	
		System.out.println("Page gets refreshed to create a new record");
	}
	

	@Then("^verify the customer help message (.*)$")
	public void verify_the_customer_help_message(String custmsg)
	{
		WebElement cmsg = driver.findElement(By.xpath("//div[@class='borrow__error__text']"));
		String strmsg = cmsg.getText();
		Assert.assertEquals(strmsg, custmsg);
	}
	
	@AfterTest
	@And("^User close the browser$")
	public void Userclose_thebrowser()
	{
		driver.quit();
	}


}

