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
public class PredictionService {

    @Autowired
    private PredictionRepository predictionRepository;
    @Autowired
    private UserRepository userRepository;
    @Autowired
    private EventRepository eventRepository;

    public Prediction placePrediction(Long userId, Long eventId, Boolean predictionValue, Double amount) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));
        Event event = eventRepository.findById(eventId)
                .orElseThrow(() -> new RuntimeException("Event not found"));

        if (!"ACTIVE".equals(event.getStatus())) {
            throw new RuntimeException("Event is not active");
        }
        if (user.getBalance() < amount) {
            throw new RuntimeException("Insufficient balance");
        }

        user.setBalance(user.getBalance() - amount);
        userRepository.save(user);

        double priceAtTime = predictionValue ? event.getYesPrice() : event.getNoPrice();
        double potentialReturn = amount * (priceAtTime + 1);

        Prediction prediction = new Prediction(user, event, predictionValue, amount, priceAtTime);
        prediction.setPotentialReturn(potentialReturn);
        prediction.setStatus("ACTIVE");

        event.setTraderCount(event.getTraderCount() + 1);
        eventRepository.save(event);

        return predictionRepository.save(prediction);
    }

    public List<Prediction> getUserPredictions(Long userId) {
        return predictionRepository.findByUserId(userId);
    }

    public List<Prediction> getEventPredictions(Long eventId) {
        return predictionRepository.findByEventId(eventId);
    }
}
