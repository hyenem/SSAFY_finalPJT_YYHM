<template>
    <div class="request">
        <div>
            <label for="all">all</label>
            <input id="all" type="radio" value=0 v-model="filterCondition" name="filterCondition">
            <label for="before">트레이너 승인대기</label>
            <input id="before" type="radio" value=1 v-model="filterCondition" name="filterCondition">
            <label for="after">회원 확인중</label>
            <input id="after" type="radio" value=2 v-model="filterCondition" name="filterCondition">
        </div>
        <div>
            <div v-for="pt in filteredPtList">
                <hr>
                <div>{{ stateList[pt.requestState] }}</div>
                <div> 요청 회원 : {{ pt.userName }}</div>
                <div v-if="pt.requestState===2 || pt.requestState===5 || pt.requestState===8">
                    <div>수정 전 일정 : {{ pt.date }} {{ pt.time }}:00</div>
                    <div>수정 후 일정 : {{ pt.requestDate }} {{ pt.requestTime }}:00</div>
                </div>
                <div v-else>
                    <div>요청 일정 : {{ pt.date }} {{ pt.time }}:00</div>
                </div>
                <div v-if="pt.requestState>=2 && pt.requestState<=4">
                    <button @click="accept(pt.diaryId)">accept</button>
                    <button @click="deny(pt.diaryId)">deny</button>
                </div>
                <div v-else>회원 확인 후 반영됩니다.</div>
                <hr>
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
.request {
    display: flex;
    flex-direction: column;
    padding: 1rem 2rem;
}
  
.title {
    font-size: 1.5rem;
    font-weight: bold;
    color: #333;
    border-bottom: 1px solid #ddd;
    padding-bottom: 0.5rem;
    margin-bottom: 1rem;
}
  
  .account-card {
    display: flex;
    flex-direction: column;
    gap: 1.5rem;
    padding: 1.5rem;
    border: 1px solid #ddd;
    border-radius: 3px;
  }
  
  .account-info {
    display: flex;
    justify-content: space-between;
    align-items: center;
    gap: 1rem;
    padding: 0.8rem;
    border-bottom: 1px solid #f0f0f0;
  }
  
  .account-info:last-child {
    border-bottom: none;
  }
  
  label {
    font-size: 1rem;
    font-weight: bold;
    color: #555;
    min-width: 100px;
  }
  
  .trainer-info {
    font-size: 1rem;
    font-weight: bold;
    color: #7fc678;
    cursor: pointer;
    transition: color 0.3s ease;
  }
  
  .trainer-info:hover {
    color: #65a45b;
  }
  
  div {
    font-size: 1rem;
    color: #444;
  }
  
  .btn-primary {
    padding: 0.5rem 1rem;
    background-color: #7fc678;
    color: white;
    border: none;
    border-radius: 3px;
    font-size: 0.9rem;
    cursor: pointer;
    transition: background-color 0.3s ease;
  }
  
  .btn-primary:hover {
    background-color: #65a45b;
  }
  
  </style>
  