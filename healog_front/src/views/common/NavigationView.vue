<template>
    <div>
        <h4>nav</h4>
        <button @click="gotoHome">home</button>
        <div>{{ userStore.loginUser.id }}</div>
        <div>{{ userStore.loginUser.type }}</div>
        <button @click="userStore.logout" v-if="userStore.loginUser.id">로그아웃</button>
        <hr>
    </div>
</template>

<script setup>
import { useUserStore } from '@/stores/userStore';
import { useRouter } from 'vue-router';

const userStore = useUserStore()
const router = useRouter()

const gotoHome = function(){
    location.href="http://localhost:5173/"
}
</script>

<style scoped>

</style>