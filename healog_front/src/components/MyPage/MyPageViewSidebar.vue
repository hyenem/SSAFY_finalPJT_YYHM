<template>
    <div>
        <h3>sidebar</h3>
        <RouterLink :to="{name : 'info'}" >info</RouterLink><br>
        <RouterLink :to="{name : 'subscribe'}" v-if="type==='user'">subscribe</RouterLink>
        <hr>
    </div>
</template>

<script setup>
import { useUserStore } from '@/stores/userStore';

const userStore =useUserStore()

const type = userStore.loginUser.type
</script>

<style scoped>

</style>