import categoryService from '@/apis/services/categoryService';
import { defineStore } from 'pinia';

export const useCategoryStore = defineStore('category', {
    state: () => ({
        categories: [],
        category: {}
    }),
    getters: {},
    actions: {
        async fecthGetAll() {
            const res = await categoryService.gettAll();
            this.categories = res;
        },

        async fecthGetById(id) {
            const res = await categoryService.getById(id);
            this.category = res;
        }
    },
})
