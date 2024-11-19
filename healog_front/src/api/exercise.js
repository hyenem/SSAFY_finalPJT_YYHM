import axios from 'axios';

const REST_API_URL = 'http://localhost:8080/';

export const getExerciseById = async (id) => {
  try {
    const response = await axios.get(`${REST_API_URL}user/exercise/${id}`);
    return response.data;
  } catch (error) {
    console.error('Failed to fetch exercise:', error);
    throw error;
  }
};

export const fetchExercisesByDiaryId = async (diaryId) => {
  try {
    const response = await axios.get(`${REST_API_URL}user/exercise`, {
      params: { diaryId },
    });
    return response.data;
  } catch (error) {
    console.error('Failed to fetch exercises:', error);
    throw error;
  }
};

export const updateExercise = async (exercise) => {
  try {
    await axios.put(`${REST_API_URL}user/exercise`, exercise);
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
    const response = await axios.post(`${REST_API_URL}user/exercise/done`, formData, {
      headers: {
        'Content-Type': 'multipart/form-data',
      },
    });
    return response.data;
  } catch (error) {
    console.error('Error marking exercise as done:', error);
    throw error;
  }
};
