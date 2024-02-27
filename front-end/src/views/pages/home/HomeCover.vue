<template>
    <div id="cover">
        <v-carousel height="100%" cycle hide-delimiter-background :show-arrows="false">
            <v-carousel-item v-for=" item  in  items " :key="item" :src="require(`@/assets/imgs/cover${item}.jpg`)">
            </v-carousel-item>
        </v-carousel>
    </div>
</template>

<script setup>
import { ref } from 'vue';

// ------------------------- Khai báo biến ---------------------------
const items = ref(2);

// ------------------------- Lifecycle --------------------------------


// ------------------------- Hàm xử lý -------------------------------



</script>

<style scoped>
#cover {
    width: 100%;
    display: flex;
    justify-content: center;
    background-color: var(--color-white);
    border-radius: var(--border-radius-page);
    padding: 10px;
}

#cover>img {
    object-fit: cover;
    width: 100%;
}
</style>