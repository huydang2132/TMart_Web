<template>
    <div id="bestseller">
        <div class="header">
            <h4>Sản phẩm bán chạy</h4>
            <router-link to="">Xem thêm <i class="fa-solid fa-chevron-right"></i></router-link>
        </div>
        <div class="product row sm-gutter">
            <div class="col l-2 m-4 c-5"
                v-for="(item) in ['Iphone 15 Iphone 15 Iphone 15 Iphone 15 15 Iphone 1515 Iphone 15 ', 'Iphone 13', 'Laptop Acer', '4', '5', '6']"
                :key="item">
                <router-link :to="{ name: 'DetailProduct', params: { id: item } }" :title="item" class="product-item">
                    <div class="product-img">
                        <p class="discount">-25%</p>
                        <img :src="require('@/assets/imgs/Iphone15-promax.webp')" alt="">
                    </div>
                    <div class="product-name">
                        <p>{{ item }}</p>
                    </div>
                    <div class="product-star ">
                        <b-rating :value="3.6" :stars="5" fontSize="12" isReadonly />
                        <p class="number-sales">Đã bán: {{ this.$helper.formatNumber(340) }}</p>
                    </div>
                    <div class="product-price">
                        <p class="price-new">300.000đ</p>
                        <p class="price-old">350.000đ</p>
                    </div>
                </router-link>
            </div>
        </div>
    </div>
</template>

<script setup>

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

.product-item {
    border: 1px solid var(--color-border);
    border-radius: var(--border-radius-page);
    padding: 5px;
    text-decoration: none;
    display: block;
    transition: transform ease-in 0.1s;
    will-change: transform;
    box-shadow: 0 2px 5px var(--color-box-shadow);
    color: var(--color-text);
}

.product-item:hover {
    transform: translateY(-1px);
}

.product-img {
    position: relative;
}

.discount {
    position: absolute;
    padding: 5px;
    left: 0px;
    top: 0px;
    background-color: var(--color-pink);
    border-radius: var(--border-radius-page);
    display: flex;
    align-items: center;
    justify-content: center;
    font-weight: 600;
    color: var(--color-red);
    font-size: 0.9rem;
}

.product-item .product-img>img {
    object-fit: cover;
    width: 100%;
}

.product-name {
    padding: 5px 10px;
    height: 50px;
    width: 100%;
    position: relative;
}

.product-name>p {
    width: inherit;
    display: -webkit-box;
    line-height: 20px;
    overflow: hidden;
    text-overflow: ellipsis;
    line-clamp: 2;
    -webkit-line-clamp: 2;
    -webkit-box-orient: vertical;
}

.product-star {
    display: flex;
    padding: 2px 10px;
    gap: 8px;
    flex-wrap: wrap;
}

.product-star .number-sales {
    font-size: 0.8rem;
}

.product-price {
    padding: 5px 10px;
    line-height: 20px;
}

.product-price .price-new {
    color: var(--color-red);
    font-weight: 600;
}

.product-price .price-old {
    text-decoration: line-through;
}
</style>