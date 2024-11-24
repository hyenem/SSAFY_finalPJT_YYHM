import { defineStore } from 'pinia';
import { useUserStore } from './userStore'; // userStore 가져오기
import { getDiariesByUserId, getDiaryByDate, saveDiary } from '@/api/diary';

export const useDiaryStore = defineStore('diary', {
  state: () => ({
    diaryEntries: [],
    selectedDiary: null,
  }),
  actions: {
    async fetchDiaryList() {
      try {
        const userStore = useUserStore(); // userStore 인스턴스 가져오기
        if (!userStore.loginUser.id) {
          console.error('User ID is not available');
          alert('로그인 상태를 확인해주세요.');
          return;
        }
        if(userStore.loginUser.type==='user'){
          this.diaryEntries = await getDiariesByUserId(userStore.loginUser.id)
        } else {
          console.log('trainer')
          this.diaryEntries = await getDiariesByUserId(userStore.follower)
        }
      } catch (error) {
        console.error('Failed to fetch diary list:', error);
        alert('다이어리 목록을 가져오는 중 문제가 발생했습니다.');
      }
    },
    async fetchDiaryByDate(date) {
      try {
        const userStore = useUserStore(); // userStore 인스턴스 가져오기
        if (!userStore.loginUser.id) {
          console.error('User ID is not available');
          alert('로그인 상태를 확인해주세요.');
          return;
        }
        if(userStore.loginUser.type==='user'){
          this.selectedDiary = await getDiaryByDate(
            userStore.loginUser.id,
            date.year,
            date.month,
            date.day
          );
        } else {
          this.selectedDiary = await getDiaryByDate(
            userStore.follower,
            date.year,
            date.month,
            date.day
          );
        }
      } catch (error) {
        console.error('Failed to fetch diary:', error);
        this.selectedDiary = null;
      }
    },
    async updateDiaryCondition(diaryDto) {
      try {
        await saveDiary(diaryDto);
        if (this.selectedDiary?.id === diaryDto.id) {
          this.selectedDiary.condition = diaryDto.condition;
        }
      } catch (error) {
        console.error('Failed to update diary condition:', error);
        alert('다이어리 저장 중 문제가 발생했습니다.');
      }
    },
  },
});
