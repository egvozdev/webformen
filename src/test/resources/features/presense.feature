Feature: Presense Table
  Scenario: Check forms
    Given User opens Verbformen homepage
    When User enters word "gehen"
    Then table "forms" should contain data:
      | Pronoun | Form   |
      | ich     | gehe   |
      | du      | gehst  |

