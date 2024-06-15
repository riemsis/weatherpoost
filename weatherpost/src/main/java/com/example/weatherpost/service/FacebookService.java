//klase kuri turetu bendrauti su „Facebook“ Graph API ir skelbti pranešimus „Facebook“ puslapyje.kuri susikuriau
package com.example.weatherpost.service;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class FacebookService {

    @Value("${facebook.page.access.token}")
    private String pageAccessToken;

    private final String facebookGraphApiUrl = "https://graph.facebook.com/v20.0/me/feed";

    public void postToFacebook(String message) {
        RestTemplate restTemplate = new RestTemplate();
        String url = facebookGraphApiUrl + "?message=" + message + "&access_token=" + pageAccessToken;
        restTemplate.postForObject(url, null, String.class);
    }

    public void postToFacebook(String message, String userAccessToken) {
        RestTemplate restTemplate = new RestTemplate();
        String url = facebookGraphApiUrl + "?message=" + message + "&access_token=" + userAccessToken;
        restTemplate.postForObject(url, null, String.class);
    }
}

