<template>
    <div>
        <h4>nav</h4>
        <button @click="userStore.logout" v-if="userStore.loginUser.value">로그아웃</button>
        <hr>
    </div>
</template>

<script setup>
import { useUserStore } from '@/stores/user';
import { useCounterStore } from '@/stores/counter';

const userStore = useUserStore()
const counterStore = useCounterStore();

</script>

<style scoped>

</style>