<template>
    <div>
        <h3>subscribe</h3>
        <div v-if="loginUserInfo.trainer">{{ loginUserInfo.trainer }}</div>
        <div v-else>
            <h3>구독하기</h3>
            <div>
                <label for="searchTrainer">트레이너 검색: </label>
                <input type="text" placeholder="검색어를 입력하세요." id="searchTrainer" v-model="searchName">
                <button @click="searchTrainer(searchName)">검색</button>
            </div>
            <div v-for="trainer in trainerList">
                <hr>
                <div>이름 : {{ trainer.name }}</div>
                <div>회원수 : {{ trainer['user_count'] }}</div>
                <div>헬스장 : {{ findGymName(trainer.location) }}</div>
                <div v-if="trainer.bio">설명 : {{ trainer.bio }}</div>
                <div v-if="trainer.img">사진 : {{ trainer.img }}</div>
                <button @click="subscribe(trainer.id)">구독</button>
            </div>
        </div>
    </div>
</template>

<script setup>
import { useUserStore } from '@/stores/userStore';
import axios from 'axios';
import { onMounted, ref } from 'vue';

const REST_API_TRAINER_URL = "http://localhost:8080/trainer"

const searchName = ref("")
const trainerList = ref([])
const gymList = ref([])

const subscribe = function(){
    if(loginUserInfo.trainer){
        alert("잘못된 접근입니다.")
    } else {
    }
}

const searchTrainer = function(name){
    axios.get(REST_API_TRAINER_URL+'/search?name='+name)
        .then((res)=>{
            trainerList.value = res.data
        }).catch((error)=>{
            alert("해당 트레이너를 찾을 수 없습니다.")
        })
}

const loginUserInfo = ref({
    trainer : null
})

const findGymName = function(number){
    let gymName = ""
    gymList.value.forEach(gym => {
        if(gym.id===number){
            gymName=gym.name
        }
    })
    return gymName
}

onMounted(()=>{
    const userStore = useUserStore()
    userStore.getUser((userData)=>{
        if(userData['trainer_exist']===1){
            loginUserInfo.value.trainer = '혜민'
        }
    })

    axios.get(REST_API_TRAINER_URL+'/all')
        .then((res)=>{
            trainerList.value = res.data
        })

    axios.get(REST_API_TRAINER_URL+"/gym")
        .then((res)=>{
            gymList.value = res.data
        })
})

</script>

<style scoped>

</style>