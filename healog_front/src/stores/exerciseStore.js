import { ref } from 'vue';
import { defineStore } from 'pinia';
import axios from 'axios';
import { fetchExercisesByDiaryId } from '@/api/exercise';
import { markExerciseAsDone } from '@/api/exercise';

const REST_API_URL = 'http://localhost:8080/';

export const useExerciseStore = defineStore('exercise', () => {
  const exercises = ref([]);
  const selectedExercise = ref(null);

  // 다이어리 ID로 운동 목록 가져오기
  const loadExercises = async (diaryId) => {
    try {
      exercises.value = await fetchExercisesByDiaryId(diaryId);
    } catch (error) {
      exercises.value = [];
      console.error('Error loading exercises:', error);
    }
  };

  const markAsDone = async (id, postureImgFile) => {
    try {
      await markExerciseAsDone(id, postureImgFile);
      const exercise = exercises.value.find((e) => e.id === id);
      if (exercise) {
        exercise.done = 1;
        exercise.postureImg = postureImgFile ? `/static/img/${postureImgFile.name}` : null;
      }
    } catch (error) {
      console.error('Error updating exercise status:', error);
    }
  };

  const fetchExerciseById = async (exerciseId) => {
    try {
      const response = await axios.get(`${REST_API_URL}user/exercise/${exerciseId}`);
      selectedExercise.value = response.data;
    } catch (error) {
      console.error('Error fetching exercise:', error);
      selectedExercise.value = null;
    }
  };
  

  // 운동 수정
  const updateExercise = async (exerciseDto) => {
    try {
      await axios.put(`${REST_API_URL}user/exercise`, exerciseDto);
      const index = exercises.value.findIndex((e) => e.id === exerciseDto.id);
      if (index !== -1) {
        exercises.value[index] = exerciseDto;
      }
    } catch (error) {
      console.error('Error updating exercise:', error);
    }
  };

  return {
    exercises,
    selectedExercise,
    loadExercises,
    fetchExerciseById,
    updateExercise,
    exercises,
    markAsDone,
  };
});
