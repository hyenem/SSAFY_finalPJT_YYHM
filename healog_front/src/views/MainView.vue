<template>
  <div>
    <NavigationView/>
    <UserMain v-if="loginUserType==='user'"/>
    <TrainerMain v-if="loginUserType==='trainer'"/>
    <FooterView />
  </div>
</template>

<script setup>
import { ref } from 'vue';
import NavigationView from './common/NavigationView.vue';
import FooterView from './common/FooterView.vue';
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
    console.log(userStore.follower.id)
      axios.get(REST_API_SUBSCRIBE_URL+"/follow?id="+userStore.loginUser.id)
      .then((res)=>{
        if(res.data.length!==0){
          userStore.follower.id=res.data[0].id
          userStore.follower.name=res.data[0].name
        }
      })
    }
})
</script>

<style scoped>


.calendar-container {
  flex: 1; /* Calendar 컴포넌트 영역 조정 */
  max-width: 25%;
}

.diary-container {
  flex: 3; /* Diary 컴포넌트가 더 넓은 공간 차지 */
  background-color: #fff;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
}
</style>

