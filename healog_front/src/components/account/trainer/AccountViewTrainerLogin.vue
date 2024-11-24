<template>
    <div>
        <h3>trainer login</h3>
        <label for="email">email: </label>
        <input type="text" v-model="email" id="email" name="email">
        <br>
        <label for="password">password: </label>
        <input type="password" v-model="password" id="password" name="password">
        <br>
        <button @click="userStore.login(email, password, 'trainer')">로그인</button>
    </div>
</template>

<script setup>
import { ref } from 'vue'
import { useUserStore } from '@/stores/userStore';

const userStore = useUserStore()

const email = ref("")
const password = ref("")


</script>

<style scoped>

</style>