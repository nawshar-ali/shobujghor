package com.shobujghor.app.gateway.api;

import com.shobujghor.app.utility.response.contentmanager.HomePageResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient(value = "contentManagerClient", url = "http://localhost:8007/content-manager")
public interface ContentManagerClient {
    @GetMapping("/home/load")
    HomePageResponse getHomePageData();
}
