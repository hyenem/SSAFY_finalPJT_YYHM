<template>
  <div class="condition">
    <h3>Plan</h3>
      <p v-if="!diary">Loading diary data...</p>
      <div v-else>
        <input v-model="condition" placeholder="Enter condition here" />
        <button @click="saveCondition">등록</button>

        <!-- Exercise List -->
        <ExerciseList v-if="diary.id" :diaryId="diary.id" />
      </div>
  </div>
</template>

<script setup>
import { ref, computed, watch } from 'vue';
import { useDiaryStore } from '@/stores/diaryStore';
import ExerciseList from '@/components/diary/ExerciseList.vue';
import { useUserStore } from '@/stores/userStore';

localStorage.setItem('lastVisitedUrl', location.href)


const props = defineProps({
  date: {
    type: Object,
    required: true,
  },
});

const userStore = useUserStore()
const diaryStore = useDiaryStore();
const diary = ref(null);
const condition = ref('');

const formattedDate = computed(() => {
  if (props.date) {
    return `${props.date.year}-${String(props.date.month).padStart(2, '0')}-${String(props.date.day).padStart(2, '0')}`;
  }
  return 'Invalid date';
});

const fetchDiary = async () => {
  if (props.date) {
    await diaryStore.fetchDiaryByDate(props.date);
    diary.value = diaryStore.selectedDiary;
    condition.value = diary?.value?.condition || '';
  }
}


const saveCondition = async () => {
  if (diary.value) {
    const diaryDto = {
      id: diary.value.id,
      condition: condition.value,
      userId: diary.value.userId,
      year: diary.value.year,
      month: diary.value.month,
      day: diary.value.day,
    };
    await diaryStore.updateDiaryCondition(diaryDto);
    alert('Condition updated successfully!');
  }
};

watch(() => props.date, fetchDiary, { immediate: true });
</script>

<style scoped>
.condition {
  display: flex;
  flex-direction: column;
  padding: 1.5rem;
}

.condition input {
  padding: 0.5rem;
  border: 1px solid #ccc;
  border-radius: 3px;
}

.condition button {
  padding: 0.5rem 1rem;
  background-color: #7FC678;
  color: white;
  border: none;
  border-radius: 3px;
  cursor: pointer;
  transition: background-color 0.3s;
}

.condition button:hover {
  background-color: #65A45B;
}
</style>

