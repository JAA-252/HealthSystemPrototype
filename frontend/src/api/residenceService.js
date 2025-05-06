import axios from 'axios';

const api = axios.create({
  baseURL: 'http://localhost:8080',
});

export const getResidences = async () => {
  const response = await api.get('/residences/all');
  return response.data;
};
