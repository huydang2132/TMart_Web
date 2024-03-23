<template>
    <div class="payments-page">
        <div class="address component-page">
            <div class="address-title">
                <i class="fa-solid fa-location-dot"></i>
                <h3>Địa chỉ nhận hàng</h3>
            </div>
            <div class="address-content">
                <b>Đặng Đình Huy - 0369555134</b>
                <p>Chợ Hạ, xóm Hội, thôn Hạ Lôi, Xã Mê Linh, Huyện Mê Linh, Hà Nội</p>
                <b-button value="Thay đổi" />
            </div>
        </div>
        <div class="product-info component-page">
            <div class="product-info-title">
                <div class="title-name product-info-name">
                    <span>Sản phẩm</span>
                </div>
                <div class="title-classify product-info-classify">
                </div>
                <div class="title-price product-info-price">
                    <span>Đơn giá</span>
                </div>
                <div class="title-quantity product-info-quantity">
                    <span>Số lượng</span>
                </div>
                <div class="title-total product-info-total">
                    <span>Thành tiền</span>
                </div>
            </div>
            <div class="product-info-content">
                <div class="content-name product-info-name">
                    <img :src="require('@/assets/imgs/Iphone15-promax.webp')" alt="">
                    <span title="">Quần tây nam hàn quốc JBagy dáng baggy vải co giãn dày dặn dáng suông ống rộng, màu đen,
                        kem -
                        JA0101</span>
                </div>
                <div class="content-classify product-info-classify">
                    <span>Loại: XANH NAVY,29</span>
                </div>
                <div class="content-price product-info-price">
                    <span>159.000₫</span>
                </div>
                <div class="title-quantity product-info-quantity">
                    <span>1</span>
                </div>
                <div class="title-total product-info-total">
                    <span>₫159.000₫</span>
                </div>
            </div>
            <div class="vouchers">
                <div class="voucher-icon">
                    <i class="fa-solid fa-ticket"></i>
                    <span>Voucher</span>
                </div>
                <div class="voucher-btn">
                    <b-button value="Chọn voucher" />
                </div>
            </div>
        </div>
        <div class="payments-method component-page">
            <div class="payments-method-header">
                <h3>Phương thức thanh toán</h3>
            </div>
            <div class="line"></div>
            <div class="payments-method-body">
                <div class="payments-method-item">
                    <input id="cod" type="radio" name="paymentsMethod" checked />
                    <label for="cod">
                        <i class="fa-solid fa-coins"></i>
                        Thanh toán khi nhận hàng
                    </label>
                </div>
                <div class="payments-method-item">
                    <input id="vnpay" type="radio" name="paymentsMethod" />
                    <label for="vnpay">
                        <i class="fa-solid fa-wallet"></i>
                        Thanh toán với VNPay
                    </label>
                </div>
            </div>
            <div class="line"></div>
            <div class="payments-method-footer">
                <div class="payments-info">
                    <div class="payments-title">
                        <p>Tạm tính</p>
                        <p>Phí vận chuyển</p>
                        <p>Giảm giá</p>
                        <p>Tổng thanh toán</p>
                    </div>
                    <div class="payments-value">
                        <p>265.000₫</p>
                        <p>32.000₫</p>
                        <p>-5.000₫</p>
                        <p>300.000₫</p>
                    </div>
                </div>
                <div class="line"></div>
                <div class="payments-btn">
                    <b-button value="ĐẶT HÀNG" type="primary" />
                </div>
            </div>
        </div>
    </div>
</template>

<script setup>

</script>

<style src="./payments.css" scoped></style>