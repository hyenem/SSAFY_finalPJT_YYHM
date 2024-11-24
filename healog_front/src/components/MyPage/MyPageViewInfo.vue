<template>
    <div>
        <h4>info</h4>
        <div>email : {{ loginUserInfo.email }}</div><br>
        <div>gender : {{ loginUserInfo.gender }}</div><br>
        <div v-if="loginUserInfo.birthday">email :  {{ loginUserInfo.birthday }}</div><br>
        <div v-if="loginUserInfo.type==='trainer'">userCount : {{ loginUserInfo.usercount }}</div><br>
        <div v-if="loginUserInfo.type==='user' && !loginUserInfo.trainerexist">
            <div>trainer</div>
            <button @click="gotoSubscribe">구독하러 가기</button>
        </div><br>
        <div v-if="loginUserInfo.type==='user' && loginUserInfo.trainerexist">
            <div @click ="openTrainerInfo">trainer : {{ loginUserInfo.trainer.name}}</div>
            <button @click="gotoSubscribe">정보보기</button>
        </div>
    </div>
</template>

<script setup>
import { useSubscribeStore } from '@/stores/subscribeStore';
import { useUserStore } from '@/stores/userStore';
import { onMounted, ref } from 'vue';
import { useRouter } from 'vue-router';

const userStore = useUserStore()
const router = useRouter()

const loginUserInfo = ref({
    type : userStore.loginUser.type,
    email : null,
    gender : null,
    trainerexist : false,
    birthday : null,
    trainer : null,
    usercount : null
})



const gotoSubscribe = function(){
    router.push({name : 'subscribe'})
}

onMounted(()=>{
    const userStore = useUserStore()
    const subscribeStore = useSubscribeStore()
    userStore.getUser((userData)=>{
        if(userData){
            loginUserInfo.value.email=userData.email
            if(userData.gender===0){
                loginUserInfo.value.gender = 'male'
            } else {
                loginUserInfo.value.gender = 'female'
            }
            if(userStore.loginUser.type==='user' && userData.trainerExist===1){
                loginUserInfo.value.trainerexist=true
            }
            if(userData.birthday){
                loginUserInfo.value.birthday = userData.birthday
            }
            if(loginUserInfo.value.type==='trainer'){
                loginUserInfo.value.usercount = userData['user_count']
            }
        }
    })
    
    if(userStore.loginUser.type=='user'){
        subscribeStore.getTrainerInfoByUserId(userStore.loginUser.id,(trainerData)=>{
            loginUserInfo.value.trainer = trainerData
        })
    }
})

</script>

<style scoped>

</style>