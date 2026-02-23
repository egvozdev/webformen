Feature: Dictionary Search
Scenario Outline: Search different words
Given User opens Verbformen homepage
When User enters word "<word>"
Then Translation should contain "<translation>"

Examples:
| word       | translation |
| wunderbar  | wonderful   |
| Haus       | house       |
| gehen      | go          |