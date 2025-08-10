package com.NxtWinBackend.NxtWinBackend.controller;


import com.NxtWinBackend.NxtWinBackend.entity.Event;
import com.NxtWinBackend.NxtWinBackend.service.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("event/api")
public class EventController {

    @Autowired
    private EventService eventService;

    @GetMapping("/getAll")
    public List<Event> getAll() {
        return eventService.getAllActiveEvents();
    }

    @GetMapping("/category/{category}")
    public List<Event> byCategory(@PathVariable String category) {
        return eventService.getEventsByCategory(category);
    }

    @GetMapping("/getById/{id}")
    public Event getById(@PathVariable Long id) {
        return eventService.getEventById(id);
    }

    @PostMapping("/create")
    public Event create(@RequestBody Event event) {
        return eventService.createEvent(event);
    }

    @PutMapping("/update/{id}")
    public Event resolve(@PathVariable Long id, @RequestBody Map<String, Boolean> request) {
        return eventService.resolveEvent(id, request.get("outcome"));
    }


}
