<template>
    <div>
        <h4>Trainer</h4>
        <Calendar @dateSelected="onDateSelected" />
  
        <!-- 선택된 날짜가 있을 경우 다이어리 컴포넌트 표시 -->
        <!-- <DiaryView v-if="selectedDate" :date="selectedDate" /> -->
    </div>
</template>
  
<script setup>
import { ref } from 'vue';
import Calendar from './Calendar.vue';
import DiaryView from '@/views/DiaryView.vue';
import { useUserStore } from '@/stores/userStore';

localStorage.setItem('lastVisitedUrl', location.href)

const userStore = useUserStore()

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
    display : flex;
    justify-content: space-around;
    padding: 2rem;
    background-color: #E0F1E2
}
</style>
  