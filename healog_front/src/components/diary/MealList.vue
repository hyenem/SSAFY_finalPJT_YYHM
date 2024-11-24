<template>
  <div>
    <h3>Meal</h3>
    <div class="meal">
    <!-- Breakfast -->
    <div>
      <h4>Breakfast</h4>
        <img
          :src="resolveImagePath(meals.breakfastImg)"
          alt="Breakfast Image"
          @click="openModal('breakfast')"
        />
        <button @click="deleteImage('breakfast')">Delete Breakfast</button>
    </div>

    <!-- Lunch -->
    <div>
      <h4>Lunch</h4>
        <img
          :src="resolveImagePath(meals.lunchImg)"
          alt="Lunch Image"
          @click="openModal('lunch')"
        />
        <button @click="deleteImage('lunch')">Delete Lunch</button>
    </div>

    <!-- Dinner -->
    <div>
      <h4>Dinner</h4>
        <img
          :src="resolveImagePath(meals.dinnerImg)"
          alt="Dinner Image"
          @click="openModal('dinner')"
        />
        <button @click="deleteImage('dinner')">Delete Dinner</button>
    </div>
    </div>

    <!-- Modal -->
    <MealListModal
      v-if="isModalOpen"
      :mealType="selectedMealType"
      :diaryId="props.diaryId"
      @close="closeModal"
    />
  </div>
</template>

<script setup>
import { useMealStore } from '@/stores/mealStore';
import { ref, computed, watch } from 'vue';
import MealListModal from './MealListModal.vue';

const props = defineProps({
  diaryId: {
    type: Number,
    required: true,
  },
});

const mealStore = useMealStore();
const meals = computed(() => mealStore.meals);
const isModalOpen = ref(false);
const selectedMealType = ref(null);

const resolveImagePath = (path) => {
  if (!path || path.trim() === '') return '';
  if (path.startsWith('/img/')) {
    return `http://localhost:8080${path}`;
  }
  return path;
};


// 이미지 삭제
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

// 모달 열기
const openModal = (mealType) => {
  selectedMealType.value = mealType;
  isModalOpen.value = true;
};

// 모달 닫기
const closeModal = () => {
  isModalOpen.value = false;
  selectedMealType.value = null;
};

// 다이어리 ID 변경 시 식단 데이터 가져오기
watch(
  () => props.diaryId,
  async (newDiaryId) => {
    if (newDiaryId) {
      console.log('Fetching meals for diaryId:', newDiaryId); // diaryId 확인
      await mealStore.fetchMeals(newDiaryId);
      console.log('Meal data after fetch:', meals.value); // meals 데이터 확인
    }
  },
  { immediate: true }
);


</script>

<style scoped>
.meal {
  display: flex;
}

h3 {
  font-size: 1.2rem;
  margin-bottom: 1rem;
  color: #333;
}

img {
  width: 100px;
  height: 100px;
  object-fit: cover;
  border-radius: 8px;
  border: 1px solid #ddd;
  cursor: pointer;
  margin-bottom: 0.5rem;
}

button {
  padding: 0.5rem 1rem;
  background-color: #7FC678;
  color: white;
  border: none;
  border-radius: 4px;
  cursor: pointer;
  transition: background-color 0.3s;
}

button:hover {
  background-color: #65A45B;
}

.no-image-placeholder {
  color: #aaa;
  font-size: 0.9rem;
  text-align: center;
}
</style>

