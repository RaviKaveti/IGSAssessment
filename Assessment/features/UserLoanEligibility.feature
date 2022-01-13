Feature: Test the Loan calculator functionality

@Scenario1
Scenario Outline: Verifying the Customer loan eligibility details

Given User is landing on the ANZ Loan Calculator page
When User clicks on <Applicationtype> selects <NumberofDependants> and clicks on <Propertyyouwouldliketobuy> under Your details section
And Also enters <Yourincome> per year and <Yourotherincome> per year under Your earnings section
And Lastly enters <Livingexpenses> per month <Currenthomeloanrepayments> per month <Otherloanrepayments> per month <Othercommitments> per month and <Totalcreditcardlimits> under Your expenses
And User clicks on Work out how much I could borrow
Then User would be shown the estimated amount 

Examples:

|Applicationtype | NumberofDependants|Propertyyouwouldliketobuy|Yourincome|Yourotherincome|Livingexpenses|Currenthomeloanrepayments|Otherloanrepayments|Othercommitments|Totalcreditcardlimits|
|Single			 |1	           		 |Home to Live in		   |80000     |10000          |500           |0			               |100		           |0 	         	|10000		       	  |		

@Scenario2
Scenario: Verifying the existing information in Loan Calculater page gets refreshed

And User clicks on Start Over button

@Scenario3
Scenario Outline: Verifying the Customer loan eligibility details when the Living expenses is $1

When User clicks on <Applicationtype> selects <NumberofDependants> and clicks on <Propertyyouwouldliketobuy> under Your details section
And Also enters <Yourincome> per year and <Yourotherincome> per year under Your earnings section
And Lastly enters <Livingexpenses> per month <Currenthomeloanrepayments> per month <Otherloanrepayments> per month <Othercommitments> per month and <Totalcreditcardlimits> under Your expenses
And User clicks on Work out how much I could borrow
Then verify the customer help message <BorrowingEstimate>
And User close the browser

Examples:

|Applicationtype | NumberofDependants|Propertyyouwouldliketobuy|Yourincome|Yourotherincome|Livingexpenses|Currenthomeloanrepayments|Otherloanrepayments|Othercommitments|Totalcreditcardlimits|BorrowingEstimate|
|Single			 |1	           		 |Home to Live in	       |0  	 	  |0		      |1	         |0	        			   |0	           	   |0 	         		|0			      |Based on the details you've entered, we're unable to give you an estimate of your borrowing power with this calculator. For questions, call us on 1800 035 500.	|		


