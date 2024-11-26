<template>
  <div class="calendar">
    <div class="calendarDiary">
      <div class="calendarUpperHeader">
        <select v-model="year" class="year">
          <option v-for="year in years">{{ year }}</option>
        </select>
        <button @click="goToday">today</button>
      </div>
      <div class="calendarHeader">
        <div @click="lastMonth"> < </div>
        <p>{{ monthToEnglish[month-1] }}</p>
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
              :class="{ today : date.day===year+'-'+month+'-'+day,
                        isPt : isPt(date.day.split('-')[0], date.day.split('-')[1], date.day.split('-')[2]),
                        notThisMonth : notThisMonth(date.day)}"
              @click="onDateChange(date.day)"
            >
              {{ date.day.split("-")[2] }}
            </td>
          </tr>
        </tbody>
      </table>
    </div>
  </div>
</template>

<script setup>
import { getDiariesByUserId } from '@/api/diary';
import { useUserStore } from '@/stores/userStore';
import axios from 'axios';
import { computed, onMounted, ref, watch } from 'vue';

const userStore = useUserStore()
const ptList = ref([])

const today = new Date()
const year = ref(today.getFullYear())
const month = ref(today.getMonth()+1)
const day = ref(today.getDate())

const years = []
for(let i = 1990; i<=2050; i++){
  years.push(i)
}

const monthToEnglish = [
  "JANUARY",   // 1월
  "FEBRUARY",  // 2월
  "MARCH",     // 3월
  "APRIL",     // 4월
  "MAY",       // 5월
  "JUNE",      // 6월
  "JULY",      // 7월
  "AUGUST",    // 8월
  "SEPTEMBER", // 9월
  "OCTOBER",   // 10월
  "NOVEMBER",  // 11월
  "DECEMBER"   // 12월
];

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
        day : d.getFullYear()+"-"+(d.getMonth()+1)+"-"+d.getDate(),
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

const isPt = function(year, month, day){
  let result = false
  ptList.value.forEach((pt)=>{
    if(pt.date===year+"-"+month+"-"+day || pt.date===year+"-0"+month+"-"+day
        || pt.date===year+"-"+month+"-0"+day || pt.date===year+"-0"+month+"-0"+day
    ) {
      result = true
    }
  })
  return result
}

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
    axios.get("http://localhost:8080/pt/user", {
    params : {
      userId : userStore.loginUser.type==='user'? userStore.loginUser.id : userStore.follower.id,
    }
  }).then((res)=>{
    ptList.value = res.data
  }).then(()=>{
    onDateChange(year.value+"-"+month.value+"-"+day.value)
  })
})

const onDateChange = (date) => {
  selectedDate.value = date;
  [year.value, month.value, day.value] = date.split('-').map((item)=>parseInt(item))
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

const notThisMonth = function(date){
  return month.value != date.split('-')[1]
}

onMounted(()=>{
  axios.get("http://localhost:8080/pt/user", {
    params : {
      userId : userStore.loginUser.type==='user'? userStore.loginUser.id : userStore.follower.id,
    }
  }).then((res)=>{
    ptList.value = res.data
  })

  // axios.get("http://localhost:8080/user/diary")
})
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
  padding: 3rem;
}

.calendar td.today {
  border: 9px solid rgba(42, 116, 42, 0.587); /* 초록색 테두리 */
  border-radius: 8px; /* 둥근 모서리 */
  padding: 0px; /* 내용과 테두리 간의 간격 */
  box-sizing: border-box; /* 테두리가 크기에 포함되도록 설정 */
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
  height: 10px;
  display: flex;
  justify-content: center;
  align-items: center;
  cursor: pointer;
}

.calendarHeader p {
  width: 40px;
  height: 0;
  display: flex;
  justify-content: center;
  align-items: center;
  font-size: large;
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
  border-radius: 3px;
  background-color: #7FC678;
  color: white;
  cursor: pointer;
}

.year {
  position: relative;
  display: inline-block;
  width: 7rem;
  height: 40px;
  background-color: #ffffff;
  border: 1px solid #7FC678;
  border-radius: 3px;
  font-size: 1rem;
  color: #333;
  padding: 0.5rem 1rem;
  cursor: pointer;
  transition: border-color 0.3s, box-shadow 0.3s;
}

.year:hover {
  border-color: #65A45B;
}

.year:focus {
  outline: none;
  border-color: #65A45B;
}

.year option {
  background-color: #ffffff;
  color: #333;
  font-size: 16px;
  padding: 5px 10px;
}

.year option:hover {
  background-color: #e6f5e6;
}

.calendar button:hover {
  background-color: #65A45B;
}

td.isPt {
  background-color: rgba(176, 212, 176, 0.291);
}

td.notThisMonth{
  background-color: rgb(233, 233, 233);
  color: #b0b0b0;
}

.calendarUpperHeader{
  width: 100%;
  display: flex;
  justify-content:space-between;
}


select {
  background-color: transparent; /* 배경색 제거 */
  border: none; /* 테두리 제거 */
  border-radius: 4px; /* 살짝 둥근 모서리 */
  padding: 10px 12px; /* 여백 */
  font-size: 19px; /* 부모의 폰트 크기 상속 */
  font-family: inherit; /* 부모의 폰트 상속 */
  text-align: center;
  color: inherit; /* 부모의 글씨 색상 상속 */
  cursor: pointer; /* 포인터 모양 변경 */
  width: 150px; /* 드롭다운 너비 */
  padding-top: 0%;
  padding-bottom: 0%;
}

select:hover {
  color: gray; /* 호버 시 부모의 글씨 색상 유지 */
}

select:focus {
  outline: none; /* 포커스 시 외곽선 제거 */
}

</style>


