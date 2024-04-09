package com.project.tmartweb.services.imp;

import com.project.tmartweb.exceptions.DataNotFoundException;
import com.project.tmartweb.models.dtos.CouponDTO;
import com.project.tmartweb.models.entities.Coupon;
import com.project.tmartweb.repositories.CouponRepository;
import com.project.tmartweb.services.ICouponService;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class CouponService implements ICouponService {
    private final CouponRepository couponRepository;
    private final ModelMapper mapper;

    @Override
    public Coupon insert(CouponDTO couponDTO) {
        Coupon coupon = mapper.map(couponDTO, Coupon.class);
        return couponRepository.save(coupon);
    }

    @Override
    public Coupon update(String id, CouponDTO couponDTO) {
        Coupon coupon = getById(id);
        mapper.map(couponDTO, coupon);
        return couponRepository.save(coupon);
    }

    @Override
    public void delete(Coupon coupon) {
        couponRepository.delete(coupon);
    }

    @Override
    public List<Coupon> getAll() {
        return couponRepository.findAll();
    }

    @Override
    public Optional<Coupon> findById(String id) {
        return couponRepository.findById(id);
    }

    @Override
    public Coupon getById(String id) {
        return findById(id).orElseThrow(() -> new DataNotFoundException("Mã giảm giá không tồn tại!", "Coupon not found"));
    }
}
