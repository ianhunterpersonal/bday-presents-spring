#Author: ian.hunter.personal@gmail.com
#Keywords Summary : Sessions User Login Logout
#Feature: List of scenarios.
#Scenario: Business rule through list of steps with arguments.
#Given: Some precondition step
#When: Some key actions
#Then: To observe outcomes or validation
#And,But: To enumerate more Given,When,Then steps
#Scenario Outline: List of steps for data-driven as an Examples and <placeholder>
#Examples: Container for s table
#Background: List of steps run before each of the scenarios
#""" (Doc Strings)
#| (Data Tables)
#@ (Tags/Labels):To group Scenarios
#<> (placeholder)
#""
## (Comments)

#Sample Feature Definition Template
##@tag 
Feature: Handling of Sessions in the Backend
  
  This feature is about managing authorisation and authentication of users to the system.
  The customer can log in giving their credentials and be granted an authentication token for
  further access to the system. Some users can be given extra admin privileges.

  ##@tag1
  Scenario: User logs in with valid credentials
    Given I create users
			| id   | name         | email         | password |
			| p1   | Ian          | ian@email.com | password |

    When I login with email 'ian@email.com' and password 'password'
    Then I recieve a valid token from server
    And I recieve HTTP Status '201'

  Scenario: User logs in with bad credentials
    Given I create users
			| id   | name         | email         | password |
			| p1   | Ian          | ian@email.com | password |

    When I login with email 'ian@email.com' and password 'password_BAD'
    Then I am not logged in
    And I recieve HTTP Status '401'

	
			
##@tag2
##  Scenario Outline: Title of your scenario outline
##    Given I want to write a step with <name>
##  When I check for the <value> in step
##    Then I verify the <status> in step

##    Examples: 
##      | name  | value | status  |
##      | name1 |     5 | success |
##      | name2 |     7 | Fail    |
