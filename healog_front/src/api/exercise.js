import api from './index';

// 운동 부위 목록 가져오기
export const getExerciseAreas = async () => {
  const response = await api.get('/category/areas');
  return response.data;
};

// 특정 운동 부위의 운동 목록 가져오기
export const getExercisesByArea = async (area) => {
  const response = await api.get('/category/exercises', { params: { area } });
  return response.data;
};

export const getExerciseById = async (id) => {
  try {
    const response = await api.get(`/user/exercise/${id}`);
    console.log('API Response for getExerciseById:', response.data); // 데이터 확인
    return response.data; // 데이터 반환
  } catch (error) {
    console.error('Error fetching exercise by ID:', error);
    throw error;
  }
};

export const addExercise = async (exercise) => {
  try {
    const response = await api.post('/user/exercise', exercise, {
      headers: { 'Content-Type': 'application/json' }, // JSON으로 전송
    });
    return response.data;
  } catch (error) {
    console.error('Failed to add exercise:', error);
    throw error;
  }
};

export const deleteExercise = async (id) => {
  try {
    await api.delete(`/user/exercise/${id}`);
  } catch (error) {
    console.error('Failed to delete exercise:', error);
    throw error;
  }
};

export const fetchExercisesByDiaryId = async (diaryId) => {
  try {
    const response = await api.get('/user/exercise', { params: { diaryId } });
    console.log('Fetched Exercises:', response.data); // 데이터 확인
    return response.data;
  } catch (error) {
    console.error('Failed to fetch exercises:', error);
    throw error;
  }
};

export const updateExercise = async (exerciseDto, postureImgFile) => {
  const formData = new FormData();
  formData.append(
      "exerciseDto",
      new Blob([JSON.stringify(exerciseDto)], { type: "application/json" })
  );

  if (postureImgFile) {
      formData.append("postureImg", postureImgFile);
  }

  try {
      const response = await api.put('/user/exercise', formData, {
          headers: { 'Content-Type': 'multipart/form-data' },
      });
      return response.data;
  } catch (error) {
      console.error('Failed to update exercise:', error);
      throw error;
  }
};


export const markExerciseAsDone = async (id, postureImgFile) => {
  const formData = new FormData();
  formData.append('id', id);
  if (postureImgFile) {
    formData.append('postureImg', postureImgFile);
  }

  try {
    const response = await api.post('/user/exercise/done', formData, {
      headers: { 'Content-Type': 'multipart/form-data' },
    });
    return response.data;
  } catch (error) {
    console.error('Error marking exercise as done:', error);
    throw error;
  }
};
