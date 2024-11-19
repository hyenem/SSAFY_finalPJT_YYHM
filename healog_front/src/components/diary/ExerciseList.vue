<template>
  <div>
    <h3>Exercise List</h3>
    <ul v-if="exercises.length">
      <li v-for="exercise in exercises" :key="exercise.id">
        <!-- Exercise Details -->
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
        <!-- Posture Image -->
        <div class="image-preview">
          <img
            v-if="exercise.postureImg"
            :src="`http://localhost:8080${exercise.postureImg}`"
            alt="Posture"
            class="posture-img"
          />
          <p v-else>No Image</p>
        </div>
        <button @click="openModal(exercise.id)">Edit</button>
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

// 체크박스 상태 변경
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
