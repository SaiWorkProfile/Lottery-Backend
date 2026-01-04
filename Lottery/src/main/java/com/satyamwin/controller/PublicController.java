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

        LocalDate today = LocalDate.now();
        LocalTime now = LocalTime.now().withNano(0);

        map.put("visitors", ds != null ? ds.getVisitors() : 0);
        map.put("nextDrawTime", ds != null ? ds.getNextDrawTime() : "--");
        map.put("date", today.toString());
        map.put("time", now.toString());

        /* ================= ADMIN-CONTROLLED TIME TO DRAW ================= */

        long timeToDrawSeconds = 0;

        if (ds != null && ds.getNextDrawTime() != null) {

            try {
                // Example: "02:30 PM"
                String[] parts = ds.getNextDrawTime().split(" ");
                String[] hm = parts[0].split(":");

                int hour = Integer.parseInt(hm[0]);
                int minute = Integer.parseInt(hm[1]);

                if ("PM".equalsIgnoreCase(parts[1]) && hour != 12) hour += 12;
                if ("AM".equalsIgnoreCase(parts[1]) && hour == 12) hour = 0;

                LocalTime drawTime = LocalTime.of(hour, minute);

                timeToDrawSeconds = Duration.between(now, drawTime).getSeconds();

                // if draw time already passed → next day
                if (timeToDrawSeconds < 0) {
                    timeToDrawSeconds += 24 * 60 * 60;
                }

            } catch (Exception e) {
                timeToDrawSeconds = 0;
            }
        }

        map.put("timeToDraw", timeToDrawSeconds);

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
