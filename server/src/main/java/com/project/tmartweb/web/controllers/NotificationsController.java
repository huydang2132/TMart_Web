package com.project.tmartweb.web.controllers;

import com.project.tmartweb.domain.dtos.NotificationDTO;
import com.project.tmartweb.services.notification.INotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("${api.prefix}/notifications")
public class NotificationsController {
    @Autowired
    private INotificationService notificationService;

    @GetMapping("")
    public ResponseEntity<?> getAllNotifications(
            @RequestParam(defaultValue = "0") Integer page,
            @RequestParam(defaultValue = "10") Integer perPage
    ) {
        var result = notificationService.getAll(page, perPage);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getNotification(
            @PathVariable UUID id
    ) {
        var result = notificationService.getById(id);
        return ResponseEntity.ok(result);
    }

    @PutMapping("/read/{id}")
    public ResponseEntity<?> readNotification(
            @PathVariable UUID id
    ) {
        var result = notificationService.readNotification(id);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<?> getAllNotificationsByUser(
            @PathVariable UUID id,
            @RequestParam Integer page,
            @RequestParam Integer perPage
    ) {
        var result = notificationService.getAllByUser(id, page, perPage);
        return ResponseEntity.ok(result);
    }

    @PostMapping("")
    public ResponseEntity<?> insertNotification(
            @RequestBody NotificationDTO notificationDTO
    ) {
        var result = notificationService.insert(notificationDTO);
        return ResponseEntity.status(201).body(result);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateNotification(
            @PathVariable UUID id,
            @RequestBody NotificationDTO notificationDTO
    ) {
        var result = notificationService.update(id, notificationDTO);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteNotification(
            @PathVariable UUID id
    ) {
        notificationService.delete(notificationService.getById(id));
        return ResponseEntity.ok("Deleted successfully!");
    }
}
