import { defineStore } from 'pinia';
import productService from '@/apis/services/productService';

export const useProductStore = defineStore('product', {
    state: () => ({
        products: {},
        productsByCategory: {},
        product: {},
    }),
    getters: {
        getProducts(state) {
            return state.products.data;
        },
        getProductsByCategory: (state) => state.productsByCategory.data,
        getProduct(state) {
            return state.product;
        },
    },
    actions: {
        async fetchGetAll(page, perPage) {
            const res = await productService.getAll(page, perPage);
            this.products = res;
        },

        async getAllByCategory(id, page, perPage) {
            const res = await productService.getAllByCategory(id, page, perPage);
            this.productsByCategory = res;
        },

        async fetchGetById(id) {
            const res = await productService.getById(id);
            this.product = res;
        },
    },
})
