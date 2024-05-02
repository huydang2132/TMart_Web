<script setup>
import { defineEmits } from 'vue';

const emits = defineEmits(['closeModal']);

const handleCloseModal = () => {
    emits('closeModal');
}

</script>

<template>
    <div class="admin-modal__container">
        <div class="admin-modal__section" v-click-outside="handleCloseModal">
            <div class="modal-header">
                <h5>Thêm mới sản phẩm</h5>
                <button @click="handleCloseModal()"><i class="fa-solid fa-xmark"></i></button>
            </div>
            <div class="modal-body">
                <form class="row g-3 form-group">
                    <div class="form-item col-md-6">
                        <label for="title" class="form-label">Tên sản phẩm</label>
                        <input type="text" class="form-control" id="title" required>
                    </div>
                    <div class="form-item col-md-6">
                        <label for="category" class="form-label">Danh mục sản phẩm</label>
                        <select class="form-select" id="category" required>
                            <option>Choose...</option>
                            <option>...</option>
                        </select>
                    </div>
                    <div class="form-item col-md-6">
                        <label for="price" class="form-label">Giá sản phẩm</label>
                        <input type="text" class="form-control" id="price" required>
                    </div>
                    <div class="form-item col-md-6">
                        <label for="discount" class="form-label">Giảm giá</label>
                        <input type="text" class="form-control" id="discount" required>
                    </div>
                    <div class="form-item col-md-12">
                        <label for="description" class="form-label">Mô tả</label>
                        <textarea type="text" class="form-control" id="description" required></textarea>
                    </div>
                    <div class="btn-submit col-12">
                        <button class="btn btn-primary" type="submit">Lưu</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</template>

<style lang="css" src="../admin-modal.css" scoped></style>
