<script setup>
import { dialogConfirm } from '@/helpers/swal';
import ProductModal from './ProductModal.vue';
import { useProductStore } from '@/stores/product';
import { nextTick, ref, watch } from 'vue';
import { storeToRefs } from 'pinia';


const productStore = useProductStore();
const { loading } = storeToRefs(productStore);
const page = ref(1);
const perPage = ref(12);
const totalPage = ref(0);
const productsData = ref([]);
const statusForm = ref('ADD');
const showModal = ref(false);
const productId = ref(null);

nextTick(async () => {
    await productStore.fetchGetAll(page.value - 1, perPage.value);
    productsData.value = productStore.products?.data;
    page.value = productStore.products?.pagination?.page + 1;
    totalPage.value = productStore.products?.pagination?.lastPage + 1;
})

watch(() => page.value, async () => {
    await productStore.fetchGetAll(page.value - 1, perPage.value);
    productsData.value = productStore.products?.data;
    page.value = productStore.products?.pagination?.page + 1;
    totalPage.value = productStore.products?.pagination?.lastPage + 1;
})

const handleAddProduct = () => {
    statusForm.value = 'ADD';
    showModal.value = true;
}

const handleEditProduct = (id) => {
    statusForm.value = 'EDIT';
    showModal.value = true;
    productId.value = id;
}

const handleCloseModal = () => {
    showModal.value = false;
}

const handleDeleteProduct = async (id) => {
    dialogConfirm('Xác nhận xóa', 'Bạn có chắc muốn xóa sản phẩm này', async () => {
        await productStore.fetchDelete(id);
    })
    await productStore.fetchGetAll(page.value - 1, perPage.value);
}
</script>

<template>
    <div class="loading" v-if="loading">
        <spinner-loader></spinner-loader>
    </div>
    <div class="admin-table">
        <div class="admin-header">
            <h1>Quản lý sản phẩm</h1>
            <div class="btn-group">
                <b-button @click="handleAddProduct" type="primary">Thêm sản phẩm</b-button>
                <b-button type="primary">Sản phẩm đã xóa</b-button>
            </div>
        </div>
        <table class="table table-bordered border-primary">
            <thead>
                <tr>
                    <th scope="col">#</th>
                    <th scope="col">Mã sản phẩm</th>
                    <th scope="col">Tên sản phẩm</th>
                    <th scope="col">Tên danh mục</th>
                    <th scope="col">Giá bán</th>
                    <th scope="col">Số lượng</th>
                    <th scope="col">Giảm giá</th>
                    <th class="action" scope="col">Chức năng</th>
                </tr>
            </thead>
            <tbody>
                <tr v-for="(item, index) in productStore.products.data" :key="item?.id">
                    <th width="50px" scope="row">{{ (index + 1) + (perPage * (page - 1)) }}</th>
                    <td>{{ item?.id }}</td>
                    <td class="value-too-long" :title="item?.title">
                        <span>{{ item?.title }}</span>
                    </td>
                    <td>{{ item?.category?.name }}</td>
                    <td>{{ $formatValue.formatMoney(item?.price) }}</td>
                    <td>{{ item?.quantity }}</td>
                    <td>{{ item?.discount }}%</td>
                    <td class="action">
                        <button @click="handleEditProduct(item?.id)" class="btn-edit">
                            <i class="fa-solid fa-pencil"></i>
                        </button>
                        <button @click="handleDeleteProduct(item?.id)" class="btn-delete">
                            <i class="fa-solid fa-trash-can"></i>
                        </button>
                    </td>
                </tr>
            </tbody>
        </table>
        <v-pagination v-model="page" size="40" :length="totalPage" rounded="circle"></v-pagination>
    </div>
    <ProductModal :productId="productId" v-if="showModal" @closeModal="handleCloseModal" :statusForm="statusForm">
    </ProductModal>
</template>

<style lang="css" src="../admin-table.css" scoped></style>
