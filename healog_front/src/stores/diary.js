import { ref } from 'vue';
import { defineStore } from 'pinia';
import axios from 'axios';

const REST_API_URL = 'http://localhost:8080/';

export const useDiaryStore = defineStore('diary', () => {
  const diaryEntries = ref([]);
  const selectedDiary = ref(null);

  const fetchDiaryByDate = async (date) => {
    try {
      const userId = '1';
      const response = await axios.get(`${REST_API_URL}user/diary/condition`, {
        params: {
          userId,
          year: date.year,
          month: date.month,
          day: date.day,
        },
      });
      selectedDiary.value = response.data;
    } catch (error) {
      console.error('Failed to fetch diary:', error);
      selectedDiary.value = null;
    }
  };

  const updateDiaryCondition = async (diaryDto) => {
    try {
      await axios.put(`${REST_API_URL}user/diary/condition`, diaryDto);
      // Update local state after success
      if (selectedDiary.value && selectedDiary.value.id === diaryDto.id) {
        selectedDiary.value.condition = diaryDto.condition;
      }
    } catch (error) {
      console.error('Failed to update diary condition:', error);
    }
  };
  

  // 유저의 다이어리 목록 가져오기
  const fetchDiaryList = async (userId) => {
    try {
      const response = await axios.get(`${REST_API_URL}diary/list`, {
        params: { userId },
      });
      diaryEntries.value = response.data;
    } catch (error) {
      console.error('Failed to fetch diary list:', error);
    }
  };

  return {
    diaryEntries,
    selectedDiary,
    fetchDiaryByDate,
    fetchDiaryList,
    updateDiaryCondition,
  };
});
