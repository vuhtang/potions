package org.example.server.controller;

import org.springframework.ui.Model;
import org.example.server.entities.Order;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class TestController {

    @GetMapping("user/start")
    String getStartPage() {
        return "user/start";
    }

    @GetMapping("user/checkStatusPage")
    String getCheckStatusPage() {
        return "user/checkStatusPage";
    }

}
