package com.library.controller;

import org.springframework.web.bind.annotation.*;

import com.library.dto.DashboardResponse;
import com.library.service.DashboardService;

@RestController
@RequestMapping("/dashboard")
public class DashboardController {

    private DashboardService dashboardService;

    public DashboardController(
            DashboardService dashboardService){

        this.dashboardService=dashboardService;
    }

    @GetMapping
    public DashboardResponse getDashboard(){

        return dashboardService.getDashboard();
    }
}