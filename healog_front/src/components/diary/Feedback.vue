<template>
  <div class="feedback">
    <div class="header">
      <h3>Feedback</h3>
    </div>
    <div class="feedback-content">
      <!-- 사용자 피드백 출력 -->
      <div v-if="userStore.loginUser.type === 'user'">
        <p class="feedback-input">{{ feedback ? feedback : "오늘의 피드백이 없습니다." }}</p>
      </div>

      <!-- 트레이너 피드백 입력 -->
      <div v-else>
        <textarea v-model="newFeedback" class="feedback-input" placeholder="피드백을 입력하세요..."></textarea>
        <div class="modal-footer">
            <button @click="insertOrUpdateFeedback(newFeedback)" class="primary-btn">
            {{ feedback ? "Edit" : "Create" }}
            </button>
        </div>
      </div>
    </div>
  </div>
</template>


<script setup>
import { useUserStore } from '@/stores/userStore';
import axios from 'axios';
import { computed, onMounted, ref, watch } from 'vue';
import { useRouter } from 'vue-router';

const router = useRouter()
const props= defineProps({
    selectedDate : {
        type : Object,
        Required : true
    }
})

const userStore = useUserStore()
const feedback = ref("")
const newFeedback = ref(feedback.value)

const insertOrUpdateFeedback = function(feedback){
    axios.put("http://localhost:8080/user/diary/condition", {
        userId : userStore.loginUser.type==='user'? userStore.loginUser.id : userStore.follower.id,
        year : props.selectedDate.year,
        month : props.selectedDate.month,
        day : props.selectedDate.day,
        feedback : feedback
    }).then(()=>{
        alert("피드백이 업데이트 되었습니다.")
        router.go(0)
    })
}

const checkUser = computed(()=>{
    return userStore.follower.id
})
const checkDate = computed(()=>{
    return props.selectedDate
})

watch([checkDate, checkUser], ()=>{
    axios.get("http://localhost:8080/user/diary/condition", {
        params : {
            userId : userStore.loginUser.type==='user'? userStore.loginUser.id : userStore.follower.id,
            year : props.selectedDate.year,
            month : props.selectedDate.month,
            day : props.selectedDate.day
        }
    }).then((res)=>{
        newFeedback.value = res.data.feedback
        feedback.value=res.data.feedback
    })
})

onMounted(()=>{
    const today = new Date()
    axios.get("http://localhost:8080/user/diary/condition", {
        params : {
            userId : userStore.loginUser.type==='user'? userStore.loginUser.id : userStore.follower.id,
            year : today.getFullYear(),
            month : today.getMonth()+1,
            day : today.getDate()
        }
    }).then((res)=>{
        console.log(res.data)
        newFeedback.value = res.data.feedback
        feedback.value=res.data.feedback
    })
})
</script>

<style scoped>
.feedback {
  display: flex;
  flex-direction: column;
  padding: 1.5rem;
}

.header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  border-bottom: 1px solid #ddd;
  margin-bottom: 1rem;
}

.header h3 {
    margin-bottom: 0.1rem;
}

.feedback-content p {
  width: 90%;
  height: 6rem;
  border: 1px solid #ddd;
  border-radius: 3px;
  resize: none;
  font-size: 1rem;
  color: #555;
  padding: 1rem;
  margin-bottom: 1rem;
  transition: border-color 0.3s ease;
}

.feedback-btn {
  display: flex;
  align-items: center;
  justify-content: center;
  padding: 0.5rem 1rem;
  background-color: #7fc678;
  color: white;
  border: none;
  border-radius: 5px;
  cursor: pointer;
  font-size: 1rem;
  transition: background-color 0.3s ease;
}

.feedback-btn:hover {
  background-color: #65a45b;
}

.feedback-input {
  width: 90%;
  height: 6rem;
  border: 1px solid #ddd;
  border-radius: 3px;
  resize: none;
  font-size: 1rem;
  color: #555;
  padding: 1rem;
  margin-bottom: 1rem;
  transition: border-color 0.3s ease;
}

.feedback-input:focus {
  border-color: #7fc678;
  outline: none;
}

.modal-footer {
  display: flex;
  justify-content: flex-end;
  gap: 0.5rem;
}

.primary-btn {
  padding: 0.5rem 1rem;
  background-color: #7fc678;
  color: white;
  border: none;
  border-radius: 5px;
  cursor: pointer;
  font-size: 1rem;
  transition: background-color 0.3s ease;
}

.primary-btn:hover {
  background-color: #65a45b;
}

button.close-btn {
  background-color: transparent;
  border: none;
  color: #555;
  font-size: 1.2rem;
  cursor: pointer;
}


</style>