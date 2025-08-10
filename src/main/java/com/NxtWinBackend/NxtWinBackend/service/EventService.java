package com.NxtWinBackend.NxtWinBackend.service;


import com.NxtWinBackend.NxtWinBackend.entity.Event;
import com.NxtWinBackend.NxtWinBackend.entity.Prediction;
import com.NxtWinBackend.NxtWinBackend.entity.User;
import com.NxtWinBackend.NxtWinBackend.repository.EventRepository;
import com.NxtWinBackend.NxtWinBackend.repository.PredictionRepository;
import com.NxtWinBackend.NxtWinBackend.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;
    @Autowired
    private PredictionRepository predictionRepository;
    @Autowired
    private UserRepository userRepository;

    public List<Event> getAllActiveEvents() {
        return eventRepository.findByStatus("ACTIVE");
    }

    public List<Event> getEventsByCategory(String category) {
        return eventRepository.findByStatus("ACTIVE")
                .stream()
                .filter(e -> category.equalsIgnoreCase(e.getCategory()))
                .toList();
    }

    public Event getEventById(Long id) {
        return eventRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Event not found"));
    }

    public Event createEvent(Event event) {
        event.setStatus("ACTIVE");
        event.setTraderCount(0);
        return eventRepository.save(event);
    }

    public Event resolveEvent(Long eventId, Boolean outcome) {
        Event event = getEventById(eventId);
        if (!"ACTIVE".equals(event.getStatus())) {
            throw new RuntimeException("Event already resolved");
        }
        event.setOutcome(outcome);
        event.setStatus("RESOLVED");
        eventRepository.save(event);

        List<Prediction> predictions = predictionRepository.findByEventId(eventId);
        for (Prediction p : predictions) {
            if (p.getPrediction().equals(outcome)) {
                double winnings = p.getAmount() * (p.getPriceAtTime() + 1);
                p.setStatus("WON");
                p.setPotentialReturn(winnings);
                User user = p.getUser();
                user.setBalance(user.getBalance() + winnings);
                userRepository.save(user);
            } else {
                p.setStatus("LOST");
                p.setPotentialReturn(0.0);
            }
            predictionRepository.save(p);
        }
        return event;
    }
}