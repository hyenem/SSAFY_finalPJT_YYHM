<template>
    <div>
        <h4>info</h4>
        <div>email : {{ loginUserInfo.email }}</div><br>
        <div>gender : {{ loginUserInfo.gender }}</div><br>
        <div v-if="loginUserInfo.birthday">email : {{ loginUserInfo.birthday }}</div><br>
        <div v-if="loginUserInfo.type==='user' && !loginUserInfo.trainerexist">
            <div>trainer</div>
            <button @click="gotoSubscribe">구독하러 가기</button>
        </div><br>
    </div>
</template>

<script setup>
import { useUserStore } from '@/stores/userStore';
import { onMounted, ref } from 'vue';
import { useRouter } from 'vue-router';

const userStore = useUserStore()
const router = useRouter()

const loginUserInfo = ref({
    type : userStore.loginUser.type,
    email : null,
    gender : null,
    trainerexist : null,
    birthday : null
})

const gotoSubscribe = function(){
    router.push({name : 'subscribe'})
}

onMounted(()=>{
    const userStore = useUserStore()
    userStore.getUser((userData)=>{
        if(userData){
            loginUserInfo.value.email=userData.email
            if(userData.gender===0){
                loginUserInfo.value.gender = 'male'
            } else {
                loginUserInfo.value.gender = 'female'
            }
            if(userData['tainer-exist']===1){
                loginUserInfo.value.trainerexist=true
            }
            if(userData.birthday){
                loginUserInfo.value.birthday = userData.birthday
            }
        }
        console.log(loginUserInfo)
    })
})

</script>

<style scoped>

</style>