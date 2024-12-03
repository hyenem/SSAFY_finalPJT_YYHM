<template>
    <div v-if="subscribeModalId" class="overlay" @click="closeSubscribeModal"></div>
    <div class="Subscription">
        <h3 class="title">Subscription</h3>
        <div v-if="loginUserInfo.trainer">
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
            <div class="search">
                <input type="text" placeholder="트레이너 찾기" id="searchTrainer" v-model="searchName">
                <button @click="searchTrainer(searchName)">
                    <font-awesome-icon :icon="['fas', 'magnifying-glass']" />
                </button>
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
                <div class="subscribeModal" v-if="subscribeModalId === trainer.id">
                    <div class="modal-header">
                        <h5>Subscription</h5>
                        <button @click="closeSubscribeModal" class="close-btn">×</button>
                    </div>
                    <div class="modal-body">
                        <label for="subscribe-months">구독 기간 : {{ subscribeMonth }} 개월</label>
                        <input
                        type="number"
                        id="subscribe-months"
                        v-model="subscribeMonth"
                        placeholder="Enter months"
                        />
                        <p class="price-display">
                            <font-awesome-icon :icon="['fas', 'won-sign']" />
                            <span>{{ subscribeMonth * 100000 }}</span>
                        </p>
                    </div>
                    <div class="modal-footer">
                        <button @click="subscribe(trainer.id)" class="confirm-btn">Subscribe</button>
                        <button @click="closeSubscribeModal" class="cancel-btn">Cancel</button>
                    </div>
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
  padding: 1rem 2rem;
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
}

button {
  padding: 0.5rem 1rem;
  font-size: 1rem;
  background-color: #7fc678;
  color: white;
  border: none;
  border-radius: 3px;
  cursor: pointer;
  transition: background-color 0.3s;
}

.search {
    display: flex;
}

.trainer-list {
    display: flex;
    justify-content: space-between;
    margin-top: 1.5rem;
    padding: 1rem;
    border: 1px solid #ddd;
    border-radius: 3px;
    cursor: pointer;
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
  position: fixed;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  z-index: 1000;
  background-color: white;
  border-radius: 3px;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
  padding: 1.5rem;
  width: 420px;
  max-width: 90%;
}

/* 모달 헤더 */
.modal-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  border-bottom: 1px solid #ddd;
  padding-bottom: 0.5rem;
  margin-bottom: 1rem;
}

.modal-header h5 {
  font-size: 1.2rem;
  margin: 0;
}

.close-btn {
  background-color: transparent;
  border: none;
  font-size: 1.5rem;
  cursor: pointer;
  color: #555;
}

.close-btn:hover {
  color: #e74c3c;
}

/* 모달 본문 */
.modal-body {
  display: flex;
  flex-direction: column;
  gap: 1rem;
}

.modal-body label {
  font-size: 0.9rem;
  color: #555;
}

.modal-body input {
  padding: 0.5rem;
  border: 1px solid #ddd;
  border-radius: 3px;
  font-size: 0.9rem;
}

.modal-body input:focus {
  border-color: #7fc678;
  box-shadow: 0 0 5px rgba(127, 198, 120, 0.5);
  outline: none;
}

.modal-body input:invalid {
  border-color: #e74c3c;
  box-shadow: 0 0 5px rgba(231, 76, 60, 0.5);
}

.price-display {
  text-align: right;
  font-weight: bold;
  gap: 1rem;
  font-size: 1rem;
  margin-bottom: 1.5rem;
  border-top: 1px solid;
  padding: 1rem 0 0 0;
  color: #555;
}

/* 모달 푸터 */
.modal-footer {
  display: flex;
  justify-content: flex-end;
  gap: 1rem;
}

.confirm-btn,
.cancel-btn {
  padding: 0.8rem 1.5rem;
  font-size: 1rem;
  font-weight: bold;
  border: none;
  border-radius: 5px;
  cursor: pointer;
  transition: background-color 0.3s ease, transform 0.2s ease;
}

.confirm-btn {
  padding: 0.5rem 1rem;
  background-color: #7fc678;
  border: none;
  color: white;
  border-radius: 3px;
  cursor: pointer;
  font-size: 0.9rem;
}

.confirm-btn:hover {
  background-color: #65a45b;
}

.cancel-btn {
  padding: 0.5rem 1rem;
  background-color: transparent;
  border: 1px solid #ff4d4f;
  color: #ff4d4f;
  border-radius: 3px;
  cursor: pointer;
  font-size: 0.9rem;
}

.cancel-btn:hover {
  background-color: #ff4d4f;
  color: white;
}

/* 반응형 스타일 */
@media screen and (max-width: 480px) {
  .subscribeModal {
    width: 90%;
    padding: 1.5rem;
  }

  .modal-body input {
    font-size: 0.9rem;
  }

  .modal-footer {
    flex-direction: column;
    align-items: stretch;
  }

  .confirm-btn,
  .cancel-btn {
    width: 100%;
    text-align: center;
  }
}

/* 애니메이션 효과 */
@keyframes fadeIn {
  from {
    opacity: 0;
    transform: translate(-50%, -40%);
  }
  to {
    opacity: 1;
    transform: translate(-50%, -50%);
  }
}


</style>
