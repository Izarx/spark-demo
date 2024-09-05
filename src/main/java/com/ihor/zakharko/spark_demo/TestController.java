package com.ihor.zakharko.spark_demo;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/test")
@RequiredArgsConstructor
public class TestController {
    private final SparkDemoService popularWordsService;

    @GetMapping
    public ResponseEntity<Long> getTopX() {
//        return ResponseEntity.ok(popularWordsService.topX());
        return ResponseEntity.ok(popularWordsService.someMethod());
    }
}
