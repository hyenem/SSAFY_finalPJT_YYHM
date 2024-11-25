<template>
    <div class="Subscription">
        <h3 class="title">Subscription</h3>
        <div v-if="loginUserInfo.trainer">
            <h4>Trainer Info</h4>
            <div class="trainer-card">
                <div class="trainer-info">
                    <label>Name</label>
                    <div>{{ loginUserInfo.trainer.name }}</div>
                </div>
                <div class="trainer-info">
                    <label>Email</label>
                    <div>{{ loginUserInfo.trainer.email }}</div>
                </div>
                <div class="trainer-info">
                    <label>Gender</label>
                    <div>{{ loginUserInfo.trainer.gender ===0? 'male':'female' }}</div>
                </div>
                <div class="trainer-info">
                    <label>Phone number</label>
                    <div>{{ loginUserInfo.trainer.phonenumber }}</div>
                </div>
                <div class="trainer-info">
                    <label>Gym</label>
                    <div @click="showGymInfo=!showGymInfo">{{ gymList.find((item)=>{return item.id===loginUserInfo.trainer.location}).name }}</div>
                    <div v-if="showGymInfo">
                        <h5>Gym Bio</h5>
                        <div> add : {{ gymList.find((item)=>{return item.id===loginUserInfo.trainer.location}).address }}</div>
                        <div> tel : {{ gymList.find((item)=>{return item.id===loginUserInfo.trainer.location}).phonenumber }}</div>
                    </div>
                </div>
                <button @click="cancelSubscription">구독 취소</button>
            </div>
        </div>
        <div v-else>
            <div>
                <input type="text" placeholder="트레이너 찾기" id="searchTrainer" v-model="searchName">
                <button @click="searchTrainer(searchName)">검색</button>
            </div>
            <div v-for="trainer in trainerList" @click="openSubscribeModal(trainer.id)" class="trainer-list">
                <div class="trainer-list-info">
                    <div class="trainer-name">
                        <div>{{ trainer.name }}</div>
                    </div>
                    <div class="trainer-info">
                        <label>회원 수</label>
                        <div>{{ trainer['user_count'] }}</div>
                    </div>
                    <div class="trainer-info">
                        <label>위치</label>
                        <div>{{ findGymName(trainer.location) }}</div>
                    </div>
                    <div v-if="trainer.bio">
                        <label>소개</label>
                        <div v-if="trainer.bio">{{ trainer.bio }}</div>
                    </div>
                    <div v-if="trainer.img">
                        <div v-if="trainer.img">사진 : {{ trainer.img }}</div>
                    </div>
                </div>
                <div class="subscribeModal" v-if="subscribeModalId===trainer.id">
                        <button @click="closeSubscribeModal">x</button>
                        <div>몇 개월 구독할까요?</div>
                        <input type="number" v-model="subscribeMonth">
                        <div>지불 가격: {{ subscribeMonth * 100000 }}만원</div>
                        <button @click="subscribe(trainer.id)">결제</button>
                </div>
            </div>
        </div>
    </div>
</template>

<script setup>
import { useSubscribeStore } from '@/stores/subscribeStore';
import { useUserStore } from '@/stores/userStore';
import axios from 'axios';
import { onMounted, ref } from 'vue';
import { useRouter } from 'vue-router';

const userStore = useUserStore()
const router = useRouter()

const REST_API_TRAINER_URL = "http://localhost:8080/trainer"
const REST_API_SUBSCRIBE_URL = "http://localhost:8080/subscribe"
const searchName = ref("")
const trainerList = ref([])
const gymList = ref([])
const subscribeModalId = ref("")
const subscribeMonth= ref(1)
const showGymInfo = ref(false)

const openSubscribeModal = function(id){
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
            router.push({name: 'info'})
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
    let confirmResult = false
    confirmResult = confirm("진짜로 구독 취소하실건가요?")
    if(confirmResult){
        axios.patch(REST_API_SUBSCRIBE_URL+"/cancel", {
            userId : userStore.loginUser.id,
            trainerId : loginUserInfo.value.trainer.id
        }).then(()=>{
            alert("구독 취소되었습니다.")
            router.push({name : 'info'})
        })
    }
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
/* 오버레이 */
.overlay {
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background-color: rgba(0, 0, 0, 0.5);
  z-index: 999;
}

.Subscription {
  display: flex;
  flex-direction: column;
  padding: 2rem;
}

.title {
  font-size: 1.5rem;
  font-weight: bold;
  color: #333;
  border-bottom: 1px solid #ddd;
  padding-bottom: 0.5rem;
  margin-bottom: 1rem;
}

/* 카드 스타일 */
.trainer-card {
  display: flex;
  flex-direction: column;
  gap: 1.5rem;
  padding: 1.5rem;
  border: 1px solid #ddd;
  border-radius: 3px;
}

.trainer-info {
  display: flex;
  justify-content: space-between;
  align-items: center;
  gap: 1rem;
  padding: 0.8rem;
  border-bottom: 1px solid #f0f0f0;
}

.trainer-info:last-child {
  border-bottom: none;
}

/* 검색 섹션 스타일 */
label {
  font-size: 1rem;
  font-weight: bold;
  margin-right: 0.5rem;
}

input {
  padding: 0.5rem;
  font-size: 1rem;
  border: 1px solid #ddd;
  border-radius: 3px;
  margin-right: 0.5rem;
}

button {
  padding: 0.5rem 1rem;
  font-size: 1rem;
  background-color: #7fc678;
  color: white;
  border: none;
  border-radius: 4px;
  cursor: pointer;
  transition: background-color 0.3s;
}

button:hover {
  background-color: #65a45b;
}

.trainer-list {
    display: flex;
    justify-content: space-between;
    margin-top: 1.5rem;
    padding: 1rem;
    border: 1px solid #ddd;
}

.trainer-name {
  font-size: 1.5rem;
  padding: 1rem;
  font-weight: bold;
}

/* 헬스장 정보 섹션 */
.trainer-card .gym-info {
  font-size: 0.9rem;
  background-color: #f9f9f9;
  padding: 1rem;
  border-radius: 4px;
  border: 1px solid #ddd;
  margin-top: 0.5rem;
}

.gym-info h5 {
  font-size: 1rem;
  font-weight: bold;
  color: #333;
}

/* 모달 스타일 */
.subscribeModal {
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  background-color: white;
  padding: 1.5rem;
  border: 1px solid #ddd;
  border-radius: 8px;
  box-shadow: 0 4px 12px rgba(0, 0, 0, 0.15);
  width: 300px;
  z-index: 1000;
}

.subscribeModal h5 {
  font-size: 1rem;
  font-weight: bold;
  color: #555;
  margin-bottom: 1rem;
}

.subscribeModal input {
  width: 100%;
  margin: 0.5rem 0;
}

.subscribeModal button {
  margin-top: 1rem;
}
</style>
