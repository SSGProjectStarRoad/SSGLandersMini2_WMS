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
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.time.LocalDate;
import java.util.Map;
import java.util.Optional;

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
            Waybill waybill = Optional.ofNullable(outcomingService.getWaybill(oid)).orElse(Waybill.builder()
                            .wbid(0l)
                            .destination("미등록")
                            .date(LocalDate.of(2000,01,01))
                            .sid(0l)

                    .build());
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
    public String details(@RequestParam(name = "oid", required = false) Long oid, @RequestParam(name = "page", required = false) Long page ,RedirectAttributes redirectAttributes) {
        // oid를 쿼리 매개변수로 전달하여 리다이렉트
        redirectAttributes.addAttribute("oid", oid);
        log.info(oid + "!!!!!!!!!!!!!!!!!!!!!!!!!!!!");

        return "redirect:/ssglanders/outList?page="+page.toString();
    }







//    출고승인
    @GetMapping("/outApproval")
    public void approvalList(@RequestParam(name = "oid", required = false) Long oid, PageRequestDTO pageRequestDTO, Model model){
        PageResponseDTO<OutcomingListDTO> pageResponseDTO = outcomingService.getApprovalList(pageRequestDTO);
        log.info(pageResponseDTO+"=========페이지 리퀘스트========");
        log.info(oid+"get에서 받은거!!!");
        model.addAttribute("responseDTO",pageResponseDTO);
        model.addAttribute("oid",oid);
    }

    @PostMapping("/outApproval")
    public String approvalClick(@RequestParam(name = "oid", required = false) Long oid,
                                @RequestParam(name = "page", required = false) Long page ,RedirectAttributes redirectAttributes){
        redirectAttributes.addAttribute("oid", oid);


        return "redirect:/ssglanders/outApproval?page="+page.toString();
    }

    @PostMapping("/status")
    @ResponseBody
    public String handleStatusRequest(@RequestBody Map<String, String> request) {

        String oid = request.get("oid");
        String status = request.get("status");

        outcomingService.modifyStatus(Long.parseLong(status), Long.parseLong(oid));

        return "redirect:/ssglanders/outList";
    }

    @PostMapping("/removeOid")
    @ResponseBody
    public void removeOid(@RequestBody Map<String, String> request){

        String oid = request.get("oid");
        outcomingService.removeOutcomingByOid(Long.parseLong(oid));

    }

    @PostMapping("/modifyShipping")
    @ResponseBody
    public void modifyShipping(@RequestBody Map<String, String> request){
        String wbid = request.get("wbid");
        String sid = request.get("sid");
        outcomingService.modifyWaybillSidByWbidAndSid(Long.parseLong(wbid),Long.parseLong(sid));
        log.info("wbid = "+wbid);
        log.info("sid = "+sid);
    }

    @PostMapping("/registerWay")
    @ResponseBody
    public void registerWay(@RequestBody Map<String, String> request) {
        String uponNum = request.get("uponNum");
        String userAdd1 = request.get("userAdd1");
        String userAdd2 = request.get("userAdd2");
        String oid = request.get("oid");

        String fullAddress = uponNum+" "+userAdd1+" "+userAdd2;
        String date = request.get("date");
        String tekbe = request.get("tekbe");

        log.info(fullAddress);
        log.info(oid+" oid");
        log.info(tekbe+" 택배");

        //라스트 wbid가져오기
        long wbid = outcomingService.getLastWbidRegisterWaybill(fullAddress,date,Long.parseLong(tekbe));
        //outcoming에 wbid부여
        outcomingService.modifyOutcomingWbidByOid(Long.parseLong(oid),wbid);
        //승인완료
        outcomingService.modifyOutcomingApprovalByOid(Long.parseLong(oid));


    }

    @PostMapping("/registerOutcoming")
    @ResponseBody
    public void registerOutcoming(@RequestBody Map<String, String> request) {
        String name = request.get("name");
        String wname = request.get("wname");
        String quantity = request.get("quantity");

        log.info(name +" "+wname+" "+quantity+"%%%%%%%%@!");

        outcomingService.registerOutcomingByNameWnameQuantity(name,wname,Long.parseLong(quantity));

    }








}
