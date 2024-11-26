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
                <input type="text" v-model="feedback">
                <button @click="insertOrUpdateFeedback">{{ feedback? "수정" : "등록" }}</button>
                <hr>
            </div>
        </div>
    </div>
</template>

<script setup>
import { useUserStore } from '@/stores/userStore';
import { ref } from 'vue';

const userStore = useUserStore()

const openFeedbackModal = ref(false);
const feedback = ref("")

</script>

<style scoped>
.feedback {
    display: flex;
    flex-direction: column;
}

</style>