<template>
  <div>
    <NavigationView/>
    <div class="main">
      <!-- 날짜 선택 컴포넌트 -->
      <Calendar @dateSelected="onDateSelected" />

      <!-- 선택된 날짜가 있을 경우 다이어리 컴포넌트 표시 -->
      <DiaryView v-if="selectedDate" :date="selectedDate" />
    </div>
  </div>
</template>

<script setup>
import { ref } from 'vue';
import Calendar from '@/components/diary/Calendar.vue';
import DiaryView from './DiaryView.vue';
import NavigationView from './common/NavigationView.vue';

const selectedDate = ref(null);

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

