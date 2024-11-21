<template>
  <div>
    <h3>Exercise List</h3>
    <button @click="openModal(null)" class="add-exercise">+ Add Exercise</button>
    <p v-if="isLoading">Loading exercises...</p>
    <ul v-else-if="exercises.length">
      <li v-for="exercise in exercises" :key="exercise.id">
        <input
          type="checkbox"
          :checked="exercise.done"
          @change="toggleDone(exercise)"
        />
        <span>
          {{ exercise.exercise }} | {{ exercise.exerciseArea }} |
          Weight: {{ exercise.weight || 'N/A' }} kg | Count: {{ exercise.count || 'N/A' }} |
          Set: {{ exercise.set || 'N/A' }} | Done: {{ exercise.done ? 'Yes' : 'No' }} |
        </span>
        <div class="image-preview">
          <img
            v-if="exercise.postureImg"
            :src="exercise.postureImg"
            alt="Posture"
            class="posture-img"
          />
          <p v-else>No Image</p>
        </div>
        <button @click="openModal(exercise.id)">Edit</button>
        <button @click="deleteExercise(exercise.id)" class="delete-exercise">Delete</button>
      </li>
    </ul>
    <p v-else>Plan을 추가해주세요.</p>

    
    <ExerciseListModal
      v-if="isModalOpen"
      :exerciseId="selectedExerciseId"
      :diaryId="diaryId"
      @close="closeModal"
    />
  </div>
</template>

<script setup>
import { useExerciseStore } from '@/stores/exerciseStore';
import { ref, computed, watch } from 'vue';
import ExerciseListModal from './ExerciseListModal.vue';

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

const deleteExercise = async (exerciseId) => {
  try {
    const confirmed = confirm('Are you sure you want to delete this exercise?');
    if (!confirmed) return;

    await exerciseStore.deleteExercise(exerciseId); // 수정된 deleteExercise 호출
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

watch(() => props.diaryId, async (newDiaryId) => {
  if (newDiaryId) {
    isLoading.value = true;
    await exerciseStore.loadExercises(newDiaryId);
    isLoading.value = false;
  }
}, { immediate: true });
</script>

<style scoped>
.image-preview {
  display: inline-block;
  margin-right: 10px;
}

.posture-img {
  width: 5rem;
  height: 5rem;
  object-fit: cover;
  border: 1px solid #ddd;
  border-radius: 4px;
}
</style>
