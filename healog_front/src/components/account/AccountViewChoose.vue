<template>
    <div>
        <h3>accountviewchoose</h3>
        <button @click="gotoUser">회원</button>
        <button @click="gotoTrainter">트레이너</button>
    </div>
</template>

<script setup>
import { useRouter } from 'vue-router'

const router = useRouter()
const gotoUser = function(){
    router.push({name : 'accountUser'})
}
const gotoTrainter = function(){
    router.push({name : 'accountTrainer'})
}
</script>

<style scoped>

</style>