<script setup>
import { nextTick, ref } from 'vue';
import UserModal from './UserModal.vue';
import { useUserStore } from '@/stores/user';
import { storeToRefs } from 'pinia';
import { dialogConfirm } from '@/helpers/swal';

const userStore = useUserStore();
const showModal = ref(false);
const statusForm = ref('ADD');
const userId = ref(null);
const { isLoading, usersData, pagination } = storeToRefs(userStore);
const page = ref(1);
const perPage = ref(10);
const totalPage = ref(pagination.value.lastPage + 1);

nextTick(async () => {
    await userStore.fetchGetAll(page.value - 1, perPage.value);
    totalPage.value = pagination.value.lastPage + 1;
})

const handleAddGallery = () => {
    showModal.value = true;
    statusForm.value = 'ADD';
}

const handleEdit = (id) => {
    showModal.value = true;
    statusForm.value = 'EDIT';
    userId.value = id;
}

const handleDelete = async (id) => {
    dialogConfirm('Xác nhận xóa', 'Bạn có chắc muốn xóa người dùng', async () => {
        await userStore.fetchDelete(id, 0, perPage.value);
    })
}

const handleCloseModal = () => {
    showModal.value = false;
}
</script>

<template>
    <div class="loading" v-if="isLoading">
        <spinner-loader></spinner-loader>
    </div>
    <div class="admin-table">
        <div class="admin-header">
            <h1>Quản lý người dùng</h1>
            <div class="btn-group">
                <b-button @click="handleAddGallery" type="primary">Thêm người dùng</b-button>
            </div>
        </div>
        <table class="table table-bordered border-primary">
            <thead>
                <tr>
                    <th scope="col">#</th>
                    <th scope="col">Họ và tên</th>
                    <th scope="col">Tên tài khoản</th>
                    <th scope="col">Email</th>
                    <th scope="col">Số điện thoại</th>
                    <th scope="col">Địa chỉ</th>
                    <th scope="col">Ngày sinh</th>
                    <th scope="col">Quyền</th>
                    <th class="action" scope="col">Chức năng</th>
                </tr>
            </thead>
            <tbody>
                <tr v-for="(item, index) in usersData" :key="item?.id">
                    <th width="50px" scope="row">{{ (index + 1) + (perPage * (page - 1)) }}</th>
                    <td class="value-too-long" :title="item?.fullName">
                        <span>{{ item?.fullName }}</span>
                    </td>
                    <td>{{ item?.userName }}</td>
                    <td>{{ item?.email }}</td>
                    <td>{{ item?.phoneNumber }}</td>
                    <td>{{ item?.address }}</td>
                    <td>{{ item?.dateOfBirth ? $formatValue.formatDate(item?.dateOfBirth) : '' }}</td>
                    <td>{{ item?.role?.id == 'ADMIN' ? 'Quản trị viên' : 'Người dùng' }}</td>
                    <td class="action">
                        <button @click="handleEdit(item?.id)" class="btn-edit">
                            <i class="fa-solid fa-pencil"></i>
                        </button>
                        <button @click="handleDelete(item?.id)" class="btn-delete">
                            <i class="fa-solid fa-trash-can"></i>
                        </button>
                    </td>
                </tr>
            </tbody>
        </table>
        <v-pagination v-model="page" size="40" :length="totalPage" rounded="circle"></v-pagination>
    </div>
    <UserModal :userId="userId" :page="page - 1" :perPage="perPage" v-if="showModal" @closeModal="handleCloseModal"
        :statusForm="statusForm">
    </UserModal>
</template>

<style lang="css" src="../admin-table.css" scoped></style>
