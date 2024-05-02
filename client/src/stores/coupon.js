import couponService from '@/apis/services/couponService';
import { dialog } from '@/helpers/swal';
import { defineStore } from 'pinia'

export const useCouponStore = defineStore('coupon', {
    state: () => ({
        coupon: {},
    }),
    getters: {},
    actions: {
        async fetchUseCoupon(id) {
            try {
                const res = await couponService.useCoupon(id);
                if (res.status === 200) {
                    this.coupon = res.data;
                    return res;
                }
            } catch (error) {
                dialog('Lấy mã giảm giá thất bại', 'error', error?.response?.data?.userMessage);
                console.error(error);
            }
        }
    },
})
