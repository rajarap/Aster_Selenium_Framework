#Summary 
This project include front-end automation test cases for the Humanify Neighbourhood application.

The framework is build on Java, using Selenium as backbone test automation library for web browser automation.

The building tool used is Maven and the test runner used is TestNG for executing the test cases.

Page Object Pattern is used to create page objects for representation of the website pages, which are later used in creating tests.

#Prerequisites
For running the project locally or in pipeline, you will need to have Java installed and also the browsers which you want to run tests upon.
*Note:* Later on when docker solution is available these prerequisites may not be mandatory for running in pipeline.

#Running the Project
1. Run the project locally in IntelliJ

The environment has to be passed as a VM option of TestNG in the Run Configuration of the project. Currently, accepted values are "DEV" and "QA". The environment need to be passed as Run Configuration of the project as **-Denv** parameter
The user credentials in the project are encrypted and not stored as plane text. The decryption key need to be passed also as Run Configuration of the project as **-Dkey** parameter.

For example, if you need to run test cases on DEV environment with a decryption key QWERTY, you need to set the following string in **Run -> Edit Configuration -> VM options**:

`-ea -Denv=DEV -Dkey=QWERTY`

To configure default values of the TestNG VM options go to **Run ->  Edit Configuration -> Edit Configuration Templates -> TestNG -> VM options** and put the same value from above. Apply and Save. After that each test case will have this VM options set by default.

2. Run the project in the terminal
Run mvn commands below to start local parallel test execution. Selenium grid with docker is developed, but not available solution at this time due to versions mismatch.
Test Suites are configured in *testng.xml files and their location is defined in pom.xml (src\test\resources). File's name should be provided runtime.
The following command should be executed in the terminal to run the test cases in this project:
mvn test -Dkey=<decryption key> -Denv=<environment> -DsuiteXmlFile=<testng.xml>		
eg:
mvn test -Dkey=key123 -Denv=QA -DsuiteXmlFile=smoke-admin-testng.xml
   
#Configuration
In data.properties file are stored all configurations used for local or remote test execution, URLs, browsers.

## Start using git hooks 
In order to start using git hooks and to make them active you need to execute this command once after repository checkout:
git config core.hooksPath <path to .githooks folder >
ex. git config core.hooksPath ~/HnV2_repo/HnV2_QA_Frontend_Automation/.githooks

