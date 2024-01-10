package org.example.server.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class TestController {

    @GetMapping("user/start")
    String getTestString() {
        return "user/start";
    }

}
