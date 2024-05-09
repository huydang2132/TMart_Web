<template>
    <TheHeader />
    <main class="grid">
        <section>
            <router-view></router-view>
        </section>
    </main>
    <TheFooter />
</template>

<script>
import TheHeader from './TheHeader.vue';
import TheFooter from './TheFooter.vue';

export default {
    name: 'TheContainer',
    components: {
        TheHeader, TheFooter
    }
}
</script>

<style scoped>
main {
    width: 100%;
    display: flex;
    background-color: var(--bg-main);
    padding: 20px 40px;
    height: 100%;
}

section {
    width: 100%;
}
</style>