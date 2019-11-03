Feature: Számlaegyenleg változtatása

  Scenario: Füzesi Zsolt egyenlegének változtatása
    Given "Füzesi Zsolt"-nak van új számlája
    When "Füzesi Zsolt" számlájához adunk 100 Ft-ot
    Then "Füzesi Zsolt" számlaegyenlege 100 Ft lesz


  Scenario: Több számla kezelése
    Given "Zsolt"-nak van új számlája
      And "Macska"-nak van új számlája
    When "Zsolt" számlájához adunk 100 Ft-ot
      And "Macska" számlájához adunk 200 Ft-ot
      And "Zsolt" számlájához adunk 10 Ft-ot
    Then "Zsolt" számlaegyenlege 90 Ft lesz
      And "Macska" számlaegyenlege 200 Ft lesz