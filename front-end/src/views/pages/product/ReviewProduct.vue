<template>
    <div id="review-product">
        <div class="review-header">
            <h3>ĐÁNH GIÁ SẢN PHẨM</h3>
            <div class="review-filter">
                <div class="filter-title">
                    <label>3.5/5</label>
                    <b-rating :value="3.5" isReadonly fontSize="22" />
                </div>
                <div class="filter-star">
                    <div class="filter-star-item all-star active">Tất cả</div>
                    <div class="filter-star-item five-star">5 Sao</div>
                    <div class="filter-star-item four-star">4 Sao</div>
                    <div class="filter-star-item three-star">3 Sao</div>
                    <div class="filter-star-item two-star">2 Sao</div>
                    <div class="filter-star-item one-star">1 Sao</div>
                </div>
            </div>
        </div>
        <div class="review-body">
            <div class="review-content" v-for="item in 3" :key="item">
                <div class="review-item">
                    <div class="reviewer-avt">
                        <img :src="require('@/assets/imgs/white-bear.jpg')" alt="">
                    </div>
                    <div class="reviewer-info">
                        <label>huydang2132</label>
                        <b-rating :value="4" isReadonly />
                        <div class="reviewer-more-info">
                            <p class="reviewer-time">
                                13-04-2023 08:09
                            </p>
                            <div class="line"></div>
                            <p class="reviewer-classify">
                                Phân loại hàng: ĐEN
                            </p>
                        </div>
                        <div class="item-content">
                            <p>
                                Giao hàng cẩn thận, hàng đẹp
                            </p>
                            <div class="item-content-img">
                                <img :src="require('@/assets/imgs/Iphone15-promax.webp')" alt="">
                                <img :src="require('@/assets/imgs/Iphone15-promax.webp')" alt="">
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="review-footer">
            <div class="paging">
                <v-pagination :length="6" :size="45" color="#006fff" active-color="#0138ff" theme="#ffa500"></v-pagination>
            </div>
        </div>
    </div>
</template>

<script setup>

</script>

<style scoped>
#review-product {
    width: 100%;
    background-color: var(--color-white);
    border-radius: var(--border-radius-page);
    padding: 20px;
    box-shadow: 0 3px 5px var(--color-greyish);
}

.review-header>h3 {
    font-size: 1.3rem;
    font-weight: 500;
    padding-bottom: 10px;
}

.review-header .review-filter {
    padding: 30px;
    background-color: var(--bg-main);
    display: flex;
    gap: 50px;
    border-radius: var(--border-radius-page);
    align-items: center;
}

.review-filter .filter-title {
    display: flex;
    gap: 10px;
}

.review-filter .filter-title>label {
    font-size: 1.2rem;
    font-weight: 500;
}

.review-filter .filter-star {
    display: flex;
    gap: 10px;
}

.review-filter .filter-star .filter-star-item {
    background-color: var(--color-white);
    border: 1px solid var(--color-border);
    padding: 5px 20px;
    cursor: pointer;
    border-radius: var(--border-radius);
}

.review-filter .filter-star .filter-star-item.active {
    color: var(--color-primary);
    border-color: var(--color-primary);
}

.review-body .review-content {
    border-bottom: 1px solid var(--color-border);
    padding: 20px 10px;
}

.review-body .review-content .review-item {
    padding: 0 20px;
    display: flex;
    align-items: flex-start;
    gap: 10px;
}

.review-body .review-content .review-item .reviewer-avt>img {
    object-fit: cover;
    width: 40px;
    border-radius: 50%;
}

.review-item .reviewer-info {
    display: flex;
    flex-direction: column;
    gap: 6px;
}

.review-item .reviewer-info .reviewer-more-info {
    display: flex;
    gap: 5px;
    color: var(--color-grey);
}

.review-item .reviewer-info .reviewer-more-info>p {
    font-size: 0.9rem;
}

.review-item .reviewer-info .reviewer-more-info .line {
    height: auto;
    width: 1px;
    background-color: var(--color-border);
}

.review-item .reviewer-info .item-content {
    padding: 10px 0;
}

.review-item .reviewer-info .item-content>p {
    padding-bottom: 10px;
    line-height: 20px;
}

.review-item .reviewer-info .item-content .item-content-img {
    display: flex;
    gap: 10px;
}

.review-item .reviewer-info .item-content .item-content-img>img {
    object-fit: cover;
    width: 80px;
    border: 1px solid var(--color-border);
}

.review-footer {
    padding-top: 20px;
    display: flex;
    justify-content: center;
}

.review-footer .paging {
    width: 40%;
}
</style>