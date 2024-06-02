<script lang="js" setup>

</script>

<template>
    <div class="admin-page">
        <div class="sidebar">
            <div class="logo">
                <router-link :to="{ name: 'HomePage' }">
                    <span class="t">T</span>
                    <span class="mart">Mart</span>
                </router-link>
            </div>
            <ul>
                <li>
                    <router-link :to="{ name: 'HomeAdmin' }">
                        <i class="fa-solid fa-house"></i>
                        Trang chủ
                    </router-link>
                </li>
                <li>
                    <router-link :to="{ name: 'UserAdmin' }">
                        <i class="fa-solid fa-users-gear"></i>
                        Quản lý người dùng
                    </router-link>
                </li>
                <li>
                    <router-link :to="{ name: 'CategoryAdmin' }">
                        <i class="fa-solid fa-list"></i>
                        Quản lý danh mục
                    </router-link>
                </li>
                <li>
                    <router-link :to="{ name: 'ProductAdmin' }">
                        <i class="fa-solid fa-box-archive"></i>
                        Quản lý sản phẩm
                    </router-link>
                </li>
                <li>
                    <router-link :to="{ name: 'OrderAdmin' }">
                        <i class="fa-solid fa-cart-shopping"></i>
                        Quản lý đơn hàng
                    </router-link>
                </li>
                <li>
                    <router-link :to="{ name: 'GalleryAdmin' }">
                        <i class="fa-solid fa-images"></i>
                        Quản lý trưng bày
                    </router-link>
                </li>
                <li>
                    <router-link :to="{ name: 'CouponAdmin' }">
                        <i class="fa-solid fa-ticket"></i>
                        Quản lý mã giảm giá
                    </router-link>
                </li>
            </ul>
        </div>
        <section>
            <router-view></router-view>
        </section>
    </div>
</template>

<style lang="css" scoped>
.admin-page .sidebar .logo>a {
    display: block;
    text-align: center;
    font-size: 2rem;
    font-weight: 700;
    text-decoration: none;
    padding: 5px 0;
}

.admin-page .sidebar .logo>a>.t {
    color: var(--color-orange);
    font-size: 2rem;

}

.admin-page .sidebar .logo>a>.mart {
    color: var(--color-white);
    font-size: 2rem;
    font-style: italic;
}

.admin-page {
    display: flex;
}

.admin-page section {
    width: calc(100% - var(--width-sidebar));
    padding: 20px 10px;
    position: relative;
}

.admin-page .sidebar {
    width: var(--width-sidebar);
    background-color: var(--color-primary);
    height: 100vh;
    padding: 20px 0;
}

.admin-page .sidebar ul {
    display: grid;
    padding: 0;
}

.admin-page .sidebar ul li {
    list-style: none;
}

.admin-page .sidebar ul li>a {
    color: var(--color-white);
    padding: 20px;
    text-decoration: none;
    font-weight: 600;
    display: flex;
    align-items: center;
    gap: 10px;
}

.admin-page .sidebar ul li>a:hover {
    background-color: var(--color-primary-hover);
}

.admin-page .sidebar ul li>a.router-link-exact-active {
    background-color: var(--color-primary-focus);
}
</style>
