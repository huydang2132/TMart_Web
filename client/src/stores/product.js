import { defineStore } from 'pinia';
import productService from '@/apis/services/productService';
import { dialog } from '@/helpers/swal';
import { toastify } from '@/helpers/toastify';

export const useProductStore = defineStore('product', {
    state: () => ({
        productListBestSeller: [],
        productListSale: [],
        pagination: {},
        products: {},
        productsByCategory: {},
        product: {},
        loading: false,
    }),
    getters: {
        getProducts(state) {
            return state.products.data;
        },
        getProductsByCategory: (state) => state.productsByCategory.data,
        getProduct(state) {
            return state.product.data;
        },
    },
    actions: {
        async fetchGetAll(page, perPage, keyword) {
            try {
                this.loading = true;
                const res = await productService.getAll(page, perPage, keyword);
                if (res) {
                    this.products = res;
                }
            } catch (error) {
                console.error(error);
            } finally {
                this.loading = false;
            }
        },

        async getAllByCategory(id, page, perPage) {
            const res = await productService.getAllByCategory(id, page, perPage);
            this.productsByCategory = res;
        },

        async fetchGetAllDeleted(page, perPage) {
            try {
                this.loading = true;
                const res = await productService.getAllDeleted(page, perPage);
                if (res.status === 200) {
                    this.products = res;
                }
            } catch (error) {
                toastify('Không tìm thấy sản phẩm', 'error');
                console.error(error);
            } finally {
                this.loading = false;
            }
        },

        async fetchGetById(id) {
            try {
                const res = await productService.getById(id);
                this.product = res.data;
            } catch (error) {
                toastify('Không tìm thấy sản phẩm', 'error');
                console.error(error);
            }
        },

        async fetchDelete(id) {
            try {
                this.loading = true;
                const res = await productService.delete(id);
                if (res.status === 200) {
                    toastify('Xóa sản phẩm thành công', 'success');
                }
            } catch (error) {
                dialog('Xóa sản phẩm thất bại', 'error', error?.response?.data?.userMessage);
                console.error(error);
            } finally {
                this.loading = false;
            }
        },

        async fetchUpdate(id, data) {
            try {
                this.loading = true;
                const res = await productService.update(id, data);
                this.product = res;
                if (res.status === 200) {
                    toastify('Cập nhật sản phẩm thành công', 'success');
                    this.fetchGetAll(0, 12);
                }
            } catch (error) {
                dialog('Cập nhật sản phẩm thất bại', 'error', error?.response?.data?.userMessage);
                console.error(error);
            } finally {
                this.loading = false;
            }
        },

        async fetchInsert(data) {
            try {
                this.loading = true;
                const res = await productService.insert(data);
                if (res.status === 201) {
                    dialog('Thêm sản phẩm thành công', 'success', null);
                    await this.fetchGetAll(0, 12);
                    return res.data;
                }
            } catch (error) {
                dialog('Thêm sản phẩm thất bại', 'error', error?.response?.data?.userMessage);
                console.error(error);
            } finally {
                this.loading = false;
            }
        },

        async fetchPostImage(id, data) {
            try {
                this.loading = true;
                const res = await productService.uploadImage(id, data);
                if (res.status === 200) {
                    await this.fetchGetAll(0, 12);
                }
            } catch (error) {
                dialog('Thêm ảnh sản phẩm thất bại', 'error', error?.response?.data?.userMessage);
                console.error(error);
            } finally {
                this.loading = false;
            }
        },

        async fetchGetAllBestSeller(page, perPage) {
            try {
                this.loading = true;
                const res = await productService.getAllBestSeller(page, perPage);
                if (res.status === 200) {
                    this.productListBestSeller = res.data.data;
                    this.pagination = res.data.pagination;
                }
            } catch (error) {
                console.error(error);
            } finally {
                this.loading = false;
            }
        },

        async fetchGetAllSale(page, perPage) {
            try {
                const res = await productService.getAllSale(page, perPage);
                if (res.status === 200) {
                    this.productListSale = res.data.data;
                    this.pagination = res.data.pagination;
                }
            } catch (error) {
                console.error(error);
            } finally {
                this.loading = false;
            }
        },

        async fetchSearchProduct(keyword, page, perPage, price, feedback) {
            try {
                this.loading = true;
                const res = await productService.searchProduct(keyword, page, perPage, price, feedback);
                if (res.status === 200) {
                    this.products = res.data;
                }
            } catch (error) {
                toastify('Lỗi tìm sản phẩm', 'error');
                console.error(error);
            } finally {
                this.loading = false;
            }
        }
    },
})
