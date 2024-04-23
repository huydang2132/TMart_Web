package com.project.tmartweb.web.controllers;

import com.project.tmartweb.domain.dtos.OrderDTO;
import com.project.tmartweb.domain.entities.Order;
import com.project.tmartweb.services.order.IOrderService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("${api.prefix}/orders")
public class OrdersController {
    @Autowired
    private IOrderService orderService;

    @GetMapping("")
    public ResponseEntity<?> getAll(
            @RequestParam Integer page,
            @RequestParam Integer perPage
    ) {
        return ResponseEntity.status(HttpStatus.OK).body(orderService.getAll(page, perPage));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Order> getById(@PathVariable UUID id) {
        return ResponseEntity.status(HttpStatus.OK).body(orderService.getById(id));
    }

    @GetMapping("/user/{id}")
    public ResponseEntity<List<Order>> getByUserId(@PathVariable UUID id) {
        return ResponseEntity.status(HttpStatus.OK).body(orderService.findByUserId(id));
    }

    @PostMapping("")
    public ResponseEntity<Order> insert(@RequestBody @Valid OrderDTO orderDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(orderService.insert(orderDTO));
    }

    @PutMapping("/{id}")
    public ResponseEntity<Order> update(@PathVariable UUID id, @RequestBody OrderDTO orderDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(orderService.update(id, orderDTO));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> delete(@PathVariable UUID id) {
        orderService.delete(orderService.getById(id));
        return ResponseEntity.status(HttpStatus.OK).body("Deleted successfully");
    }
}
