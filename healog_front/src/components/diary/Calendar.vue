<template>
  <div class="calendar">
    <h3>Calendar</h3>
    <div>
      <input type="date" @change="onDateChange" />
      <button @click="clearDate">Reset</button>
    </div>
  </div>
</template>

<script setup>
import { ref } from 'vue';

const emit = defineEmits(['dateSelected']);
const selectedDate = ref(null);

const onDateChange = (event) => {
  selectedDate.value = event.target.value;
  emitDate();
};

const clearDate = () => {
  selectedDate.value = null;
  emit('dateSelected', null); // 날짜 초기화 이벤트 발생
};

const emitDate = () => {
  if (selectedDate.value) {
    const dateParts = selectedDate.value.split('-');
    const dateObject = {
      year: parseInt(dateParts[0], 10),
      month: parseInt(dateParts[1], 10),
      day: parseInt(dateParts[2], 10),
    };
    emit('dateSelected', dateObject);
  }
};

</script>

<style scoped>
.calendar {
  border: 1px solid;
  display: flex;
  flex-direction: column;
  background-color: white;
  padding: 1rem;
}
</style>
