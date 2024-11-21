<template>
  <div>
    <NavigationView/>
    <h1>Main View</h1>
    <UserMain v-if="loginUserType==='user'"/>
    <TrainerMain v-if="loginUserType==='trainer'"/>
    <!-- 날짜 선택 컴포넌트 -->
    <Calendar @dateSelected="onDateSelected" />

    <!-- 선택된 날짜가 있을 경우 다이어리 컴포넌트 표시 -->
    <DiaryView v-if="selectedDate" :date="selectedDate" />
  </div>
</template>

<script setup>
import { ref } from 'vue';
import Calendar from '@/components/diary/Calendar.vue';
import DiaryView from './DiaryView.vue';
import NavigationView from './common/NavigationView.vue';
import UserMain from '@/components/diary/UserMain.vue';
import TrainerMain from '@/components/diary/TrainerMain.vue';
import { useUserStore } from '@/stores/userStore';

localStorage.setItem('lastVisitedUrl', location.href)

const userStore = useUserStore()

const loginUserType = userStore.loginUser.type

const selectedDate = ref(null);

const onDateSelected = (date) => {
  selectedDate.value = date;
};
</script>

<style scoped>

</style>
