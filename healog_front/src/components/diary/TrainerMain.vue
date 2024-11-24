<template>
    <div>
        <h4>Trainer</h4>
        <Calendar @dateSelected="onDateSelected" />  
        <!-- 선택된 날짜가 있을 경우 다이어리 컴포넌트 표시 -->
        <DiaryView v-if="selectedDate" :date="selectedDate" /> 
    </div>
</template>
  
<script setup>
import { ref } from 'vue';
import Calendar from './Calendar.vue';
import DiaryView from '@/views/DiaryView.vue';
import { useUserStore } from '@/stores/userStore';
import { onMounted } from 'vue';
import axios from 'axios';
import { watch } from 'vue';
import { computed } from 'vue';
import { useRouter } from 'vue-router';

const REST_API_SUBSCRIBE_URL = "http://localhost:8080/subscribe"

localStorage.setItem('lastVisitedUrl', location.href)

const router = useRouter()
const userStore = useUserStore()

const today = new Date()
const selectedDate = ref({
    id : userStore.follower.id,
    year : today.getFullYear(),
    month : today.getMonth()+1,
    day : today.getDate()
});

const onDateSelected = (date) => {
    selectedDate.value = date
};


onMounted(()=>{
    if(userStore.loginUser.type==='trainer'){
        if(!userStore.follower.id){
            axios.get(REST_API_SUBSCRIBE_URL+"/follow?id="+userStore.loginUser.id)
            .then((res)=>{
              userStore.follower.id=res.data[0].id
              userStore.follower.name = res.data[0].name
            })
        }
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
  
