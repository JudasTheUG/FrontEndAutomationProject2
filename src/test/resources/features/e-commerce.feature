Feature: E-commerce

  Background:
    Given I initialize chrome driver
    When I go to "https://www.trendyol.com/" url
    Then I see home page
    And I wait close image button element 3 seconds
    Then I click on element: close image button index: 1

  @addToBasket
  Scenario: 1 - Add product to cart with user login
    When I focus sign in title element
    Then I click on element: login button index: 1
    Then I see login page

    When I enter "seleniumdeneme8@gmail.com" text to email address bar text area
    Then I enter "123asd***" text to password bar text area

    When I click element: submit login button index: 1
    Then I see home page
    And I wait my account title element 4 seconds

    When  I click on element: my cart button index: 1
    Then I see basket page
    Then I wait for 3 seconds
    Then I check the basket with element: product list and empty the basket if it has product on index: 1

    When I click on element: homepage button index: 1
    Then I see home page

    When I enter "Bluetooth Kulaklık" text to search input box text area
    Then I click "ENTER" keyboard button
    And I see search results page

    When I focus order element
    Then I click element: order index: 1
    And I wait for 2 seconds

    When I click element: order item index: 1
    Then I wait for 3 seconds
    Then I click "ESC" keyboard button
    Then I click on element: overlay index: 1

    When I click element: model index: 1
    Then I wait model option element 3 seconds
    Then I click element: model option index: 1
    Then I wait for 3 seconds

    When I click element: add to cart button index: 1
    Then I wait continue to cart button element 3 seconds

    When I click element: my cart button index: 1
    Then I wait for 3 seconds
    And I see basket page