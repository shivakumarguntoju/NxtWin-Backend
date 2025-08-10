package com.NxtWinBackend.NxtWinBackend.controller;


import com.NxtWinBackend.NxtWinBackend.entity.Prediction;
import com.NxtWinBackend.NxtWinBackend.repository.PredictionRepository;
import com.NxtWinBackend.NxtWinBackend.service.PredictionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("prediction/api")
public class PredictionController {

    @Autowired
    private PredictionService predictionService;

    @Autowired
    private PredictionRepository predictionRepository;

    @PostMapping("/create")
    public Prediction place(
            @RequestParam Long userId,
            @RequestParam Long eventId,
            @RequestParam Boolean prediction,
            @RequestParam Double amount) {
        return predictionService.placePrediction(userId, eventId, prediction, amount);
    }

    @GetMapping("/user")
    public List<Prediction> userPredictions(@RequestParam Long userId) {
        return predictionService.getUserPredictions(userId);
    }

    @GetMapping("/event/{eventId}")
    public List<Prediction> eventPredictions(@PathVariable Long eventId) {
        return predictionService.getEventPredictions(eventId);
    }

    @GetMapping("/getAllPredictions")
    public List<Prediction> getAllPredictions() {
        return predictionRepository.findAll();
    }

}
