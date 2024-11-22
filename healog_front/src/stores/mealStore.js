import { defineStore } from 'pinia';
import { getMeals, saveMeal, deleteMeal } from '@/api/meal';

export const useMealStore = defineStore('meal', {
    state: () => ({
        meals: [], // 특정 다이어리의 식단 데이터
    }),
    actions: {
        // 특정 다이어리의 식단 조회
        async fetchMeals(diaryId) {
            try {
                const data = await getMeals(diaryId);
                console.log('Fetched Meals:', data); // 서버에서 반환된 데이터를 확인
                this.meals = data.map(meal => ({
                    ...meal,
                    breakfastImg: meal.breakfastImg ? meal.breakfastImg : null,
                    lunchImg: meal.lunchImg ? meal.lunchImg : null,
                    dinnerImg: meal.dinnerImg ? meal.dinnerImg : null,
                }));
            } catch (error) {
                console.error('Failed to fetch meals:', error);
            }
        },
        
        // 식단 저장
        async saveMeal(diaryId, formData) {
            try {
                formData.append('diaryId', diaryId); // diaryId를 FormData에 추가
                await saveMeal(formData); // saveMeal 호출
                await this.fetchMeals(diaryId); // 저장 후 다시 조회
            } catch (error) {
                console.error('Failed to save meal:', error);
            }
        },
        // 식단 삭제
        async deleteMeal(mealId, diaryId) {
            try {
                await deleteMeal(mealId);
                await this.fetchMeals(diaryId); // 삭제 후 다시 조회
            } catch (error) {
                console.error('Failed to delete meal:', error);
            }
        },
    },
});
