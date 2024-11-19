<template>
  <div>
    <h3>Calendar</h3>
    <input type="date" @change="onDateChange" />
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

// Emit the selected date
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

</style>
