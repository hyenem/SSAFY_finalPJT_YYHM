<template>
  <div class="overlay" @click="closeModal"></div>
  <div class="modal">
    <div class="modal-header">
      <h3>{{ mealType }}</h3>
      <button class="close-btn" @click="closeModal">×</button>
    </div>
    <div class="modal-body">
      <p class="helper-text">Please upload square image, size less than 100KB</p>
      <img src="@/assets/default-placeholder.png" />
      <div class="file-upload">
        <input type="file" @change="uploadImage" />
      </div>
    </div>
    <div class="modal-footer">
      <button class="delete-btn" @click="deleteImage(mealType)">Delete</button>
      <button class="create-btn" @click="uploadImage">Create</button>
    </div>
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

// 이미지 삭제
const deleteImage = async (mealType) => {
  const mealId = meals.value.id; // 현재 다이어리의 mealId
  try {
    const confirmed = confirm(`Are you sure you want to delete ${mealType}?`);
    if (!confirmed) return;

    await mealStore.deleteMealImage(mealId, mealType);
    alert(`${mealType} image deleted successfully.`);
    closeModal();
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

.modal {
  position: fixed;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  background: white;
  padding: 20px;
  border: 1px solid #ccc;
  border-radius: 3px;
  z-index: 1000;
}

.modal-header {
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 16px;
}

.helper-text {
  font-size: 0.8rem;
  color: #888;
}

.file-upload {
  display: flex;
  align-items: center;
  gap: 8px;
  margin-bottom: 16px;
}

.file-label {
  background: #eaf4ff;
  color: #007bff;
  padding: 8px 16px;
  border-radius: 3px;
  cursor: pointer;
  font-size: 14px;
  font-weight: 500;
  text-align: center;
}

.file-label:hover {
  background: #d4ebff;
}

.file-name {
  font-size: 14px;
  color: #666666;
}


.modal-footer {
  display: flex;
  justify-content: flex-end;
  gap: 1rem;
}

.close-btn {
  background-color: transparent;
  border: none;
  font-size: 1.5rem;
  cursor: pointer;
  color: #555;
}

.close-btn:hover {
  color: #333333;
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

.create-btn {
  padding: 0.5rem 1rem;
  background-color: #7fc678;
  border: none;
  color: white;
  border-radius: 3px;
  cursor: pointer;
  font-size: 0.9rem;
}

.create-btn:hover {
  background-color: #65a45b;
}
</style>
