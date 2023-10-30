package com.dodone.dodone;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TodoController {


    @GetMapping("/test")
    public int test() {
        return 1;
    }
}
