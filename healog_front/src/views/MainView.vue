<template>
  <div>
    <NavigationView/>
    <h1>Main View</h1>
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

const selectedDate = ref(null);

const onDateSelected = (date) => {
  selectedDate.value = date;
};
</script>

<style scoped>

</style>
