import axios from 'axios';

const api = axios.create({
  baseURL: 'http://localhost:8080/api',
});

export const getPatients = async () => {
  const response = await api.get('/patients');
  return response.data;
};
