import React, { useState, useEffect } from 'react';
import axios from 'axios';

function AssignPatient() {
  const [doctors, setDoctors] = useState([]);
  const [patients, setPatients] = useState([]);
  const [form, setForm] = useState({
    patientId: '',
    doctorId: '',
  });

  useEffect(() => {
    axios.get('http://localhost:8080/api/doctors').then(res => setDoctors(res.data));
    axios.get('http://localhost:8080/api/patients').then(res => {
      // Only show unassigned patients
      const unassigned = res.data.filter(p => !p.doctorId);
      setPatients(unassigned);
    });
  }, []);

  const handleChange = (e) => {
    setForm({ ...form, [e.target.name]: e.target.value });
  };

  const handleAssign = async (e) => {
    e.preventDefault();
    try {
      await axios.put(`http://localhost:8080/api/doctors/${form.doctorId}/assign/${form.patientId}`);
      alert('Patient assigned!');
    } catch (err) {
      alert('Assignment failed: ' + err.response.data.message);
    }
  };

  return (
    <form onSubmit={handleAssign}>
      <h2>Assign Patient to Doctor</h2>
      <select name="patientId" onChange={handleChange} required>
        <option value="">Select Patient</option>
        {patients.map(p => (
          <option key={p.patientId} value={p.patientId}>
            {p.firstName} {p.lastName}
          </option>
        ))}
      </select>
      <select name="doctorId" onChange={handleChange} required>
        <option value="">Select Doctor</option>
        {doctors.map(d => (
          <option key={d.doctorId} value={d.doctorId}>
            Dr. {d.firstName} {d.lastName}
          </option>
        ))}
      </select>
      <button type="submit">Assign</button>
    </form>
  );
}

export default AssignPatient;
