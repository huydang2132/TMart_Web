<template>
    <div class="order-detail">
        <div class="order-detail-section">
            <h3>Chi tiết đơn hàng</h3>
            <div class="order-detail-header">
                <div class=" detail-header-item">
                    <h5>Địa chỉ người nhận</h5>
                    <div class="detail-header-address header-item-content">
                        <b class="full-name">Đặng Đình Huy</b>
                        <p class="address">Địa chỉ: Khu 9 Xóm Hội - Xã Mê Linh, Xã Mê Linh, Huyện Mê Linh, Hà Nội, Việt
                            Nam
                        </p>
                        <p class="phone-number">Điện thoại: 0378858091</p>
                    </div>
                </div>
                <div class="detail-header-item">
                    <h5>Trạng thái</h5>
                    <div class="detail-header-status header-item-content">
                        <b>Ngày đặt hàng: 12:58 01/02/2021</b>
                        <p>Đơn hàng đã được giao thành công, ngày 12:58 01/02/2021</p>
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
                <div class="detail-body-content">
                    <div class="body-content-product detail-body-product">
                        <img :src="require('@/assets/imgs/Iphone15-promax.webp')" alt="">
                        <div class="product-info">
                            <p title="" class="product-name">500 Câu Hỏi Luyện Thi Năng Lực Nhật Ngữ Trình Độ N2</p>
                            <p class="product-classify">Phân loại: Đen</p>
                        </div>
                    </div>
                    <div class="detail-body-price">
                        <p>58.400 ₫</p>
                    </div>
                    <div class="detail-body-quantity">
                        <p>1</p>
                    </div>
                    <div class="detail-body-discount">
                        <p>17.520 ₫</p>
                    </div>
                    <div class="detailt-body-total">
                        <p>40.880 ₫</p>
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
                            <p>265.000₫</p>
                            <p>32.000₫</p>
                            <p>-5.000₫</p>
                            <p>300.000₫</p>
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

// ---------------------- Props ----------------------


// ---------------------- Khai báo biến --------------


// ---------------------- Watcher --------------------


// ---------------------- Lifecycle ------------------


// ---------------------- Hàm xử lý ------------------

</script>

<style scoped src="./order-detail.css"></style>