package com.project.tmartweb.services.coupon;

import com.project.tmartweb.exceptions.InvalidParamException;
import com.project.tmartweb.exceptions.NotFoundException;
import com.project.tmartweb.models.dtos.CouponDTO;
import com.project.tmartweb.models.entities.Coupon;
import com.project.tmartweb.repositories.CouponRepository;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;
import java.util.Optional;

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
        return couponRepository.findAll(Sort.by("createdAt").descending());
    }

    @Override
    public Optional<Coupon> findById(String id) {
        return couponRepository.findById(id);
    }

    @Override
    public Coupon getById(String id) {
        return findById(id).orElseThrow(() -> new NotFoundException("Mã giảm giá không tồn tại!", "Coupon not found"));
    }

    @Override
    public Coupon useCoupon(String code) {
        Coupon coupon = getById(code);
        if (coupon.getExpired()) {
            throw new InvalidParamException("Mã giảm giá đã hết hạn!", "Coupon is expired");
        }
        if (coupon.getExpirationDate().compareTo(new Timestamp(System.currentTimeMillis())) < 0) {
            coupon.setExpired(Boolean.TRUE);
            couponRepository.save(coupon);
            throw new InvalidParamException("Mã giảm giá đã hết hạn!", "Coupon is expired");
        }
        if (coupon.getQuantity() == 0) {
            throw new InvalidParamException("Mã giảm giá đã hết lượt sử dụng!", "Coupon is out of stock");
        }
        coupon.setQuantity(coupon.getQuantity() - 1);
        couponRepository.save(coupon);
        return coupon;
    }
}
