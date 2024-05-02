package com.project.tmartweb.web.controllers;

import com.project.tmartweb.application.services.order.IOrderService;
import com.project.tmartweb.domain.dtos.OrderDTO;
import com.project.tmartweb.domain.entities.Cart;
import com.project.tmartweb.domain.entities.Order;
import com.project.tmartweb.web.base.RestAPI;
import com.project.tmartweb.web.base.RolesAdminUser;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestAPI("${api.prefix}/orders")
public class OrdersController {
    @Autowired
    private IOrderService orderService;

    @GetMapping("")
    @RolesAdminUser
    public ResponseEntity<?> getAll(
            @RequestParam Integer page,
            @RequestParam Integer perPage
    ) {
        return ResponseEntity.status(HttpStatus.OK).body(orderService.getAll(page, perPage));
    }

    @GetMapping("/{id}")
    @RolesAdminUser
    public ResponseEntity<Order> getById(@PathVariable UUID id) {
        return ResponseEntity.status(HttpStatus.OK).body(orderService.getById(id));
    }

    @GetMapping("/user/{id}")
    @RolesAdminUser
    public ResponseEntity<List<Order>> getByUserId(@PathVariable UUID id) {
        return ResponseEntity.status(HttpStatus.OK).body(orderService.findByUserId(id));
    }

    @PostMapping("")
    @RolesAdminUser
    public ResponseEntity<Order> insert(@RequestBody @Valid OrderDTO orderDTO) {
        return ResponseEntity.status(HttpStatus.CREATED).body(orderService.insert(orderDTO));
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAnyRole('ADMIN', 'USER')")
    public ResponseEntity<Order> update(@PathVariable UUID id, @RequestBody OrderDTO orderDTO) {
        return ResponseEntity.status(HttpStatus.OK).body(orderService.update(id, orderDTO));
    }

    @DeleteMapping("/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> delete(@PathVariable UUID id) {
        orderService.delete(orderService.getById(id));
        return ResponseEntity.status(HttpStatus.OK).body("Deleted successfully");
    }

    @PostMapping("/calculate")
    @RolesAdminUser
    public ResponseEntity<?> calculate(
            @RequestBody @Valid List<Cart> orderItems,
            @RequestParam(defaultValue = "0", required = false) double discount
    ) {
        var response = orderService.totalMoneyOrder(orderItems, discount);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
