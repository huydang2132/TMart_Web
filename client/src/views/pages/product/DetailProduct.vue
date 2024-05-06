<template>
    <div class="detail-product">
        <div class="product-infor">
            <div class="product-imgs">
                <carousel-gallery :pagination="false" :wrapAround="false" :slides="product.imageProducts">

                </carousel-gallery>
            </div>
            <div class="product-more-infor">
                <h3 class="title">
                    {{ product?.title }}
                </h3>
                <div class="product-rating">
                    <div v-if="product?.feedbacks?.star" class="product-star">
                        <ins>
                            {{ product?.feedbacks?.star / product?.feedbacks?.length }}
                        </ins>
                        <b-rating :value="product?.feedbacks?.length > 0 ?
                            product?.feedbacks?.star / product?.feedbacks.length : 0" isReadonly />
                    </div>
                    <span class="line"></span>
                    <div class="quantity-rating">
                        <ins>{{ $helper.formatNumber(product?.feedbacks?.length) }}</ins> <span>Đã đánh giá</span>
                    </div>
                    <span class="line"></span>
                    <div class="product-sold">
                        <ins>{{ $helper.formatNumber(product?.orderDetails?.length) }}</ins> <span>Đã bán</span>
                    </div>
                </div>
                <div class="product-price">
                    <p v-if="product?.discount > 0" class="price-old">{{ $formatValue.formatMoney(product?.price) }}</p>
                    <p class="price-new">
                        {{ product?.discount > 0 ?
                            $formatValue.formatMoney(product?.price * (100 - (product?.discount)) / 100) :
                            $formatValue.formatMoney(product?.price) }}
                    </p>
                    <div v-if="product?.discount > 0" class="discount">
                        <p>{{ product?.discount }}% GIẢM</p>
                    </div>
                </div>
                <div class="product-option">
                    <div class="options">
                        <h5>Màu sắc</h5>
                        <div class="option-item">
                            <div :class="['option-value', { 'option-active': index % 2 !== 0 }]"
                                v-for="(item, index) in ['Đen', 'Xanh', 'Trắng']" :key="index">
                                <img :src="require('@/assets/imgs/Iphone15-promax.webp')" alt="">
                                <p class="option-label">{{ item }}</p>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="product-quantity">
                    <p class="quantity-tile">Số lượng</p>
                    <div class="select-quantity">
                        <b-button @click="reduce" icon="fa-solid fa-minus" />
                        <input min="1" :max="quantityProduct" @input="validInputQuantity" type="number"
                            v-model="quantity" />
                        <b-button @click="increment" icon="fa-solid fa-plus" />
                    </div>
                    <p class="quantity">{{ $helper.formatNumber(product.quantity) }} sản phẩm có sẵn</p>
                </div>
                <div class="button-handle">
                    <b-button @click="handleAddToCart" type="secondary" icon="fa-solid fa-cart-plus"
                        value="Thêm vào giỏ hàng" />
                    <b-button type="primary" value="Mua ngay" @click="handleBuyNow()" />
                </div>
            </div>
        </div>
        <div class="product-description">
            <h3>
                MÔ TẢ SẢN PHẨM
            </h3>
            <pre class="description-content">{{ product.description }}</pre>
        </div>
        <ReviewProduct />
    </div>
</template>

<script setup>
import { nextTick, ref, watch } from 'vue';
import ReviewProduct from './ReviewProduct.vue';
import router from '@/routers/router';
import { useRoute } from 'vue-router';
import { useProductStore } from '@/stores/product';
import { useCartStore } from '@/stores/cart';
import { useUserStore } from '@/stores/user';


// --------------------- Khai báo biến ----------------------
const star = ref(4);
const quantity = ref(1);
const product = ref({});
const route = useRoute();
const productId = ref(route.params.id);
const quantityProduct = ref(0);

const cartStore = useCartStore();
const userStore = useUserStore();

// --------------------- Lifecycle vue ----------------------
watch(() => star.value, () => {
    console.log(star.value);
})
const productStore = useProductStore();

nextTick(async () => {
    await productStore.fetchGetById(productId.value);
    product.value = productStore.product;
    quantityProduct.value = product.value.quantity;
    console.log(product.value);
})

// --------------------- Hàm xử lý --------------------------

const validInputQuantity = () => {
    const maxQuantity = product.value.quantity;
    if (quantity.value > maxQuantity) {
        quantity.value = maxQuantity;
    }
    else {
        quantity.value = Number.parseInt(quantity.value);
    }
}

const reduce = () => {
    quantity.value = quantity.value > 1 ? quantity.value -= 1 : 1;
}

const increment = () => {
    quantity.value = quantity.value < quantityProduct.value ? quantity.value += 1 : quantityProduct.value;
}

const handleBuyNow = () => {
    router.push({ name: 'Payments' })
}

const handleAddToCart = () => {
    cartStore.fetchInsert({
        productId: productId.value,
        quantity: quantity.value,
        userId: userStore.userId,
        createdBy: userStore.fullName
    });
}
</script>

<style src="./detailProduct.css" scoped></style>