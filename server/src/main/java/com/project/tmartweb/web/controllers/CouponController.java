package com.project.tmartweb.web.controllers;

import com.project.tmartweb.models.dtos.CouponDTO;
import com.project.tmartweb.models.entities.Coupon;
import com.project.tmartweb.services.category.ICategoryService;
import com.project.tmartweb.services.coupon.ICouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("${api.prefix}/coupons")
public class CouponController {
    @Autowired
    private ICouponService couponService;

    @GetMapping("")
    public ResponseEntity<?> getAllCoupons() {
        var result = couponService.getAll();
        return ResponseEntity.status(200).body(result);
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getCoupon(@PathVariable String id) {
        var result = couponService.getById(id);
        return ResponseEntity.status(200).body(result);
    }

    @PostMapping("")
    public ResponseEntity<?> insertCoupon(@RequestBody CouponDTO couponDTO) {
        var result = couponService.insert(couponDTO);
        return ResponseEntity.status(201).body(result);
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCoupon(@PathVariable String id, @RequestBody CouponDTO couponDTO) {
        var result = couponService.update(id, couponDTO);
        return ResponseEntity.status(200).body(result);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteCoupon(@PathVariable String id) {
        Coupon coupon = couponService.getById(id);
        couponService.delete(coupon);
        return ResponseEntity.status(200).body("Deleted successfully");
    }
}
