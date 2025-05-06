import React, { useState } from 'react';
import axios from 'axios';

function PatientForm() {
  const [form, setForm] = useState({
    firstName: '',
    lastName: '',
    birthDate: '',
    residenceId: '',
  });

  const handleChange = (e) => {
    setForm({ ...form, [e.target.name]: e.target.value });
  };

  const handleSubmit = async (e) => {
    e.preventDefault();

    const payload = {
      patientId: null, // Auto-generated in backend
      residenceId: parseInt(form.residenceId),
      firstName: form.firstName,
      lastName: form.lastName,
      birthDate: form.birthDate,
      primaryDoctorId: null,
    };

    try {
      await axios.post('http://localhost:8080/api/patients', payload);
      alert('Patient created!');
    } catch (error) {
      console.error(error);
      alert('Error creating patient');
    }
  };

  return (
    <form onSubmit={handleSubmit}>
      <h2>Register Patient</h2>
      <input name="firstName" placeholder="First Name" onChange={handleChange} required />
      <input name="lastName" placeholder="Last Name" onChange={handleChange} required />
      <input name="birthDate" type="date" onChange={handleChange} required />
      <input name="residenceId" placeholder="Residence ID" onChange={handleChange} required />
      <button type="submit">Create</button>
    </form>
  );
}

export default PatientForm;
