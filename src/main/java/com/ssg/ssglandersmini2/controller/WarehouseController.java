package com.ssg.ssglandersmini2.controller;

import com.ssg.ssglandersmini2.domain.Warehouse;
import com.ssg.ssglandersmini2.dto.PageRequestDTO;
import com.ssg.ssglandersmini2.dto.PageResponseDTO;
import com.ssg.ssglandersmini2.dto.WarehouseDTO;
import com.ssg.ssglandersmini2.mappers.WarehouseMapper;
import com.ssg.ssglandersmini2.service.interfaces.WarehouseService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
@RequestMapping("/ssglanders")
@Log4j2
@RequiredArgsConstructor
public class WarehouseController {
    private final WarehouseService warehouseService;
    private final ModelMapper modelMapper;
    private final WarehouseMapper warehouseMapper;



    @GetMapping("/warehouse")
    public String list(PageRequestDTO pageRequestDTO, Model model) {
        PageResponseDTO<WarehouseDTO> responseDTO = warehouseService.list(pageRequestDTO);
        model.addAttribute("responseDTO", responseDTO);
        return "ssglanders/warehouse"; // 검색 결과를 보여줄 뷰 페이지 경로
    }




    @GetMapping("/register")
    public String showRegisterForm(Model model) {
        log.info("warehouse register GetMapping doing......");
        model.addAttribute("warehouseDTO", new WarehouseDTO());
        return "warehouse"; // 창고 등록 폼을 보여주는 Thymeleaf 템플릿의 이름
    }


    @PostMapping("/register")
    public String registerPost(@ModelAttribute WarehouseDTO warehouseDTO, RedirectAttributes redirectAttributes,
                               @RequestParam("warehousetype") String warehousetype,
                               @RequestParam("zipp_code") String zipp_code,
                               @RequestParam("UserAdd1") String UserAdd1,
                               @RequestParam("UserAdd2") String UserAdd2,
                               @RequestParam("capacity") int capacity

            )
    {
        log.info("warehouse register Post doing...");
        // 주소를 fullAddress 하나로 합치기
        String fullAddress = String.join(" ", UserAdd1, UserAdd2);

        //warehouseDTO에 뷰에서 입력한 값 넣기
        warehouseDTO.setWarehousetype(warehousetype);
        warehouseDTO.setAddress(fullAddress); // 합친 주소 설정
        warehouseDTO.setCapacity(capacity);

        // 서비스 계층에 DTO 전달
        warehouseService.register(warehouseDTO);

        redirectAttributes.addAttribute("success", true);
        return "redirect:warehouse";

    }

    @GetMapping({"/read"})
    public void read(long wid, Model model) {
        WarehouseDTO warehouseDTO = warehouseService.getOne(wid);
        model.addAttribute("warehouseDTO", warehouseDTO);
    }

    @PostMapping("/modify")
    public String modify(@RequestBody WarehouseDTO warehouseDTO) {
        log.info("warehouse modify doing...");
        warehouseService.modify(warehouseDTO);

        // 성공 응답 반환
        return "redirect:warehouse";
    }

    @PostMapping("/remove")
    public String remove(long wid) {
        log.info(wid + " warehouse remove doing...");
        warehouseService.remove(wid);
        return "redirect:warehouse";
    }
}
