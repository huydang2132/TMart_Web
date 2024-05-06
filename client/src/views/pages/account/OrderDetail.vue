<template>
    <div class="order-detail">
        <div class="order-detail-section">
            <h3>Chi tiết đơn hàng</h3>
            <div class="order-detail-header">
                <div class=" detail-header-item">
                    <h5>Địa chỉ người nhận</h5>
                    <div class="detail-header-address header-item-content">
                        <b class="full-name">{{ order?.fullName }}</b>
                        <p class="address">
                            Địa chỉ: {{ order?.address }}
                        </p>
                        <p class="phone-number">Điện thoại: {{ order?.phoneNumber }}</p>
                    </div>
                </div>
                <div class="detail-header-item">
                    <h5>Trạng thái</h5>
                    <div class="detail-header-status header-item-content">
                        <b>Ngày đặt hàng: {{ $formatValue.formatDateTime(order?.createdAt) }}</b>
                        <p>{{ statusOrder() }}, ngày {{ $formatValue.formatDateTime(order?.updatedAt) }}</p>
                    </div>
                </div>
                <div class="detail-header-item">
                    <h5>Hình thức thanh toán</h5>
                    <div class="detailt-header-payments-method header-item-content">
                        <b>Thanh toán bằng ví ZaloPay</b>
                        <p>Thanh toán thất bại. Vui lòng thanh toán lại hoặc chọn phương thức thanh toán khác</p>
                    </div>
                </div>
            </div>
            <div class="order-detail-body">
                <div class="detailt-body-title">
                    <div class="detail-body-product">
                        <p>Sản phẩm</p>
                    </div>
                    <div class="detail-body-price">
                        <p>Giá</p>
                    </div>
                    <div class="detail-body-quantity">
                        <p>Số lượng</p>
                    </div>
                    <div class="detail-body-discount">
                        <p>Giảm giá</p>
                    </div>
                    <div class="detailt-body-total">
                        <p>Tạm tính</p>
                    </div>
                </div>
                <div class="line"></div>
                <div v-for="item in orderDetailList" :key="item?.id" class="detail-body-content">
                    <div class="body-content-product detail-body-product">
                        <img :src="item.product?.imageProducts[0]?.url" alt="">
                        <div class="product-info">
                            <p title="" class="product-name">{{ item?.product?.title }}</p>
                            <p class="product-classify">Phân loại: {{ item?.classify }}</p>
                        </div>
                    </div>
                    <div class="detail-body-price">
                        <p>{{ $formatValue.formatMoney(item?.product?.price) }}</p>
                    </div>
                    <div class="detail-body-quantity">
                        <p>{{ item?.quantity }}</p>
                    </div>
                    <div class="detail-body-discount">
                        <p>{{ $formatValue.formatMoney(item?.product?.price - item?.price) }}</p>
                    </div>
                    <div class="detailt-body-total">
                        <p>{{ $formatValue.formatMoney(item?.price * item?.quantity) }}</p>
                    </div>
                </div>
                <div class="line"></div>
                <div class="detail-body-total">
                    <div class="product-total">
                        <div class="total-title">
                            <p>Tạm tính</p>
                            <p>Phí vận chuyển</p>
                            <p>Giảm giá</p>
                            <p>Tổng thanh toán</p>
                        </div>
                        <div class="total-value">
                            <p>{{ $formatValue.formatMoney(order?.totalMoney) }}</p>
                            <p>32.000₫</p>
                            <p>-5.000₫</p>
                            <p>{{ $formatValue.formatMoney(order?.totalMoney) }}</p>
                        </div>
                    </div>
                    <div class="btn">
                        <b-button type="secondary">Mua lại</b-button>
                    </div>
                </div>
            </div>
            <div class="order-detail-footer">
                <router-link :to="{ name: 'OrdersPage' }">&lt;&lt; Quay lại đơn hàng của tôi</router-link>
            </div>
        </div>
    </div>
</template>

<script setup>
import { useOrderStore } from '@/stores/order';
import { useOrderDetailStore } from '@/stores/orderDetail';
import { storeToRefs } from 'pinia';
import { nextTick, ref } from 'vue';
import { useRoute } from 'vue-router';



// ---------------------- Props ----------------------


// ---------------------- Khai báo biến --------------
const route = useRoute();

const orderStore = useOrderStore();
const oderDetailStore = useOrderDetailStore();
const { orderDetailList } = storeToRefs(oderDetailStore);
const { order } = storeToRefs(orderStore);
const orderId = ref(route.params.id);

// ---------------------- Watcher --------------------


// ---------------------- Lifecycle ------------------
nextTick(async () => {
    await orderStore.fetchGetById(orderId.value);
    await oderDetailStore.fetchGetAllByOrderId(orderId.value);
    console.log(order.value);
    console.log(orderDetailList.value);
})

// ---------------------- Hàm xử lý ------------------
const statusOrder = () => {
    if (order.value.status === 'PENDING') {
        return 'Đơn hàng đang chờ xử lý';
    } else if (order.value?.status === 'PROCESSED') {
        return 'Đơn hàng đã xử lý thành công'
    } else if (order.value?.status === 'SHIPPING') {
        return 'Đơn hàng đang vận chuyển'
    } else if (order.value?.status === 'SHIPPED') {
        return 'Đơn hàng đã được vận chuyển'
    } else if (order.value?.status === 'CANCELLED') {
        return 'Đơn hàng đã được hủy thành công'
    }
}

</script>

<style scoped src="./order-detail.css"></style>