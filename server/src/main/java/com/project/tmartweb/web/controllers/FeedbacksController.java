package com.project.tmartweb.web.controllers;

import com.project.tmartweb.domain.dtos.FeedbackDTO;
import com.project.tmartweb.domain.entities.Feedback;
import com.project.tmartweb.services.feedback.IFeedbackService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("${api.prefix}/feedbacks")
public class FeedbacksController {
    @Autowired
    private IFeedbackService feedbackService;

    @GetMapping("")
    public ResponseEntity<?> getAllFeedbacks(
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer perPage
    ) {
        var result = feedbackService.getAll(page, perPage);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/product/{id}")
    public ResponseEntity<?> getAllFeedbacksByProduct(
            @PathVariable("id") UUID id,
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer perPage
    ) {
        var result = feedbackService.getAllByProduct(id, page, perPage);
        return ResponseEntity.status(200).body(result);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getFeedbackById(
            @PathVariable("id") UUID id
    ) {
        var result = feedbackService.getById(id);
        return ResponseEntity.status(200).body(result);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateFeedbackById(
            @PathVariable("id") UUID id,
            @RequestBody FeedbackDTO feedbackDTO
    ) {
        var result = feedbackService.update(id, feedbackDTO);
        return ResponseEntity.status(200).body(result);
    }

    @PostMapping("")
    public ResponseEntity<?> insertFeedback(
            @RequestBody FeedbackDTO feedbackDTO
    ) {
        var result = feedbackService.insert(feedbackDTO);
        return ResponseEntity.status(201).body(result);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteFeedbackById(
            @PathVariable("id") UUID id
    ) {
        Feedback feedback = feedbackService.getById(id);
        feedbackService.delete(feedback);
        return ResponseEntity.status(200).body("Deleted successfully");
    }
}
