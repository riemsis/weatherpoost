//klasae kuri turetu gauti duomenis is api.meteo.lt
package com.example.weatherpost.service;

import com.example.weatherpost.model.WeatherData;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class WeatherService {
    private final String apiUrl = "https://api.meteo.lt/v1/places/telsiai/forecasts/long-term";

    public WeatherData getWeatherData() {
        RestTemplate restTemplate = new RestTemplate();
        WeatherData weatherData = restTemplate.getForObject(apiUrl, WeatherData.class);
        return weatherData;
    }
}
