<template>
  <div class="modal">
    <h3>{{ mealType }}</h3>
    <input type="file" @change="uploadImage" />
    <button @click="closeModal">Cancel</button>
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

// 모달 닫기
const closeModal = () => {
  emit('close');
};
</script>

<style scoped>
.modal {
  position: fixed;
  top: 50%;
  left: 50%;
  transform: translate(-50%, -50%);
  background: white;
  padding: 20px;
  border: 1px solid #ccc;
  border-radius: 8px;
  z-index: 1000;
}

button {
  margin-top: 10px;
}
</style>
