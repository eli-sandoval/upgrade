#In order to run the project:
- import project as maven project
- download dependencies and sources
- run "mvn clean install"

#To run UI tests:
- set system properties -Dtimeout={desired_timeout}. Working timeout with existing elements = 30.
- set system properties -Dpollingevery={desired_polling}. eg=5.
- run borrower_suite.xml as TestNG test class.

#To run the IT tests:
- Simply execute StateListIT class.