<template>
    <div class="cart">
        <h3>GIỎ HÀNG</h3>
        <div class="cart-section">
            <div class="cart-info">
                <div class="cart-header">
                    <div class="title-name cart-name">
                        <b-checkbox label="Tất cả (3 sản phẩm)" />
                    </div>
                    <div class="title-price cart-price">
                        <p>Đơn giá</p>
                    </div>
                    <div class="title-quantity cart-quantity">
                        <p>Số lượng</p>
                    </div>
                    <div class="title-total cart-total">
                        <p>Thành tiền</p>
                    </div>
                    <div class="title-option cart-option">
                        <b-button class="btn" icon="fa-solid fa-trash-can" color="var(--color-grey)" />
                    </div>
                </div>
                <div class="cart-body">
                    <div class="cart-content" v-for="item in 10" :key="item">
                        <div class="cart-content-name cart-name">
                            <b-checkbox @change="handleCheck(item)" />
                            <img :src="require('@/assets/imgs/Iphone15-promax.webp')" alt="">
                            <span title="">Quần tây nam hàn quốc JBagy dáng baggy vải co giãn dày dặn dáng suông ống
                                rộng,
                                màu
                                đen,
                                kem -
                                JA0101</span>
                        </div>
                        <div class="cart-content-price cart-price">
                            <p>1.157.000₫</p>
                        </div>
                        <div class="cart-content-quantity cart-quantity">
                            <div class="select-quantity">
                                <b-button @click="reduce()" icon="fa-solid fa-minus" />
                                <b-input @input="validInputQuantity($event)" @keydown="handleArrowUpDown($event)"
                                    v-model="quantity" />
                                <b-button @click="increment()" icon="fa-solid fa-plus" />
                            </div>
                            <p>Còn 4 sản phẩm</p>
                        </div>
                        <div class="cart-content-total cart-total">
                            <p>1.157.000₫</p>
                        </div>
                        <div class="cart-content-option cart-option">
                            <b-button class="btn" icon="fa-solid fa-trash-can" color="var(--color-grey)" />
                        </div>
                    </div>
                </div>
            </div>
            <div class="cart-sell">
                <div class="cart-sell-address">
                    <div class="address-header">
                        <h5>Giao tới</h5>
                        <b-button value="Thay đổi" color="var(--color-primary)" />
                    </div>
                    <div class="address-content">
                        <b>Đặng Đình Huy - 0378858091</b>
                        <p>
                            Chợ Hạ Xóm Hội - Xã Mê Linh, Xã Mê Linh, Huyện Mê Linh, Hà Nội
                        </p>
                    </div>
                </div>
                <div class="cart-sell-voucher">
                    <h5>Mã khuyến mãi</h5>
                    <b-button icon="fa-solid fa-ticket" value="Chọn hoặc nhập mã khuyến mãi"
                        color="var(--color-primary)" />
                </div>
                <div class="cart-sell-buy-now">
                    <div class="buy-now-bill">
                        <div class="bill-title">
                            <p>Tạm tính</p>
                            <p>Giảm giá</p>
                        </div>
                        <div class="bill-value">
                            <p>1.157.000₫</p>
                            <p>0₫</p>
                        </div>
                    </div>
                    <div class="line"></div>
                    <div class="total-amount">
                        <p>Tổng tiền</p>
                        <p>1.157.000₫</p>
                    </div>
                    <div class="buy-now-btn">
                        <b-button @click="handleRedrectPayments()" value="Đặt hàng" type="primary" />
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<script setup>
import { ref } from 'vue';
import router from '@/routers/router';

// ------------------------- Khai báo biến ----------------------
const quantity = ref(1);


// ------------------------ Lifecle -----------------------------


// ------------------------ Watcher -----------------------------

// ------------------------ Hàm xử lý ---------------------------

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
const handleCheck = (item) => {
    console.log(item);
}

const handleRedrectPayments = () => {
    router.push({ name: 'Payments' })
}

</script>

<style scoped src="./cart.css"></style>