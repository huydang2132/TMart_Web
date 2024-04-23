<template>
    <div id="bestseller">
        <div class="header">
            <h4>Sản phẩm bán chạy</h4>
            <router-link to="">Xem thêm <i class="fa-solid fa-chevron-right"></i></router-link>
        </div>
        <div class="product row sm-gutter">
            <b-carousel id="carousel" :breakpoints="breakpoints" :pagination="false">
                <V-Slide
                    v-for="(item) in ['Iphone 15 Iphone 15 Iphone 15 Iphone 15 15 Iphone 1515 Iphone 15 ', 'Iphone 13', 'Laptop Acer', '4', '5', '6']"
                    :key="item">
                    <router-link :to="{ name: 'DetailProduct', params: { id: item } }" :title="item"
                        class="product-item">
                        <ProductTag></ProductTag>
                    </router-link>
                </V-Slide>
            </b-carousel>
        </div>
    </div>
</template>

<script setup>
import ProductTag from '@/views/pages/product/ProductTag.vue';
import { ref } from 'vue';

const breakpoints = ref({
    700: {
        itemsToShow: 3,
        snapAlign: 'center'
    },
    1024: {
        itemsToShow: 6,
        snapAlign: 'start'
    }
})


</script>

<style scoped>
#bestseller {
    width: 100%;
    background-color: var(--color-white);
    border-radius: var(--border-radius-page);
    padding: 10px;
}

.header {
    display: flex;
    justify-content: space-between;
    padding-top: 10px;
}

.header>h4 {
    font-size: 1.2rem;
}

.header>a {
    text-decoration: none;
    font-size: 1.2rem;
    color: var(--color-primary);
}

.header>a>i {
    padding-left: 10px;
}

.product {
    display: flex;
    padding: 20px 0;
}

.product .product-item {
    text-decoration: none;
}

.product #carousel .carousel__slide {
    padding: 0 5px;
}
</style>