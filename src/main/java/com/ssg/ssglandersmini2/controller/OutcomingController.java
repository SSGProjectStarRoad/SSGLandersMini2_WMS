package com.ssg.ssglandersmini2.controller;

import com.ssg.ssglandersmini2.domain.Shippingcompany;
import com.ssg.ssglandersmini2.domain.Waybill;
import com.ssg.ssglandersmini2.dto.DetailsDTO;
import com.ssg.ssglandersmini2.dto.OutcomingListDTO;
import com.ssg.ssglandersmini2.dto.PageRequestDTO;
import com.ssg.ssglandersmini2.dto.PageResponseDTO;
import com.ssg.ssglandersmini2.service.interfaces.OutcomingService;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
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
    public void list(@RequestParam(name = "oid", required = false) Long oid, PageRequestDTO pageRequestDTO, Model model) {
        if (oid != null) {
            DetailsDTO detailsDTO = outcomingService.getDetails(oid);
            model.addAttribute("detail", detailsDTO);
            //oid의 waybill 정보
            Waybill waybill = outcomingService.getWaybill(oid);
            model.addAttribute("waybill", waybill);

            Shippingcompany shippingcompany = outcomingService.getShippingcompanyByWbid(waybill.getWbid());
            model.addAttribute("shippingcompany", shippingcompany);

            log.info(shippingcompany + "나오냐?");
        }
        PageResponseDTO<OutcomingListDTO> pageResponseDTO = outcomingService.getList(pageRequestDTO);
        log.info(pageResponseDTO+"=========페이지 리퀘스트========");

        // oid의 detail 정보
        model.addAttribute("responseDTO", pageResponseDTO);

    }

    @PostMapping("/outList")
    public String details(@RequestParam(name = "oid", required = false) Long oid, RedirectAttributes redirectAttributes) {
        // oid를 쿼리 매개변수로 전달하여 리다이렉트
        redirectAttributes.addAttribute("oid", oid);
        log.info(oid + "!!!!!!!!!!!!!!!!!!!!!!!!!!!!");
        return "redirect:/ssglanders/outList";
    }

    @PostMapping("/status")
    public String handleStatusRequest(@RequestParam("selectedValue") String selectedValue
            , @RequestParam("selectedOid") Long oid) {
        // 선택된 값에 대한 처리
        log.info("Selected value:$$$$$$$$$$$$$$$ " + selectedValue);
        log.info("oid " + oid);

        outcomingService.modifyStatus(Long.parseLong(selectedValue), oid);

        return "redirect:/ssglanders/outList";
    }

    @PostMapping("/modifyShipping")
    public String modifyShipping(@RequestParam("selectShipping") long selectShipping,
                                 @RequestParam("wbid") long wbid){

        outcomingService.modifyWaybillSidByWbidAndSid(wbid,selectShipping);

        return "redirect:/ssglanders/outList";
    }

    @PostMapping("/removeOid")
    public String removeOid(@RequestParam("oid") long oid){

        log.info(oid+"삭제할 oid !!!");
        outcomingService.removeOutcomingByOid(oid);

        return "redirect:/ssglanders/outList";
    }

//    출고승인
    @GetMapping("/outApproval")
    public void approvalList(@RequestParam(name = "oid", required = false) Long oid, PageRequestDTO pageRequestDTO, Model model){
        PageResponseDTO<OutcomingListDTO> pageResponseDTO = outcomingService.getList(pageRequestDTO);
        log.info(pageResponseDTO+"=========페이지 리퀘스트========");

        model.addAttribute("responseDTO",pageResponseDTO);
    }

}
