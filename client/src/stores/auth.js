import authService from '@/apis/services/authService';
import { defineStore } from 'pinia';
import { dialog } from '@/helpers/swal';
import router from '@/routers/router';

export const useAuthStore = defineStore('auth', {
    state: () => ({
        isLoggedIn: localStorage.getItem('user') ? true : false
    }),
    getters: {
        getIsLoggedIn(state) {
            return state.isLoggedIn
        }
    },
    actions: {
        async fetchLogin(data) {
            try {
                const res = await authService.login(data);
                if (res.status === 200) {
                    localStorage.setItem('user', res.data.token);
                    this.isLoggedIn = true;
                }
            } catch (error) {
                dialog('Đăng nhập thất bại', 'error', error.response.data.userMessage);
                console.error(error);
            }
        },

        fetchLogout() {
            localStorage.removeItem('user');
            this.isLoggedIn = false;
        },

        async fetchRegister(data) {
            try {
                const res = await authService.register(data);
                if (res.status === 201) {
                    router.push({ name: 'Login' });
                    dialog('Đăng ký thành công', 'success', null);
                }
                return res.data;
            } catch (error) {
                dialog('Đăng nhập thất bại', 'error', error.response.data.userMessage);
                console.error(error);
            }
        }
    },
})
