<template>
    <div class="orders">
        <div class="order-section">
            <div class="order-title">
                <h3>Đơn hàng của tôi</h3>
            </div>
            <div class="order-header">
                <div class="order-menu">
                    <div class="order-menu-item order-all active">
                        <p>Tất cả đơn</p>
                    </div>
                    <div class="order-menu-item order-peding">
                        <p>Đang xử lý</p>
                    </div>
                    <div class="order-menu-item order-shipping">
                        <p>Đang vận chuyển</p>
                    </div>
                    <div class="order-menu-item order-done">
                        <p>Đã giao</p>
                    </div>
                    <div class="order-menu-item order-cancel">
                        <p>Đã hủy</p>
                    </div>
                    <div class="order-menu-item order-exchange">
                        <p>Đổi trả</p>
                    </div>
                </div>
            </div>
            <div class="order-search-box">
                <div class="search-icon">
                    <i class="fa-solid fa-magnifying-glass"></i>
                </div>
                <div class="search-input">
                    <b-input ref="refInputSearch" placeholder="Tìm theo mã hoặc tên sản phẩm" />
                </div>
                <div class="search-button">
                    <b-button value="Tìm đơn hàng" />
                </div>
            </div>
            <div class="order-body order-component" v-for="item in 3" :key="item">
                <div class="order-body-content">
                    <div class="content-header">
                        <div class="order-status">
                            <i class="fa-solid fa-ban"></i>
                            <p>Đã hủy</p>
                        </div>
                    </div>
                    <div class="line"></div>
                    <div @click="redirectOrderDetail(item)" class="content-body">
                        <img :src="require('@/assets/imgs/Iphone15-promax.webp')" alt="">
                        <div class="product-info">
                            <p class="product-name">Đồng Hồ Thông Minh Xiaomi Redmi Watch 3 / Watch 3 Active - Hàng
                                Chính
                                Hãng FPT - Black BHR7266GL</p>
                            <p class="product-quantity">Số lượng: 1</p>
                        </div>
                    </div>
                    <div class="line"></div>
                    <div class="content-footer">
                        <div class="total-amount">
                            <span>Tổng tiền:</span>
                            <p>1.157.000 ₫</p>
                        </div>
                        <div class="option">
                            <b-button value="Mua lại" type="secondary" />
                            <b-button @click="redirectOrderDetail(item)" value="Xem chi tiết" type="secondary" />
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</template>

<script setup>
import router from '@/routers/router';
import { onMounted, ref } from 'vue';

// ---------------------- Props ----------------------


// ---------------------- Khai báo biến --------------
const refInputSearch = ref(null);

// ---------------------- Watcher --------------------


// ---------------------- Lifecycle ------------------
onMounted(() => {
    refInputSearch.value.focus();
})

// ---------------------- Hàm xử lý ------------------
const redirectOrderDetail = (id) => {
    router.push({ name: 'OrderDetail', params: { id: id } })
}

</script>

<style scoped src="./orders.css"></style>