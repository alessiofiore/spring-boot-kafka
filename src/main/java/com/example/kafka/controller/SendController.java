package com.example.kafka.controller;

import com.example.kafka.service.SendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/send")
public class SendController {

    @Autowired
    private SendService sendService;

    @GetMapping
    public String send(@RequestParam("msg") String message) {
        sendService.send(message);
        return "Messange sent";
    }

}
