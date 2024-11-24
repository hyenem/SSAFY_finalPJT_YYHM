<template>
  <div class="overlay" @click="closeModal"></div>
  <div class="modal">
    <div class="modal-header">
      <h3>Meal</h3>
      <button class="close-btn" @click="closeModal">×</button>
    </div>
    <h3>{{ mealType }}</h3>
    <input type="file" @change="uploadImage" />
    <button @click="closeModal">Cancel</button>
    <button @click="deleteImage(mealType)">Delete {{ mealType }}</button>
  </div>
</template>

<script setup>
import { ref, computed } from 'vue';
import { useMealStore } from '@/stores/mealStore';

const props = defineProps({
  mealType: {
    type: String,
    required: true,
  },
  diaryId: {
    type: Number,
    required: true,
  },
});

const emit = defineEmits(['close']);
const mealStore = useMealStore();
const meal = ref({});
const meals = computed(() => mealStore.meals);
const isModalOpen = ref(false);
const selectedMealType = ref(null);

// 이미지 업로드
const uploadImage = async (event) => {
  const file = event.target.files[0];
  if (!file) return;

  try {
    await mealStore.uploadMealImage(props.diaryId, props.mealType, file);
    alert(`${props.mealType} image uploaded successfully.`);
    closeModal();
  } catch (error) {
    console.error('Error uploading image:', error);
    alert(`Failed to upload ${props.mealType} image.`);
  }
};

const deleteImage = async (mealType) => {
  const mealId = meals.value.id; // 현재 다이어리의 mealId
  try {
    const confirmed = confirm(`Are you sure you want to delete ${mealType}?`);
    if (!confirmed) return;

    await mealStore.deleteMealImage(mealId, mealType);
    alert(`${mealType} image deleted successfully.`);
  } catch (error) {
    console.error('Error deleting image:', error);
    alert(`Failed to delete ${mealType} image.`);
  }
};

// 모달 닫기
const closeModal = () => {
  emit('close');
};
</script>

<style scoped>
.overlay {
  position: fixed;
  top: 0;
  left: 0;
  width: 100%;
  height: 100%;
  background-color: rgba(0, 0, 0, 0.5);
  z-index: 999;
}

.modal {
  position: fixed;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  background: white;
  border-radius: 8px;
  box-shadow: 0 4px 8px rgba(0, 0, 0, 0.2);
  padding: 1.5rem;
  width: 420px;
  z-index: 1000;
}

.modal-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  border-bottom: 1px solid #ddd;
  padding-bottom: 0.5rem;
  margin-bottom: 1rem;
}

.close-btn {
  background: transparent;
  border: none;
  font-size: 1.5rem;
  cursor: pointer;
  color: #555;
}

</style>
