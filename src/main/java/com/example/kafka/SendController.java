package com.example.kafka;

import com.octotelematics.otp.serialization.SerializationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author AFIORE
 * Created on 05/06/2019
 */
@RestController
@RequestMapping("/send")
public class SendController {

    @Autowired
    private SendService sendService;

    @GetMapping
    public String send() {
        sendService.send();
        return "Messange sent";
    }

}
