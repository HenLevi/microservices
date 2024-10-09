package com.code.service_b.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class MsgController {

    @GetMapping("/msg")
    public void getMsg() {
        System.out.println( "Hello Microsoft!");
    }
}