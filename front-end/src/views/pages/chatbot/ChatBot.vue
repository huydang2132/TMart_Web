<template>
    <div class="chatbot">
        <img :src="require('@/assets/imgs/chatbot.gif')" alt="">
    </div>
</template>

<script setup>

</script>

<style scoped>
.chatbot {
    position: fixed;
    bottom: 30px;
    right: 30px;
    z-index: 100;
    border-radius: 50%;
    width: 55px;
    height: 55px;
    cursor: pointer;
}

.chatbot>img {
    width: inherit;
    height: inherit;
    object-fit: contain;
    border-radius: 50%;
    box-shadow: 0 5px 10px var(--color-box-shadow);
    border: 1px solid var(--color-primary);
}
</style>