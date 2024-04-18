package com.project.tmartweb.services.coupon;

import com.project.tmartweb.models.dtos.CouponDTO;
import com.project.tmartweb.models.entities.Coupon;
import com.project.tmartweb.services.base.IBaseService;

public interface ICouponService extends IBaseService<Coupon, CouponDTO, String> {
    Coupon useCoupon(String code);
}
