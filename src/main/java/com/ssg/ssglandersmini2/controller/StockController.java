package com.ssg.ssglandersmini2.controller;

import com.ssg.ssglandersmini2.dto.PageRequestDTO;
import com.ssg.ssglandersmini2.dto.StockDTO;
import com.ssg.ssglandersmini2.mappers.StockMapper;
import com.ssg.ssglandersmini2.service.interfaces.StockService;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/ssglanders")
@Log4j2
@RequiredArgsConstructor
public class StockController {
    private final StockService stockService;
    private StockMapper stockMapper;

    @GetMapping("/stock")
    public void stock(PageRequestDTO pageRequestDTO, Model model) {
        model.addAttribute("responseDTO", stockService.getList(pageRequestDTO));
        model.addAttribute("list", stockService.getAll());
        model.addAttribute("whList", stockService.getWareHouse());

    }

    @PostMapping("/stock")
    @ResponseBody
    public ResponseEntity<?> outcomingRequest(@RequestBody Map<String, String> requestbody,
                                           Model model) {
        List<StockDTO> stocklist = stockService.getWarehouseList(requestbody.get("wname"));
        log.info("여기 뭐야?" + stocklist);
        log.info("여기 뭐야?2" + requestbody.get("wname"));

        return ResponseEntity.ok().body(stocklist);
    }
}

