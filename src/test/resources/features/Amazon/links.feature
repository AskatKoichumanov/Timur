@amazon
Feature: print links from amazon

  Background:
    Given navigate to amazon

    @allLinks
    Scenario: Print out all links
      When user gets all links
      Then print "all" links

    @workingLinks
    Scenario: Print out working links
      When user gets all links
      Then print "working" links

