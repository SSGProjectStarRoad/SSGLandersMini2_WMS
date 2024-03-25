package com.ssg.ssglandersmini2.controller;

import com.ssg.ssglandersmini2.dto.PageRequestDTO;
import com.ssg.ssglandersmini2.dto.PageResponseDTO;
import com.ssg.ssglandersmini2.dto.ProductDTO;
import com.ssg.ssglandersmini2.service.interfaces.IncomingService;
import com.ssg.ssglandersmini2.service.interfaces.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/ssglanders")
@Log4j2
@RequiredArgsConstructor
public class IncomingController {

    private final IncomingService incomingService;
    private final ProductService productService;

    @GetMapping("/inList")
    public void list(PageRequestDTO pageRequestDTO, BindingResult bindingResult, Model model) {

        log.info("inList......");

        if (bindingResult.hasErrors()) {
            pageRequestDTO = PageRequestDTO.builder().build();
        }

        model.addAttribute("responseDTO", incomingService.getIncomingList(pageRequestDTO));
        log.info("spread incoming list data!!!");
    }

    @GetMapping("/inApproval")
    public void inApproval(PageRequestDTO pageRequestDTO, BindingResult bindingResult, Model model) {
        log.info("inList......");

        if (bindingResult.hasErrors()) {
            pageRequestDTO = PageRequestDTO.builder().build();
        }

        model.addAttribute("responseDTO", incomingService.getIncomingList(pageRequestDTO));
        log.info("spread incoming list data!!!");
    }

    @GetMapping("/inRegister")
    public void register(PageRequestDTO pageRequestDTO, BindingResult bindingResult, Model model) {
        log.info("inRegister......");

        if (bindingResult.hasErrors()) {
            pageRequestDTO = PageRequestDTO.builder().build();
        }

        model.addAttribute("responseDTO", productService.getProductList(pageRequestDTO));
        log.info("spread product list data!!!");
    }


}
