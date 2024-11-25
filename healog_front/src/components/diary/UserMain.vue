<template>
  <div>
    <div class="userMain">
      <div class="leftPanel">
        <div class="calendarWrapper">
          <Calendar @dateSelected="onDateSelected" />
        </div>
        <div class="feedbackWrapper">
          <Feedback />
        </div>
      </div>
      <div class="rightPanel">
        <!-- 선택된 날짜가 있을 경우 다이어리 컴포넌트 표시 -->
        <DiaryView v-if="selectedDate" :date="selectedDate" />
      </div>
    </div>
  </div>
</template>

<script setup>
import { ref } from 'vue';
import Calendar from './Calendar.vue';
import Feedback from './Feedback.vue';
import DiaryView from '@/views/DiaryView.vue';

localStorage.setItem('lastVisitedUrl', location.href);

const today = new Date();
const selectedDate = ref({
  year: today.getFullYear(),
  month: today.getMonth()+1,
  day: today.getDate(),
});

const onDateSelected = (date) => {
  selectedDate.value = date;
};
</script>

<style scoped>

.userMain {
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
</style>
