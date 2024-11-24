<template>
    <div>
        <h4>nav</h4>
        <button @click="gotoHome">home</button>
        <div v-if="userStore.loginUser.type==='trainer'">
            <hr>
            <h5>팔로워 선택</h5>
            <div v-if="followerList.length===0">팔로워가 없습니다.</div>
            <div v-else>
                <div v-for="follower in followerList" @click = "setFollower(follower.id)">
                    {{ follower.name }}
                </div>
            </div>
            지금 보고있는 사람의 id : {{ userStore.follower }}
        </div>
        <div>{{ userStore.loginUser.type }}</div>
        <div>{{ userStore.loginUser.name }}</div>
        <div @click="gotoMyPage">마이페이지</div>
        <button @click="userStore.logout" v-if="userStore.loginUser.id">로그아웃</button>
        <hr>
    </div>
</template>

<script setup>
import { useUserStore } from '@/stores/userStore';
import axios from 'axios';
import { onBeforeMount, onMounted, ref } from 'vue';
import { useRouter } from 'vue-router';

const userStore = useUserStore()
const router = useRouter()

const followerList = ref([])
const REST_API_SUBSCRIBE_URL = "http://localhost:8080/subscribe"

const setFollower = function(id){
    userStore.follower = id
}

const gotoHome = function(){
    router.push({name : 'main'})
}

const loginUserInfo = ref({
    name : null
})

const gotoMyPage = function(){
    router.push({name: 'mypage'})
}

onBeforeMount(()=>{
    userStore.getUser((userData)=>{
        if(userData){
            loginUserInfo.value = userData
        }
    })

    if(userStore.loginUser.type==='trainer'){
        axios.get(REST_API_SUBSCRIBE_URL+"/follow?id="+userStore.loginUser.id)
        .then((res)=>{
            followerList.value=res.data
            if(!userStore.follower){
              userStore.follower=res.data[0].id
            }
        })
    }
})
</script>

<style scoped>

</style>