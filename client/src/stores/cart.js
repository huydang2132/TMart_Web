import cartService from '@/apis/services/cartService'
import { dialog } from '@/helpers/swal';
import { toastify } from '@/helpers/toastify';
import { defineStore } from 'pinia'

export const useCartStore = defineStore('cart', {
    state: () => ({
        carts: {}
    }),
    getters: {},
    actions: {
        async fetchGetAll() {
            try {
                const res = await cartService.getAll();
                this.carts = res.data;
            } catch (error) {
                toastify('Lỗi không lấy được đơn hàng', 'error');
                console.error(error);
            }
        },

        async fetchGetById(id) {
            try {
                const res = await cartService.getById(id);
                this.carts = res.data;
            } catch (error) {
                toastify('Lỗi không lấy được đơn hàng', 'error');
                console.error(error);
            }
        },

        async fetchDelete(id) {
            try {
                const res = await cartService.delete(id);
                this.carts = res.data;
            } catch (error) {
                dialog('Xóa đơn hàng thất bại', 'error', error.response.data.userMessage);
                console.error(error);
            }
        },

        async fetchInsert(data) {
            try {
                const res = await cartService.insert(data);
                if (res.status === 201) {
                    toastify('Thêm vào giỏ hàng thành công!', 'success');
                }
                this.carts = res.data;
            } catch (error) {
                dialog('Thêm vào giỏ hàng thất bại', 'error', error.response.data.userMessage);
                console.error(error);
            }
        },

        async fetchUpdate(id, data) {
            try {
                const res = await cartService.update(id, data);
                this.carts = res.data;
            } catch (error) {
                dialog('Cập nhật giỏ hàng thất bại', 'error', error.response.data.userMessage);
                console.error(error);
            }
        }
    },
})
