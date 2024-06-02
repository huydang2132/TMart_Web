<script lang="js" setup>
import { useProductStore } from '@/stores/product';
import { storeToRefs } from 'pinia';
import { nextTick, ref } from 'vue';
import ProductTag from './ProductTag.vue';

// ------------------------- Ref, reactive, emits, computed ----------------------
const productStore = useProductStore();
const { loading, productListBestSeller, pagination } = storeToRefs(productStore);
const page = ref(1);
const perPage = ref(12);
const totalPage = ref(0);
const productsData = ref([]);

// ------------------------- Lifecycle ----------------------
nextTick(async () => {
    await productStore.fetchGetAllBestSeller(page.value - 1, perPage.value);
    productsData.value = productListBestSeller.value;
    totalPage.value = pagination.value.lastPage + 1;
})

// ------------------------- Methods -----------------------
const handleLoadMore = async () => {
    if (page.value < totalPage.value) {
        page.value += 1;
        await productStore.fetchGetAllBestSeller(page.value - 1, perPage.value);
        productsData.value = [...productsData.value, ...productListBestSeller.value];
    }
}

</script>

<template>
    <div class="product-catalogry">
        <div class="loading" v-if="loading">
            <spinner-loader></spinner-loader>
        </div>
        <div class="header">
            <h4>Sản phẩm bán chạy</h4>
        </div>
        <div class="product row sm-gutter">
            <div class="col-2 product__item" v-for="(item) in productListBestSeller" :key="item?.id">
                <router-link :to="{ name: 'DetailProduct', params: { id: item.id } }" :title="item?.title"
                    class="product-item">
                    <ProductTag :item="item"></ProductTag>
                </router-link>
            </div>
        </div>
        <div class="link-more">
            <b-button @click="handleLoadMore" class="btn-more">Xem thêm</b-button>
        </div>
    </div>
</template>

<style lang="css" scoped>
.loading {
    position: absolute;
    top: 50%;
    left: 50%;
    transform: translate(-50%, -50%);
    display: flex;
    justify-content: center;
    align-items: center;
    z-index: 100;
}

.product-catalogry {
    position: relative;
    width: 100%;
    background-color: var(--color-white);
    border-radius: var(--border-radius-page);
    padding: 10px 20px;
    box-shadow: 0 2px 5px var(--color-box-shadow);
}

.header {
    display: flex;
    justify-content: space-between;
    padding-top: 10px;
}

.header>h4 {
    font-size: 1.8rem;
    font-weight: 500;
}

.header>a>i {
    padding-left: 10px;
}

.product {
    display: flex;
    padding: 20px 0;
}

.product a {
    text-decoration: none;
}

.product .product__item {
    padding: 10px;
}

.link-more {
    display: flex;
    justify-content: center;
    padding-bottom: 10px;
}

.link-more .btn-more {
    text-decoration: none;
    background-color: var(--color-white);
    border: 1px solid var(--color-primary);
    color: var(--color-primary);
    display: block;
    padding: 10px 40px;
    width: fit-content;
    border-radius: var(--border-radius);
    font-size: 1.1rem;
    font-weight: 600;
}

.link-more .btn-more:hover {
    background-color: var(--color-primary);
    color: var(--color-white);
}
</style>
