import axios from 'axios';

const REST_API_URL = 'http://localhost:8080/';

// 특정 유저의 다이어리 리스트 가져오기
export const getDiariesByUserId = async (userId) => {
  try {
    const response = await axios.get(`${REST_API_URL}diary`, {
      params: { userId },
    });
    return response.data;
  } catch (error) {
    console.error('Error fetching diaries:', error);
    throw error;
  }
};

// 특정 날짜의 다이어리 가져오기
export const getDiaryByDate = async (userId, year, month, day) => {
  try {
    const response = await axios.get(`${REST_API_URL}diary/date`, {
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
    const response = await axios.post(`${REST_API_URL}diary`, diaryData);
    return response.data;
  } catch (error) {
    console.error('Error saving diary:', error);
    throw error;
  }
};
