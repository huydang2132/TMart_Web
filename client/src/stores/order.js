import orderService from '@/apis/services/orderService'
import { dialog } from '@/helpers/swal';
import { toastify } from '@/helpers/toastify';
import router from '@/routers/router';
import { defineStore } from 'pinia'

export const useOrderStore = defineStore('order', {
    state: () => ({
        totalMoney: 0,
        orderItem: [],
        coupon: null,
        orders: {},
        order: {},
        ordersByUser: {},
    }),
    getters: {},
    actions: {

        async fetchGetAllOrder() {
            try {
                const res = await orderService.getAll();
                console.log(res);
                if (res.status === 200) {
                    this.orders = res.data;
                }
            } catch (error) {
                dialog('Lấy đơn hàng thất bại', 'error', error?.response?.data?.userMessage);
                console.error(error);
            }
        },

        async fetchGetById(id) {
            try {
                const res = await orderService.getById(id);
                console.log(res);
                if (res.status === 200) {
                    this.order = res.data;
                }
            } catch (error) {
                dialog('Lấy đơn hàng thất bại', 'error', error?.response?.data?.userMessage);
                console.error(error);
            }
        },

        async fetchUpdateOrder(id, data) {
            try {
                const res = await orderService.update(id, data);
                console.log(res);
                if (res.status === 200) {
                    dialog('Cập nhật đơn hàng thành công', 'success', null);
                }
            } catch (error) {
                dialog('Cập nhật đơn hàng thất bại', 'error', error?.response?.data?.userMessage);
                console.error(error);
            }
        },

        async fetchInsertOrder(data) {
            try {
                const res = await orderService.insert(data);
                console.log(res);
                if (res.status === 201) {
                    router.push({ name: 'HomePage' })
                    dialog('Đặt hàng thành công', 'success', null);
                }
            } catch (error) {
                dialog('Đặt hàng thất bại', 'error', error?.response?.data?.userMessage);
                console.error(error);
            }
        },

        async fetchCalculateTotalMoney(data, discount) {
            try {
                const total = await orderService.calculateTotalPrice(data, discount);
                this.totalMoney = total.data;
            } catch (error) {
                toastify('Lỗi tính tiền', 'error');
                console.error(error);
            }
        },

        async fetchGetAllByUser(id) {
            try {
                const res = await orderService.getAllByUser(id);
                if (res.status === 200) {
                    this.ordersByUser = res.data;
                    console.log(res);
                }
            } catch (error) {
                toastify('Lỗi không lấy được đơn hàng', 'error');
                console.error(error);
            }
        },

        fetchGetCoupon(orderItem, coupon) {
            this.orderItem = orderItem;
            this.coupon = coupon;
        }
    },
})
