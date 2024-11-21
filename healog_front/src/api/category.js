import api from './index';

export const getExerciseAreas = async () => {
  try {
    const response = await api.get('category/areas');
    return response.data;
  } catch (error) {
    console.error('Error fetching exercise areas:', error);
    throw error;
  }
};

export const getExercisesByArea = async (area) => {
  try {
    const response = await api.get('category/exercises', { params: { area } });
    return response.data;
  } catch (error) {
    console.error('Error fetching exercises by area:', error);
    throw error;
  }
};
