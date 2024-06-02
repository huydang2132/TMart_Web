<script setup>
import { useOrderDetailStore } from '@/stores/orderDetail';
import FeedbackItem from './FeedbackItem.vue';
import { storeToRefs } from 'pinia';
import { nextTick, ref } from 'vue';
import { useRoute } from 'vue-router';
import router from '@/routers/router';
import { useFeedbackStore } from '@/stores/feedback';
import { dialog } from '@/helpers/swal';
import { useOrderStore } from '@/stores/order';

const orderDetailStore = useOrderDetailStore();
const { orderDetailList, loadingOrderDetail } = storeToRefs(orderDetailStore);
const feedbackStore = useFeedbackStore();
const { feedbackListData } = storeToRefs(feedbackStore);
const orderStore = useOrderStore();

const route = useRoute();
const orderId = ref(route.params.id);

nextTick(async () => {
    await orderDetailStore.fetchGetAllByOrderId(orderId.value);
})

const backToOrderPage = () => {
    router.push({ name: 'OrdersPage' })
}

const handleFeedback = async () => {
    if (feedbackListData.value.length == 0) {
        dialog('Lỗi', 'error', 'Vui lý đánh giá cho sản phẩm');
        feedbackStore.fetchPushDataFeedback([]);
        return;
    }
    for (let i = 0; i < feedbackListData.value.length; i++) {
        if (feedbackListData.value[i].star == 0) {
            dialog('Lỗi', 'error', 'Vui lý đánh giá cho sản phâm');
            feedbackStore.fetchPushDataFeedback([]);
            return;
        }
    }
    await orderStore.fetchFeedbackOrder(orderId.value);
    await feedbackStore.fetchInsertMultiple(feedbackListData.value);
    feedbackStore.fetchPushDataFeedback([]);
}

</script>

<template>
    <div class="loading" v-if="loadingOrderDetail">
        <spinner-loader />
    </div>
    <div class="feedback__container container">
        <div class="feedback__section">
            <div class="feedback__header">
                <button @click="backToOrderPage()">
                    <i class="fa-solid fa-arrow-left"></i>
                </button>
                <h4>Đánh giá sản phẩm</h4>
            </div>
            <div class="feedback__body">
                <div class="feedback__content">
                    <FeedbackItem v-for="item in orderDetailList" :key="item" :classify="item.classify"
                        :data="item.product" />
                </div>
            </div>
            <div class="feedback__footer">
                <b-button @click="backToOrderPage()" type="secondary">Trở lại</b-button>
                <b-button @click="handleFeedback()" type="primary">Đánh giá</b-button>
            </div>
        </div>
    </div>
</template>

<style lang="css" src="./feedback.css" scoped></style>
