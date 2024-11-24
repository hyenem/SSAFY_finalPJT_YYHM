<template>
    <div>
        <h3>User</h3>
        <button @click="gotoUserLogin">로그인</button>
        <button @click="gotoUserSignup">회원가입</button>
    </div>
</template>

<script setup>
import { useRouter } from 'vue-router'


const router = useRouter()
const gotoUserLogin = function(){
    router.push({name: 'userLogin'})
}
const gotoUserSignup = function(){
    router.push({name: 'userSignup'})
}
</script>

<style scoped>

</style>