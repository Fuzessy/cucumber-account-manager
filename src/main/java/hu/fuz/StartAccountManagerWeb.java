package hu.fuz;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class StartAccountManagerWeb
{
    public static void main( String[] args ) {
        System.out.println( "App start..." );
        SpringApplication.run(StartAccountManagerWeb.class);
    }
}
