import { defineStore } from 'pinia';
import { getMealsByDiaryId, uploadMealImage, deleteMealImage } from '@/api/meal';

export const useMealStore = defineStore('meal', {
  state: () => ({
    meals: {}, // 현재 다이어리의 식단 정보
    diaryId: null, // 현재 선택된 다이어리 ID
  }),

  actions: {
    // 특정 다이어리의 식단 조회
    async fetchMeals(diaryId) {
      try {
        const data = await getMealsByDiaryId(diaryId);
        console.log('Fetched Meals:', data[0]); // 첫 번째 데이터 확인
        this.meals = data[0] || {}; // 첫 번째 데이터만 저장, 없으면 빈 객체
        this.diaryId = diaryId;
      } catch (error) {
        console.error('Failed to fetch meals:', error);
        throw error;
      }
    },

    // 식단 이미지 업로드
    async uploadMealImage(diaryId, mealType, file) {
      try {
        const response = await uploadMealImage(diaryId, mealType, file);
        console.log('Meal image uploaded:', response);
        await this.fetchMeals(diaryId); // 업로드 후 최신 데이터 다시 로드
      } catch (error) {
        console.error('Failed to upload meal image:', error);
        throw error;
      }
    },

    // 식단 이미지 삭제
    async deleteMealImage(mealId, mealType) {
      try {
        await deleteMealImage(mealId, mealType);
        console.log(`Meal image of type "${mealType}" deleted.`);
        if (this.diaryId) {
          await this.fetchMeals(this.diaryId); // 삭제 후 최신 데이터 다시 로드
        }
      } catch (error) {
        console.error('Failed to delete meal image:', error);
        throw error;
      }
    },
  },
});
