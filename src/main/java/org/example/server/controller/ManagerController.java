package org.example.server.controller;


import lombok.RequiredArgsConstructor;
import org.example.server.model.EPIdForm;
import org.example.server.model.OrderNumberForm;
import org.example.server.model.enterprise.EnterprisePoint;
import org.example.server.service.EPService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@RequiredArgsConstructor
@Controller
@RequestMapping("/manager")
public class ManagerController {

    private final EPService epService;

    @PostMapping("/setEP")
    String setEPService(@ModelAttribute EPIdForm idEPform, Model model){
        EnterprisePoint ep = epService.getEnterprisePointById(idEPform.getIdEP());
        model.addAttribute("EP", ep);
        return ("manager/main");
    }

    @GetMapping("/start")
    String getStartManagerPage(Model model){
        model.addAttribute("idEPform", new EPIdForm());
        return ("manager/start");
    }

}
