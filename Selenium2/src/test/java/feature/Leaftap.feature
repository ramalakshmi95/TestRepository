Feature: Create the file for Create Lead 

Background: Login to the browser
Given Launch the url
And Enter username as DemosalesManager
And Enter password as crmsfa
When user  clicks on login button
Then verify login is successfull

Scenario Outline: Create Lead for leaftaps
Given Click on the Create Lead link
And Enter the company Name as <Company Name>
And Enter the first Name as <First Name>
And Enter the first Name as <Last Name>
When user  clicks on submit button
Then verify whether lead is created

Examples:
	|Company Name|First Name|Last Name|
	|Temenos|Papu|Ramu|
	|TCS|Jayshri|jc|
	|CTS|Prashanth|jc|



