<template>
  <div class="condition">
    <div class="header">
      <h3>Plan</h3>
    </div>
      <div>
        <p v-if="!diary">Loading diary data...</p>
        <div v-else>
          <input v-model="condition" placeholder="Enter condition here" />
          <button @click="saveCondition">등록</button>
        </div>
        <div v-else>
          <div v-if="!isPt">
            <button @click="openPtModal = !openPtModal"> pt일정 등록 </button>
            <div v-if="openPtModal">
              <hr>
              <h4>PT 일정 등록 폼</h4>
              <label for ="time">PT 시간</label>
              <select id="time" v-model="selectedTime">
                <option v-for="i in time">{{ i }}</option>
              </select>
              <button @click="setPt">등록하기</button>
              <hr>
            </div>
          </div>
          <div v-else>
            <div>pt 시간 : {{ ptInfo.time }}시</div>
            <button @click="openPtModal=!openPtModal">pt 일정 수정하기</button>
            <div v-if="openPtModal">
              <hr>
              <label for ="time">PT 시간</label>
              <select id="time" v-model="selectedTime">
                <option v-for="i in time">{{ i }}</option>
              </select>
              <button @click="updatePt">수정하기</button>
              <button @click="deletePt">삭제하기</button>
              <hr>
            </div>
          </div>
          <div > 오늘의 컨디션 : {{ condition ? condition : "등록된 컨디션이 없습니다."  }}</div>
        </div>
        <!-- Exercise List -->
        <ExerciseList v-if="diary.id" :diaryId="diary.id" />
      </div>
  </div>
</template>

<script setup>
import { ref, computed, watch, onMounted } from 'vue';
import { useDiaryStore } from '@/stores/diaryStore';
import ExerciseList from '@/components/diary/ExerciseList.vue';
import { useUserStore } from '@/stores/userStore';
import axios from 'axios';

localStorage.setItem('lastVisitedUrl', location.href)


const props = defineProps({
  date: {
    type: Object,
    required: true,
  },
});

const userStore = useUserStore()
const diaryStore = useDiaryStore();
const diary = ref(null);
const condition = ref('');
const openPtModal = ref(false)
const selectedTime = ref(-1)
const time = [0, 1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23]
const ptInfo = ref({})

const updatePt = function(){
  let check = confirm(diary.value.year+"년 "+diary.value.month+"월 "+diary.value.day+"일 "+userStore.follower.name+"의 pt 일정을 "+selectedTime.value+"시에로 수정하시겠습니까?")
  if(check){
    axios.patch("http://localhost:8080/pt/time",{
      id : diary.value.id,
      time : selectedTime.value
    }).then(()=>{
      alert("pt 일정을 수정하였습니다.")
      const tmp = userStore.follower.id
    }).catch(()=>{
      alert("pt 일정 수정에 실패하였습니다.")
    })
  }
}

const deletePt = function(){
  let check = confirm(diary.value.year+"년 "+diary.value.month+"월 "+diary.value.day+"일 "+userStore.follower.name+"의 pt 일정을 삭제하시겠습니까?")
  if(check){
    axios.delete("http://localhost:8080/pt",{
      params: {
        id : diary.value.id,
      }
    }).then(()=>{
      alert("pt 일정을 삭제하였습니다.")
    }).catch(()=>{
      alert("pt 일정 삭제를 실패하였습니다.")
    })
  }
}

const setPt = function(){
  if(selectedTime.value===-1){
    alert("시간을 설정해주세요.")
    return
  }

  let check = confirm(diary.value.year+"년 "+diary.value.month+"월 "+diary.value.day+"일 "+selectedTime.value+"시에 "+userStore.follower.name+"의 pt 일정을 등록하시겠습니까?")
  if(check){
      axios.post("http://localhost:8080/pt",{
      trainerId : userStore.loginUser.id,
      id : diary.value.id.toString(),
      time : selectedTime.value.toString()
    }).then(()=>{
      alert("pt 일정을 등록하였습니다.")
    }).catch(()=>{
      alert("pt 일정 등록에 실패하였습니다.")
    })
  }
}

const formattedDate = computed(() => {
  if (props.date) {
    return `${props.date.year}-${String(props.date.month).padStart(2, '0')}-${String(props.date.day).padStart(2, '0')}`;
  }
  return 'Invalid date';
});

const fetchDiary = async () => {
  if (props.date) {
    await diaryStore.fetchDiaryByDate(props.date);
    diary.value = diaryStore.selectedDiary;
    condition.value = diary?.value?.condition || '';
  }
}

const followerChange = computed(()=>{
  return userStore.follower.id
})

const isPt = ref(false)
watch([props.date, followerChange], ()=>{
  openPtModal.value = false
  selectedTime.value = -1

  axios.get("http://localhost:8080/pt", {
    params : {
      userId : userStore.loginUser.type==='user'? userStore.loginUser.id : userStore.follower.id,
      year : props.date.year,
      month : props.date.month,
      day : props.date.day
    }
  }).then((res)=>{
    isPt.value = res.data
    if(isPt.value){
      axios.get("http://localhost:8080/pt/info",{
        params : {
          userId : userStore.loginUser.type==='user'? userStore.loginUser.id : userStore.follower.id,
          year : props.date.year,
          month : props.date.month,
          day : props.date.day
        }
      })
      .then((res)=>{
        ptInfo.value = res.data
        selectedTime.value = ptInfo.value.time
      })
    }
  })
})

const saveCondition = async () => {
  if (diary.value) {
    const diaryDto = {
      id: diary.value.id,
      condition: condition.value,
      userId: diary.value.userId,
      year: diary.value.year,
      month: diary.value.month,
      day: diary.value.day,
    };
    await diaryStore.updateDiaryCondition(diaryDto);
    alert('Condition updated successfully!');
  }
};


watch(() => props.date, fetchDiary, { immediate: true });
onMounted(()=>{
  const today = new Date()
  const userStore = useUserStore()
    axios.get("http://localhost:8080/user/diary/condition",{
      params : {
        userId : userStore.loginUser.type==='user'? userStore.loginUser.id : userStore.follower.id,
        year : today.getFullYear(),
        month : today.getMonth()+1,
        day : today.getDate()
      }
    }).then((res)=>{
      diary.value = res.data
      condition.value = diary?.value?.condition || ''
      axios.get("http://localhost:8080/pt", {
        params : {
          userId : userStore.loginUser.type==='user'? userStore.loginUser.id : userStore.follower.id,
          year : today.getFullYear(),
          month : today.getMonth()+1,
          day : today.getDate()
        }
      }).then((res)=>{
        console.log(res)
        isPt.value = res.data
        if(isPt.value){
          axios.get("http://localhost:8080/pt/info",{
            params : {
              userId : userStore.loginUser.type==='user'? userStore.loginUser.id : userStore.follower.id,
              year : today.getFullYear(),
              month : today.getMonth()+1,
              day : today.getDate()
            }
          })
          .then((res)=>{
            ptInfo.value = res.data
            selectedTime.value = ptInfo.value.time
          })
        }
      })
    })
  })

</script>

<style scoped>
.condition {
  display: flex;
  flex-direction: column;
  padding: 1.5rem;
  margin: auto;
}

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

.condition input {
  padding: 0.5rem;
  border: 1px solid #ccc;
  border-radius: 3px;
}

.condition button {
  padding: 0.5rem 1rem;
  background-color: #7FC678;
  color: white;
  border: none;
  border-radius: 3px;
  cursor: pointer;
  transition: background-color 0.3s;
}

.condition button:hover {
  background-color: #65A45B;
}
</style>

