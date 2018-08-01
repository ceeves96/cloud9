Feature: NewBooking

Scenario: SuccessfulBooking

    Given A valid login with email  "crobinson52@qub.ac.uk" and password "password"
    When  I input Origin as "Chicago"  and Destination as "Durban" and Seat as "21A" and Class as "First"
    Then the booking should be successful