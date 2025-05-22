package com.example.flipkart.repository;

import com.example.flipkart.model.EventListener;
import java.util.*;

/**
 * Central registry of order listeners (Observer pattern).
 */
public class OrderLogRepository {
    private final List<EventListener> listeners = new ArrayList<>();

    public void register(EventListener l) {
        listeners.add(l);
    }

    public List<EventListener> getListeners() {
        return Collections.unmodifiableList(listeners);
    }
}
