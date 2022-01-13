package cucumberTests;

import org.junit.runner.RunWith;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;



@RunWith(Cucumber.class)
@CucumberOptions(features="features",
				 glue="stepdefinition",
				
tags= {"@Scenario1,@Scenario2,@Scenario3"})

public class LoanEligibilityRunFile {

	 
}
