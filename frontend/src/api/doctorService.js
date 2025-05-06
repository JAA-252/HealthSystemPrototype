import axios from 'axios';

const api = axios.create({
  baseURL: 'http://localhost:8080',
});

export const getDoctors = async () => {
  const response = await api.get('/doctors/all');
  return response.data;
};
