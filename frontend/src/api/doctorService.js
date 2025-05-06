import axios from 'axios';

const api = axios.create({
  baseURL: 'http://localhost:8080/api',
});

export const getDoctors = async () => {
  const response = await api.get('/doctors');
  return response.data;
};
