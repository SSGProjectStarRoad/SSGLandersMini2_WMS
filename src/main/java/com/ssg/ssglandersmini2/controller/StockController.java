package com.ssg.ssglandersmini2.controller;

import com.ssg.ssglandersmini2.dto.PageRequestDTO;
import com.ssg.ssglandersmini2.mappers.StockMapper;
import com.ssg.ssglandersmini2.service.interfaces.StockService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
@RequestMapping("/ssglanders")
@Log4j2
@RequiredArgsConstructor
public class StockController {
    private final StockService stockService;
    private StockMapper stockMapper;

    @GetMapping("/stock")
    public void stock(PageRequestDTO pageRequestDTO, Model model) {
        log.info(stockService.getWarehouseList());
        model.addAttribute("responseDTO", stockService.getList(pageRequestDTO));
        model.addAttribute("list", stockService.getAll());
        model.addAttribute("whList", stockService.getWareHouse());
        model.addAttribute("wrList",stockService.getWarehouseList());

    }

    @PostMapping("/stock")
    public void outcomingRequest(@RequestParam("productSelect") String productSelect,
                                 @RequestParam("warehouseSelect") String warehouseSelect,
                                 @RequestParam("quantity") int quantity,
                                 @RequestParam("")
                                 RedirectAttributes redirectAttributes) {
        log.info("outcomingRequest~~~!!");
        log.info(productSelect + warehouseSelect + quantity);
        redirectAttributes.addFlashAttribute("productSelect", productSelect);
        redirectAttributes.addFlashAttribute("warehouseSelect", warehouseSelect);
        redirectAttributes.addFlashAttribute("quantity", quantity);
        return ;

    }
}

