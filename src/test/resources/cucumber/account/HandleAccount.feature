Feature: Számlaegyenleg változtatása

  Scenario: Füzesi Zsolt egyenlegének változtatása
    Given Füzesi Zsolt-nak van számlája
    When Füzesi Zsolt számláhához adunk 100 Ft-ot
    Then Füzesi Zsolt számlaegyenlege 100 Ft lesz