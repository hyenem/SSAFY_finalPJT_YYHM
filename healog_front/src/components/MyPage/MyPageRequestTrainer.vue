<template>
    <div class="request">
        <div class="selectOption">
            <div
                :class="['option', filterCondition === 0 ? 'active' : '']"
                @click="filterCondition = 0"
            >
                All
            </div>
            <div
                :class="['option', filterCondition === 1 ? 'active' : '']"
                @click="filterCondition = 1"
            >
                트레이너 승인대기
            </div>
            <div
                :class="['option', filterCondition === 2 ? 'active' : '']"
                @click="filterCondition = 2"
            >
                회원 확인중
            </div>
        </div>

        <div class="alarmlist">
            <div v-for="pt in filteredPtList">
                <div class="alarm-info">
                    <div class="alarm-state">{{ stateList[pt.requestState] }}</div>
                </div>
                <div class="alarm-info">
                    <label>회원</label>
                    <div>{{ pt.userName }}</div>
                </div>
                <div>
                    <div v-if="pt.requestState===2 || pt.requestState===5 || pt.requestState===8" class="alarm-info">
                        <label>수정 전 일정</label>
                        <div>{{ pt.date }} {{ pt.time }}:00</div>
                        <label>수정 후 일정</label>
                        <div>{{ pt.requestDate }} {{ pt.requestTime }}:00</div>
                    </div>
                    <div v-else class="alarm-info">
                        <label>요청 일정</label>
                        <div>{{ pt.date }} {{ pt.time }}:00</div>
                    </div>
                </div>
                <div v-if="pt.requestState>=2 && pt.requestState<=4" class="btn-list">
                    <button @click="deny(pt.diaryId)">deny</button>
                    <button @click="accept(pt.diaryId)">accept</button>
                </div>
                <div v-else>회원 확인 후 반영됩니다.</div>
            </div>
        </div>
    </div>
</template>
  
  
<script setup>
import { useSubscribeStore } from '@/stores/subscribeStore';
import { useUserStore } from '@/stores/userStore';
import axios from 'axios';
import { computed, onMounted, ref, watch } from 'vue';
import { useRouter } from 'vue-router';
  
const userStore = useUserStore()
const router = useRouter()

const stateList = ["비활성화", "진행중인 요청 없음", "수정 요청", "생성 요청", "삭제 요청", "수정 요청 승인 확인 중", "생성 요청 승인 확인 중", "삭제 요청 승인 확인 중", "수정 요청 거절 확인 중", "생성 요청 거절 확인 중", "삭제 요청 거절 확인 중"]
  
const filterCondition = ref(0)
const ptList = ref([])

const filteredPtList = computed(()=>{
    if(filterCondition.value==0){
        return ptList.value
    } else if(filterCondition.value==1){
        return ptList.value.filter((item)=>{
            return item.requestState===2 || item.requestState === 3 || item.requestState ===4
        })
    } else {
        return ptList.value.filter((item)=>{
            return !(item.requestState===2|| item.requestState === 3|| item.requestState ===4)
        })
    }
})

const accept = function(diaryId){
    axios.patch("http://localhost:8080/pt/accept", {
        diaryId: diaryId
    }).then(()=>{
        alert("승인되었습니다.")
        router.go(0)
    })
}

const deny = function(diaryId){
    axios.patch("http://localhost:8080/pt/deny", {
        diaryId: diaryId
    }).then(()=>{
        alert("거부되었습니다.")
        router.go(0)
    })
}

onMounted(()=>{
    axios.get("http://localhost:8080/pt/request/trainer",{
        params : {
            trainerId : userStore.loginUser.id
        }
    }).then((res)=>{
        ptList.value = res.data
    })
})
  
</script>
  
<style scoped>
/* 전체 컨테이너 */
.request {
  display: flex;
  flex-direction: column;
  padding: 1rem 2rem;
}

/* 선택 옵션 섹션 */
.selectOption {
  display: flex;
  justify-content: center;
  gap: 2rem;
  margin-bottom: 2rem;
  padding: 0.5rem;
  border-radius: 3px;
}

.option {
  padding: 0.8rem 1.0rem;
  font-size: 1rem;
  font-weight: bold;
  color: #333;
  background-color: white;
  border: 1px solid #ddd;
  border-radius: 3px;
  cursor: pointer;
  transition: all 0.3s ease;
  text-align: center;
}

.option:hover {
  background-color: #eef5eb;
  color: #7fc678;
}

.option.active {
  background-color: #7fc678;
  color: white;
  border-color: #7fc678;
  transform: scale(1.05);
}

/* 알림 리스트 */
.alarmlist {
  display: flex;
  flex-direction: column;
  gap: 1.5rem;
}

/* 각 요청 카드 */
.alarmlist > div {
  display: flex;
  flex-direction: column;
  gap: 1.5rem;
  padding: 1.5rem;
  border: 1px solid #ddd;
  border-radius: 3px;
}

.alarm-info {
  display: flex;
  justify-content: space-between;
  align-items: center;
  gap: 1rem;
  padding: 0.8rem;
  border-bottom: 1px solid #f0f0f0;
}

.alarm-info:last-child {
  border-bottom: none;
}

.alarm-state, label {
  font-size: 1rem;
  font-weight: bold;
  color: #555;
  min-width: 100px;
}

.btn-list {
    display: flex; 
    justify-content: flex-end;
    gap: 1rem;
}

/* 버튼 스타일 */
button {
  padding: 0.5rem 1rem;
  border: none;
  border-radius: 4px;
  font-size: 0.9rem;
  cursor: pointer;
  transition: background-color 0.3s ease;
}

button:hover {
  background-color: #65a45b;
}

button:active {
  transform: scale(0.98);
}

button:last-of-type {
  background-color: #7fc678;
  color: white;
}

button:first-of-type {
  background-color: #ff5b5b;
  color: white;
}

/* 상태 텍스트 스타일링 */
.alarmlist > div > .state {
  font-size: 1.1rem;
  font-weight: bold;
  color: #7fc678;
}
</style>