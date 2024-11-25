<template>
  <div>
    <!-- 오버레이 -->
    <div class="overlay" @click="closeModal"></div>
    <!-- 모달 -->
    <div class="modal">
      <div class="modal-header">
        <h3>Plan</h3>
        <button class="close-btn" @click="closeModal">×</button>
      </div>
      <form @submit.prevent="saveChanges" class="modal-form">
        <div class="form-row-first">
          <!-- 부위 -->
          <div class="form-group">
            <label>부위</label>
            <select id="exerciseArea" v-model="selectedArea" @change="fetchExercisesByArea">
              <option v-for="area in exerciseStore.exerciseAreas" :key="area" :value="area">
                {{ area }}
              </option>
            </select>
          </div>
          <!-- 기구 -->
          <div class="form-group">
            <label>기구</label>
            <select id="exercise" v-model="exercise.exercise">
              <option v-for="ex in exerciseStore.exercisesByArea" :key="ex.id" :value="ex.exercise">
                {{ ex.exercise }}
              </option>
            </select>
          </div>
        </div>
        <div class="form-row">
          <!-- 세트, 무게, 횟수 -->
          <div class="form-group">
            <label>세트</label>
            <input type="number" v-model="exercise.set" />
          </div>
          <div class="form-group">
            <label>무게</label>
            <input type="number" v-model="exercise.weight" />
          </div>
          <div class="form-group">
            <label>횟수</label>
            <input type="number" v-model="exercise.count" />
          </div>
        </div>
        <!-- 이미지 업로드 -->
        <div class="form-group">
          <label>Posture Image</label>
          <p class="helper-text">Please upload square image, size less than 100KB</p>
          <div class="image-upload">
            <img v-if="postureImgPreview" :src="postureImgPreview" alt="Preview" class="preview-img" />
            <img v-else src="@/assets/default-placeholder.png">
            <input type="file" @change="handleFileChange" />
          </div>
        </div>
        <!-- 버튼 -->
        <div class="button-group">
          <button type="button" class="delete-btn" @click="deleteExercise(exercise.id)">Delete</button>
          <button type="submit" class="save-btn">{{ isEditing ? "Save" : "Create" }}</button>
        </div>
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

/* 모달 */
.modal {
  position: fixed;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  z-index: 1000;
  background-color: white;
  border-radius: 3px;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
  padding: 1.5rem;
  width: 420px;
  max-width: 90%;
}

/* 헤더 */
.modal-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  border-bottom: 1px solid #ddd;
  padding-bottom: 0.5rem;
  margin-bottom: 1rem;
}

.modal-header h3 {
  font-size: 1.2rem;
  margin: 0;
}

.close-btn {
  background-color: transparent;
  border: none;
  font-size: 1.5rem;
  cursor: pointer;
  color: #555;
}

/* 폼 */
.modal-form {
  display: flex;
  flex-direction: column;
  gap: 1rem;
}

/* 행 */
.form-row-first {
  display: flex;
  gap: 1rem;
}

.form-row {
  display: flex;
  flex-direction: column;
  gap: 1rem;
}

/* 필드 */
.form-group {
  display: flex;
  flex-direction: column;
  flex: 1;
}

label {
  font-size: 0.9rem;
  margin-bottom: 0.3rem;
  color: #555;
}

input,
select {
  padding: 0.5rem;
  border: 1px solid #ddd;
  border-radius: 3px;
  font-size: 0.9rem;
}

/* 이미지 업로드 */
.image-upload {
  display: flex;
  align-items: center;
  gap: 0.5rem;
}

.preview-img {
  width: 80px;
  height: 80px;
  object-fit: cover;
  border-radius: 3px;
  border: 1px solid #ddd;
}

.helper-text {
  font-size: 0.8rem;
  color: #888;
  margin-top: 0.3rem;
}

/* 버튼 그룹 */
.button-group {
  display: flex;
  justify-content: flex-end;
  gap: 1rem;
}

.delete-btn {
  padding: 0.5rem 1rem;
  background-color: transparent;
  border: 1px solid #ff4d4f;
  color: #ff4d4f;
  border-radius: 3px;
  cursor: pointer;
  font-size: 0.9rem;
}

.delete-btn:hover {
  background-color: #ff4d4f;
  color: white;
}

.save-btn {
  padding: 0.5rem 1rem;
  background-color: #7fc678;
  border: none;
  color: white;
  border-radius: 3px;
  cursor: pointer;
  font-size: 0.9rem;
}

.save-btn:hover {
  background-color: #65a45b;
}
</style>


