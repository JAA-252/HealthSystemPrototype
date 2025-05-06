import React, { useState, useEffect } from 'react';
import axios from 'axios';

function UnassignPatient() {
  const [doctors, setDoctors] = useState([]);
  const [selectedDoctorId, setSelectedDoctorId] = useState('');
  const [patients, setPatients] = useState([]);
  const [selectedPatientId, setSelectedPatientId] = useState('');

  useEffect(() => {
    axios.get('http://localhost:8080/api/doctors').then(res => setDoctors(res.data));
  }, []);

  useEffect(() => {
    if (selectedDoctorId) {
      axios.get(`http://localhost:8080/api/doctors/${selectedDoctorId}/patients`)
        .then(res => setPatients(res.data));
    } else {
      setPatients([]);
    }
  }, [selectedDoctorId]);

  const handleUnassign = async (e) => {
    e.preventDefault();
    try {
      await axios.put(`http://localhost:8080/api/doctors/${selectedDoctorId}/unassign/${selectedPatientId}`);
      alert('Patient unassigned!');
      setSelectedPatientId('');
      // Refresh list
      const res = await axios.get(`http://localhost:8080/api/doctors/${selectedDoctorId}/patients`);
      setPatients(res.data);
    } catch (err) {
      alert('Unassignment failed: ' + err.response?.data?.message);
    }
  };

  return (
    <form onSubmit={handleUnassign}>
      <h2>Unassign Patient from Doctor</h2>
      <select onChange={(e) => setSelectedDoctorId(e.target.value)} required>
        <option value="">Select Doctor</option>
        {doctors.map(d => (
          <option key={d.doctorId} value={d.doctorId}>
            Dr. {d.firstName} {d.lastName}
          </option>
        ))}
      </select>

      {patients.length > 0 && (
        <select onChange={(e) => setSelectedPatientId(e.target.value)} required>
          <option value="">Select Patient</option>
          {patients.map(p => (
            <option key={p.patientId} value={p.patientId}>
              {p.firstName} {p.lastName}
            </option>
          ))}
        </select>
      )}

      <button type="submit" disabled={!selectedPatientId}>Unassign</button>
    </form>
  );
}

export default UnassignPatient;
