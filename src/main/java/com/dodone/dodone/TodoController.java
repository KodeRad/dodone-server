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

// 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