Feature: Reservation Booking

  Scenario: Prevent double booking
    Given a table is already booked at 7 PM
    When another customer tries to book the same table at 7 PM
    Then the system should reject the reservation