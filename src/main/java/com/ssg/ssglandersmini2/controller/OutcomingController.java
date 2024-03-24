package com.ssg.ssglandersmini2.controller;

import com.ssg.ssglandersmini2.dto.DetailsDTO;
import com.ssg.ssglandersmini2.dto.OutcomingListDTO;
import com.ssg.ssglandersmini2.dto.PageRequestDTO;
import com.ssg.ssglandersmini2.dto.PageResponseDTO;
import com.ssg.ssglandersmini2.service.interfaces.OutcomingService;
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
public class OutcomingController {

    private final OutcomingService outcomingService;

    @GetMapping("/outList")
    public void list(@RequestParam(name = "oid", required = false) Long oid, PageRequestDTO pageRequestDTO, Model model){
        if (oid != null) {
            DetailsDTO detailsDTO = outcomingService.getDetails(oid);
            model.addAttribute("detail", detailsDTO);
        }
        PageResponseDTO<OutcomingListDTO> pageResponseDTO = outcomingService.getList(pageRequestDTO);
        log.info(pageResponseDTO);
        model.addAttribute("responseDTO", pageResponseDTO);
    }

    @PostMapping("/outList")
    public String details(long oid, RedirectAttributes redirectAttributes) {
        // oid를 쿼리 매개변수로 전달하여 리다이렉트
        redirectAttributes.addAttribute("oid", oid);
        log.info(oid + "!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        return "redirect:/ssglanders/outList";
    }



    @GetMapping("/test")
    public void test(){
        log.info("test진입!!");
    }


}
