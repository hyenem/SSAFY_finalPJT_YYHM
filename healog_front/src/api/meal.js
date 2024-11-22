import api from './index';

// 특정 다이어리의 식단 조회
export function getMeals(diaryId) {
    return api.get(`/user/meal?diaryId=${diaryId}`).then(res => res.data);
}

// 식단 저장
export function saveMeal(formData) {
    return api.post('/user/meal', formData, {
        headers: {
            'Content-Type': 'multipart/form-data',
        },
    });
}

// 식단 삭제
export function deleteMeal(mealId) {
    return api.delete(`/user/meal/${mealId}`);
}
