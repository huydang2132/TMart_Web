package com.project.tmartweb.web.controllers;

import com.project.tmartweb.domain.dtos.CouponDTO;
import com.project.tmartweb.domain.entities.Coupon;
import com.project.tmartweb.services.coupon.ICouponService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("${api.prefix}/coupons")
public class CouponController {
    @Autowired
    private ICouponService couponService;

    @GetMapping("")
    public ResponseEntity<?> getAllCoupons(
            @RequestParam(defaultValue = "1") Integer page,
            @RequestParam(defaultValue = "10") Integer perPage
    ) {
        var result = couponService.getAll(page, perPage);
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
