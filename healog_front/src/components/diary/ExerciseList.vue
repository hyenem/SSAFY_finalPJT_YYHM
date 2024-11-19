<template>
  <div>
    <h3>Exercise List</h3>
    <ul v-if="exercises.length">
      <li
        v-for="exercise in exercises"
        :key="exercise.id"
      >
        <input
          type="checkbox"
          :checked="exercise.done"
          @change="toggleDone(exercise)"
        />
        <span>
          Category: {{ exercise.categoryId }} | Weight: {{ exercise.weight || 'N/A' }} kg |
          Count: {{ exercise.count || 'N/A' }} | Set: {{ exercise.set || 'N/A' }} |
          Done: {{ exercise.done ? 'Yes' : 'No' }}
        </span>
        <button @click="openModal(exercise.id)">수정</button>
      </li>
    </ul>
    <p v-else>Plan을 추가해주세요.</p>

    <ExerciseListModal
      v-if="selectedExerciseId"
      :exerciseId="selectedExerciseId"
      @close="closeModal"
    />
  </div>
</template>

<script setup>
import { useExerciseStore } from '@/stores/exerciseStore';
import { watch, ref, computed } from 'vue';
import ExerciseListModal from './ExerciseListModal.vue';

const props = defineProps({
  diaryId: {
    type: Number,
    required: true,
  },
});

const exerciseStore = useExerciseStore();
const selectedExerciseId = ref(null);

const exercises = computed(() => exerciseStore.exercises);

const toggleDone = async (exercise) => {
  exercise.done = exercise.done ? 0 : 1;
  try {
    await exerciseStore.updateExercise(exercise);
    alert(`Exercise marked as ${exercise.done ? 'Done' : 'Not Done'}`);
  } catch (error) {
    console.error('Error toggling done status:', error);
    alert('Failed to update exercise status.');
    exercise.done = !exercise.done;
  }
};

const openModal = (exerciseId) => {
  selectedExerciseId.value = exerciseId;
};

const closeModal = () => {
  selectedExerciseId.value = null;
};

watch(
  () => props.diaryId,
  async (newDiaryId) => {
    if (newDiaryId) {
      await exerciseStore.loadExercises(newDiaryId);
    }
  },
  { immediate: true }
);
</script>

<style scoped>
.posture-img {
  width: 50px;
  height: 50px;
  object-fit: cover;
  margin-right: 10px;
  border-radius: 4px;
  border: 1px solid #ddd;
}
</style>
