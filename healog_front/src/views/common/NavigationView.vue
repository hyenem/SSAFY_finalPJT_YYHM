<template>
    <div>
        <h4>nav</h4>
        <button @click="gotoHome">home</button>
        <div>{{ userStore.loginUser.id }}</div>
        <div>{{ userStore.loginUser.type }}</div>
        <div @click="gotoMyPage">이름 : {{ loginUserInfo.name }}</div>
        <button @click="userStore.logout" v-if="userStore.loginUser.id">로그아웃</button>
        <hr>
    </div>
</template>

<script setup>
import { useUserStore } from '@/stores/userStore';
import { onMounted, ref } from 'vue';
import { useRouter } from 'vue-router';

const userStore = useUserStore()
const router = useRouter()

const gotoHome = function(){
    router.push({name : 'main'})
}

const loginUserInfo = ref({
    name : null
})

const gotoMyPage = function(){
    router.push({name: 'mypage'})
}

onMounted(()=>{
    userStore.getUser((userData)=>{
        if(userData){
            loginUserInfo.value = userData
        }
    })
})
</script>

<style scoped>

</style>