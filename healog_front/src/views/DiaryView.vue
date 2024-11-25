<template>
  <div class="condition">
    <div class="header">
      <h3>Plan</h3>
      <div v-if="!isPt" class="header">
        <button @click="openPtModal = !openPtModal"> PT 일정 등록 </button>
      </div>
    </div>

      <div>
        <div v-if="userStore.loginUser.type === 'user'">
          <input v-model="condition" placeholder="Enter condition here" />
          <button @click="saveCondition">등록</button>
        </div>

        <div>
          <div v-if="!isPt">
            <div v-if="openPtModal" ref="modalRef" class="openPtModal">
              <div class="modal-header">
                <h3>PT Schedule</h3>
                <button @click="openPtModal = false" class="close-btn">X</button>
              </div>
              <div>
                <label for="time">Time</label>
                <select id="time" v-model="selectedTime">
                  <option v-for="i in time" :key="i" :value="i">{{ i }}:00</option>
                </select>
              </div>
              <div class="modal-footer">
                <button @click="updatePt" class="primary-btn">Create</button>
                <button @click="deletePt" class="danger-btn">Delete</button>
              </div>
            </div>
          </div>
          <div v-else>
            <div>pt 시간 : {{ ptInfo.time }}시</div>
            <button @click="openPtModal=!openPtModal">pt 일정 수정하기</button>
            <div v-if="openPtModal" class="openPtModal" >
              <div class="modal-header">
                <h3 for ="time">Personal Training Schedule</h3>
              </div>
              <div>
                <select id="time" v-model="selectedTime">
                  <option v-for="i in time">{{ i }}</option>
                </select>
              </div>
              <div class="modal-footer">
                <button @click="updatePt">Create</button>
                <button @click="deletePt">Delete</button>
              </div>
            </div>
          </div>
          <div v-if="userStore.loginUser.type === 'trainer'" class="condition-input">{{ condition ? condition : "등록된 컨디션이 없습니다."  }}</div>
        </div>
        <!-- Exercise List -->
        <ExerciseList v-if="diary" :diaryId="diary.id" />
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
const time = [8,9,10,11,12,13,14,15,16,17,18,19,20]
const ptInfo = ref({})

const requestInsertPt = function(){
  const check = confirm(diary.value.year+"년 "+diary.value.month+"월 "+diary.value.day+"일 "+selectedTime.value+"시에 pt를 요청하시겠습니까?")
  if(check){
    axios.post("http://localhost:8080/pt/request/insert",{
      id : diary.value.id,
      time : selectedTime.value,
    }).then(()=>{
      alert("요청이 전송되었습니다.")
      router.push({name : 'main'})
    }).catch((error)=>{
      console.log(error)
    })
  }
}

const adjustModalPosition = (buttonElement) => {
  // 버튼의 위치 정보를 가져옴
  const buttonRect = buttonElement.getBoundingClientRect();
  const modal = modalRef.value;

  // 기본 위치 설정: 버튼 바로 아래
  modal.style.top = `${buttonRect.bottom + window.scrollY}px`;
  modal.style.left = `${buttonRect.left}px`;
};

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
    axios.get("http://localhost:8080/pt",{
      params : {
        userId : userStore.loginUser.type==='user' ? userStore.loginUser.id : userStore.follower.id,
        year : diary.value.year,
        month : diary.value.month,
        day : diary.value.day
      }
    }).then((res)=>{
      isPt.value = res.data
      if(isPt.value!==0){
        axios.get("http://localhost:8080/pt/info",{
          params : {
            userId : userStore.loginUser.type==='user' ? userStore.loginUser.id : userStore.follower.id,
            year : diary.value.year,
            month : diary.value.month,
            day : diary.value.day
        }
      }).then((res)=>{
        console.log(res.data)
        ptInfo.value = res.data
        selectedTime.value = ptInfo.value.time
      })
    }else{
      ptInfo.value=null
    }
  })
}
}

const followerChange = computed(()=>{
  return userStore.follower.id
})

const isPt = ref(0)

watch([props.date, followerChange], ()=>{
  openPtModal.value = false
  selectedTime.value = -1

  return fetchDiary
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
        isPt.value = res.data
        if(isPt.value!==0){
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
/* 오버레이 */
.overlay {
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background-color: rgba(0, 0, 0, 0.5);
  z-index: 999;
}

.condition {
  display: flex;
  flex-direction: column;
  padding: 1.5rem;
  margin: auto;
}

.header {
  display: flex;
  flex-direction: row;
  justify-content: space-between;
  border-bottom: 1px solid #ddd;
  margin-bottom: 1rem;
}

.helper-text {
  font-size: 0.8rem;
  color: #888;
}

.openPtModal {
  position: absolute;
  display: flex;
  flex-direction: column;
  gap: 1rem;
  top: 25%;
  right: 4%;
  background: white;
  border: 1px solid #ddd;
  box-shadow: 0px 4px 8px rgba(0, 0, 0, 0.1);
  border-radius: 3px;
  padding: 10px;
  z-index: 1000;
  min-width: 15rem;
  min-height: 10rem;
  overflow: visible;
}

.modal-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  border-bottom: 1px solid #ddd;
}

.openPtModal label {
  font-size: 1rem;
  font-weight: bold;
  color: #555;
  margin-bottom: 0.5rem;
}

.openPtModal select {
  width: 100%;
  padding: 0.5rem;
  font-size: 1rem;
  border: 1px solid #ddd;
  border-radius: 5px;
  background-color: #f9f9f9;
  transition: border-color 0.3s ease;
}

.openPtModal select:focus {
  border-color: #7fc678;
  outline: none;
}

/* 모달 푸터 */
.modal-footer {
  display: flex;
  justify-content: flex-end;
  gap: 0.5rem;
}

button.close-btn {
  background-color: transparent !important;
  color: #555 !important;
  border: none !important;
}


.modal-footer .danger-btn {
  padding: 0.5rem 1rem;
  background-color: transparent;
  border: 1px solid #ff4d4f;
  color: #ff4d4f;
  border-radius: 3px;
  cursor: pointer;
  font-size: 0.9rem;
}

.modal-footer .danger-btn:hover {
  background-color: #ff4d4f;
  color: white;
}

.condition input, .condition-input {
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

