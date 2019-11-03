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
      And "Zsolt" számlájához adunk -10 Ft-ot
    Then "Zsolt" számlaegyenlege 90 Ft lesz
      And "Macska" számlaegyenlege 200 Ft lesz

  Scenario: Átvezetés számlák között
    Given "Zsolt"-nak 0 Ft van a számláján
      And "Macska"-nak 5000 Ft van a számláján
      And "Zoli"-nak 5000 Ft van a számláján
      And "Marcinak"-nak 5000 Ft van a számláján
    When A következő tranzakciók jönnek létre
      | Kezdeményező | Kedvezményezett | Összeg |
      | Macska       | Zsolt           | 1000   |
      | Zsolt        | Zoli            |  500   |
      | Marci        | Macska          | 1000   |
    Then A felhasználók számlaegyenlege a következő lesz
      | Felhasználó | Egyenleg |
      | Zoli        | 5500     |
      | Macska      | 5000     |
      | Marci       | 4000     |
      | Zsolt       |  500     |
