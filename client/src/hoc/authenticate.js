import { defineComponent } from 'vue';
import { useStore } from 'pinia';
import { useRouter } from 'vue-router';

export function userIsAuthenticated(Component) {
    return defineComponent({
        setup(props, { slots }) {
            const store = useStore(); // Sử dụng store của Pinia
            const router = useRouter();

            if (!store.user.isLoggedIn) {
                router.push({ name: 'Login' });
            }

            return () => (Component, { ...props }, slots);
        },
    });
}

export function userIsNotAuthenticated(Component) {
    return defineComponent({
        setup(props, { slots }) {
            const store = useStore(); // Sử dụng store của Pinia
            const router = useRouter();

            if (store.user.isLoggedIn) {
                const redirectPath = router.currentRoute.value.query.redirect || '/';
                router.push(redirectPath);
            }

            return () => (Component, { ...props }, slots);
        },
        allowRedirectBack: false, // Cài đặt này có thể được xử lý bên trong logic của router
    });
}
