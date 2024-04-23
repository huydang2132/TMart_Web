<template>
    <b-carousel :autoplay="4000" :navigator="false" :pagination="false">
        <V-Slide v-for="slide in ['Asus_sliding', 'iphone-15-17390-sliding', 'MSI_sliding', 'soundpeats_watch-sliding']"
            :key="slide">
            <RouterLink class="carousel__item" to="/">
                <img :src="require('@/assets/imgs/' + slide + '.webp')" alt="">
            </RouterLink>
        </V-Slide>
    </b-carousel>
</template>

<script setup>

// ------------------------- Khai báo biến ---------------------------

// ------------------------- Lifecycle --------------------------------


// ------------------------- Hàm xử lý -------------------------------



</script>

<style scoped></style>