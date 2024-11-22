<template>
    <div>
        <h3>Meal</h3>
        <div>
            <div>
                <label>Breakfast</label>
                <input type="file" @change="handleFileUpload('breakfastImg', $event)">
            </div>
            <div>
                <label>Lunch</label>
                <input type="file" @change="handleFileUpload('lunchImg', $event)">
            </div>
            <div>
                <label>Dinner</label>
                <input type="file" @change="handleFileUpload('dinnerImg', $event)">
            </div>
        </div>

        <button @click="saveMeal">Save Meal</button>

        <div v-if="meals.length">
            <div v-for="meal in meals" :key="meal.id">
                <div>
                    <span>Breakfast: <img v-if="meal.breakfastImg" :src="resolveImagePath(meal.breakfastImg)" alt="breakfast" /></span>
                    <span>Lunch: <img v-if="meal.lunchImg" :src="resolveImagePath(meal.lunchImg)" alt="lunch" /></span>
                    <span>Dinner: <img v-if="meal.dinnerImg" :src="resolveImagePath(meal.dinnerImg)" alt="dinner" /></span>
                    <button @click="deleteMeal(meal.id)">Delete</button>
                </div>
            </div>
        </div>
        <p v-else>No meals found for this diary.</p>
    </div>
</template>

<script setup>
import { ref, computed, onMounted, watch } from 'vue';
import { useMealStore } from '@/stores/mealStore';
import { useDiaryStore } from '@/stores/diaryStore';

const mealStore = useMealStore();
const diaryStore = useDiaryStore();
const formData = ref(new FormData());

// 현재 다이어리 정보 가져오기
const selectedDiary = computed(() => diaryStore.selectedDiary);
const diaryId = computed(() => selectedDiary.value?.id || null);
const meals = computed(() => mealStore.meals);

// 파일 업로드 핸들러
const handleFileUpload = (mealType, event) => {
    const file = event.target.files[0];
    if (file) {
        formData.value.append(mealType, file);
    }
};

const resolveImagePath = (path) => {
    if (path.startsWith('/img/')) {
        return `http://localhost:8080${path}`;
    }
    return path;
};


// 식단 저장
const saveMeal = async () => {
    if (!diaryId.value) {
        alert('Diary ID is not available. Please select a diary first.');
        return;
    }

    // FormData 확인
    for (let [key, value] of formData.value.entries()) {
        console.log(key, value);
    }

    try {
        await mealStore.saveMeal(diaryId.value, formData.value);
        formData.value = new FormData(); // 초기화
    } catch (error) {
        console.error('Failed to save meal:', error);
    }
};

// 식단 삭제
const deleteMeal = async (mealId) => {
    if (!diaryId.value) {
        alert('Diary ID is not available. Please select a diary first.');
        return;
    }
    await mealStore.deleteMeal(mealId, diaryId.value);
};

// 다이어리 선택 변경 시 데이터 로드
watch(diaryId, async (newDiaryId) => {
    if (newDiaryId) {
        await mealStore.fetchMeals(newDiaryId);
    }
});

// 초기 데이터 로드
onMounted(() => {
    if (diaryId.value) {
        mealStore.fetchMeals(diaryId.value);
    } else {
        console.warn('No diary selected.');
    }
});
</script>

<style scoped>
img {
    width: 3rem;
}
</style>
