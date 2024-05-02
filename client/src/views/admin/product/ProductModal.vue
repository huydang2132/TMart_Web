<script setup>
import { toastify } from '@/helpers/toastify';
import { useCategoryStore } from '@/stores/category';
import { useProductStore } from '@/stores/product';
import { useUserStore } from '@/stores/user';
import { defineEmits, nextTick, reactive, ref, defineProps, toRaw } from 'vue';

const props = defineProps({
    statusForm: String,
    productId: String
})

const emits = defineEmits(['closeModal']);

const categoryStore = useCategoryStore();
const categoriesData = ref([]);
const productStore = useProductStore();
const productImages = ref([]);
const userStore = useUserStore();

const isChangeImg = ref(false)

const productData = reactive({
    title: null,
    categoryId: null,
    price: 0,
    quantity: 1,
    discount: 0,
    description: null,
    images: null,
    createdBy: userStore.fullName
});

nextTick(async () => {
    await categoryStore.fecthGetAll();
    categoriesData.value = categoryStore.categories.data;
    if (props.statusForm === 'EDIT') {
        await productStore.fetchGetById(props.productId);
        console.log(productStore.product);
        productData.title = productStore.product.title;
        productData.categoryId = productStore.product.category.id;
        productData.price = productStore.product.price;
        productData.quantity = productStore.product.quantity;
        productData.discount = productStore.product.discount;
        productData.description = productStore.product.description;
        productData.images = productStore.product.imageProducts;
        if (productStore.product.imageProducts) {
            productImages.value = productStore.product.imageProducts.map(image => image.url);
        }
    }

})

const handleCloseModal = () => {
    emits('closeModal');
}

const changeImg = (event) => {
    let files = event.target.files;
    let urlImages = productImages.value;
    if (urlImages.length + files.length > 5) {
        toastify('Chỉ chọn tối đa 5 ảnh', 'warning');
        return;
    }
    else {
        for (let i = 0; i < files.length; i++) {
            urlImages.push(URL.createObjectURL(files[i]));
        }
        productImages.value = urlImages;
        productData.images = files;
        isChangeImg.value = true;
    }
}

const handleSubmit = async () => {
    let formData = new FormData();
    if (productData.images) {
        for (let i = 0; i < productData.images.length; i++) {
            formData.append("images", productData.images[i]);
        }
    }
    let res;
    if (props.statusForm === 'ADD') {
        res = await productStore.fetchInsert(toRaw(productData));
    }
    else if (props.statusForm === 'EDIT') {
        res = await productStore.fetchUpdate(props.productId, toRaw(productData));
    }
    if (res && productData.images && isChangeImg.value) {
        await productStore.fetchPostImage(res.id, formData);
    }
    emits('closeModal');
}
</script>

<template>
    <div class="admin-modal__container">
        <div v-if="productStore.loading" class="loading">
            <spinner-loader></spinner-loader>
        </div>
        <div class="admin-modal__section" v-click-outside="handleCloseModal">
            <div class="modal-header">
                <h5>{{ statusForm === 'ADD' ? 'Thêm mới sản phẩm' : 'Sửa sản phẩm' }}</h5>
                <button @click="handleCloseModal()" title="Đóng"><i class="fa-solid fa-xmark"></i></button>
            </div>
            <div class="modal-body">
                <form @submit.prevent="handleSubmit" class="row g-3 form-group">
                    <div class="form-item col-md-6">
                        <label for="title" class="form-label">Tên sản phẩm</label>
                        <input v-model="productData.title" type="text" class="form-control" id="title" required>
                    </div>
                    <div class="form-item col-md-6">
                        <label for="category" class="form-label">Danh mục sản phẩm</label>
                        <select v-model="productData.categoryId" class="form-select" id="category" required>
                            <option v-for="category in categoriesData" :value="category.id" :key="category.id">
                                {{ category.name }}
                            </option>
                        </select>
                    </div>
                    <div class="form-item col-md-4">
                        <label for="price" class="form-label">Giá sản phẩm</label>
                        <input v-model="productData.price" type="number" class="form-control" id="price" required>
                    </div>
                    <div class="form-item col-md-4">
                        <label for="discount" class="form-label">Giảm giá</label>
                        <input v-model="productData.discount" type="number" class="form-control" id="discount" required>
                    </div>
                    <div class="form-item col-md-4">
                        <label for="quantity" class="form-label">Số lượng</label>
                        <input v-model="productData.quantity" type="number" class="form-control" id="quantity" required>
                    </div>

                    <div class="form-item col-md-12">
                        <label for="description" class="form-label">Mô tả</label>
                        <textarea v-model="productData.description" type="text" class="form-control" id="description"
                            required></textarea>
                    </div>
                    <div class="form-item col-md-12">
                        <label for="images" class="form-label">Ảnh sản phẩm</label>
                        <input @change="changeImg($event)" multiple="multiple" type="file" class="form-control"
                            id="images">
                    </div>
                    <div class="form-item col-md-12">
                        <img v-for="url in productImages" :key="url" :src="url" alt="">
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
