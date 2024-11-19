<template>
    <div>
        <h3>accountviewchoose</h3>
        <button @click="gotoUser">회원</button>
        <button @click="gotoTrainter">트레이너</button>
        <button @click="counterStore.goHome">goHome</button>
    </div>
</template>

<script setup>
import { useRoute, useRouter } from 'vue-router'
import { useCounterStore } from '@/stores/counter';
const counterStore = useCounterStore()

const router = useRouter()
const gotoUser = function(){
    router.push({name : 'user'})
}
const gotoTrainter = function(){
    router.push({name : 'trainer'})
}
</script>

<style scoped>

</style>