package com.ssg.ssglandersmini2.controller;

import com.ssg.ssglandersmini2.domain.Incoming;
import com.ssg.ssglandersmini2.dto.*;
import com.ssg.ssglandersmini2.service.interfaces.IncomingService;
import com.ssg.ssglandersmini2.service.interfaces.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/ssglanders")
@Log4j2
@RequiredArgsConstructor
public class IncomingController {

    private final IncomingService incomingService;
    private final ProductService productService;

    @GetMapping("/inList")
    public void getInList(PageRequestDTO pageRequestDTO, BindingResult bindingResult, Model model) {

        log.info("inList......");

        if (bindingResult.hasErrors()) {
            pageRequestDTO = PageRequestDTO.builder().build();
        }

        model.addAttribute("responseDTO", incomingService.getIncomingList(pageRequestDTO));
        log.info("spread incoming list data!!!");
    }

    @GetMapping("/inApproval")
    public void getInApproval(PageRequestDTO pageRequestDTO, BindingResult bindingResult, Model model) {
        log.info("inList......");

        if (bindingResult.hasErrors()) {
            pageRequestDTO = PageRequestDTO.builder().build();
        }

        model.addAttribute("responseDTO", incomingService.getIncomingNotApprovalList(pageRequestDTO));
        log.info("spread incoming list data!!!");
    }

    @GetMapping("/inRegister")
    public void getInRegister(PageRequestDTO pageRequestDTO, BindingResult bindingResult, Model model) {
        log.info("inRegister......");

        if (bindingResult.hasErrors()) {
            pageRequestDTO = PageRequestDTO.builder().build();
        }

        model.addAttribute("responseDTO", productService.getProductList(pageRequestDTO));
        log.info("spread product list data!!!");
    }

    @PostMapping("/inRegister")
    @ResponseBody
    public String postInRegister(@RequestBody Map<String, String> requestBody, BindingResult bindingResult, RedirectAttributes redirectAttributes) {
        log.info("post register incoming.....");
        log.info(requestBody);

        incomingService.register(IncomingDTO.builder()
                .pid(Long.parseLong(requestBody.get("pid")))
                .wid(incomingService.getWidByWarehouseName(requestBody.get("name")))
                .quantity(Integer.parseInt(requestBody.get("quantity")))
                .date(LocalDate.parse(requestBody.get("regdate")))
                .build());

        if (bindingResult.hasErrors()) {
            log.info("has error");
            redirectAttributes.addFlashAttribute("errors", bindingResult.getAllErrors());
            return "redirect:/ssglanders/inRegister";
        }
        return "redirect:/ssglanders/inRegister";
    }


    @PostMapping("/getWarehouseList")
    @ResponseBody
    public ResponseEntity<?> getWarehouseListByType(@RequestBody Map<String, String> requestBody,RedirectAttributes redirectAttributes) {
        String type = requestBody.get("type");
        List<Map<String, String>> warehouseList = incomingService.getWarehouseListByType(type);
        log.info(warehouseList);
        return ResponseEntity.ok().body(warehouseList);
    }

    @PostMapping("/updateIncoming")
    @ResponseBody
    public void updateIncomingStatus(@RequestBody Map<String, String> requestBody){
        log.info(Integer.parseInt(requestBody.get("quantity")));
        log.info(LocalDate.parse(requestBody.get("regdate")));
        incomingService.modify(IncomingDTO.builder()
                .iid(Long.parseLong(requestBody.get("iid")))
                .pid(Long.parseLong(requestBody.get("pid")))
                .wid(Long.parseLong(requestBody.get("wid")))
                .quantity(Integer.parseInt(requestBody.get("quantity")))
                .date(LocalDate.parse(requestBody.get("regdate")))
                .status(requestBody.get("status"))
                .approval(requestBody.get("approval"))
                .build());


    }


    @PostMapping("/deleteIncoming")
    @ResponseBody
    public void deleteIncomingStatus(@RequestBody Map<String, String> requestBody){
        incomingService.remove(Long.parseLong(requestBody.get("iid")));
    }

    @PostMapping("/approveApprovalData")
    @ResponseBody
    public void approveApproval(@RequestBody Map<String, Long> requestBody){
        incomingService.modifyApprovalByIid(requestBody.get("iid"));
    }

    @PostMapping("/changeStatus")
    @ResponseBody
    public void changeStatus(@RequestBody Map<String, Long> requestBody){
        log.info(requestBody);
        log.info(requestBody.get("iid"));
        incomingService.changeStatus(requestBody.get("iid"));
    }

    @PostMapping("/checkWarehouseCapacity")
    @ResponseBody
    public ResponseEntity<?> checkWarehouseCapacity(@RequestBody Map<String, String> requestBody, BindingResult bindindingResult){

        if (incomingService.compareCapacity(Long.parseLong(requestBody.get("wid")), Integer.parseInt(requestBody.get("quantity")))){
            return ResponseEntity.ok().body(true);
        }else return ResponseEntity.ok().body(false);

    }
}





