package com.satyamwin.controller;

import com.satyamwin.entity.*;
import com.satyamwin.repository.*;
import org.springframework.web.bind.annotation.*;

import java.time.*;
import java.util.*;

@RestController
@CrossOrigin
public class PublicController {

    private final DrawSettingsRepository drawRepo;
    private final CurrentDrawRepository currentRepo;
    private final DailyResultRepository dailyRepo;

    public PublicController(DrawSettingsRepository d,
                            CurrentDrawRepository c,
                            DailyResultRepository dr) {
        this.drawRepo = d;
        this.currentRepo = c;
        this.dailyRepo = dr;
    }

    @GetMapping("/header")
    public Map<String, Object> headerInfo() {

        Map<String, Object> map = new HashMap<>();
        DrawSettings ds = drawRepo.findAll().stream().findFirst().orElse(null);

        map.put("visitors", ds != null ? ds.getVisitors() : 0);
        map.put("nextDrawTime", ds != null ? ds.getNextDrawTime() : "--");

        // purely informational
        map.put("date", LocalDate.now().toString());
        map.put("time", LocalTime.now(ZoneId.of("Asia/Kolkata"))
                .withNano(0)
                .toString());

        return map;
    }

    @GetMapping("/current-draw")
    public CurrentDrawResult currentDraw() {
        return currentRepo.findAll().stream().findFirst().orElse(null);
    }

    @GetMapping("/results")
    public List<DailyResult> resultsByDate(@RequestParam String date) {
        return dailyRepo.findByResultDate(LocalDate.parse(date));
    }
}
