import userSerive from '@/apis/services/userSerive'
import { defineStore } from 'pinia'

export const useUserStore = defineStore('user', {
    state: () => ({
        user: {}
    }),
    getters: {},
    actions: {
        async fetchGetUser(id) {
            const res = await userSerive.getById(id);
            this.user = res;
        },

        async fetchEditUser(id, data) {
            const res = await userSerive.update(id, data);
            this.user = res;
        },

        async fetchGetByToken() {
            if (localStorage.getItem('user')) {
                const res = await userSerive.getByToken(localStorage.getItem('user'));
                this.user = res;
            }
        }
    },
})
