package com.shobujghor.app.contentmanager.controller;

import com.shobujghor.app.contentmanager.service.HomePageService;
import com.shobujghor.app.utility.response.contentmanager.HomePageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("/home")
public class HomePageController {

    private final HomePageService homePageService;

    @GetMapping("/load")
    public HomePageResponse getHomePageData() {
        return homePageService.getHomePageData();
    }
}
