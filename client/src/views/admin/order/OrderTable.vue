<script setup>
import { nextTick, ref, watch } from 'vue';
import { storeToRefs } from 'pinia';
import { useOrderStore } from '@/stores/order';
import { dialog, dialogConfirm } from '@/helpers/swal';

const orderStore = useOrderStore();
const { loadingOrder, orderList, pagination } = storeToRefs(orderStore);
const totalPage = ref(pagination.value.lastPage + 1);
const page = ref(1);
const perPage = ref(12);


nextTick(async () => {
    await orderStore.fetchGetAllOrder(page.value - 1, perPage.value);
    totalPage.value = pagination.value.lastPage + 1;
})

watch(() => page.value, async () => {
    await orderStore.fetchGetAllOrder(page.value - 1, perPage.value);
})

const getStatusOrder = (status) => {
    switch (status) {
        case 'PENDING':
            return 'Chờ xác nhận';
        case 'PAID':
            return 'Đã thanh toán';
        case 'UNPAID':
            return 'Chưa thanh toán';
        case 'PROCESSED':
            return 'Đã xác nhận';
        case 'SHIPPING':
            return 'Đang vận chuyển';
        case 'SHIPPED':
            return 'Đã giao';
        case 'CANCELLED':
            return 'Đã huỷ';
        default:
            return '';
    }
}

const handleUpdateStatus = async (item) => {
    if (item?.status === 'SHIPPED') {
        dialog('Thông báo', 'warning', 'Đơn hàng đã giao thành công');
        return;
    }
    if (item?.status === 'CANCELLED') {
        dialog('Thông báo', 'warning', 'Đơn hàng đã bị hủy');
        return;
    }
    if (item?.status === 'UNPAID') {
        dialog('Thông báo', 'warning', 'Đơn hàng thanh toán không thành công');
        return;
    }
    let status = '';
    switch (item?.status) {
        case 'PENDING':
            status = 'PROCESSED';
            break;
        case 'PROCESSED', 'PAID':
            status = 'SHIPPING';
            break;
        case 'SHIPPING':
            status = 'SHIPPED';
            break;
        default:
            break;
    }
    await orderStore.fetchUpdateOrder(item?.id, {
        status
    }, page.value - 1, perPage.value);
}

const handleCancelOrder = async (item) => {
    if (item?.status === 'CANCELLED') {
        dialog('Thông báo', 'warning', 'Đơn hàng đã bị hủy');
        return;
    }
    dialogConfirm('Xác nhận', 'Hủy đơn hàng?', async () => {
        await orderStore.fetchUpdateOrder(item?.id, {
            status: 'CANCELLED'
        }, page.value - 1, perPage.value);
    })
}

</script>

<template>
    <div class="loading" v-if="loadingOrder">
        <spinner-loader></spinner-loader>
    </div>
    <div class="admin-table">
        <div class="admin-header">
            <h1>Quản lý đơn hàng</h1>
        </div>
        <table class="table table-bordered border-primary">
            <thead>
                <tr>
                    <th scope="col">#</th>
                    <th scope="col">Họ và tên</th>
                    <th scope="col">Số điện thoại</th>
                    <th scope="col">Địa chỉ</th>
                    <th scope="col">Ghi chú</th>
                    <th scope="col">Mã giảm giá</th>
                    <th scope="col">Hình thức TT</th>
                    <th scope="col">Tổng tiền</th>
                    <th scope="col">Trạng thái</th>
                    <th scope="col">Chức năng</th>
                </tr>
            </thead>
            <tbody>
                <tr v-for="(item, index) in orderList" :key="item?.id">
                    <th width="50px" scope="row">{{ (index + 1) + (perPage * (page - 1)) }}</th>
                    <td>{{ item.fullName }}</td>
                    <td>{{ item?.phoneNumber }}</td>
                    <td class="value-too-long" :title="item?.address">
                        <span>
                            {{ item?.address }}
                        </span>
                    </td>
                    <td class="value-too-long" :title="item?.note">
                        <span>{{ item?.note }}</span>
                    </td>
                    <td>{{ item?.coupon?.code }}</td>
                    <td>{{ item?.paymentMethod }}</td>
                    <td>{{ item?.totalMoney }}</td>
                    <td class="status-order">
                        <span :class="['status-order-item', item?.status.toLowerCase()]">
                            {{ getStatusOrder(item?.status) }}
                        </span>
                    </td>
                    <td class="action">
                        <button @click="handleUpdateStatus(item)" class="btn-confirm">
                            <i class="fa-regular fa-square-check"></i>
                        </button>
                        <button @click="handleCancelOrder(item)" class="btn-cancel">
                            <i class="fa-solid fa-ban"></i>
                        </button>
                    </td>
                </tr>
            </tbody>
        </table>
        <v-pagination v-model="page" size="40" :length="totalPage" rounded="circle"></v-pagination>
    </div>
</template>

<style lang="css" src="../admin-table.css" scoped></style>
