package com.example.weatherpost.service;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;
import org.springframework.stereotype.Component;

@Component
public class RateLimiter {
    private static final int MAX_POSTS_PER_DAY = 3;
    private HashMap<LocalDate, AtomicInteger> dailyPosts = new HashMap<>();

    public boolean allowPost() {
        LocalDate today = LocalDate.now();
        dailyPosts.putIfAbsent(today, new AtomicInteger(0));
        return dailyPosts.get(today).incrementAndGet() <= MAX_POSTS_PER_DAY;
    }

    public int getRemainingPosts() {
        LocalDate today = LocalDate.now();
        dailyPosts.putIfAbsent(today, new AtomicInteger(0));
        return MAX_POSTS_PER_DAY - dailyPosts.get(today).get();
    }
}
