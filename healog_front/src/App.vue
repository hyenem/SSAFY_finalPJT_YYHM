<template>
  <div>
    <h1>Healog</h1>
    <AccountView v-if="!userStore.loginUser.value" />
    <button @click="userStore.logout" v-if="userStore.loginUser.value">로그아웃</button>
  </div>
</template>

<script setup>
import AccountView from "@/components/account/AccountView.vue"
import { useUserStore } from "./stores/user";
import { useCounterStore } from "./stores/counter";

const userStore = useUserStore()
const counterStore = useCounterStore()


</script>

<style scoped>
</style>