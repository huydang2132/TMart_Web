<template>
    <div class="detail-product">
        <div class="product-infor">
            <div class="product-imgs">
                <div class="img-show">
                    <img :src="require('@/assets/imgs/Iphone15-promax.webp')" alt="">
                </div>
                <div class="img-carousel">
                    <div class="img-carousel-item" v-for="item in 5" :key="item">
                        <img :src="require('@/assets/imgs/Iphone15-promax.webp')" alt="">
                    </div>
                </div>
            </div>
            <div class="product-more-infor">
                <h3 class="title">
                    Bàn Phím Bluetooth Mini Không Dây Kèm Chuột Bàn Phím Kết Nối Điện Thoại Cho Điện Thoại Ipad Máy Tính
                    Laptop
                </h3>
                <div class="product-rating">
                    <div class="product-star">
                        <ins>{{ (star * 10) % 10 == 0 ? star + '.0' : star }}</ins>
                        <b-rating :value="star" isReadonly />
                    </div>
                    <span class="line"></span>
                    <div class="quantity-rating">
                        <ins>{{ this.$helper.formatNumber(300) }}</ins> <span>Đã đánh giá</span>
                    </div>
                    <span class="line"></span>
                    <div class="product-sold">
                        <ins>{{ this.$helper.formatNumber(300) }}</ins> <span>Đã bán</span>
                    </div>
                </div>
                <div class="product-price">
                    <p class="price-old">140.000đ</p>
                    <p class="price-new">99.000đ</p>
                    <div class="discount">
                        <p>30% GIẢM</p>
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
                        <b-input @input="validInputQuantity" @keydown="handleArrowUpDown($event)" v-model="quantity" />
                        <b-button @click="increment" icon="fa-solid fa-plus" />
                    </div>
                    <p class="quantity">40 sản phẩm có sẵn</p>
                </div>
                <div class="button-handle">
                    <b-button type="extra" icon="fa-solid fa-cart-plus" value="Thêm vào giỏ hàng" />
                    <b-button type="primary" value="Mua ngay" @click="handleBuyNow()" />
                </div>
            </div>
        </div>
        <div class="product-description">
            <h3>
                MÔ TẢ SẢN PHẨM
            </h3>
            <pre class="description-content">
❣️Tính năng:
Tương thích với điện thoại, máy tính bảng, máy tính xách tay của hệ thống Android/Windows/iOS. Làm cho điện thoại nghệ thuật và máy tính bảng của bạn linh hoạt như một chiếc máy tính.

❣️Ba hệ thống chuyển đổi
Fn+Q Dành Cho Hệ Thống Android
Fn+W cho hệ thống Windows
Fn+E Dành Cho Hệ Thống IOS
(Vui lòng chuyển đổi hệ thống cần thiết trước khi kết nối bàn phím)

❣️Bạn có thể chuyển đổi theo hệ thống của thiết bị hiện tại để có được chức năng phím tắt của các hệ thống khác nhau.

❣️Làm việc như một chiếc máy tính.

❣️Bạn có thể sử dụng nó để tiến hành các cuộc trò chuyện hội nghị, hồ sơ công việc, báo cáo tài chính và các công việc khác yêu cầu máy tính hoàn thành.
            </pre>
        </div>
        <ReviewProduct />
    </div>
</template>

<script setup>
import { ref, watch } from 'vue';
import ReviewProduct from './ReviewProduct.vue';
import router from '@/routers/router';

// --------------------- Khai báo biến ----------------------
const star = ref(4);
const quantity = ref(1);

// --------------------- Lifecycle vue ----------------------
watch(() => star.value, () => {
    console.log(star.value);
})

// --------------------- Hàm xử lý --------------------------

const validInputQuantity = () => {
    const pattern = /[^0-9]/;
    if (pattern.test(quantity.value)) {
        quantity.value = quantity.value.replace(/[^0-9]/, '')
    }
    if (quantity.value < 1) {
        quantity.value = 1;
    }
    else if (quantity.value > 40) {
        quantity.value = 40;
    }
    else {
        quantity.value = Number.parseInt(quantity.value);
    }
}

const handleArrowUpDown = (e) => {
    if (e.keyCode == '38') {
        increment();
    }
    else if (e.keyCode == '40') {
        reduce();
    }
}

const reduce = () => {
    quantity.value = quantity.value > 1 ? quantity.value -= 1 : 1;
}

const increment = () => {
    quantity.value = quantity.value < 40 ? quantity.value += 1 : 40;
}

const handleBuyNow = () => {
    router.push({ name: 'Payments' })
}
</script>

<style src="./detailProduct.css" scoped></style>