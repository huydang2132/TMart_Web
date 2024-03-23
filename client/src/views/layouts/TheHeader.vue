<template>
    <header id="header">
        <div class="logo">
            <router-link :to="{ name: 'HomePage' }"><span>T</span>Mart</router-link>
        </div>
        <div class="search-box">
            <div class="search-box-item">
                <i class="fa-solid fa-magnifying-glass"></i>
                <b-input ref="refInputSearch" @input="handleSearch()" v-model:modelValue="searchValue"
                    placeholder="Tìm kiếm..." />
                <b-button value="Tìm kiếm" />
            </div>
        </div>
        <div class="redirect">
            <div class="redirect__home">
                <b-button @click="handleRedirectHome" icon="fa-solid fa-house" value="Trang chủ" />
            </div>
            <div class="redirect__account">
                <div class="account-notify">
                    <router-link :to="{ name: 'NotifyPage' }">
                        <i class="fa-solid fa-bell"></i>
                        Thông báo
                        <span class="quantity-notify">3</span>
                    </router-link>
                </div>
                <div class="account-tile" @mouseover="isShowAccOption = true" @mouseleave="isShowAccOption = false">
                    <img :src="require('@/assets/imgs/avatar.png')" alt="">
                    <span>Đăng nhập</span>
                </div>
                <div @mouseover="isShowAccOption = true" @mouseleave="isShowAccOption = false" v-show="isShowAccOption"
                    class="account-box">
                    <div class="account-router-link">
                        <router-link :to="{ name: 'AccountPage' }">Thông tin tài khoản</router-link>
                    </div>
                    <div class="account-router-link">
                        <router-link :to="{ name: 'OrdersPage' }">Đơn hàng</router-link>
                    </div>
                    <div class="account-router-link">
                        <button>Đăng xuất</button>
                    </div>
                </div>
            </div>
        </div>
        <div class="cart">
            <div class="cart-item">
                <b-button @click="handleRedirectCart" icon="fa-solid fa-cart-shopping">
                    <span class="quantity-notify">3</span>
                </b-button>
            </div>
        </div>
    </header>
</template>

<script>
import router from '@/routers/router';
import _ from 'lodash';

export default {
    name: 'TheHeader',
    data() {
        return {
            searchValue: '',
            isShowAccOption: false,
        }
    },
    methods: {
        /**
         * Hàm chuyển hướng đến trang chủ
         */
        handleRedirectHome() {
            router.push('/');
        },
        /**
         * Hàm chuyển hướng đến giỏ hàng
         * Created by: DDHuy (17-01-2024)
         */
        handleRedirectCart() {
            router.push('/cart');
        },
        /**
         * Hàm xử lý chức năng tìm kiếm
         */
        handleSearch: _.debounce(function () {
            console.log('tìm kiếm', this.searchValue);
        }, 500),
    },
    mounted() {
        this.$nextTick(() => {
            this.$refs.refInputSearch.focus();
        })
    },
    watch: {

    },
}
</script>

<style scoped>
#header {
    height: var(--height-header);
    background-color: var(--color-white);
    width: 100%;
    display: flex;
    justify-content: space-between;
    align-items: center;
    padding: 0 40px;
    gap: 10px;
    position: sticky;
    top: 0;
    z-index: 10;
}

#header .logo {
    width: 10%;
}

#header .logo>a,
#header .logo>a>span {
    font-family: cursive;
    font-size: 3rem;
    color: var(--color-primary);
    cursor: pointer;
    text-decoration: none;
    font-weight: 600;
}

#header .logo>a>span {
    color: var(--color-orange);
}

#header .search-box {
    display: flex;
    width: 62%;
    justify-content: center;
    align-items: center;
}

#header .search-box .search-box-item {
    display: flex;
    width: 100%;
    border: 1px solid var(--color-border);
    border-radius: var(--border-radius-page);
    align-items: center;
}

#header .search-box .search-box-item>i {
    text-align: center;
    font-size: 1.2rem;
    color: var(--color-greyish);
    width: 5%;
}

#header .search-box .search-box-item>input {
    border: none;
    width: 85%;
}

#header .search-box .search-box-item>button {
    width: 15%;
    background-color: transparent;
    color: var(--color-primary);
    border-radius: unset;
    box-sizing: border-box;
    border-radius: 0 var(--border-radius-page) var(--border-radius-page) 0;
    position: relative;
}

#header .search-box .search-box-item>button::after {
    content: "";
    position: absolute;
    left: -1px;
    height: 70%;
    width: 1px;
    background-color: var(--color-border);
}

#header .search-box .search-box-item>button:hover,
#header .cart .cart-item>button:hover,
#header .redirect .redirect__home>button:hover,
#header .redirect .redirect__account>.account-tile:hover {
    background-color: var(--color-primary-opacity);
}

#header .redirect {
    display: flex;
    width: 28%;
    justify-content: flex-end;
}

#header .redirect button {
    background-color: transparent;
}

.redirect .redirect__account {
    position: relative;
    display: flex;
}

.redirect .redirect__account .account-notify {
    border-radius: var(--border-radius);
    position: relative;
}

.quantity-notify {
    position: absolute;
    top: 0;
    right: 0;
    display: flex;
    background-color: var(--color-red);
    border-radius: 50%;
    height: 15px;
    width: 15px;
    font-size: 0.7rem;
    align-items: center;
    justify-content: center;
    font-weight: 500;
    color: var(--color-white);
    user-select: none;
}

.redirect .redirect__account .account-notify:hover {
    background-color: var(--color-primary-opacity);
}

.redirect .redirect__account .account-notify>a {
    text-decoration: none;
    color: var(--color-text);
    font-weight: 500;
    display: block;
    height: 100%;
    padding: 10px 15px;
    height: var(--height-btn-default);
}

.redirect .redirect__account .account-notify>a>i {
    font-size: 15px;
}

.redirect .redirect__account .account-tile {
    display: flex;
    gap: 5px;
    align-items: center;
    font-weight: 500;
}

.redirect .redirect__account .account-tile>img {
    object-fit: cover;
    width: 15px;
}

.redirect .redirect__account .account-tile {
    padding: 10px 15px;
    height: var(--height-btn-default);
    border-radius: var(--border-radius);
    cursor: pointer;
}

.redirect .redirect__account .account-box {
    position: absolute;
    top: 36px;
    right: 0;
    background-color: var(--color-white);
    box-shadow: 0 2px 4px var(--color-box-shadow);
    border-radius: var(--border-radius);
    width: 150px;
}

.redirect .redirect__account .account-box .account-router-link:hover {
    background-color: var(--color-primary-opacity);
}

.redirect .redirect__account .account-box .account-router-link>a {
    text-decoration: none;
    color: var(--color-text);
    display: block;
    padding: 10px;
}

.redirect .redirect__account .account-box .account-router-link>button {
    color: var(--color-text);
    border: none;
    padding: 10px;
    cursor: pointer;
    width: 100%;
    text-align: left;
}

#header .cart {
    width: 5%;
    display: flex;
    justify-content: center;
    position: relative;
}

.cart .cart-item {
    width: fit-content;
    position: relative;
}

#header .cart::after {
    content: "";
    position: absolute;
    width: 1px;
    height: 70%;
    left: 0px;
    background-color: var(--color-border);
    top: 50%;
    transform: translateY(-50%);
}

#header .cart .cart-item>button {
    background-color: transparent;
    color: var(--color-primary);
}
</style>