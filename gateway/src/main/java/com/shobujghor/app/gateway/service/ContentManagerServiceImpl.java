package com.shobujghor.app.gateway.service;

import com.shobujghor.app.gateway.api.ContentManagerClient;
import com.shobujghor.app.utility.response.contentmanager.HomePageResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ContentManagerServiceImpl implements ContentManagerService {

    private final ContentManagerClient contentManagerClient;

    @Override
    public HomePageResponse getHomePageData() {
        return contentManagerClient.getHomePageData();
    }
}
