package org.example.server.controller;


import lombok.RequiredArgsConstructor;
import org.example.server.model.EPIdForm;
import org.example.server.model.OrderNumberForm;
import org.example.server.model.enterprise.EnterprisePoint;
import org.example.server.model.logistics.Order;
import org.example.server.service.EPService;
import org.example.server.service.OrderService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@RequiredArgsConstructor
@Controller
@SessionAttributes("ep")
@RequestMapping("/manager")
public class ManagerController {

    private final EPService epService;
    private final OrderService orderService;

    @PostMapping("/setEP")
    String setEPService(@ModelAttribute EPIdForm idEPform, Model model) {
        EnterprisePoint ep = epService.getEnterprisePointById(idEPform.getIdEP());
        model.addAttribute("ep", ep);
        return ("manager/main");
    }

    @GetMapping("/main")
    String getMain(Model model){
        return ("manager/main");
    }

    @GetMapping("/start")
    String getStartManagerPage(Model model) {
        model.addAttribute("idEPform", new EPIdForm());
        return ("manager/start");
    }

    @GetMapping("/factoryPotion")
    String getFactoryPotionPage(Model model) {
        return "manager/factoryPotion";
    }

    @GetMapping("/historyOrder")
    String getHistoryOrderPage(Model model,
                               @RequestParam Optional<Integer> page,
                               @RequestParam Optional<Integer> size) {

        int currentPage = page.orElse(1);
        int pageSize = size.orElse(10);

        Page<Order> orderPage = orderService.findPaginated(PageRequest.of(currentPage - 1, pageSize));

        model.addAttribute("orderPage", orderPage);

        int totalPages = orderPage.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }

        return "manager/historyOrder";
    }

    @GetMapping("/orderIngredient")
    String getOrderIngredientPage(Model model) {
        return "manager/orderIngredient";
    }

    @GetMapping("/orderList")
    String getOrderListPage(Model model,
                            @RequestParam Optional<Integer> page,
                            @RequestParam Optional<Integer> size
    ) {
        int currentPage = page.orElse(1);
        int pageSize = size.orElse(10);

        Page<Order> orderPage = orderService.findPaginatedActive(PageRequest.of(currentPage - 1, pageSize));

        model.addAttribute("orderPage", orderPage);

        int totalPages = orderPage.getTotalPages();
        if (totalPages > 0) {
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages)
                    .boxed()
                    .collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }
        return "manager/orderList";
    }

    @GetMapping("/warehouse")
    String getWarehousePage(Model model) {
        return "manager/warehouse";
    }

}
