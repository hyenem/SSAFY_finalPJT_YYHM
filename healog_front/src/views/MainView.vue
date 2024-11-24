<template>
  <div>
    <NavigationView/>
    <h1>Main View</h1>

    <UserMain v-if="loginUserType==='user'"/>
    <TrainerMain v-if="loginUserType==='trainer'"/>
  </div>
</template>

<script setup>
import { ref } from 'vue';
import NavigationView from './common/NavigationView.vue';
import UserMain from '@/components/diary/UserMain.vue';
import TrainerMain from '@/components/diary/TrainerMain.vue';
import { useUserStore } from '@/stores/userStore';
import { onMounted } from 'vue';
import axios from 'axios';

const REST_API_SUBSCRIBE_URL = "http://localhost:8080/subscribe"

localStorage.setItem('lastVisitedUrl', location.href)

const userStore = useUserStore()

const loginUserType = userStore.loginUser.type

const today = new Date()
const selectedDate = ref({
  year : today.getFullYear(),
  month : today.getMonth(),
  day : today.getDate()
});

const onDateSelected = (date) => {
  selectedDate.value = date;
};

onMounted(()=>{
  if(userStore.loginUser.type==='trainer'){
      axios.get(REST_API_SUBSCRIBE_URL+"/follow?id="+userStore.loginUser.id)
      .then((res)=>{
        userStore.follower=res.data[0].id
      })
    }
})
</script>

<style scoped>
.main {
  display : flex;
  justify-content: space-around;
  padding: 2rem;
  background-color: #E0F1E2
}
</style>
