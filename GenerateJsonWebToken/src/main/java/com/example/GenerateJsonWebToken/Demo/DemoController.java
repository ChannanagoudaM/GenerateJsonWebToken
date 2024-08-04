package com.example.GenerateJsonWebToken.Demo;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v2")
@RequiredArgsConstructor
public class DemoController {


    @GetMapping("/demo")
    public ResponseEntity<String> demo()
    {
        return ResponseEntity.ok("hello");
    }
}
