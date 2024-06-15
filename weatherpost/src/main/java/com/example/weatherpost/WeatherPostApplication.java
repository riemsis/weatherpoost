//klase skirta aplikacijos paleidimui
package com.example.weatherpost;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;

@SpringBootApplication
public class WeatherPostApplication extends SpringBootServletInitializer {

    @Override
    protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
        return application.sources(WeatherPostApplication.class);
    }

    public static void main(String[] args) {
        SpringApplication.run(WeatherPostApplication.class, args);
    }
}
