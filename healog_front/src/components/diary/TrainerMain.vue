<template>
    <div class="trainerMain">
        <div class="leftPanel">
            <div class="calendarWrapper">
                <Calendar v-if="userStore.follower.id" @dateSelected="onDateSelected" />  
            </div>
            <div class="feedbackWrapper">
                <Feedback/>
            </div>
        </div>
        <div class="rightPanel">
            <!-- 선택된 날짜가 있을 경우 다이어리 컴포넌트 표시 -->
            <DiaryView v-if="userStore.follower.id && selectedDate" :date="selectedDate" />
            <div v-else>팔로워가 없습니다.</div>
        </div>
    </div>
</template>
  
<script setup>
import { ref } from 'vue';
import Calendar from './Calendar.vue';
import Feedback from './Feedback.vue';
import DiaryView from '@/views/DiaryView.vue';
import { useUserStore } from '@/stores/userStore';
import { onMounted } from 'vue';
import axios from 'axios';
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
        axios.get(REST_API_SUBSCRIBE_URL+"/follow?id="+userStore.loginUser.id)
        .then((res)=>{
            if(!userStore.follower.id && res.data.length!==0){
              userStore.follower.id=res.data[0].id
              userStore.follower.name = res.data[0].name
            console.log(userStore.follower)
            }
        })
    }
})
</script>
  
<style scoped>
.trainerMain {
  display: flex;
  justify-content: space-between;
  align-items: flex-start;
  padding: 2rem;
  background-color: #e0f1e2;
  gap: 1rem;
}

.leftPanel {
  display: flex;
  flex-direction: column;
  justify-content: space-between;
  gap: 2rem;
  width: 30%; /* 좌측 패널 폭 설정 */
  height: 100%; /* 좌측 패널 높이 설정 */
}

.calendarWrapper,
.feedbackWrapper {
  height: 50%; /* 좌측 패널의 절반 높이를 각각 차지 */
  display: flex;
  align-items: center;
  justify-content: center;
  background-color: #fff; /* 시각적으로 분리 */
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1); /* 약간의 그림자 효과 */
  border-radius: 3px; /* 둥근 모서리 */
}

.rightPanel {
  width: 70%; /* 우측 패널 폭 설정 */
  height: 100%; /* 우측 패널 높이 설정 */
  overflow-y: auto; /* 내용이 길 경우 스크롤 활성화 */
  background-color: white; /* 배경 */
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1); /* 약간의 그림자 효과 */
  border-radius: 3px; /* 둥근 모서리 */
}

/* 반응형 스타일 */
@media (max-width: 768px) {
  .trainerMain {
    flex-direction: column; /* 세로로 정렬 */
  }

  .leftPanel {
    width: 100%; /* 좌측 패널이 전체 폭 차지 */
    height: auto; /* 높이는 내용에 맞게 조정 */
  }

  .rightPanel {
    width: 100%; /* 우측 패널도 전체 폭 차지 */
    height: auto; /* 높이는 내용에 맞게 조정 */
  }
}
</style>
  
