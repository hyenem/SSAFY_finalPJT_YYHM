<template>
  <div>
    <div class="header">
      <h3>Meal</h3>
      <p class="helper-text">Please upload square image, size less than 100KB</p>
    </div>
    <div class="meal">
    <!-- Breakfast -->
    <div class="mealImg">
      <img v-if="meals.breakfastImg"
        :src="resolveImagePath(meals.breakfastImg)"
        alt="Breakfast Image"
        @click="openModal('breakfast')"
      />
      <img v-else 
        src="@/assets/default-image.png"
        alt="Breakfast Image"
        @click="openModal('breakfast')"
      />
      <h4>Breakfast</h4>
    </div>

    <!-- Lunch -->
    <div class="mealImg">
      <img v-if="meals.lunchImg"
        :src="resolveImagePath(meals.lunchImg)"
        alt="Lunch Image"
        @click="openModal('lunch')"
      />
      <img v-else 
        src="@/assets/default-image.png"
        alt="Lunch Image"
        @click="openModal('lunch')"
      />
      <h4>Lunch</h4>
    </div>

    <!-- Dinner -->
    <div class="mealImg">
      <img v-if="meals.dinnerImg"
        :src="resolveImagePath(meals.dinnerImg)"
        alt="Dinner Image"
        @click="openModal('dinner')"
      />
      <img v-else 
        src="@/assets/default-image.png"
        alt="Dinner Image"
        @click="openModal('dinner')"
      />
      <h4>Dinner</h4>
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
  justify-content: space-between;
}

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

.mealImg {
  display: flex;
  flex-direction: column;
  text-align: center;
}

img {
  width: 15rem;
  height: 15rem;
  object-fit: cover;
  border-radius: 8px;
  border: 1px solid #ddd;
  cursor: pointer;
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
