Feature: user can login to Demo app with specific credentials

  Scenario Outline: Successful login
    Given I'm on <login> page
    And fill "user name" with <user>
    And  I fill "password" with <password>
    When I click on "Login" button
    Then I should be redirected on <home> page


    Examples:
      | login                       | user | password | home                       |  |
      | http://localhost:8090/login | user | password | http://localhost:8090/home |  |