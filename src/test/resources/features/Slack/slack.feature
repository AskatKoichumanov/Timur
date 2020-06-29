@slack
Feature: slack API test

  @postViaAPI @checkViaAPI
  Scenario: Post via API check via API
    When write message via API with text "Sorry, test1"
    And  get message timeStamp with "Sorry, test1"
    Then verify message via API with text "Sorry, test1"
    And  delete message

  @postViaSelenium @checkViaSelenium
  Scenario: Post via Selenium check via Selenium
    Given go to channel page
    When  write message via Selenium with text "Sorry, test2"
    Then  verify message via Selenium with text "Sorry, test2"

  @postViaAPI @checkViaSelenium
  Scenario: Post via API check via Selenium
    When write message via API with text "Sorry, test3"
    And  go to channel page
    Then verify message via Selenium with text "Sorry, test3"
    And  get message timeStamp with "Sorry, test3"
    And  delete message

  @postViaSelenium @checkViaAPI
  Scenario: Post via Selenium check via API
    Given go to channel page
    When write message via Selenium with text "Sorry, test4"
    And  get message timeStamp with "Sorry, test4"
    Then verify message via API with text "Sorry, test4"

  @deleteViaAPI @checkViaAPI
  Scenario: Delete via API check via API
    Given write message via API with text "Sorry, test5"
    When  get message timeStamp with "Sorry, test5"
    And   delete message
    And   get message timeStamp with "Sorry, test5"
    Then  verify message deleted via API

  @deleteViaAPI @checkViaSelenium
  Scenario: Delete via API check via Selenium
    Given write message via API with text "Sorry, test6"
    When  get message timeStamp with "Sorry, test6"
    And   delete message
    Then  verify message deleted via Selenium with text "Sorry, test6"
