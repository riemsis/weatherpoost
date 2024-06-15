//klase kuri turetu buti kaip duomenu gavimas ir siuntimas i faccebook
package com.example.weatherpost.controller;

import com.example.weatherpost.model.WeatherData;
import com.example.weatherpost.service.FacebookService;
import com.example.weatherpost.service.RateLimiter;
import com.example.weatherpost.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/api/weather")
public class WeatherController {

    @Autowired
    private WeatherService weatherService;

    @Autowired
    private FacebookService facebookService;

    @Autowired
    private RateLimiter rateLimiter;

    @GetMapping
    public WeatherData getWeather(@RequestParam String userAccessToken) {
        WeatherData weatherData = weatherService.getWeatherData();
        String message = "Current weather condition: " + weatherData.getConditionCode();
        facebookService.postToFacebook(message, userAccessToken);
        return weatherData;
    }

    @PostMapping("/post")
    public Map<String, String> postWeatherToFacebook(@RequestBody Map<String, String> payload) {
        Map<String, String> response = new HashMap<>();
        
        if (!rateLimiter.allowPost()) {
            response.put("message", "Šiandien jau užteks postų. Susitiksime rytoj.");
            return response;
        }

        String message = payload.get("message");
        if (message == null || message.isEmpty()) {
            WeatherData weatherData = weatherService.getWeatherData();
            message = "Current weather condition: " + weatherData.getConditionCode();
        }
        
        facebookService.postToFacebook(message);
        response.put("message", "Pranešimas nusiųstas: " + message);
        response.put("remainingPosts", String.valueOf(rateLimiter.getRemainingPosts()));
        
        return response;
    }
}
