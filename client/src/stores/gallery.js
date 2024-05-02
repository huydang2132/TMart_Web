import { defineStore } from 'pinia';
import galleryService from '@/apis/services/galleryService';
import { toastify } from '@/helpers/toastify';
import { dialog } from '@/helpers/swal';

export const useGalleryStore = defineStore('gallery', {
    state: () => ({
        isLoading: false,
        gallery: {},
        galleries: {},
    }),
    getters: {},
    actions: {
        async fetchGetAll() {
            try {
                this.isLoading = true;
                const res = await galleryService.getAll();
                if (res.status === 200) {
                    this.galleries = res.data;
                }
            } catch (error) {
                toastify('Lấy dữ liệu thất bại', 'error');
                console.error(error);
            } finally {
                this.isLoading = false;
            }
        },

        async fetchGetById(id) {
            try {
                this.isLoading = true;
                const res = await galleryService.getById(id);
                if (res.status === 200) {
                    this.gallery = res.data;
                }
            } catch (error) {
                toastify('Lấy dữ liệu thất bại', 'error');
                console.error(error);
            } finally {
                this.isLoading = false;
            }
        },

        async fetchInsert(productId, image) {
            try {
                this.isLoading = true;
                const res = await galleryService.insert(productId, image);
                if (res.status === 201) {
                    toastify('Thêm thành công', 'success');
                    this.fetchGetAll();
                }
            } catch (error) {
                dialog('Thêm thất bại', 'error', error?.response?.data?.userMessage);
                console.error(error);
            } finally {
                this.isLoading = false;
            }
        },

        async fetchUpdate(id, productId, image) {
            try {
                this.isLoading = true;
                const res = await galleryService.update(id, productId, image);
                if (res.status === 200) {
                    toastify('Cập nhật thành công', 'success');
                    this.fetchGetAll();
                }
            } catch (error) {
                dialog('Cập nhật thất bại', 'error', error?.response?.data?.userMessage);
                console.error(error);
            } finally {
                this.isLoading = false;
            }
        }
    },
})
