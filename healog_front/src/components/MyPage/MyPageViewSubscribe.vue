<template>
    <div>
        <h3>subscribe</h3>
        <div v-if="loginUserInfo.trainer">
            <h4>트레이너 정보</h4>
            <div>name : {{ loginUserInfo.trainer.name }}</div>
            <div>email : {{ loginUserInfo.trainer.email }}</div>
            <div>gender : {{ loginUserInfo.trainer.gender ===0? 'male':'female' }}</div>
            <div>phonenumber : {{ loginUserInfo.trainer.phonenumber }}</div>
            <div @click="showGymInfo=!showGymInfo">gym : {{ gymList.find((item)=>{return item.id===loginUserInfo.trainer.location}).name }}</div>
            <div v-if="showGymInfo">
                <hr>
                <h5>헬스장 정보</h5>
                <div> add : {{ gymList.find((item)=>{return item.id===loginUserInfo.trainer.location}).address }}</div>
                <div> tel : {{ gymList.find((item)=>{return item.id===loginUserInfo.trainer.location}).phonenumber }}</div>
                <hr>
            </div>
            <button @click="cancelSubscription">구독 취소</button>
        </div>
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
                <button @click="openSubscribeModal(trainer.id)">구독</button>
                <div v-if="subscribeModalId===trainer.id">
                    <hr>
                        <button @click="closeSubscribeModal">x</button>
                        <div>몇 개월 구독할까요?</div>
                        <input type="number" v-model="subscribeMonth">
                        <div>지불 가격: {{ subscribeMonth * 100000 }}만원</div>
                        <button @click="subscribe(trainer.id)">결제</button>
                    <hr>
                </div>
            </div>
        </div>
    </div>
</template>

<script setup>
import router from '@/router';
import { useSubscribeStore } from '@/stores/subscribeStore';
import { useUserStore } from '@/stores/userStore';
import axios from 'axios';
import { onBeforeMount, onMounted, ref, unref } from 'vue';

const userStore = useUserStore()

const REST_API_TRAINER_URL = "http://localhost:8080/trainer"
const REST_API_SUBSCRIBE_URL = "http://localhost:8080/subscribe"
const searchName = ref("")
const trainerList = ref([])
const gymList = ref([])
const subscribeModalId = ref("")
const subscribeMonth= ref(1)
const showGymInfo = ref(false)

const openSubscribeModal = function(id){
    subscribeMonth.value = 1
    subscribeModalId.value=id
}

const closeSubscribeModal = function(){
    subscribeModalId.value=""
}

const subscribe = function(id){

    if(userStore.loginUser.type!=='user'||loginUserInfo.trainer){
        alert("잘못된 접근입니다.")
    } else {
        axios.post(REST_API_SUBSCRIBE_URL, {
            trainerId : id,
            userId : userStore.loginUser.id,
            subscribeMonth : subscribeMonth.value
        }).then((res)=>{
            alert("구독되었습니다.")
            router.replace({name: 'mypage'})
        }).catch((error)=>{
            alert("구독에 실패했습니다.")
        })
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
    trainer : null,
    gym : null
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

const cancelSubscription = function(){
    
}

onMounted(()=>{
    const userStore = useUserStore()
    const subscribeStore = useSubscribeStore()

    userStore.getUser((userData)=>{
        if(userData.trainerExist===1){
            subscribeStore.getTrainerInfoByUserId(userStore.loginUser.id, (trainerData)=>{
                loginUserInfo.value.trainer = trainerData
            })
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