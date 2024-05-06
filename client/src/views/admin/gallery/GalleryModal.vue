<script setup>
import { useGalleryStore } from '@/stores/gallery';
import { useProductStore } from '@/stores/product';
import { storeToRefs } from 'pinia';
import { defineEmits, nextTick, defineProps } from 'vue';

const props = defineProps({
    statusForm: String,
    galleryId: String
})

const emits = defineEmits(['closeModal']);

const galleryStore = useGalleryStore();
const productStore = useProductStore();

const { gallery } = storeToRefs(galleryStore);

nextTick(async () => {
    await productStore.fetchGetAll();
    if (props.statusForm === 'EDIT') {
        await galleryStore.fetchGetById(props.galleryId);
        console.log(gallery.value);
    }
})

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
                <form class="form-gallery row g-3 form-group">
                    <table class="col-md-12 table table-bordered border-primary">
                        <thead>
                            <tr>
                                <th scope="col">#</th>
                                <th scope="col">Tên sản phẩm</th>
                                <th scope="col">Ngày tạo</th>
                            </tr>
                        </thead>
                        <tbody>
                            <tr style="cursor: pointer;" v-for="(item) in 5" :key="item">
                                <td class="value-too-long" :title="item">
                                    <span>{{ item }}</span>
                                </td>
                                <td>{{ item }}</td>
                                <td>{{ item }}</td>
                            </tr>
                        </tbody>
                    </table>
                    <v-pagination v-model="page" size="35" :length="4" rounded="circle"></v-pagination>
                    <div class="form-item col-md-12">
                        <label for="images" class="form-label">Ảnh sản phẩm</label>
                        <input @change="changeImg($event)" multiple="multiple" type="file" class="form-control"
                            id="images">
                    </div>
                    <div class="btn-submit col-12">
                        <button class="btn btn-primary" type="submit">Lưu</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
</template>

<style lang="css" src="../admin-modal.css" scoped>
.form-gallery table tbody tr {
    cursor: pointer;
}
</style>
