1. van egy "üres" spring boot alkalmazásunk Nem tud semmit, csak köszönni.
    
    http://localhost:8080/hello/Zsolti

2. van egy új igény

    Az igény az, hogy kezeljünk számlákat és egyenlegeket. 
    Legyen a felhasználóknak számlája, tudjon a felhasználó 
    a számlájához pénzt adni illetve elvenni.
    
    a. írjuk le Gerkhin nyelv segítségével
       mindig "Feature:"-el kezdünk, majd a feature rövid leírása következik
                 
    ```Gherkin
     Feature: Számlaegyenleg változtatása
     
       Scenario: Füzesi Zsolt egyenlegének változtatása
         Given Füzesi Zsolt-nak van számlája
         When Füzesi Zsolt számláhához adunk 100 Ft-ot
         Then Füzesi Zsolt számlaegyenlege 100 Ft lesz
    ```
    + pom xml
    + java teszt fájlok
    