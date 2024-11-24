<template>
  <div class="modal">
    <h3>{{ isEditing ? "Edit" : "Add" }} Exercise Plan</h3>
    <form @submit.prevent="saveChanges">
      <!-- 운동 부위 선택 -->
      <div>
        <label for="exerciseArea">Exercise Area:</label>
        <select id="exerciseArea" v-model="selectedArea" @change="fetchExercisesByArea">
          <option v-for="area in exerciseStore.exerciseAreas" :key="area" :value="area">
            {{ area }}
          </option>
        </select>
      </div>

      <!-- 운동 선택 -->
      <div>
        <label for="exercise">Exercise:</label>
        <select id="exercise" v-model="exercise.exercise">
          <option v-for="ex in exerciseStore.exercisesByArea" :key="ex.id" :value="ex.exercise">
            {{ ex.exercise }}
          </option>
        </select>
      </div>

      <!-- 나머지 수정 필드 -->
      <label>Weight:</label>
      <input type="number" v-model="exercise.weight" />

      <label>Count:</label>
      <input type="number" v-model="exercise.count" />

      <label>Set:</label>
      <input type="number" v-model="exercise.set" />

      <div>
        <label for="postureImg">Posture Image:</label>
        <input type="file" id="postureImg" @change="handleFileChange" />
        <img v-if="postureImgPreview" :src="postureImgPreview" alt="Preview" />
      </div>

      <button type="submit">{{ isEditing ? "Save" : "Add" }}</button>
      <button type="button" @click="closeModal">Cancel</button>
    </form>
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
.modal {
  border: 1px solid;
  padding: 1rem;
}

form {
  display: flex;
  flex-direction: column;
}

img {
  width: 3rem;
}
</style>
