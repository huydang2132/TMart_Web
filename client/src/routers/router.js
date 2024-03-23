import { createRouter, createWebHistory } from "vue-router";

const routes = [
    {
        path: '/',
        name: 'HomePage',
        component: () => import('@/views/pages/home/HomePage.vue'),
        meta: {
            title: 'TMart - Mua sắm online giá tốt',
        }
    },
    {
        path: '/products',
        name: 'ListProduct',
        component: () => import('@/views/pages/product/ListProduct.vue'),
        meta: {
            title: 'TMart - Sản phẩm nổi bật'
        }
    },
    {
        path: '/product/:id',
        name: 'DetailProduct',
        component: () => import('@/views/pages/product/DetailProduct.vue'),
        meta: {
            title: 'TMart - Mua sắm online giá tốt',
        }
    },
    {
        path: '/catalogry/:id',
        name: 'ProductCatalogry',
        component: () => import('@/views/pages/catalogry/ProductCatalogry.vue'),
        meta: {
            title: 'TMart - Mua sắm online giá tốt',
        }
    },
    {
        path: '/payments',
        name: 'Payments',
        component: () => import('@/views/pages/payments/PaymentsPage.vue'),
        meta: {
            title: 'Thanh toán',
        }
    },
    {
        path: '/cart',
        name: 'CartPage',
        component: () => import('@/views/pages/cart/CartPage.vue'),
        meta: {
            title: 'Giỏ hàng',
        }
    },
    {
        path: '/account/edit',
        name: 'AccountPage',
        component: () => import('@/views/pages/account/AccountPage.vue'),
        meta: {
            title: 'Tài khoản của tôi',
        }
    },
    {
        path: '/orders',
        name: 'OrdersPage',
        component: () => import('@/views/pages/account/OrdersPage.vue'),
        meta: {
            title: 'Đơn hàng của tôi',
        }
    },
    {
        path: '/notifications',
        name: 'NotifyPage',
        component: () => import('@/views/pages/account/NotifyPage.vue'),
        meta: {
            title: 'Thông báo của tôi',
        }
    },
    {
        path: '/order/detail/:id',
        name: 'OrderDetail',
        component: () => import('@/views/pages/account/OrderDetail.vue'),
        meta: {
            title: 'Thông báo của tôi',
        }
    },
    {
        path: '/:pathMatch(.*)*',
        name: 'NotFound',
        component: () => import('@/views/pages/not-found/NotFoundPage.vue'),
        meta: {
            title: '404 - PAGE NOT FOUND'
        }
    },
]

const router = createRouter({
    history: createWebHistory(),
    routes,
    scrollBehavior(to, from, savedPosition) {
        if (savedPosition) {
            return savedPosition
        } else {
            return {
                top: 0, behavior: 'smooth',
            }
        }
    }
});

router.beforeEach((to, from, next) => {
    document.title = to.meta.title;
    next();
})

export default router;