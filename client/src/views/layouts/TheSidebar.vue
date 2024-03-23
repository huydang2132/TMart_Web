<template>
    <nav id="sidebar">
        <div class="sidebar-title">
            <h3>DANH MỤC</h3>
        </div>
        <router-link :to="{ name: 'ProductCatalogry', params: { id: item } }"
            v-for="(item) in ['Điện thoại', 'Apple Watch', 'Máy tính bảng']" :key="item">
            {{ item }}
        </router-link>
    </nav>
</template>

<script>
export default {
    name: 'TheSidebar'
}
</script>

<style scoped>
#sidebar {
    width: var(--width-sidebar);
    height: 100%;
    background-color: var(--color-white);
    overflow-y: auto;
    border-radius: var(--border-radius-page);
    padding: 10px 10px;
}

#sidebar .sidebar-title {
    padding: 10px;
    padding-bottom: 15px;
}

#sidebar a {
    padding: 10px 0;
    padding-left: 15px;
    display: block;
    text-decoration: none;
    color: var(--color-text);
    font-size: 1.1rem;
    border-radius: var(--border-radius-page);
}

#sidebar a:hover {
    background-color: var(--color-greyish);
}

#sidebar a.router-link-exact-active {
    background-color: var(--color-primary);
    color: var(--color-white);
}
</style>