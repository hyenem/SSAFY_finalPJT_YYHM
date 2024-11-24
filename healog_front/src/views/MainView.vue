<template>
  <div>
    <NavigationView/>
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
</script>

<style scoped>
.main {
  display: flex;
  justify-content: space-between; /* 좌우로 요소 분배 */
  gap: 2rem;
  padding: 2rem;
  background-color: #E0F1E2;
  height: calc(100vh - 4rem); /* 화면 크기 기준으로 영역 설정 */
}

.calendar-container {
  flex: 1; /* Calendar 컴포넌트 영역 조정 */
  max-width: 25%;
}

.diary-container {
  flex: 3; /* Diary 컴포넌트가 더 넓은 공간 차지 */
  background-color: #fff;
  padding: 1.5rem;
  border-radius: 8px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
}
</style>

