<template>
  <div>
    <h1>Healog</h1>
    <AccountView v-if="!userStore.loginUser.value" />
    <DiaryView v-if="userStore.loginUser.value" />
  </div>
</template>

<script setup>
import { onMounted } from "vue";
import AccountView from "./components/account/AccountView.vue";
import DiaryView from "./components/diary/DiaryView.vue";

import { useUserStore } from "./stores/user";

const userStore = useUserStore()

</script>

<style scoped>
</style>