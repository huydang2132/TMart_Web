package com.project.tmartweb.web.controllers;

import com.project.tmartweb.domain.dtos.CartDTO;
import com.project.tmartweb.domain.entities.Cart;
import com.project.tmartweb.services.cart.ICartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("${api.prefix}/carts")
public class CartsController {
    @Autowired
    private ICartService cartService;

    @GetMapping("/user/{id}")
    public ResponseEntity<?> getAllCarts(
            @PathVariable UUID id,
            @RequestParam Integer page,
            @RequestParam Integer perPage
    ) {
        var result = cartService.getAllByUser(id, page, perPage);
        return ResponseEntity.ok(result);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCart(@PathVariable UUID id) {
        var result = cartService.getById(id);
        return ResponseEntity.ok(result);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCart(@PathVariable UUID id) {
        Cart cart = cartService.getById(id);
        cartService.delete(cart);
        return ResponseEntity.ok("Deleted successfully");
    }

    @PostMapping("")
    public ResponseEntity<?> insertCart(@RequestBody CartDTO cartDTO) {
        var result = cartService.insert(cartDTO);
        return ResponseEntity.status(201).body(result);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCart(@PathVariable UUID id, @RequestBody CartDTO cartDTO) {
        var result = cartService.update(id, cartDTO);
        return ResponseEntity.ok(result);
    }
}
