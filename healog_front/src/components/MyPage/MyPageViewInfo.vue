<template>
  <div class="settings">
    <h4 class="title">Settings</h4>
    <div class="account-card">
      <div class="account-info">
        <label>Email:</label>
        <div>{{ loginUserInfo.email }}</div>
      </div>
      <div class="account-info">
        <label>Gender:</label>
        <div>{{ loginUserInfo.gender }}</div>
      </div>
      <div class="account-info">
        <label>Birthday:</label>
        <div v-if="loginUserInfo.birthday">{{ loginUserInfo.birthday }}</div>
      </div>
      <div class="account-info" v-if="loginUserInfo.type === 'trainer'">
        <label>User Count:</label>
        <div>{{ loginUserInfo.usercount }}</div>
      </div>
      <div class="account-info" v-if="loginUserInfo.type === 'user' && !loginUserInfo.trainerexist">
        <label>Trainer:</label>
        <button class="btn-primary" @click="gotoSubscribe">구독하러 가기</button>
      </div>
      <div class="account-info" v-if="loginUserInfo.type === 'user' && loginUserInfo.trainerexist">
        <label>Trainer:</label>
        <div class="trainer-info" @click="openTrainerInfo">
          {{ loginUserInfo.trainer.name }}
        </div>
        <button class="btn-primary" @click="gotoSubscribe">정보보기</button>
      </div>
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
.settings {
  display: flex;
  flex-direction: column;
  gap: 1rem;
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

.account-card {
  display: flex;
  flex-direction: column;
  gap: 1.5rem;
  padding: 1.5rem;
  border: 1px solid #ddd;
  border-radius: 3px;
}

.account-info {
  display: flex;
  justify-content: space-between;
  align-items: center;
  gap: 1rem;
  padding: 0.8rem;
  border-bottom: 1px solid #f0f0f0;
}

.account-info:last-child {
  border-bottom: none;
}

label {
  font-size: 1rem;
  font-weight: bold;
  color: #555;
  min-width: 100px;
}

.trainer-info {
  font-size: 1rem;
  font-weight: bold;
  color: #7fc678;
  cursor: pointer;
  transition: color 0.3s ease;
}

.trainer-info:hover {
  color: #65a45b;
}

div {
  font-size: 1rem;
  color: #444;
}

.btn-primary {
  padding: 0.5rem 1rem;
  background-color: #7fc678;
  color: white;
  border: none;
  border-radius: 3px;
  font-size: 0.9rem;
  cursor: pointer;
  transition: background-color 0.3s ease;
}

.btn-primary:hover {
  background-color: #65a45b;
}

</style>
