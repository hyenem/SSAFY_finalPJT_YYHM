<template>
  <div>
    <!-- 오버레이 -->
    <div class="overlay" @click="closeModal"></div>
    <!-- 모달 -->
    <div class="modal">
      <h3>{{ isEditing ? "Edit" : "Add" }} Exercise Plan</h3>
      <button class="close-btn" type="button" @click="closeModal">X</button>
      <form @submit.prevent="saveChanges">
        <!-- 운동 부위 선택 -->
        <div class="category">
          <div>
            <label>부위</label>
            <select id="exerciseArea" v-model="selectedArea" @change="fetchExercisesByArea">
              <option v-for="area in exerciseStore.exerciseAreas" :key="area" :value="area">
                {{ area }}
              </option>
            </select>
          </div>

          <!-- 운동 선택 -->
          <div>
            <label>기구</label>
            <select id="exercise" v-model="exercise.exercise">
              <option v-for="ex in exerciseStore.exercisesByArea" :key="ex.id" :value="ex.exercise">
                {{ ex.exercise }}
              </option>
            </select>
          </div>
        </div>

        <!-- 나머지 수정 필드 -->
        <label>세트</label>
        <input type="number" v-model="exercise.set" />

        <label>무게</label>
        <input type="number" v-model="exercise.weight" />

        <label>횟수</label>
        <input type="number" v-model="exercise.count" />


        <div>
          <label for="postureImg">Posture Image:</label>
          <input type="file" id="postureImg" @change="handleFileChange" />
          <img v-if="postureImgPreview" :src="postureImgPreview" alt="Preview" />
        </div>

        <button type="submit">{{ isEditing ? "Save" : "Add" }}</button>
        <button @click.stop="deleteExercise(exercise.id)">delete</button>
      </form>
    </div>
  </div>
</template>


<script setup>
import { ref, computed, onMounted } from 'vue';
import { useExerciseStore } from '@/stores/exerciseStore';

const props = defineProps({
  exerciseId: {
    type: Number,
    required: false,
  },
  diaryId: {
    type: Number,
    required: true,
  },
});

const emit = defineEmits(['close']);
const exerciseStore = useExerciseStore();
const exercise = ref({});
const isEditing = computed(() => !!props.exerciseId);
const postureImgFile = ref(null);
const postureImgPreview = computed(() =>
  postureImgFile.value ? URL.createObjectURL(postureImgFile.value) : ''
);
const selectedArea = ref('');
const isLoading = ref(false);

const initializeExercise = async () => {
  if (isEditing.value) {
    await exerciseStore.fetchExerciseById(props.exerciseId);
    exercise.value = { ...exerciseStore.selectedExercise };
    selectedArea.value = exercise.value.exerciseArea;
    await fetchExercisesByArea();
  } else {
    exercise.value = {
      exercise: '',
      weight: 0,
      count: 0,
      set: 0,
      postureImg: null,
      diaryId: props.diaryId,
      categoryId: null,
    };
    selectedArea.value = '';
    postureImgFile.value = null;
  }
};

const fetchExercisesByArea = async () => {
  if (selectedArea.value) {
    await exerciseStore.loadExercisesByArea(selectedArea.value);
  }
};

const deleteExercise = async (exerciseId) => {
  try {
    const confirmed = confirm('Are you sure you want to delete this exercise?');
    if (!confirmed) return;

    await exerciseStore.deleteExercise(exerciseId);
    alert('Exercise deleted successfully!');
  } catch (error) {
    console.error('Error deleting exercise:', error);
    alert('Failed to delete exercise.');
  }
};

const handleFileChange = (event) => {
  postureImgFile.value = event.target.files[0];
};

const saveChanges = async () => {
  try {
    isLoading.value = true;

    const matchedCategory = exerciseStore.exercisesByArea.find(
      (ex) => ex.exercise === exercise.value.exercise
    );

    if (!matchedCategory) {
      alert('Please select a valid exercise.');
      return;
    }

    exercise.value.categoryId = matchedCategory.id;

    if (isEditing.value) {
      await exerciseStore.updateExercise(exercise.value, postureImgFile.value);
      alert('Exercise updated successfully!');
    } else {
      await exerciseStore.addExercise(exercise.value);
      alert('Exercise added successfully!');
    }

    emit('close');
  } catch (error) {
    console.error('Error saving changes:', error);
    alert('Failed to save exercise changes.');
  } finally {
    isLoading.value = false;
  }
};

const closeModal = () => {
  emit('close');
};

onMounted(async () => {
  await exerciseStore.loadExerciseAreas();
  await initializeExercise();
});
</script>


<style scoped>
/* 오버레이 스타일 */
.overlay {
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background-color: rgba(0, 0, 0, 0.5); /* 어두운 배경 */
  z-index: 999; /* 모달보다 아래에 표시 */
}

.modal {
  position: fixed;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  z-index: 1000; /* 오버레이 위에 표시 */
  background-color: white;
  padding: 2rem;
  border-radius: 8px;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
  width: 400px; /* 적절한 너비 설정 */
  max-width: 90%; /* 화면 크기에 따라 유동적으로 조정 */
}

form {
  display: flex;
  flex-direction: column;
}

.category {
  display: flex;
}

.close-btn {
  position: absolute;
  top: 0.5rem;
  right: 0.5rem;
  background-color: transparent;
  border: none;
  font-size: 1.5rem;
  color: grey;
  cursor: pointer;
}

img {
  width: 100px;
  height: auto;
  margin-top: 1rem;
  border-radius: 4px;
  border: 1px solid #ddd;
}
</style>

