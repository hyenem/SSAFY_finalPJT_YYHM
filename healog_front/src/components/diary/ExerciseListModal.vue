<template>
  <div v-if="isVisible" class="modal">
    <h3>Plan</h3>
    <form @submit.prevent="saveChanges">
      <label>Weight</label>
      <input type="number" v-model="exercise.weight" />
      <label>Count</label>
      <input type="number" v-model="exercise.count" />
      <label>Set</label>
      <input type="number" v-model="exercise.set" />
      <div>
        <label for="postureImg">Posture Image</label>
        <p v-if="exercise.postureImg">{{ extractFileName(exercise.postureImg) }}</p>
        <input type="file" id="postureImg" @change="handleFileChange" />
      </div>
      <div class="button">
        <button type="submit">저장</button>
        <button type="button" @click="closeModal">취소</button>
      </div>
    </form>
  </div>
</template>

<script setup>
import { ref, watch } from 'vue';
import axios from 'axios';
import { useExerciseStore } from '@/stores/exerciseStore';

const props = defineProps({
  exerciseId: {
    type: Number,
    required: true,
  },
});

const emit = defineEmits(['close']);
const exerciseStore = useExerciseStore();
const postureImgFile = ref(null);
const isVisible = ref(true);
const exercise = ref({});

// 파일 이름 추출 함수
const extractFileName = (filePath) => {
  return filePath.split('_').pop();
};

// 운동 데이터 가져오기
const fetchExercise = async () => {
  if (props.exerciseId) {
    await exerciseStore.fetchExerciseById(props.exerciseId);
    exercise.value = { ...exerciseStore.selectedExercise };
  }
};

// 파일 변경 핸들러
const handleFileChange = (event) => {
  postureImgFile.value = event.target.files[0];
};

// 변경사항 저장
const saveChanges = async () => {
  try {
    const formData = new FormData();
    formData.append('id', exercise.value.id);
    formData.append('weight', exercise.value.weight);
    formData.append('count', exercise.value.count);
    formData.append('set', exercise.value.set);

    if (postureImgFile.value) {
      formData.append('postureImg', postureImgFile.value);
    }

    await axios.post('http://localhost:8080/user/exercise/done', formData, {
      headers: { 'Content-Type': 'multipart/form-data' },
    });

    // 운동 리스트 갱신
    await exerciseStore.loadExercises(exercise.value.diaryId);

    alert('Exercise updated successfully!');
    emit('close');
  } catch (error) {
    console.error('Error saving changes:', error);
    alert('Failed to save exercise changes.');
  }
};



const closeModal = () => {
  emit('close');
};

watch(() => props.exerciseId, fetchExercise, { immediate: true });
</script>

<style scoped>
.modal {
  border: 1px solid;
  padding: 1rem;
}

form {
  display: flex;
  flex-direction: column;
}

.button {
  display: flex;
  justify-content: space-between;
  margin-top: 1rem;
}
</style>
