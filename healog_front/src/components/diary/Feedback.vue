<template>
    <div class="feedback">
        <h3>Feedback</h3>
        <div v-if="userStore.loginUser.type==='user'">
            <p>{{ feedback? feedback : "오늘의 피드백이 없습니다." }}</p>
        </div>
        <div v-else>
            <div @click="openFeedbackModal=!openFeedbackModal">
                {{ feedback? feedback : "+" }}
            </div>
            <div v-if="openFeedbackModal">
                <hr>
                <input type="text" v-model="newFeedback">
                <button @click="insertOrUpdateFeedback(newFeedback)" >{{ feedback ? "수정" : "등록"  }}</button>
                <hr>
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

const openFeedbackModal = ref(false);
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
}

</style>