package com.satyamwin.controller;

import com.satyamwin.entity.*;
import com.satyamwin.repository.*;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDate;

@RestController
@RequestMapping("/x9p7kA2_2026/admin-panel")
@CrossOrigin
public class AdminController {

    private final DrawSettingsRepository drawRepo;
    private final CurrentDrawRepository currentRepo;
    private final DailyResultRepository dailyRepo;

    public AdminController(
            DrawSettingsRepository d,
            CurrentDrawRepository c,
            DailyResultRepository dr
    ) {
        this.drawRepo = d;
        this.currentRepo = c;
        this.dailyRepo = dr;
    }

    @PostMapping("/draw-settings")
    public void saveDrawSettings(@RequestBody DrawSettings settings) {
        drawRepo.deleteAll();
        drawRepo.save(settings);
    }

    @PostMapping("/current-draw")
    public void saveCurrentDraw(@RequestBody CurrentDrawResult result) {
        result.setDrawDate(LocalDate.now());
        currentRepo.deleteAll();
        currentRepo.save(result);
    }

    @PostMapping("/publish-result")
    public void publishResult() {

        CurrentDrawResult current = currentRepo.findAll()
                .stream()
                .findFirst()
                .orElseThrow(() -> new RuntimeException("No current draw"));

        DailyResult dr = new DailyResult();
        dr.setDrawTime(current.getDrawTime());
        dr.setSatyamA(current.getSatyamA());
        dr.setSatyamB(current.getSatyamB());
        dr.setSatyamC(current.getSatyamC());
        dr.setResultDate(LocalDate.now());

        dailyRepo.save(dr);
        currentRepo.deleteAll();
    }
}
