<template>
    <div class="notify-page">
        <div class="notify-section">
            <div class="notify-title">
                <h3>Thông báo của tôi</h3>
            </div>
            <div class="notify-content">
                <div class="content-header">
                    <b-button>Đánh dấu đã đọc tất cả</b-button>
                </div>
                <div class="line"></div>
                <div class="content-body">
                    <router-link :to="{ name: 'OrderDetail', params: { id: item } }"
                        :class="['content-body-item', { 'active': item % 2 === 0 }]" v-for="item in 3" :key="item">
                        <div class="product-info">
                            <img :src="require('@/assets/imgs/Iphone15-promax.webp')" alt="">
                            <div class="notify-detail">
                                <div class="notify-detail-title">
                                    <p>Giao kiện hàng thành công</p>
                                </div>
                                <div class="notify-detail-content">
                                    <p>Kiện hàng đã giao thành công</p>
                                </div>
                                <div class="notify-detail-time">
                                    <p>10:30 12-05-2023</p>
                                </div>
                            </div>
                        </div>
                        <router-link :to="{ name: 'OrderDetail', params: { id: item } }">Xem chi tiết</router-link>
                    </router-link>
                </div>
            </div>
        </div>
    </div>
</template>

<script setup>

// ---------------------- Props ----------------------


// ---------------------- Khai báo biến --------------


// ---------------------- Watcher --------------------


// ---------------------- Lifecycle ------------------


// ---------------------- Hàm xử lý ------------------

</script>

<style scoped src="./notify.css"></style>