<template>
  <div class="calendar">
    <h3>Calendar</h3>
    <div>
      <div @click="lastMonth"> < </div>
      <div>{{ year }} 년</div>
      <div>{{ month }} 월</div>
      <div @click="nextMonth"> > </div>
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
          <tr v-for="week in dateOfThisMonth">
            <td @click="onDateChange(week[0])">{{ week[0].split("-")[2] }}</td>
            <td @click="onDateChange(week[1])">{{ week[1].split("-")[2] }}</td>
            <td @click="onDateChange(week[2])">{{ week[2].split("-")[2] }}</td>
            <td @click="onDateChange(week[3])">{{ week[3].split("-")[2] }}</td>
            <td @click="onDateChange(week[4])">{{ week[4].split("-")[2] }}</td>
            <td @click="onDateChange(week[5])">{{ week[5].split("-")[2] }}</td>
            <td @click="onDateChange(week[6])">{{ week[6].split("-")[2] }}</td>
          </tr>
        </tbody>
      </table>
      <button @click="goToday">today</button>
    </div>
  </div>
</template>

<script setup>
import { useUserStore } from '@/stores/userStore';
import { computed, ref, watch } from 'vue';

const userStore = useUserStore()

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
        weekDates.push(d.getFullYear()+"-"+(d.getMonth()+1)+"-"+d.getDate());
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

watch(()=>userStore.follower,()=>{
    selectedDate.value = null
    onDateChange(year.value+"-"+month.value+"-"+day.value)
})

const onDateChange = (date) => {
  selectedDate.value = date;
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
