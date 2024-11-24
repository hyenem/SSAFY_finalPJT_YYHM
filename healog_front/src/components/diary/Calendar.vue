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
  display: flex;
  flex-direction: column;
  align-items: center;
  gap: 1rem;
  background-color: #fff;
  padding: 1rem;
  border-radius: 8px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
}

.calendar h3 {
  font-size: 1.2rem;
  color: #333;
}

.calendar input[type='date'] {
  padding: 0.5rem;
  border: 1px solid #ccc;
  border-radius: 4px;
}

.calendar button {
  padding: 0.5rem 1rem;
  border: none;
  border-radius: 4px;
  background-color: #4CAF50;
  color: white;
  cursor: pointer;
  transition: background-color 0.3s;
}

.calendar button:hover {
  background-color: #45a049;
}
</style>

