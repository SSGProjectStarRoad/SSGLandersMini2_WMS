package com.ssg.ssglandersmini2.controller;

import com.ssg.ssglandersmini2.service.interfaces.OverallService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/ssglanders")
@Log4j2
@RequiredArgsConstructor
public class OverallController {
    private final OverallService overallService;

    @GetMapping("/overall")
    public void list(Model model){
        model.addAttribute("incomingcount",overallService.getIncomingCount());
        model.addAttribute("outcomingcount",overallService.getOutcomingCount());
        model.addAttribute("getIncomingBeforeCount",overallService.getIncomingBeforeCount());
        model.addAttribute("getIncomingArriveCount",overallService.getIncomingArriveCount());
    }
}
