<template>
    <div>
        <h3>Trainer</h3>
        <button @click="gotoTrainerLogin">로그인</button>
        <button @click="gotoTrainerSignup">회원가입</button>
    </div>
</template>

<script setup>
import { useRouter } from 'vue-router'


const router = useRouter()
const gotoTrainerLogin = function(){
    router.push({name: 'trainerLogin'})
}
const gotoTrainerSignup = function(){
    router.push({name: 'trainerSignup'})
}
</script>

<style scoped>

</style>