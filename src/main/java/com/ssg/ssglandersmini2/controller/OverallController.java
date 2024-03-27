package com.ssg.ssglandersmini2.controller;

import com.ssg.ssglandersmini2.service.interfaces.OverallService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/ssglanders")
@Log4j2
@RequiredArgsConstructor
public class OverallController {
    private final OverallService overallService;

    @GetMapping("/overall")
    public void list(Model model){
        model.addAttribute("WarehouseCityDTOlist",overallService.getWarehouseCityTotal());
        model.addAttribute("MonthlyDTO",overallService.getMonthlyInOut());

    }

    @GetMapping("/api/overall")
    @ResponseBody
    public ResponseEntity<?> getOverallStatus() {
        Map<String, Object> data = new HashMap<>();
        data.put("incomingcount", overallService.getIncomingCount().orElse(0));
        data.put("outcomingcount", overallService.getOutcomingCount().orElse(0));
        data.put("getIncomingBeforeCount", overallService.getIncomingBeforeCount().orElse(0));
        data.put("getIncomingArriveCount", overallService.getIncomingArriveCount().orElse(0));
        data.put("getOutcomingBeforeCount", overallService.getOutcomingBeforeCount().orElse(0));
        data.put("getOutcomingArriveCount", overallService.getOutcomingArriveCount().orElse(0));
        data.put("getNotApprovalCount", overallService.getNotApprovalCount().orElse(0));
        data.put("getTotalUsingCapacity", overallService.getTotalUsingCapacity().orElse(0));
        log.info("dataoverall"+data.toString());
        return ResponseEntity.ok().body(data);
    }
}
