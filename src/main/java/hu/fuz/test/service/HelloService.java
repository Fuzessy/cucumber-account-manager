package hu.fuz.test.service;

import org.springframework.lang.NonNull;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/hello")
public class HelloService {

    @GetMapping("/{name}")
    public String hello(@PathVariable @NonNull String name){
        return "Hello kedves " + name + "!";
    }
}
