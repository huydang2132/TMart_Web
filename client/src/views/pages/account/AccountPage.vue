<template>
    <div class="account">
        <div class="account-section">
            <div class="account-avatar">
                <div class="account-img">
                    <img :src="urlImg ? urlImg : require('@/assets/imgs/avatar.png')" alt="Ảnh đại diện" />
                    <input @change="handleChangeImg($event)" id="avatar" type="file" hidden />
                    <label for="avatar">
                        <i class="fa-solid fa-pencil"></i>
                    </label>
                    <span>Ảnh đại diện</span>
                </div>
            </div>
            <div class="line-vertical"></div>
            <div class="account-info">
                <div class="account-info-title">
                    <h3>Thông tin tài khoản</h3>
                </div>
                <div class="account-info-row">
                    <div class="info-content-item user-name">
                        <label for="userName">Tên đăng nhập</label>
                        <b-input id="userName" readonly />
                    </div>
                    <div class="info-content-item full-name">
                        <label for="fullName">Họ tên</label>
                        <b-input id="fullName" />
                    </div>
                </div>
                <div class="account-info-row">
                    <div class="info-content-item email">
                        <label for="email">Email</label>
                        <b-input id="email" />
                    </div>
                    <div class="info-content-item phone-number">
                        <label for="phoneNumber">Số điện thoại</label>
                        <b-input id="phoneNumber" />
                    </div>
                </div>
                <div class="account-info-row">
                    <div class="info-content-item date-birth">
                        <label for="dateOfBirth">Ngày sinh</label>
                        <b-datepicker v-model="date" />
                    </div>
                    <div class="info-content-item gender">
                        <label for="gender">Giới tính</label>
                        <div class="radio-gender">
                            <b-checkbox type="radio" label="Nam" name="gender" />
                            <b-checkbox type="radio" label="Nữ" name="gender" />
                            <b-checkbox type="radio" label="Khác" name="gender" />
                        </div>
                    </div>
                </div>
                <div class="account-btn">
                    <b-button value="Lưu thay đổi" type="primary" />
                </div>
            </div>
        </div>
    </div>
</template>

<script setup>
import { ref } from 'vue';


// ---------------------- Props ----------------------
const urlImg = ref(null);
const date = ref();

// ---------------------- Khai báo biến --------------


// ---------------------- Watcher --------------------


// ---------------------- Lifecycle ------------------


// ---------------------- Hàm xử lý ------------------
const handleChangeImg = (event) => {
    urlImg.value = URL.createObjectURL(event.target.files[0]);
}


</script>

<style scoped src="./account.css"></style>