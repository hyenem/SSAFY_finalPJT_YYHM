import api from './index';

// 특정 다이어리의 식단 조회
export const getMealsByDiaryId = async (diaryId) => {
  try {
    const response = await api.get(`/user/meal?diaryId=${diaryId}`);
    return response.data;
  } catch (error) {
    throw error;
  }
}

// 식단 이미지 업로드
export const uploadMealImage = async (diaryId, mealType, file) => {
  try {
    const formData = new FormData();
    formData.append('diaryId', diaryId);
    formData.append('mealType', mealType);
    formData.append('file', file);

    const response = await api.post('/user/meal', formData, {
      headers: { 'Content-Type': 'multipart/form-data' },
    });
    return response.data;
  } catch (error) {
    throw error;
  }
}

// 식단 이미지 삭제
export const deleteMealImage = async (mealId, mealType) => {
  try {
    await api.delete(`/user/meal/${mealId}`, {
      params: {
        mealType: mealType,
      },
    });
  } catch (error) {
    throw error;
  }
}
