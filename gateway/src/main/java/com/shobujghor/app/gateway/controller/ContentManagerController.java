package com.shobujghor.app.gateway.controller;

import com.shobujghor.app.gateway.service.ContentManagerService;
import com.shobujghor.app.utility.response.contentmanager.HomePageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class ContentManagerController {

    private final ContentManagerService contentManagerService;

    @GetMapping("/home/load")
    public HomePageResponse getHomePageData() {
        return contentManagerService.getHomePageData();
    }
}
