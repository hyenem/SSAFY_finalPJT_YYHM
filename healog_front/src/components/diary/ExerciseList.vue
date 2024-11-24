<template>
  <div>
    <div class="header">
      <h3>Exercise List</h3>
    </div>
    <p v-if="isLoading">Loading exercises...</p>
    <div v-else-if="exercises.length">
      <div class="exerciseList" v-for="exercise in exercises" :key="exercise.id">
        <!-- Exercise Card -->
        <div class="exerciseCard" @click="openModal(exercise.id)">
          <div>
            {{ exercise.exercise }} | {{ exercise.exerciseArea }}
          </div>
          <div>
            {{ exercise.set || 'N/A' }}세트 X {{ exercise.weight || 'N/A' }} kg X {{ exercise.count || 'N/A' }}회
          </div>
        </div>
        <!-- Done Checkbox -->
        <input
          type="checkbox"
          :checked="exercise.done"
          @change="toggleDone(exercise)"
        />
        <!-- Image Preview -->
        <div class="image-preview">
          <img
            v-if="exercise.postureImg"
            :src="resolveImagePath(exercise.postureImg)"
            alt="Posture"
            class="posture-img"
          />
          <p v-else>No Image</p>
        </div>
      </div>
    </div>
    <p v-else>Plan을 추가해주세요.</p>
    <button @click="openModal(null)" class="add-exercise">+</button>
    
    <ExerciseListModal
      v-if="isModalOpen"
      :exerciseId="selectedExerciseId"
      :diaryId="diaryId"
      @close="closeModal"
    />

    <MealList :diaryId="diaryId" />
  </div>
</template>

<script setup>
import { useExerciseStore } from '@/stores/exerciseStore';
import { ref, computed, watch } from 'vue';
import ExerciseListModal from './ExerciseListModal.vue';
import MealList from './MealList.vue';

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

const toggleDone = async (exercise) => {
  try {
    const updatedExercise = { ...exercise, done: exercise.done ? 0 : 1 };
    await exerciseStore.updateExercise(updatedExercise);
    alert(`Exercise marked as ${updatedExercise.done ? 'Done' : 'Not Done'}`);
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

const openModal = (exerciseId) => {
  selectedExerciseId.value = exerciseId;
  isModalOpen.value = true;
};

const closeModal = async () => {
  selectedExerciseId.value = null;
  isModalOpen.value = false;
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
  gap: 1rem;
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

</style>
