package com.project.tmartweb.web.controllers;

import com.project.tmartweb.application.services.order_detail.IOrderDetailService;
import com.project.tmartweb.web.base.RolesAdminUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("${api.prefix}/order-details")
public class OrderDetailController {
    @Autowired
    private IOrderDetailService orderDetailService;

    @GetMapping("")
    @RolesAdminUser
    public ResponseEntity<?> getAllOrderDetails(
            @RequestParam Integer page,
            @RequestParam Integer perPage
    ) {
        var result = orderDetailService.getAll(page, perPage);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{id}")
    @RolesAdminUser
    public ResponseEntity<?> getOrderDetail(@PathVariable UUID id) {
        var result = orderDetailService.getById(id);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/order/{id}")
    @RolesAdminUser
    public ResponseEntity<?> getAllOrderDetailsByOrder(
            @PathVariable UUID id,
            @RequestParam Integer page,
            @RequestParam Integer perPage
    ) {
        var result = orderDetailService.getAllByOrder(id, page, perPage);
        return ResponseEntity.ok(result);
    }


}
