<template>
    <div>
        <h2>account</h2>
        <RouterView />
    </div>
</template>

<script setup>
import { useRouter } from 'vue-router'
import {onMounted} from 'vue'
import { useUserStore } from '@/stores/userStore';

const router = useRouter()

onMounted((to, from, next) => {
    const userStore = useUserStore()
    if(userStore.login.id){
        alert("잘못된 접근입니다.")
        next(from)
    }
    router.push({name: 'choose'})
})

</script>

<style scoped>

</style>