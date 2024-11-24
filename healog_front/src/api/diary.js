import api from './index';

// 특정 유저의 다이어리 리스트 가져오기
export const getDiariesByUserId = async (userId) => {
  try {
    const response = await api.get('/user/diary', { params: { userId } });
    return response.data;
  } catch (error) {
    console.error('Error fetching diaries:', error);
    throw error;
  }
};

// 특정 날짜의 다이어리 가져오기
export const getDiaryByDate = async (userId, year, month, day) => {
  try {
    const response = await api.get('/user/diary/condition', {
      params: { userId, year, month, day },
    });
    return response.data;
  } catch (error) {
    console.error('Error fetching diary by date:', error);
    throw error;
  }
};

// 다이어리 저장 (추가/수정)
export const saveDiary = async (diaryData) => {
  try {
    const response = await api.put('/user/diary/condition', diaryData);
    console.log('Diary saved:', response.data); // 명확한 로그 추가
    return response.data;
  } catch (error) {
    console.error('Error saving diary:', error);
    throw error;
  }
};
