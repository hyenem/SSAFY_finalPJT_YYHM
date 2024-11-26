<template>
  <div>
    <div class="header">
      <h3>Exercise List</h3>
      <p class="helper-text" v-if="userStore.login.type==='trainer'">Please upload square image, size less than 100KB</p>
    </div>
    <p v-if="isLoading">Loading exercises...</p>
    <div v-else-if="userStore.loginUser.type==='trainer'">
      <div v-if="exercises.length">
        <div class="exerciseList" v-for="exercise in exercises" :key="exercise.id">
          <!-- Exercise Card -->
          <!-- Done Checkbox -->
          <input
            type="checkbox"
            class="large-checkbox"
            :checked="exercise.done"
            :disabled="userStore.loginUser.type==='trainer'"
            @change="toggleDone(exercise)"
          />
          <div class="exerciseCard" @click="openModal(exercise.id, false, exercise.done)">
            <div class="category">
              <div class="exercise">
                {{ exercise.exercise }}
              </div>
              <div class="exerciseArea">
                {{ exercise.exerciseArea }}
              </div>
            </div>
            <div>
              {{ exercise.set || 'N/A' }}세트 
              <font-awesome-icon :icon="['fas', 'xmark']" />
              {{ exercise.weight || 'N/A' }}kg
              <font-awesome-icon :icon="['fas', 'xmark']" />
              {{ exercise.count || 'N/A' }}회
            </div>
          </div>
          <!-- Image Preview -->
          <div class="image-preview">
            <img
              v-if="exercise.postureImg"
              :src="resolveImagePath(exercise.postureImg)"
              alt="Posture"
              class="posture-img"
            />
          </div>
        </div>
      </div>
      <p v-else>Plan을 추천해주세요.</p>
      <button @click="openModal(null, true, false)" class="add-exercise">
        <font-awesome-icon :icon="['fas', 'plus']" />
      </button>
      
      <ExerciseListModal
        v-if="isModalOpen"
        :exerciseId="selectedExerciseId"
        :diaryId="diaryId"
        :done="isDone"
        :isCreate="isCreate"
        @close="closeModal"
      />

      <MealList :diaryId="diaryId" />
    </div>
    <div v-else>
      <div v-if="exercises.length">
        <div class="exerciseList" v-for="exercise in exercises" :key="exercise.id">
          <!-- Exercise Card -->
          <!-- Done Checkbox -->
          <input
            type="checkbox"
            class="large-checkbox"
            :checked="exercise.done"
            @change="toggleDone(exercise)"
          />
          <div class="exerciseCard" @click="openModal(exercise.id, false, exercise.done)">
            <div class="category">
              <div class="exercise">
                {{ exercise.exercise }}
              </div>
              <div class="exerciseArea">
                {{ exercise.exerciseArea }}
              </div>
            </div>
            <div>
              {{ exercise.set || 'N/A' }}세트 
              <font-awesome-icon :icon="['fas', 'xmark']" />
              {{ exercise.weight || 'N/A' }}kg
              <font-awesome-icon :icon="['fas', 'xmark']" />
              {{ exercise.count || 'N/A' }}회
            </div>
          </div>
          <!-- Image Preview -->
          <div class="image-preview">
            <img
              v-if="exercise.postureImg"
              :src="resolveImagePath(exercise.postureImg)"
              alt="Posture"
              class="posture-img"
            />
          </div>
        </div>
      </div>
      <p v-else>Plan을 추가해주세요.</p>
      <button @click="openModal(null, true, false)" class="add-exercise">
        <font-awesome-icon :icon="['fas', 'plus']" />
      </button>
      
      <ExerciseListModal
        v-if="isModalOpen"
        :exerciseId="selectedExerciseId"
        :diaryId="diaryId"
        :done="isDone"
        :isCreate="isCreate"
        @close="closeModal"
      />

      <MealList :diaryId="diaryId" />
    </div>
    
  </div>
</template>

<script setup>
import { useExerciseStore } from '@/stores/exerciseStore';
import { ref, computed, watch } from 'vue';
import ExerciseListModal from './ExerciseListModal.vue';
import MealList from './MealList.vue';
import { useUserStore } from '@/stores/userStore';

const userStore = useUserStore()
const props = defineProps({
  diaryId: {
    type: Number,
    required: true,
  },
});

const exerciseStore = useExerciseStore();
const selectedExerciseId = ref(null);
const isModalOpen = ref(false);
const exercises = computed(() => exerciseStore.exercises);
const isLoading = ref(false);
const isDone = ref(false)
const isCreate = ref(false)

const toggleDone = async (exercise) => {
  try {
    await exerciseStore.markAsDone(exercise.id, null, exercise.done ? 0 : 1);

    alert(`Exercise marked as ${exercise.done ? 'Done' : 'Not Done'}`);
  } catch (error) {
    console.error('Error toggling done status:', error);
    alert('Failed to update exercise status.');
  }
};

const resolveImagePath = (path) => {
  if (path.startsWith('/img/')) {
    return `http://localhost:8080${path}`;
  }
  return path;
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

const openModal = (exerciseId, create, done) => {
  selectedExerciseId.value = exerciseId;
  isModalOpen.value = true;
  isDone.value = done
  isCreate.value = create
};

const closeModal = async () => {
  selectedExerciseId.value = null;
  isModalOpen.value = false;
  isDone.value = false
  await exerciseStore.loadExercises(props.diaryId);
};

watch(
  () => props.diaryId,
  async (newDiaryId) => {
    if (newDiaryId) {
      isLoading.value = true;
      await exerciseStore.loadExercises(newDiaryId);
      isLoading.value = false;
    }
  },
  { immediate: true }
);
</script>

<style scoped>
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

.exerciseList {
  display: flex;
  justify-content: space-between;
  align-items: center;
  gap: 1rem;
  padding: 1rem;
  border: 1px solid #ddd;
  border-radius: 3px;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
  margin-bottom: 1rem;
  position: relative; /* Delete 버튼 위치 조정 */
}


.exerciseCard {
  display: flex;
  flex-direction: column;
  gap: 1rem;
  cursor: pointer;
  flex: 2;
}

.image-preview img {
  width: 80px;
  height: 80px;
  object-fit: cover;
  border-radius: 3px;
  border: 1px solid #ccc;
}

button {
  width: 100%;
  padding: 1rem;
  background-color: transparent;
  border-radius: 3px;
  border: 1px solid #ccc;
  color: black;
  cursor: pointer;
}

/* Scoped CSS */
.large-checkbox {
  width: 24px;
  height: 24px;
  appearance: none; /* 기본 브라우저 스타일 제거 */
  border: 2px solid #ccc;
  border-radius: 3px;
  cursor: pointer;
  position: relative;
  display: inline-block;
  background-color: #fff;
}

.large-checkbox:checked {
  background-color: #4caf50; /* 체크된 상태 색상 */
  border-color: #4caf50;
}

.large-checkbox:checked::after {
  content: '✔'; /* 체크 표시 */
  font-size: 18px;
  color: white;
  position: absolute;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
}

.category {
  display: flex;
  gap: 1rem;
}

.exercise {
  font-weight: bold;
}

.exerciseArea {
  color: grey;
}


</style>
