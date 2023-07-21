Feature: One App Automation Testing

  @addNewCard
  Scenario: Add something to do today.
    Given I opened the app
    When I click on burger icon
    And I click on today
    Then I should see today word at the navbar
    When I click on plus button
    And I enter title for thing i will do
    And I enter note for thing i will do
    And I click on tag
    Then I should see quick action menu displayed to select tag
    And I select work tag
    And I click on ok button
    When I click on start date
    Then I should see start date piker displayed
    And I select today from start date piker
    And I click on ok button
    Then I should see today selected
    When I click on due date
    Then I should see due date piker displayed
    And I select today from due date piker
    And I click on ok button
    Then I should see today selected
    When I click on priority flag text field
    Then I should see quick action menu for priority displayed
    And I select medium priority
    And I click on ok button
    And I click on save button
    Then I should see in today board card with the title i entered

    @updateExistenceCard
    Scenario: Update card already exist
      Given I click on the card
      And I click on tag
      Then I should see quick action menu displayed to select tag
      And I select personal tag
      And I click on ok button
      And I click on due date
      Then I should see due date piker displayed
      And I select 31 from due date piker
      And I click on ok button
      Then I should see mon 31 jul selected
      And I click on update button






