Feature: Search translation

  Scenario: Search for wunderbar
    Given User opens Verbformen homepage
    When User enters word "wunderbar"
#    Then Russian translation should be displayed
    Then Translation should contain "wonderful"