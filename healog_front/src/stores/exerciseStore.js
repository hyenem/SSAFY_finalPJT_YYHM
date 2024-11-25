import { defineStore } from 'pinia';
import {
  fetchExercisesByDiaryId,
  getExerciseById,
  addExercise,
  updateExercise,
  markExerciseAsDone,
  deleteExercise,
} from '@/api/exercise';
import { getExerciseAreas, getExercisesByArea } from '@/api/category';

export const useExerciseStore = defineStore('exercise', {
  state: () => ({
    exercises: [],
    selectedExercise: null,
    exerciseAreas: [],
    exercisesByArea: [],
  }),
  actions: {
    async loadExerciseAreas() {
      try {
        this.exerciseAreas = await getExerciseAreas();
      } catch (error) {
        console.error('Error loading exercise areas:', error);
        this.exerciseAreas = [];
      }
    },
    async loadExercisesByArea(area) {
      try {
        this.exercisesByArea = await getExercisesByArea(area);
      } catch (error) {
        console.error('Error loading exercises by area:', error);
        this.exercisesByArea = [];
      }
    },
    async loadExercises(diaryId) {
      try {
        this.exercises = await fetchExercisesByDiaryId(diaryId);
      } catch (error) {
        console.error('Error loading exercises:', error);
        this.exercises = [];
      }
    },
    async addExercise(exerciseDto) {
      try {
        await addExercise(exerciseDto);
        await this.loadExercises(exerciseDto.diaryId);
      } catch (error) {
        console.error('Error adding exercise:', error);
      }
    },
    async deleteExercise(exerciseId) {
      try {
        await deleteExercise(exerciseId); // 올바른 메서드 호출
        this.exercises = this.exercises.filter(
          (exercise) => exercise.id !== exerciseId
        ); // 상태에서 삭제
      } catch (error) {
        console.error('Error deleting exercise:', error);
        throw error;
      }
    },    
    async fetchExerciseById(exerciseId) {
      try {
        this.selectedExercise = await getExerciseById(exerciseId);
      } catch (error) {
        console.error('Error fetching exercise by ID:', error);
        this.selectedExercise = null;
      }
    },
    async updateExercise(exerciseDto, postureImgFile) {
      try {
          await updateExercise(exerciseDto, postureImgFile);
          await this.loadExercises(exerciseDto.diaryId);
      } catch (error) {
          console.error('Error updating exercise:', error);
      }
  },  
    async markAsDone(id, postureImgFile, done = 1) {
      try {
        await markExerciseAsDone(id, postureImgFile, done);
        const exercise = this.exercises.find((e) => e.id === id);
        if (exercise) {
          exercise.done = done;
          if (postureImgFile) {
            exercise.postureImg = `/static/img/${postureImgFile.name}`;
          }
        }
      } catch (error) {
        console.error('Error updating exercise status:', error);
      }
    },  
  },
});
