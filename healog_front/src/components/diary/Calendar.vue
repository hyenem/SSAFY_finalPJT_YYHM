<template>
  <div class="calendar">
    <div class="header">
      <h3>Calendar</h3>
    </div>
    <div class="calendarDiary">
      <div class="calendarHeader">
        <div @click="lastMonth"> < </div>
        <div>{{ year }} 년 {{ month }} 월</div>
        <div @click="nextMonth"> > </div>
      </div>
      <table>
        <thead>
          <tr>
            <th>SUN</th>
            <th>MON</th>
            <th>TUE</th>
            <th>WED</th>
            <th>THU</th>
            <th>FRI</th>
            <th>SAT</th>
          </tr>
        </thead>
        <tbody>
          <tr v-for="week in dateOfThisMonth" :key="week">
            <td
              v-for="date in week"
              :key="date.day"
              :class="{ today : isToday(date.day)}"
              @click="onDateChange(date.day)"
            >
              {{ date.day.split("-")[2] }}
            </td>
          </tr>
        </tbody>
      </table>
      <button @click="goToday">today</button>
    </div>
  </div>
</template>

<script setup>
import { getDiariesByUserId } from '@/api/diary';
import { useUserStore } from '@/stores/userStore';
import axios from 'axios';
import { computed, ref, watch } from 'vue';

const userStore = useUserStore()
let ptList = []

const today = new Date()
const year = ref(today.getFullYear())
const month = ref(today.getMonth()+1)
const day = ref(today.getDate())

const dateOfThisMonth = computed(()=>{
  const diaryList = []
  let end = 0;
  let date = new Date(year.value+"-"+month.value +"-01")
  while(true && end<10){
    let dayOfWeek = date.getDay();
    
    let startDate = new Date(date);
    startDate.setDate(date.getDate() - dayOfWeek);

    let endDate = new Date(date);
    endDate.setDate(date.getDate() + (6 - dayOfWeek));
    console.log(startDate)
    let weekDates = [];
    let count = 0;
    for (let d = new Date(startDate); d <= endDate; d.setDate(d.getDate() + 1)) {
      weekDates.push({
        day : d.getFullYear()+"-"+(d.getMonth()+1)+"-"+d.getDate()
      })
    }
    
    date.setDate(date.getDate()+7)
    end++
    if(count===1) continue
    if((endDate.getMonth()+2)%12===month.value%12) continue
    if((startDate.getMonth())%12===month.value%12) break
    diaryList.push(weekDates)
  }

  return diaryList;
})



const emit = defineEmits(['dateSelected']);
const selectedDate = ref({
  year : year,
  month : month,
  day : day
});

const lastMonth = function(){
  if( month.value-1 ==0){
    month.value = 12
    year.value = year.value -1
  } else {
    month.value = month.value-1
  }
}
const nextMonth = function(){
  if(month.value+1==13){
    year.value = year.value+1
    month.value = 1
  } else {
    month.value = month.value+1
  }
}

const checkFollow = computed(()=>{
  return userStore.follower.id
})
watch([checkFollow],()=>{
    selectedDate.value = null
    onDateChange(year.value+"-"+month.value+"-"+day.value)
})

const onDateChange = (date) => {
  selectedDate.value = date;
  [year.value, month.value, day.value] = date.split('-')
  emitDate();
};

const goToday = () => {
  year.value = today.getFullYear()
  month.value = today.getMonth()+1
  day.value = today.getDate()
  onDateChange(year.value+"-"+month.value+"-"+day.value)
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

const isToday = (date) => {
  const [y, m, d] = date.split("-").map(Number);
  return (
    y === today.getFullYear() &&
    m === today.getMonth() + 1 &&
    d === today.getDate()
  );
};


</script>

<style scoped>
.header {
  display: flex;
  flex-direction: column;
  justify-content: space-between;
  border-bottom: 1px solid #ddd;
  margin-bottom: 1rem;
}

.header h3 {
  margin-bottom: 0.1rem;
}

.helper-text {
  font-size: 0.8rem;
  color: #888;
}

.calendar {
  display: flex;
  flex-direction: column;
  gap: 1rem;
  padding: 1.5rem;
}

.calendar td.today {
  background-color: #B7E0B2;
  color: black; /* 가독성을 위해 텍스트 색상을 검정으로 변경 */
  font-weight: bold;
}

.calendarDiary {
  display: flex;
  flex-direction: column;
  align-items: center;
  gap: 1rem;
}

.calendarHeader {
  display: flex;
  justify-content: space-between;
  align-items: center;
  width: 100%;
  font-weight: bold;
  gap: 10px; /* 버튼과 텍스트 간 간격 조정 */
}

.calendarHeader div:nth-child(2) {
  flex-grow: 1; /* 년과 월을 가운데 정렬 */
  text-align: center;
}

.calendarHeader div {
  width: 40px;
  height: 40px;
  display: flex;
  justify-content: center;
  align-items: center;
  cursor: pointer;
}

.calendar table {
  width: 100%;
  border-collapse: collapse;
}

.calendar td {
  text-align: center;
  padding: 1rem;
  border: 1px solid #e0e0e0;
  cursor: pointer;
  font-size: 0.8rem;
}

.calendar th {
  text-align: center;
  padding: 0.5rem;
  color: black;
  font-weight: bold;
}

.calendar td:hover {
  background-color: #7FC678;
  color: white;
}

.calendar button {
  padding: 0.5rem 1rem;
  border: none;
  border-radius: 8px;
  background-color: #7FC678;
  color: white;
  cursor: pointer;
}

.calendar button:hover {
  background-color: #65A45B;
}
</style>


